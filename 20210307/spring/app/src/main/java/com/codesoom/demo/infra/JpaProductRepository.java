package com.codesoom.demo.infra;

import com.codesoom.demo.domain.Product;
import com.codesoom.demo.domain.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface JpaProductRepository
        extends ProductRepository, CrudRepository<Product, Long> {
    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    void delete(Product product);
}
