package com.codesoom.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Builder.Default
    private String email = "";

    @Builder.Default
    private String name = "";

    @Builder.Default
    private String password = "";

    @Builder.Default
    private boolean deleted = false;

    public void changeWith(User source) {
        name = source.name;
    }

    public void changePassword(String password,
                               PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    public void destroy() {
        deleted = true;
    }

    public boolean authenticate(String password,
                                PasswordEncoder passwordEncoder) {
        return !deleted && passwordEncoder.matches(password, this.password);
    }
}
