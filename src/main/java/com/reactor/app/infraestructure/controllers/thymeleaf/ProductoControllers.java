package com.reactor.app.infraestructure.controllers.thymeleaf;

import com.reactor.app.application.dto.ProductDto;
import com.reactor.app.application.port.ProductAppPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Controller
public class ProductoControllers {
    @Autowired
    private ProductAppPort productAppPort;
    private static final Logger log = LoggerFactory.getLogger(ProductoControllers.class);

    @GetMapping({"/listar", "/"})
    public String listar(Model model) {
        Flux<ProductDto> productos = this.productAppPort.findAll().map(productDto -> {
            productDto.setNombre(productDto.getNombre().toUpperCase());
            return productDto;
        });
        productos.subscribe(prod -> log.info(prod.getNombre()));
        model.addAttribute("productos", productos);
        model.addAttribute("titulo", "Listado de productos");
        return "listar";
    }

    /*
    * esta es una manera de manejar la conta presion los dato se van
    * enviando al subcriptor conforme se van obteniendo los resultados
    * */
    @GetMapping("/listar-datadriver")
    public String listarDataDriver(Model model) {
        Flux<ProductDto> productos = this.productAppPort.findAll().map(productDto -> {
            productDto.setNombre(productDto.getNombre().toUpperCase());
            return productDto;
        }).delayElements(Duration.ofSeconds(1));
        productos.subscribe(prod -> log.info(prod.getNombre()));
        model.addAttribute("productos", new ReactiveDataDriverContextVariable(productos, 2));
        model.addAttribute("titulo", "Listado de productos");
        return "listar";
    }

    @GetMapping("/listar-full")
    public String listarFull(Model model) {
        Flux<ProductDto> productos = this.productAppPort.findAll().map(productDto -> {
            productDto.setNombre(productDto.getNombre().toUpperCase());
            return productDto;
        }).repeat(5000);

        model.addAttribute("productos", productos);
        model.addAttribute("titulo", "Listado de productos");
        return "listar";
    }

    @GetMapping("/listar-chunked")
    public String listarChunked(Model model) {
        Flux<ProductDto> productos = this.productAppPort.findAll().map(productDto -> {
            productDto.setNombre(productDto.getNombre().toUpperCase());
            return productDto;
        }).repeat(5000);

        model.addAttribute("productos", productos);
        model.addAttribute("titulo", "Listado de productos");
        return "listar-chunked";
    }
}
