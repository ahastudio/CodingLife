package com.ahastudio.user.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.ahastudio.user.dtos.UserCreatedDto;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String email;

    private String password;

    public User() {
    }

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public boolean authenticate(PasswordEncoder passwordEncoder,
                                String password) {
        return passwordEncoder.matches(password, this.password);
    }

    public void changePassword(PasswordEncoder passwordEncoder,
                               String password) {
        this.password = passwordEncoder.encode(password);
    }

    public UserCreatedDto toCreatedDto() {
        return new UserCreatedDto(id, name, email);
    }
}
