package com.reactor.app.infraestructure.controllers.rest;

import com.reactor.app.application.dto.ProductDto;
import com.reactor.app.application.port.ProductAppPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {

    @Autowired
    private ProductAppPort productAppPort;


    @GetMapping
    public Flux<ProductDto> index() {
        return this.productAppPort.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ProductDto> show(@PathVariable String id) {
        /*
        return this.productAppPort.findAll()
                .filter(p -> p.getId().equals(id))
                .next()
                .doOnNext(prod -> log.info(prod.getNombre()));
        */
        return this.productAppPort.findById(id);
    }
}
