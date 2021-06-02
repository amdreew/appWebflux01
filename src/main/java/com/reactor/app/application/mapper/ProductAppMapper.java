package com.reactor.app.application.mapper;

import com.reactor.app.application.dto.ProductDto;
import com.reactor.app.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProductAppMapper {
    ProductDto mapProductToProductDto(Product product);
}
