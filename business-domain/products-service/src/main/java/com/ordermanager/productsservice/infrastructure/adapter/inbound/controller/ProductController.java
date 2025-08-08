package com.ordermanager.productsservice.infrastructure.adapter.inbound.controller;


import com.ordermanager.productsservice.application.service.ProductService;
import com.ordermanager.productsservice.domain.model.Product;
import com.ordermanager.productsservice.infrastructure.adapter.inbound.dto.ProductRequest;
import com.ordermanager.productsservice.infrastructure.adapter.inbound.dto.ProductResponse;
import com.ordermanager.productsservice.infrastructure.adapter.inbound.mapper.ProductRequestMapper;
import com.ordermanager.productsservice.infrastructure.adapter.inbound.mapper.ProductResponseMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductRequestMapper productRequestMapper;
    private final ProductResponseMapper productResponseMapper;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest product) {
        Product saved = productService.createProduct(productRequestMapper.toDomain(product));
        return ResponseEntity.ok(productResponseMapper.toObject(saved));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        List<ProductResponse> products = productResponseMapper.toObjectList(productService.getAllProducts());

        return products.isEmpty() ?
            ResponseEntity.noContent().build() :
            ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable(name = "id") Long id) {
        Product product = productService.getById(id);
        return ResponseEntity.ok(productResponseMapper.toObject(product));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ProductResponse> deleteProduct(@PathVariable(name = "id") Long id) {
        productService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
