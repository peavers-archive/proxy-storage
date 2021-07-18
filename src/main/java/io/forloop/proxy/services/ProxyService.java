package io.forloop.proxy.services;

import io.forloop.proxy.domain.Proxy;

import java.util.List;

public interface ProxyService {

    void deleteAll();

    void deleteById(String id);

    void delete(Proxy proxy);

    void markValid(Proxy proxy);

}
