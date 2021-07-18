package io.forloop.proxy.services;

import io.forloop.proxy.domain.Proxy;
import io.forloop.proxy.repositories.ProxyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProxyServiceImpl implements ProxyService {

    private final ProxyRepository repository;

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public void deleteById(final String id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(final Proxy proxy) {
        repository.delete(proxy);
    }

    @Override
    public void markValid(final Proxy proxy) {
        log.info("Valid proxy: {}", proxy);

        proxy.setValidated(true);

        repository.save(proxy);
    }

}
