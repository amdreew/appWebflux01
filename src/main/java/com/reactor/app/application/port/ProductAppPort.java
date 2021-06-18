package com.reactor.app.application.port;

import com.reactor.app.application.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductAppPort {
    Flux<ProductDto> findAll();
    Mono<ProductDto> findById(String id);
    Mono<ProductDto> save(ProductDto productDto);
    Mono<ProductDto> update(ProductDto productDto) throws Exception;
}
