package io.forloop.proxy.tasks;

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

    private final ValidateService validateService;

    private final ProxyService proxyService;

    @Scheduled(fixedDelay = 21_600_000) // 6 Hours
    public void process() {
        validateService.validate();

        log.info("Validation complete: {}", proxyService.count().toString());
    }
}
