package com.ordermanager.productsservice.infrastructure.adapter.inbound.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record ProductResponse(
    @Schema(description = "Identificador único del producto.", example = "101")
    Long id,

    @Schema(description = "SKU (Stock Keeping Unit) único del producto.", example = "SKU-PROD-001")
    String sku,

    @Schema(description = "Nombre del producto.", example = "Laptop Gaming XYZ")
    String name,

    @Schema(description = "Descripción detallada del producto.", example = "Laptop de alto rendimiento para gaming con procesador i9 y 32GB RAM.")
    String description,

    @Schema(description = "Precio del producto.", example = "1299.99")
    Double price,

    @Schema(description = "Estado actual del producto (activo/inactivo).", example = "true")
    Boolean status
) {
}
