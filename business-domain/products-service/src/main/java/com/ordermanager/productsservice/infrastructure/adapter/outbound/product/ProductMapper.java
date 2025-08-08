package com.ordermanager.productsservice.infrastructure.adapter.outbound.product;

import com.ordermanager.productsservice.domain.mapper.DataMapper;
import com.ordermanager.productsservice.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
    unmappedSourcePolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper extends DataMapper<Product, ProductJpaEntity> {
}
