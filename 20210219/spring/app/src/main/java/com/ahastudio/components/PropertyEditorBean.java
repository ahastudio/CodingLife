package com.ahastudio.components;

import com.ahastudio.domain.FullName;
import com.ahastudio.propertyeditors.NamePropertyEditor;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;

public class PropertyEditorBean {
    private String name;
    private int age;
    private FullName fullName;

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void print() {
        System.out.println("name: " + name);
        System.out.println("age: " + age);
        System.out.println("full name: " + fullName);
    }

    public static class CustomPropertyEditorRegistrar
            implements PropertyEditorRegistrar {
        @Override
        public void registerCustomEditors(PropertyEditorRegistry registry) {
            registry.registerCustomEditor(String.class,
                    new StringTrimmerEditor(true));

//            registry.registerCustomEditor(FullName.class,
//                    new NamePropertyEditor());
        }
    }
}
