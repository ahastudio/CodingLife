package com.example.demo.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "carts")
public class Cart {
    @EmbeddedId
    private CartId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "user_id"))
    private UserId userId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cart_id")
    @OrderBy("id")
    private List<CartLineItem> lineItems = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private Cart() {
    }

    public Cart(UserId userId) {
        this.userId = userId;
    }

    public Cart(CartId id, UserId userId) {
        this.id = id;
        this.userId = userId;
    }

    public int lineItemSize() {
        return lineItems.size();
    }

    public CartLineItem lineItem(int index) {
        return lineItems.get(index);
    }

    public void addProduct(
            Product product, Set<CartLineItemOption> options, int quantity) {
        Optional<CartLineItem> found = findLineItem(product, options);

        if (found.isPresent()) {
            CartLineItem lineItem = found.orElseThrow();
            lineItem.increaseQuantity(quantity);
            return;
        }

        CartLineItem lineItem = createLineItem(product, options, quantity);
        lineItems.add(lineItem);
    }

    private Optional<CartLineItem> findLineItem(
            Product product, Set<CartLineItemOption> options) {
        return lineItems.stream()
                .filter(lineItem ->
                        lineItem.sameProduct(product.id(), options))
                .findFirst();
    }

    private static CartLineItem createLineItem(
            Product product, Set<CartLineItemOption> options, int quantity) {
        CartLineItemId id = CartLineItemId.generate();
        return new CartLineItem(id, product.id(), options, quantity);
    }
}
