package com.ahastudio.user.application;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.ahastudio.user.exceptions.LoginFailed;
import com.ahastudio.user.models.User;
import com.ahastudio.user.respositories.UserRepository;
import com.ahastudio.user.utils.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthenticationService(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public String authenticate(String email, String password) {
        try {
            User user = userRepository.getByEmail(email);

            if (user == null || !user.authenticate(passwordEncoder, password)) {
                throw new LoginFailed();
            }

            return jwtUtil.encode(user.id());
        } catch (EntityNotFoundException exception) {
            throw new LoginFailed(exception);
        }
    }
}
