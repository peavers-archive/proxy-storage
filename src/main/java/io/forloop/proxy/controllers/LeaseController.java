package io.forloop.proxy.controllers;

import io.forloop.proxy.domain.Proxy;
import io.forloop.proxy.services.LeaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/lease")
@RequiredArgsConstructor
public class LeaseController {

    private final LeaseService leaseService;

    @GetMapping
    public Proxy checkout() {
        return leaseService.checkoutProxy();
    }

    @GetMapping("/return-proxy/{id}")
    public void returnProxy(@PathVariable final String id) throws Exception {
        leaseService.returnProxy(id);
    }

    @GetMapping("/return-all-proxies")
    public void returnAllProxies() {
        leaseService.returnAllProxies();
    }
}
