package com.shiyou.arbitrage.api;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class SignatureUtil {

    /**
     * 生成签名结果(新版本使用)
     *
     * @param params 要签名的数组
     * @return 签名结果字符串
     */
    public static String buildSignature(Map<String, String> params, String apiKey, String secretKey) {
        String mysign = "";
        try {
            if (params == null) {
                params = new HashMap<>();
            }
            params.put("apiKey", apiKey);
            String prestr = createLinkString(params); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
            mysign = SHA256HMAC.hash(prestr.getBytes(), secretKey.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mysign;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }




    private static String encode(byte[] bstr) {
        return new sun.misc.BASE64Encoder().encode(bstr);
    }


    public static String signatureFcoin(String method, String url, String timestamp, Map params, String accessKey) {
        StringBuilder sb = new StringBuilder(500);
        method = Objects.requireNonNull(method).toUpperCase();
        sb.append(method).append(url).append(timestamp).append(extractPostBody(method, params));
        String concat = sb.toString();
        //logger.info(concat);
        byte[] encodeData = Base64.getEncoder().encode(concat.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder()
                .encodeToString(hmacSha1(encodeData, accessKey.getBytes(StandardCharsets.UTF_8)));
    }

    public static String extractPostBody(String method, Map<String, String> params) {
        if (!"POST".equals(method)) {
            return "";
        }
        if (params.isEmpty()) {
            return "";
        }
        List paramKeys = new LinkedList();

        for(Map.Entry entry : params.entrySet()){
            if (entry.getValue() == null) {
                continue;
            }
            if ((entry.getValue() instanceof String) && ((String) entry.getValue()).isEmpty()) {
                continue;
            }
            paramKeys.add(entry.getKey());
        }
        Collections.sort(paramKeys);
        StringBuilder sb = new StringBuilder(100);
        for (Object paramKey : paramKeys) {
            sb.append(paramKey).append("=").append(params.get(paramKey)).append("&");
        }
        sb.deleteCharAt(sb.length() - 1);// 删除结尾多余的&
        return sb.toString();
    }


    public static byte[] hmacSha1(byte[] data, byte[] key) {
        try {
            String digestMethod = "HmacSHA1";
            SecretKeySpec signingKey = new SecretKeySpec(key, digestMethod);
            Mac mac = Mac.getInstance(digestMethod);
            mac.init(signingKey);
            return mac.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("不支持的加密方式", e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException("无效的私钥", e);
        }
    }


}
