package com.shiyou.arbitrage.common;

import org.apache.http.client.HttpClient;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;

import java.util.concurrent.TimeUnit;

/**
 * @Package: com.lanjiejia.demo.common.bloex
 * @Project: lanJieHouseKeeping
 * @Description:
 * @Auther: Aihua
 * @Date: 2018/11/22 0022 14:55
 */
public class HttpClientUtil {


    public HttpClient httpClient() {
        BasicHttpClientConnectionManager connManager = new BasicHttpClientConnectionManager(
                RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("http", PlainConnectionSocketFactory.getSocketFactory())
                        .register("https", SSLConnectionSocketFactory.getSocketFactory())
                        .build(),
                null,
                null,
                null
        );

        return HttpClientBuilder.create()
                .setConnectionManager(connManager)
                .setConnectionTimeToLive(60, TimeUnit.SECONDS)
                .build();
    }

}
