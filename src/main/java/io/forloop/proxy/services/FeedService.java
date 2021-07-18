package io.forloop.proxy.services;

import io.forloop.proxy.domain.Proxy;

import java.util.List;

public interface FeedService {

    List<Proxy> fetch();

}
