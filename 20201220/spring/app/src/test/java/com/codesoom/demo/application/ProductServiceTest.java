// 1. getProducts -> 목록
// 2. getProduct -> 상세 정보
// 3. createProduct -> 상품 추가
// 4. updateProduct -> 상품 수정
// 5. deleteProduct -> 상품 삭제

package com.codesoom.demo.application;

import com.codesoom.demo.errors.ProductNotFoundException;
import com.codesoom.demo.domain.Product;
import com.codesoom.demo.domain.ProductRepository;
import com.codesoom.demo.dto.ProductData;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ProductServiceTest {
    private ProductService productService;

    private final ProductRepository productRepository =
            mock(ProductRepository.class);

    @BeforeEach
    void setUp() {
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();

        productService = new ProductService(mapper, productRepository);

        Product product = Product.builder()
                .id(1L)
                .name("쥐돌이")
                .maker("냥이월드")
                .price(5000)
                .build();

        given(productRepository.findAll()).willReturn(List.of(product));

        given(productRepository.findById(1L)).willReturn(Optional.of(product));

        given(productRepository.save(any(Product.class))).will(invocation -> {
            Product source = invocation.getArgument(0);
            return Product.builder()
                    .id(2L)
                    .name(source.getName())
                    .maker(source.getMaker())
                    .price(source.getPrice())
                    .build();
        });
    }

    @Test
    void getProductsWithNoProduct() {
        given(productRepository.findAll()).willReturn(List.of());

        assertThat(productService.getProducts()).isEmpty();
    }

    @Test
    void getProducts() {
        List<Product> products = productService.getProducts();

        assertThat(products).isNotEmpty();

        Product product = products.get(0);

        assertThat(product.getName()).isEqualTo("쥐돌이");
    }

    @Test
    void getProductWithExsitedId() {
        Product product = productService.getProduct(1L);

        assertThat(product).isNotNull();
        assertThat(product.getName()).isEqualTo("쥐돌이");
    }

    @Test
    void getProductWithNotExsitedId() {
        assertThatThrownBy(() -> productService.getProduct(1000L))
                .isInstanceOf(ProductNotFoundException.class);
    }

    @Test
    void createProduct() {
        ProductData productData = ProductData.builder()
                .name("쥐돌이")
                .maker("냥이월드")
                .price(5000)
                .build();

        Product product = productService.createProduct(productData);

        verify(productRepository).save(any(Product.class));

        assertThat(product.getId()).isEqualTo(2L);
        assertThat(product.getName()).isEqualTo("쥐돌이");
        assertThat(product.getMaker()).isEqualTo("냥이월드");
    }

    @Test
    void updateProductWithExistedId() {
        ProductData productData = ProductData.builder()
                .name("쥐순이")
                .maker("냥이월드")
                .price(5000)
                .build();

        Product product = productService.updateProduct(1L, productData);

        assertThat(product.getId()).isEqualTo(1L);
        assertThat(product.getName()).isEqualTo("쥐순이");
    }

    @Test
    void updateProductWithNotExistedId() {
        ProductData productData = ProductData.builder()
                .name("쥐순이")
                .maker("냥이월드")
                .price(5000)
                .build();

        assertThatThrownBy(() -> productService.updateProduct(1000L, productData))
                .isInstanceOf(ProductNotFoundException.class);
    }

    @Test
    void deleteProductWithExistedId() {
        productService.deleteProduct(1L);

        verify(productRepository).delete(any(Product.class));
    }

    @Test
    void deleteProductWithNotExistedId() {
        assertThatThrownBy(() -> productService.deleteProduct(1000L))
                .isInstanceOf(ProductNotFoundException.class);
    }
}
