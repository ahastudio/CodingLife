package com.example.demo.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "cart_line_items")
public class CartLineItem {
    @EmbeddedId
    private CartLineItemId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "product_id"))
    private ProductId productId;

    @ElementCollection
    @CollectionTable(
            name = "cart_line_item_options",
            joinColumns = @JoinColumn(name = "cart_line_item_id")
    )
    private Set<CartLineItemOption> options = new HashSet<>();

    @Column(name = "quantity")
    private int quantity = 0;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private CartLineItem() {
    }

    public CartLineItem(CartLineItemId id,
                        ProductId productId,
                        Set<CartLineItemOption> cartOptionItems,
                        int quantity) {
        this.id = id;
        this.productId = productId;
        this.options = cartOptionItems;
        this.quantity = quantity;
    }

    public CartLineItemId id() {
        return id;
    }

    public ProductId productId() {
        return productId;
    }

    public int optionSize() {
        return options.size();
    }

    public List<ProductOptionId> optionIds() {
        return options.stream().map(CartLineItemOption::optionId).toList();
    }

    public ProductOptionItemId optionItemId(ProductOptionId optionId) {
        return options.stream()
                .filter(cartOption -> cartOption.optionId().equals(optionId))
                .map(CartLineItemOption::optionItemId)
                .findFirst()
                .orElseThrow();
    }

    public int quantity() {
        return quantity;
    }

    public boolean sameProduct(ProductId productId,
                               Set<CartLineItemOption> cartOptionItems) {
        return this.productId.equals(productId) &&
                this.options.equals(cartOptionItems);
    }

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }
}
