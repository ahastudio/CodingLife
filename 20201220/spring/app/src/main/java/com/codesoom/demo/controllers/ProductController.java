// REST
// /products -> Create, Read
// /products/{id} -> Read, Update, Delete

package com.codesoom.demo.controllers;

import com.codesoom.demo.application.AuthenticationService;
import com.codesoom.demo.application.ProductService;
import com.codesoom.demo.domain.Product;
import com.codesoom.demo.dto.ProductData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    private final AuthenticationService authenticationService;

    public ProductController(ProductService productService,
                             AuthenticationService authenticationService) {
        this.productService = productService;
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public List<Product> list() {
        return productService.getProducts();
    }

    @GetMapping("{id}")
    public Product detail(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(
            @RequestAttribute Long userId,
            @RequestBody @Valid ProductData productData
    ) {
        System.out.println("*** Create a new product by user: " + userId);
        return productService.createProduct(productData);
    }

    @PatchMapping("{id}")
    public Product update(
            @RequestAttribute Long userId,
            @PathVariable Long id,
            @RequestBody @Valid ProductData productData
    ) {
        System.out.println("*** Update a product by user: " + userId);
        return productService.updateProduct(id, productData);
    }

    @DeleteMapping("{id}")
    public void destroy(
            @RequestAttribute Long userId,
            @PathVariable Long id
    ) {
        System.out.println("*** Delete a product by user: " + userId);
        productService.deleteProduct(id);
    }
}
