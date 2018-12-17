package com.shiyou.arbitrage.api;

import com.google.gson.reflect.TypeToken;
import com.shiyou.arbitrage.cache.PlatformInfoCache;
import com.shiyou.arbitrage.common.HttpClientUtil;
import com.shiyou.arbitrage.common.ObjectMapper;
import com.shiyou.arbitrage.common.StringUtil;
import com.shiyou.arbitrage.data.model.ApiResult;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.shiyou.arbitrage.data.model.Contant.BLOEX;

/**
 * @Package: com.lanjiejia.demo.common.bloex
 * @Project: lanJieHouseKeeping
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/11/22 0022 15:04
 */
public class BloexAPI {

    private HttpClient client;
    private String urlPre = null;

    public String getData(String url, Map<String, String> params, String signature) {

        String returnString = null;
        try {
            client = new HttpClientUtil().httpClient();


            HttpPost post = new HttpPost(getUrlPre() + url);
            post.setHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            List<NameValuePair> nvps = new ArrayList<>();
            if (params != null && params.size() > 0) {
                nvps = getNameValuePair(params);
            }
            if (StringUtil.isNotEmpty(signature)) {
                nvps.add(new BasicNameValuePair("signature", signature));
            }
            if (nvps.size() > 0) {
                post.setEntity(new UrlEncodedFormEntity(nvps));
            }
            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            returnString = EntityUtils.toString(entity, "UTF-8");
            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnString;
    }

    public String getDataWithOutSignature(String urlStr, Map<String, String> params) {
        return getData(urlStr, params, null);
    }


    public String getServerTime() {
        String url = getUrlPre() + "/server/time";
        String serverTime = null;
        try {

            client = new HttpClientUtil().httpClient();
            HttpPost post = new HttpPost(url);
            post.setHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            HttpResponse httpResponse = client.execute(post);
            HttpEntity entity = httpResponse.getEntity();
            String returnString = EntityUtils.toString(entity, "UTF-8");
            EntityUtils.consume(entity);
            Type type = new TypeToken<ApiResult<String>>() {
            }.getType();
            ApiResult<String> apiResult = ObjectMapper.fromJson(returnString, type);
            serverTime = apiResult.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverTime;
    }


    public String getBinanceServerTime() {
        String url = " https://api.binance.com/api/v1/time";
        String serverTime = null;
        try {

            client = new HttpClientUtil().httpClient();
            HttpPost post = new HttpPost(url);
            post.setHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            HttpResponse httpResponse = client.execute(post);
            HttpEntity entity = httpResponse.getEntity();
            String returnString = EntityUtils.toString(entity, "UTF-8");
            EntityUtils.consume(entity);
            Type type = new TypeToken<ApiResult<String>>() {
            }.getType();
            ApiResult<String> apiResult = ObjectMapper.fromJson(returnString, type);
            serverTime = apiResult.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverTime;
    }

    private List<NameValuePair> getNameValuePair(Map<String, String> params) {
        List<NameValuePair> nvps = new ArrayList<>();
        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            String key = next.getKey();
            String value = next.getValue();
            nvps.add(new BasicNameValuePair(key, value));
        }
        return nvps;
    }

    private String getUrlPre() {
        if (urlPre == null) {
            urlPre = PlatformInfoCache.getInfo(BLOEX, "apiUrl");
        }
        return urlPre;
    }

}
