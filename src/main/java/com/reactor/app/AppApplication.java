package com.reactor.app;

import com.reactor.app.models.dao.ProductoDao;
import com.reactor.app.infraestructure.repository.documents.ProductDocumet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

import java.util.Date;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {

    @Autowired
    private ProductoDao dao;
    @Autowired
    private ReactiveMongoTemplate mongoTemplate;
    private static final Logger log = LoggerFactory.getLogger(AppApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Override
    public void run(String... args) {
        mongoTemplate.dropCollection("productos").subscribe();
        Flux.just(
                new ProductDocumet(null, "TV Panasonic Pantalla LCD", 456.89, null),
                new ProductDocumet(null, "Sony Camara HD Digital", 177.89, null),
                new ProductDocumet(null, "Apple iPod", 46.89, null),
                new ProductDocumet(null, "Sony Notebook", 846.89, null),
                new ProductDocumet(null, "Hewlett Packard Multifuncional", 200.89, null),
                new ProductDocumet(null, "Bianchi Bicicleta", 70.89, null),
                new ProductDocumet(null, "HP Notebook Omen 17", 2500.89, null),
                new ProductDocumet(null, "Mica Cómoda 5 Cajones", 150.89, null),
                new ProductDocumet(null, "TV Sony Bravia OLED 4K Ultra HD", 2255.89, null)
        )
                .flatMap(producto -> {
                    producto.setCreateAt(new Date());
                    return dao.save(producto);
                })
                .subscribe(producto -> log.info(producto.toString()));
    }
}
