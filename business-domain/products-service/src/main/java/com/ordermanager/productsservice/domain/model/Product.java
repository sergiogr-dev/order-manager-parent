package com.ordermanager.productsservice.domain.model;

import lombok.Builder;

@Builder(toBuilder = true)
public record Product(
    Long id,
    String sku,
    String name,
    String description,
    Double price,
    Boolean status
) {
}
