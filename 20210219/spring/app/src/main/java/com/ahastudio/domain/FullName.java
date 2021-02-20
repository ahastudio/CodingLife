package com.ahastudio.domain;

public class FullName {
    private String firstName;
    private String lastName;

    public FullName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String toString() {
        return "<< " + firstName + " " + lastName + " >>";
    }
}
