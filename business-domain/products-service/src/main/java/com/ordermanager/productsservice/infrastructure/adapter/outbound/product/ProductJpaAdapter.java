package com.ordermanager.productsservice.infrastructure.adapter.outbound.product;


import com.ordermanager.productsservice.domain.model.Product;
import com.ordermanager.productsservice.domain.port.ProductRepository;
import com.paymentchain.exception.type.CommonException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductJpaAdapter implements ProductRepository {

    private final ProductJpaRepository repository;
    private final ProductMapper mapper;

    @Override
    public Product create(Product product) {
        ProductJpaEntity saved = repository.save(mapper.toObject(product));
        return mapper.toDomain(saved);
    }

    @Override
    public Product getById(Long id) {
        return repository.findById(id)
            .map(mapper::toDomain)
            .orElseThrow(CommonException.Type.RESOURCE_NOT_FOUND.defer(id));
    }

    @Override
    public List<Product> getAll() {
        return mapper.toDomainList(repository.findAll());
    }

    @Override
    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
