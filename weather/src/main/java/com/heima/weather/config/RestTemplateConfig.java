package com.heima.weather.config;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
//        // 创建支持 Gzip 的 HttpClient
//        RequestConfig config = RequestConfig.custom()
//                .build();
//
//        // 启用 Gzip 解压缩（HttpClient 5 默认已支持，但显式声明更安全）
//        HttpClient httpClient = HttpClients.custom()
//                .setDefaultRequestConfig(config)
//                .disableCookieManagement() // 可选
//                .build();
//
//        HttpComponentsClientHttpRequestFactory factory =
//                new HttpComponentsClientHttpRequestFactory(httpClient);
//
//        return new RestTemplate(factory);

        //配置支持gzip的resttemplate

        CloseableHttpClient httpClient = HttpClients.custom().build();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

        return new RestTemplate(factory);
    }
}