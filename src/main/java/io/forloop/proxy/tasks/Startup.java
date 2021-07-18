package io.forloop.proxy.tasks;

import io.forloop.proxy.services.FeedService;
import io.forloop.proxy.services.ProxyService;
import io.forloop.proxy.services.ValidateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Startup {

    private final ProxyService proxyService;

    private final ValidateService validateService;

    private final FeedService feedService;

    @Scheduled(fixedDelay = 21_600_000) // 6 Hours
    public void process() {

        proxyService.deleteAll();

        final var proxies = feedService.fetch();

        validateService.validate(proxies);
    }
}
