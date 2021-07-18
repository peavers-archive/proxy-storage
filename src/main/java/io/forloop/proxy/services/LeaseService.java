package io.forloop.proxy.services;

import io.forloop.proxy.domain.Proxy;

public interface LeaseService {

    Proxy checkoutProxy();

    void returnProxy(String proxyId) throws Exception;

    void returnAllProxies();

}
