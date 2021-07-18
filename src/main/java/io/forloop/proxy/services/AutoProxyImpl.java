package io.forloop.proxy.services;

import io.forloop.proxy.domain.Proxy;
import io.forloop.proxy.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AutoProxyImpl implements AutoProxy {

    private static final String ENDPOINT = "https://www.proxy-list.download/api/v1/get?type=https";

    @Override
    public List<Proxy> fetch() {

        final var strings = List.of(request().split("\n"));

        return strings.stream()
                .map(s -> s.split(":"))
                .map(getProxyFunction())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private String request() {
        try (final var httpClient = HttpUtils.getHttpClient(null)) {
            try (final var response = httpClient.execute(new HttpGet(ENDPOINT))) {
                return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            }
        } catch (final Exception exception) {
            return StringUtils.EMPTY;
        }
    }

    private Function<String[], Proxy> getProxyFunction() {
        return proxyString -> Proxy
                .builder()
                .host(proxyString[0])
                .port(Integer.parseInt(proxyString[1].replaceAll("[^0-9]", "")))
                .build();
    }
}
