package io.forloop.proxy.utils;

import io.forloop.proxy.domain.Proxy;
import lombok.experimental.UtilityClass;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.lang.Nullable;

import java.time.Duration;

@UtilityClass
public class HttpUtils {

    public CloseableHttpClient getHttpClient(@Nullable final Proxy proxy) {

        final var httpClientBuilder = HttpClientBuilder
                .create()
                .disableAutomaticRetries()
                .setUserAgent(UserAgentUtils.getRandomUserAgent())
                .setDefaultRequestConfig(getRequestConfig());

        if (proxy != null) {
            httpClientBuilder.setProxy(new HttpHost(proxy.getHost(), proxy.getPort()));
        }

        return httpClientBuilder.build();
    }

    private RequestConfig getRequestConfig() {

        final var timeout = Math.toIntExact(Duration.ofSeconds(60).toMillis());

        return RequestConfig.custom()
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setSocketTimeout(timeout)
                .build();
    }

}
