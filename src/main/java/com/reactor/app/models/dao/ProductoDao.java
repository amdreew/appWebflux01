package com.reactor.app.models.dao;

import com.reactor.app.infraestructure.repository.documents.ProductDocumet;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductoDao  extends ReactiveMongoRepository<ProductDocumet, String> {
}
