package io.forloop.proxy.services;

import io.forloop.proxy.domain.Count;
import io.forloop.proxy.domain.Proxy;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProxyService {

    Proxy findValid();

    List<Proxy> findAll();

    List<Proxy> saveAllFromFile(MultipartFile file) throws IOException;

    void deleteAll();

    void deleteById(String id);

    void delete(Proxy proxy);

    Proxy markValid(Proxy proxy);

    Count count();
}
