package com.reactor.app.application.service;

import com.reactor.app.application.dto.ProductDto;
import com.reactor.app.application.mapper.ProductAppMapper;
import com.reactor.app.application.port.ProductAppPort;
import com.reactor.app.domain.model.Product;
import com.reactor.app.domain.service.ProductService;
import com.reactor.app.infraestructure.controllers.rest.ProductoRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(ProductoRestController.class);

    @Override
    public Flux<ProductDto> findAll() {
        return productService.findAll().map(productAppMapper::mapProductToProductDto)
                .map(producto -> {
                    producto.setNombre(producto.getNombre().toUpperCase());
                    return producto;
                });
                /*.doOnNext(prod -> log.info(prod.getNombre()));*/
    }

    @Override
    public Mono<ProductDto> findById(String id) {
        return this.productService.findById(id)
                .map(productAppMapper::mapProductToProductDto);
    }

    @Override
    public Mono<ProductDto> save(ProductDto productDto) {
        return this.productService.save(this.productAppMapper.mapProductDtoToProduc(productDto))
                .map(productAppMapper::mapProductToProductDto);
    }

    @Override
    public Mono<ProductDto> update(ProductDto productDto) throws Exception {
        Product newProduct = this.productAppMapper.mapProductDtoToProduc(productDto);
        Mono<ProductDto> currentProduct = this.findById(newProduct.getId());
        if (currentProduct != null) {
           this.save(productDto);
        } else {
            throw new Exception("no se encontro el producto");
        }
        return this.productService.save(this.productAppMapper.mapProductDtoToProduc(productDto))
                .map(productAppMapper::mapProductToProductDto);
    }
}
