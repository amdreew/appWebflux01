package com.reactor.app.infraestructure.repository.adapter;

import com.reactor.app.domain.model.Product;
import com.reactor.app.domain.port.ProductPort;
import com.reactor.app.infraestructure.repository.dataMongoDB.ProductDataMongoRepository;
import com.reactor.app.infraestructure.repository.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProductRepositoryAdapter implements ProductPort {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductDataMongoRepository productDataMongoRepository;

    @Override
    public Flux<Product> findAll() {
        return this.productDataMongoRepository.findAll().map(productMapper::mapProductDocumetToProduct);
    }

    @Override
    public Mono<Product> findById(String id) {
        return this.productDataMongoRepository.findById(id).map(productMapper::mapProductDocumetToProduct);
    }
}
