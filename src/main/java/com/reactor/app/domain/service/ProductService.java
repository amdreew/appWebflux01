package com.reactor.app.domain.service;

import com.reactor.app.domain.model.Product;
import com.reactor.app.domain.port.ProductPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    @Autowired
    private ProductPort productPort;

    public Flux<Product> findAll() {
        return this.productPort.findAll();
    }

    public Mono<Product> findById(String id) {
        return this.productPort.findById(id);
    }
}
