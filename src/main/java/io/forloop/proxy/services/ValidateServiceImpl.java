package io.forloop.proxy.services;

import io.forloop.proxy.domain.Proxy;
import io.forloop.proxy.utils.HttpUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService {

    private static final String TEST_ENDPOINT = "https://google.com";

    private final ProxyService proxyService;

    private final ExecutorService executorService;

    @Override
    public void validate(List<Proxy> proxies) {

        Collections.shuffle(proxies);

        proxies.forEach(proxy -> executorService.submit(() -> process(proxy)));
    }

    private void process(final Proxy proxy) {
        if (checkProxy(proxy)) {
            proxyService.markValid(proxy);
        } else {
            proxyService.delete(proxy);
        }
    }

    private boolean checkProxy(final Proxy proxy) {
        try (final var httpClient = HttpUtils.getHttpClient(proxy)) {
            try (final var response = httpClient.execute(new HttpGet(TEST_ENDPOINT))) {
                return response.getStatusLine().getStatusCode() == HttpStatus.SC_OK;
            }
        } catch (final Exception exception) {
            return false;
        }
    }
}
