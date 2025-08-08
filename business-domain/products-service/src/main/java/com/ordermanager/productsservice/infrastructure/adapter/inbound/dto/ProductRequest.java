package com.ordermanager.productsservice.infrastructure.adapter.inbound.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductRequest(
    @Schema(description = "SKU (Stock Keeping Unit) único del producto.", example = "SKU-PROD-001")
    @NotBlank(message = "El SKU no puede estar vacío.")
    @Size(max = 50, message = "El SKU no puede exceder los 50 caracteres.")
    String sku,

    @Schema(description = "Nombre del producto.", example = "Laptop Gaming XYZ")
    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres.")
    String name,

    @Schema(description = "Descripción detallada del producto.", example = "Laptop de alto rendimiento para gaming con procesador i9 y 32GB RAM.")
    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres.")
    String description,

    @Schema(description = "Precio del producto.", example = "1299.99")
    @NotNull(message = "El precio no puede ser nulo.")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor que cero.")
    Double price,

    @Schema(description = "Estado actual del producto (activo/inactivo).", example = "true")
    @NotNull(message = "El estado no puede ser nulo.")
    Boolean status
) {
}
