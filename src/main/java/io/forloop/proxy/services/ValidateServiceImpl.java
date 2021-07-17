package io.forloop.proxy.services;

import io.forloop.proxy.domain.Proxy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.ExecutorService;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService {

    private static final String TEST_ENDPOINT = "https://google.com";

    private static final int REQUEST_TIMEOUT = Math.toIntExact(Duration.ofSeconds(60).toMillis());

    private final ProxyService proxyService;

    private final ExecutorService executorService;

    @Override
    public void validate() {
        proxyService
                .findAll()
                .forEach(proxy -> executorService.submit(() -> process(proxy)));
    }

    private void process(final Proxy proxy) {
        if (checkProxy(proxy)) {
            proxyService.markValid(proxy);
        } else {
            proxyService.delete(proxy);
        }
    }

    /**
     * Make a request to a known endpoint and expect a 200 status response back, returning true.
     * If any errors at all occur (timeout, unknown host etc) assume the proxy to be dead and return false.
     */
    private boolean checkProxy(final Proxy proxy) {

        log.info("Checking proxy: {}", proxy.getValue());

        try (final var httpClient = getHttpClient(proxy, getRequestConfig())) {
            try (final var response = httpClient.execute(new HttpGet(TEST_ENDPOINT))) {
                final int statusCode = response.getStatusLine().getStatusCode();
                return statusCode == HttpStatus.SC_OK;
            }
        } catch (final Exception exception) {
            return false;
        }
    }

    /**
     * Create a Http Client configured to use the Proxy we need to validate.
     */
    private CloseableHttpClient getHttpClient(final Proxy proxy, final RequestConfig requestConfig) {

        return HttpClientBuilder
                .create()
                .disableAutomaticRetries()
                .setProxy(new HttpHost(proxy.host(), proxy.port()))
                .setDefaultRequestConfig(requestConfig).build();
    }

    /**
     * Create a request configuration which will timeout after a specific time, regardless of which socket doesn't
     * respond.
     */
    private RequestConfig getRequestConfig() {

        return RequestConfig.custom()
                .setConnectTimeout(REQUEST_TIMEOUT)
                .setConnectionRequestTimeout(REQUEST_TIMEOUT)
                .setSocketTimeout(REQUEST_TIMEOUT)
                .build();
    }
}
