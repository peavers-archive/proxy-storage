package io.forloop.proxy.services;

import io.forloop.proxy.domain.Count;
import io.forloop.proxy.repositories.ProxyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MetricServiceImpl implements MetricService {

    private final ProxyRepository repository;

    @Override
    public Count countProxies() {
        return Count.builder()
                .validatedTrue(repository.countAllByValidatedTrue())
                .validatedFalse(repository.countAllByValidatedFalse())
                .inUse(repository.countAllByInUseTrue())
                .build();
    }
}
