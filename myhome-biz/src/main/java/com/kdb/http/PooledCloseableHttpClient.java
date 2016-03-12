package com.kdb.http;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.FactoryBean;

import java.util.concurrent.TimeUnit;

/**
 * @author xiliang.zxl
 * @date 2016-01-17 上午10:22
 */
public class PooledCloseableHttpClient implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        PoolingHttpClientConnectionManager connectionManager =
                new PoolingHttpClientConnectionManager(3, TimeUnit.MINUTES);
        connectionManager.setMaxTotal(500);
        connectionManager.setDefaultMaxPerRoute(50);

        SocketConfig socketConfig = SocketConfig.copy(SocketConfig.DEFAULT)
                .setSoTimeout(5000)
                .setSoReuseAddress(true)
                .setTcpNoDelay(true)
                .build();

        RequestConfig requestConfig = RequestConfig.copy(RequestConfig.DEFAULT)
                .setConnectTimeout(8000)
                .setSocketTimeout(5000)
                .setConnectionRequestTimeout(3000)
                .build();

        HttpClientBuilder builder = HttpClients.custom().setConnectionManager(connectionManager)
                .setDefaultSocketConfig(socketConfig)
                .setDefaultRequestConfig(requestConfig);

        return builder.build();
    }

    @Override
    public Class<?> getObjectType() {
        return CloseableHttpClient.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
