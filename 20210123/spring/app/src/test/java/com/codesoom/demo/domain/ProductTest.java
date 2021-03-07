package com.codesoom.demo.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {
    @Test
    void creationWithBuilder() {
        Product product = Product.builder()
                .id(1L)
                .name("쥐돌이")
                .maker("냥이월드")
                .price(5000)
                .build();

        assertThat(product.getId()).isEqualTo(1L);
        assertThat(product.getName()).isEqualTo("쥐돌이");
        assertThat(product.getMaker()).isEqualTo("냥이월드");
        assertThat(product.getPrice()).isEqualTo(5000);
        assertThat(product.getImageUrl()).isNull();
    }

    @Test
    void changeWith() {
        Product product = Product.builder()
                .id(1L)
                .name("쥐돌이")
                .maker("냥이월드")
                .price(5000)
                .build();

        product.changeWith(Product.builder()
                .name("쥐순이")
                .maker("코드숨")
                .price(10000)
                .build());

        assertThat(product.getName()).isEqualTo("쥐순이");
        assertThat(product.getMaker()).isEqualTo("코드숨");
        assertThat(product.getPrice()).isEqualTo(10000);
    }
}
