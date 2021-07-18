package io.forloop.proxy.controllers;


import io.forloop.proxy.domain.Count;
import io.forloop.proxy.services.MetricService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/metric")
@RequiredArgsConstructor
public class MetricController {

    private final MetricService metricService;

    @GetMapping
    public Count count() {
        return metricService.countProxies();
    }

}
