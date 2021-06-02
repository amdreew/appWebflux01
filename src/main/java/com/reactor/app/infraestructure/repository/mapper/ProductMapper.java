package com.reactor.app.infraestructure.repository.mapper;

import com.reactor.app.domain.model.Product;
import com.reactor.app.infraestructure.repository.documents.ProductDocumet;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProductMapper {
    Product mapProductDocumetToProduct(ProductDocumet productDocumet);
    ProductDocumet mapProductToProductDocument(Product product);
}
