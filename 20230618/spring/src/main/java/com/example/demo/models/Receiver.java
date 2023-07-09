package com.example.demo.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class Receiver {
    @Column(name = "receiver_name")
    private String name;

    @Embedded
    private Address address;

    @Embedded
    private PhoneNumber phoneNumber;

    private Receiver() {
    }

    public Receiver(String name, Address address, PhoneNumber phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Receiver other = (Receiver) o;
        return Objects.equals(name, other.name) &&
                Objects.equals(address, other.address) &&
                Objects.equals(phoneNumber, other.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, phoneNumber);
    }
}
