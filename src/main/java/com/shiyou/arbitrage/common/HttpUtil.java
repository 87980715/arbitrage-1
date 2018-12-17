package com.shiyou.arbitrage.common;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName: HttpClientUtil
 * Description:
 * Author: ZhangYX
 * Date:  2018/4/23
 */
public class HttpUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private static PoolingHttpClientConnectionManager connMgr;
    private static RequestConfig requestConfig;
    private static final int MAX_TIMEOUT = 5000;

    static {
        // 设置连接池
        connMgr = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connMgr.setMaxTotal(200);
        connMgr.setDefaultMaxPerRoute(100);

        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        // 在提交请求之前 测试连接是否可用
        //configBuilder.setStaleConnectionCheckEnabled(true);
        requestConfig = configBuilder.build();
    }

    /**
     * 发送 GET 请求（HTTP），不带输入数据
     *
     * @param url 接口地址
     * @return 接口返回内容
     */
    public static String doGet(String url) {
        return doGet(url, new HashMap<String, String>(), null, null, null);
    }

    /**
     * 发送 GET 请求（HTTP），K-V形式
     *
     * @param url    接口地址
     * @param params 参数
     * @return 接口返回内容
     */
    public static String doGet(String url, Map<String, String> params, String apiKey, String signature, String timeStamp) {
        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0) {
                param.append("?");
            } else {
                param.append("&");
            }
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl += param;
        String result = null;
        HttpClient httpclient = HttpClientBuilder.create().build();
        try {
            HttpGet httpPost = new HttpGet(apiUrl);
            httpPost.setHeader("FC-ACCESS-KEY", apiKey);
            httpPost.setHeader("FC-ACCESS-SIGNATURE", signature);
            httpPost.setHeader("FC-ACCESS-TIMESTAMP", timeStamp);
            httpPost.setHeader("content-type", "application/json;charset=UTF-8");
            HttpResponse response = httpclient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                logger.error("请求服务错误 ：" + response.toString());
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                result = IOUtils.toString(instream, "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String doGetSSL(String url) {
        String result = null;
        CloseableHttpClient httpClient = null;
        InputStream inputStream = null;
        try {
            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                inputStream = entity.getContent();
                result = IOUtils.toString(inputStream, "UTF-8");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return result;
    }

    public static String doGetSSL(String url, Map<String, Object> params) {
        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0) {
                param.append("?");
            } else {
                param.append("&");
            }
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl += param;
        String result = null;
        CloseableHttpClient httpClient = null;
        InputStream inputStream = null;
        try {
            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
            HttpGet httpGet = new HttpGet(apiUrl);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                inputStream = entity.getContent();
                result = IOUtils.toString(inputStream, "UTF-8");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return result;
    }

    /**
     * 发送 POST 请求（HTTP），不带输入数据
     *
     * @param apiUrl 接口地址
     * @return 接口返回内容
     */
    public static String doPost(String apiUrl) {
        return doPost(apiUrl, new HashMap<String, String>(),null, null, null);
    }

    /**
     * 发送 POST 请求（HTTP），K-V形式
     *
     * @param apiUrl API接口URL
     * @param params 参数map
     * @return 接口返回内容
     */
    public static String doPost(String apiUrl, Map<String, String> params,String apiKey, String signature, String timeStamp) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        httpPost.setHeader("FC-ACCESS-KEY", apiKey);
        httpPost.setHeader("FC-ACCESS-SIGNATURE", signature);
        httpPost.setHeader("FC-ACCESS-TIMESTAMP", timeStamp);
        httpPost.setHeader("content-type", "application/json;charset=UTF-8");
        CloseableHttpResponse response = null;

        try {
            httpPost.setConfig(requestConfig);
//            List<NameValuePair> pairList = new ArrayList<>(params.size());
//            for (Map.Entry<String, String> entry : params.entrySet()) {
//                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry
//                        .getValue().toString());
//                pairList.add(pair);
//            }
//            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
            String json = ObjectMapper.toJson(params);
            StringEntity se = new StringEntity(json);
            httpPost.setEntity(se);

            response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    /**
     * 发送 POST 请求（HTTP），JSON形式
     *
     * @param apiUrl 接口地址
     * @param json   json对象
     * @return 接口返回内容
     */
    public static String doPost(String apiUrl, String json) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;

        try {
            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(json, "UTF-8");//解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            System.out.println(response.getStatusLine().getStatusCode());
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    /**
     * 发送 SSL POST 请求（HTTPS），K-V形式
     *
     * @param apiUrl API接口URL
     * @param params 参数map
     * @return 接口返回内容
     */
    public static String doPostSSL(String apiUrl, Map<String, Object> params) {
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        String httpStr = null;

        try {
            httpPost.setConfig(requestConfig);
            List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry
                        .getValue().toString());
                pairList.add(pair);
            }
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("utf-8")));
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            httpStr = EntityUtils.toString(entity, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    /**
     * 发送 SSL POST 请求（HTTPS），JSON形式
     *
     * @param apiUrl API接口URL
     * @param json   JSON对象
     * @return 接口返回内容
     */
    public static String doPostSSL(String apiUrl, String json) {
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        String httpStr = null;

        try {
            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(json, "UTF-8");//解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/x-www-form-urlencoded");
            httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, "utf-8");
            /*if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            httpStr = EntityUtils.toString(entity, "utf-8");*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    /**
     * 创建SSL安全连接
     */
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslConnectionSocketFactory = null;
        try {
            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, (TrustStrategy) (chain, authType) -> true).build();
            sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return sslConnectionSocketFactory;
    }


}
