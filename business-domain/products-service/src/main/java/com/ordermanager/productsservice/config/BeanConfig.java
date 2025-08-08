package com.ordermanager.productsservice.config;

import com.ordermanager.productsservice.application.service.ProductService;
import com.ordermanager.productsservice.infrastructure.adapter.outbound.product.ProductJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public ProductService productService(ProductJpaAdapter productJpaAdapter) {
        return  new ProductService(productJpaAdapter);
    }
}
