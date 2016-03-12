package com.kdb.http;

import org.apache.http.client.HttpClient;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

/**
 * @author xiliang.zxl
 * @date 2016-01-16 下午11:36
 */
public class PooledHttpRequestFactory extends HttpComponentsClientHttpRequestFactory {

    public PooledHttpRequestFactory(HttpClient httpClient) {
        super(httpClient);
    }
}
