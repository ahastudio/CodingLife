package com.example.demo.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.demo.dtos.admin.AdminUpdateProductDto;

@Entity
@Table(name = "product_options")
public class ProductOption {
    @EmbeddedId
    private ProductOptionId id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_option_id")
    @OrderBy("id")
    private List<ProductOptionItem> items = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private ProductOption() {
    }

    public ProductOption(ProductOptionId id, String name,
                         List<ProductOptionItem> items) {
        this.id = id;
        this.name = name;
        this.items = new ArrayList<>(items);
    }

    public ProductOptionId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public int itemSize() {
        return items.size();
    }

    public ProductOptionItem item(int index) {
        return items.get(index);
    }

    public ProductOptionItem itemById(ProductOptionItemId itemId) {
        return items.stream()
                .filter(item -> Objects.equals(item.id(), itemId))
                .findFirst()
                .orElseThrow();
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void updateItems(List<AdminUpdateProductDto.OptionItemDto> items) {
        this.items.removeIf(item -> {
            String itemId = item.id().toString();
            return items.stream().noneMatch(i -> itemId.equals(i.id()));
        });

        items.forEach(item -> {
            if (item.id() == null) {
                this.items.add(new ProductOptionItem(
                        ProductOptionItemId.generate(),
                        item.name()
                ));
                return;
            }
            this.items.stream()
                    .filter(i -> i.id().toString().equals(item.id()))
                    .forEach(i -> i.changeName(item.name()));
        });
    }
}
