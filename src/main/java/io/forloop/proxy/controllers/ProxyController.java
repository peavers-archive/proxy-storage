package io.forloop.proxy.controllers;

import io.forloop.proxy.services.ProxyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/proxy")
@RequiredArgsConstructor
public class ProxyController {

    private final ProxyService proxyService;

    @DeleteMapping
    public void deleteAll() {
        proxyService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable final String id) {
        proxyService.deleteById(id);
    }
}
