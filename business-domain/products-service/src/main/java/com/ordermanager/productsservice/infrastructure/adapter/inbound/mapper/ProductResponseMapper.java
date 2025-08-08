package com.ordermanager.productsservice.infrastructure.adapter.inbound.mapper;

import com.ordermanager.productsservice.domain.mapper.DataMapper;
import com.ordermanager.productsservice.domain.model.Product;
import com.ordermanager.productsservice.infrastructure.adapter.inbound.dto.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
    unmappedSourcePolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductResponseMapper extends DataMapper<Product, ProductResponse> {
}
