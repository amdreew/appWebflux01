package com.reactor.app.application.service;

import com.reactor.app.application.dto.ProductDto;
import com.reactor.app.application.mapper.ProductAppMapper;
import com.reactor.app.application.port.ProductAppPort;
import com.reactor.app.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProductAppService implements ProductAppPort {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductAppMapper productAppMapper;

    @Override
    public Flux<ProductDto> findAll() {
        return productService.findAll().map(productAppMapper::mapProductToProductDto);
    }

    @Override
    public Mono<ProductDto> findById(String id) {
        return this.productService.findById(id).map(productAppMapper::mapProductToProductDto);
    }
}
