package com.ordermanager.productsservice.application.service;

import com.ordermanager.productsservice.domain.model.Product;
import com.ordermanager.productsservice.domain.port.ProductRepository;
import com.paymentchain.exception.type.CommonException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository repository;

    public Product createProduct(Product product) {
        return repository.create(product);
    }

    public Product getById(Long id) {
        return repository.getById(id);
    }

    public List<Product> getAllProducts() {
        return repository.getAll();
    }

    public void deleteById(Long id) {
        boolean  exists = repository.existsById(id);

        if (exists) {
            repository.deleteById(id);
        } else {
            throw CommonException.Type.RESOURCE_NOT_FOUND.build(id);
        }
    }
}
