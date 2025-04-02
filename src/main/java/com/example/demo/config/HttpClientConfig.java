package com.example.demo.config;

import com.aliyun.core.http.HttpClient;
import com.aliyun.httpcomponent.httpclient.ApacheAsyncHttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class HttpClientConfig {

    @Bean
    public ApacheAsyncHttpClientBuilder ignoreSSLApacheAsyncHttpClientBuilder() {
        return new ApacheAsyncHttpClientBuilder()
                .connectionTimeout(Duration.ofSeconds(10)) // Set the connection timeout time, the default is 10 seconds
                .responseTimeout(Duration.ofSeconds(10)) // Set the response timeout time, the default is 20 seconds
                .maxConnections(128) // Set the connection pool size
                .maxIdleTimeOut(Duration.ofSeconds(50)) // Set the connection pool timeout, the default is 30 seconds
                .ignoreSSL(true);
    }
}
