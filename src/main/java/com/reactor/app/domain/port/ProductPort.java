package com.reactor.app.domain.port;

import com.reactor.app.domain.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductPort {
    Flux<Product> findAll();
    Mono<Product> findById(String id);
    Mono<Product> save(Product product);
}
