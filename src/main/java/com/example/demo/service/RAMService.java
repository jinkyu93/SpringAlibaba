package com.example.demo.service;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.httpcomponent.httpclient.ApacheAsyncHttpClientBuilder;
import com.aliyun.sdk.service.ram20150501.AsyncClient;
import com.aliyun.sdk.service.ram20150501.models.ListUsersRequest;
import com.aliyun.sdk.service.ram20150501.models.ListUsersResponseBody;
import darabonba.core.client.ClientOverrideConfiguration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;


@Slf4j
@Service
@AllArgsConstructor
public class RAMService {
    ApacheAsyncHttpClientBuilder ignoreSSLApacheAsyncHttpClientBuilder;

    public ListUsersResponseBody getUsers() {
        var provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"))
                .accessKeySecret(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"))
                .build());

        var client = AsyncClient.builder()
                .httpClient(ignoreSSLApacheAsyncHttpClientBuilder.build())
                .credentialsProvider(provider)
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride("ram.aliyuncs.com")
                )
                .build();

        var listUsersRequest = ListUsersRequest.builder()
                .build();

        try {
            var response = client.listUsers(listUsersRequest);
            return response.get().getBody();
        }
        catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            client.close();
        }
    }
}
