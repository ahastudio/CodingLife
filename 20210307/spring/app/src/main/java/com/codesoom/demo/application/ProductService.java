package com.codesoom.demo.application;

import com.codesoom.demo.domain.Product;
import com.codesoom.demo.domain.ProductRepository;
import com.codesoom.demo.dto.ProductData;
import com.codesoom.demo.errors.ProductNotFoundException;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Service for products.
 *
 * @author Ashal aka JOEKR
 */
@Service
@Transactional
public class ProductService {
    private final Mapper mapper;
    private final ProductRepository productRepository;

    /**
     * Constructor.
     *
     * @param dozerMapper       is an object mapper for DTO and domain model.
     * @param productRepository is an interface for product CRUD.
     */
    public ProductService(
            Mapper dozerMapper,
            ProductRepository productRepository
    ) {
        this.mapper = dozerMapper;
        this.productRepository = productRepository;
    }

    /**
     * Returns all products in this application.
     *
     * @return all products.
     */
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    /**
     * Returns a product by its ID.
     *
     * @param id is an identifier.
     * @return the product with given ID.
     * @throws ProductNotFoundException in case any product
     * with the given ID is not existed.
     */
    public Product getProduct(Long id) {
        return findProduct(id);
    }

    public Product createProduct(ProductData productData) {
        Product product = mapper.map(productData, Product.class);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductData productData) {
        Product product = findProduct(id);

        product.changeWith(mapper.map(productData, Product.class));

        return product;
    }

    public Product deleteProduct(Long id) {
        Product product = findProduct(id);

        productRepository.delete(product);

        return product;
    }

    private Product findProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}
