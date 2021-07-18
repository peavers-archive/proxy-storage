package io.forloop.proxy.repositories;

import io.forloop.proxy.domain.Proxy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProxyRepository extends MongoRepository<Proxy, String> {

    Optional<Proxy> findFirstByValidatedTrueAndInUseFalse();

    Proxy findFirstByValidatedFalseAndInUseFalse();

    List<Proxy> findAllByInUseTrue();

    long countAllByValidatedTrue();

    long countAllByValidatedFalse();

    long countAllByInUseTrue();
}
