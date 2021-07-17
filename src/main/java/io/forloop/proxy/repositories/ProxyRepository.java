package io.forloop.proxy.repositories;

import io.forloop.proxy.domain.Proxy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProxyRepository extends MongoRepository<Proxy, String> {

    Proxy findFirstByValidatedTrueAndInUseFalse();

    long countAllByValidatedTrue();

    long countAllByValidatedFalse();
}
