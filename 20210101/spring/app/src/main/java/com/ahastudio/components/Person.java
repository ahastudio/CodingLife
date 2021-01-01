package com.ahastudio.components;

public class Person {
    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "PERSON: " + name + "(" + age + ")";
    }
}
