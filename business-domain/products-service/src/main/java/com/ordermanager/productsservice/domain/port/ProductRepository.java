package com.ordermanager.productsservice.domain.port;


import com.ordermanager.productsservice.domain.model.Product;

import java.util.List;

public interface ProductRepository {
    Product create(Product product);
    Product getById(Long id);
    List<Product> getAll();
    Boolean existsById(Long id);
    void deleteById(Long id);
}
