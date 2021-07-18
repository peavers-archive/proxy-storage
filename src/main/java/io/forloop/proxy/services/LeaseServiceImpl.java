package io.forloop.proxy.services;

import io.forloop.proxy.domain.Proxy;
import io.forloop.proxy.repositories.ProxyRepository;
import io.forloop.proxy.utils.UserAgentUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LeaseServiceImpl implements LeaseService {

    private final ProxyRepository repository;

    @Override
    public Proxy checkoutProxy() {
        final var optionalProxy = repository.findFirstByValidatedTrueAndInUseFalse();
        final var proxy = optionalProxy.isEmpty() ? repository.findFirstByValidatedFalseAndInUseFalse() : optionalProxy.get();

        proxy.setInUse(true);
        proxy.setUserAgent(UserAgentUtils.getRandomUserAgent());

        log.info("Issued proxy: {}", proxy);

        return repository.save(proxy);
    }

    @Override
    public void returnProxy(String proxyId) throws Exception {
        final var proxy = repository.findById(proxyId).orElseThrow(Exception::new);

        proxy.setInUse(false);

        repository.save(proxy);
    }

    @Override
    public void returnAllProxies() {
        final var proxies = repository.findAllByInUseTrue();

        proxies.forEach(proxy -> {
            proxy.setInUse(false);
            repository.save(proxy);
        });
    }
}
