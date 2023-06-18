package com.example.demo.models;

import java.util.Objects;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import io.hypersistence.tsid.TSID;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class EntityId {
    @Column(name = "id")
    private String value;

    protected EntityId() {
    }

    protected EntityId(String value) {
        this.value = value;
    }

    protected static String newTsid() {
        return TSID.Factory.getTsid().toString();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EntityId other = (EntityId) o;
        return Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
