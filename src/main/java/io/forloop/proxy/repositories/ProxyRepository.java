package io.forloop.proxy.repositories;

import io.forloop.proxy.domain.Proxy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProxyRepository extends MongoRepository<Proxy, String> {

    Optional<Proxy> findFirstByValidatedTrueAndInUseFalse();

    Proxy findFirstByValidatedFalseAndInUseFalse();

    long countAllByValidatedTrue();

    long countAllByValidatedFalse();
}
