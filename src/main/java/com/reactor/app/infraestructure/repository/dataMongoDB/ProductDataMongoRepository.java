package com.reactor.app.infraestructure.repository.dataMongoDB;

import com.reactor.app.infraestructure.repository.documents.ProductDocumet;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductDataMongoRepository extends ReactiveMongoRepository<ProductDocumet, String> {
}
