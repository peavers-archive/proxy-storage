package io.forloop.proxy.services;

import io.forloop.proxy.domain.Proxy;

import java.util.List;

public interface ValidateService {

    void validate(List<Proxy> proxies);

}
