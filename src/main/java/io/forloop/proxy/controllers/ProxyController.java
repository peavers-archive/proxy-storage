package io.forloop.proxy.controllers;

import io.forloop.proxy.domain.Count;
import io.forloop.proxy.domain.Proxy;
import io.forloop.proxy.services.ProxyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/proxy")
@RequiredArgsConstructor
public class ProxyController {

    private final ProxyService proxyService;

    @GetMapping("/count")
    public Count count() {
        return proxyService.count();
    }

    @GetMapping
    public Proxy lease() {
        return proxyService.lease();
    }

    @GetMapping("/release/{id}")
    public void release(@PathVariable final String id) {
        proxyService.release(id);
    }

    @PostMapping
    public List<Proxy> saveAll(@RequestParam("file") final MultipartFile file) throws IOException {
        return proxyService.saveAllFromFile(file);
    }

    @DeleteMapping
    public void deleteAll() {
        proxyService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteProxy(@PathVariable final String id) {
        proxyService.deleteById(id);
    }
}
