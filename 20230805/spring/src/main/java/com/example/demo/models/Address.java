package com.example.demo.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class Address {
    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Embedded
    private PostalCode postalCode;

    private Address() {
    }

    public Address(String address1, String address2, PostalCode postalCode) {
        this.address1 = address1;
        this.address2 = address2;
        this.postalCode = postalCode;
    }

    public String address1() {
        return address1;
    }

    public String address2() {
        return address2;
    }

    public PostalCode postalCode() {
        return postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address other = (Address) o;
        return Objects.equals(address1, other.address1) &&
                Objects.equals(address2, other.address2) &&
                Objects.equals(postalCode, other.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address1, address2, postalCode);
    }
}
