package com.ahastudio.user.application;

import javax.transaction.Transactional;

import com.ahastudio.user.dtos.UserRegistrationDto;
import com.ahastudio.user.models.User;
import com.ahastudio.user.respositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(UserRegistrationDto userRegistrationDto) {
        User user = new User(null,
                userRegistrationDto.getName(),
                userRegistrationDto.getEmail());
        user.changePassword(passwordEncoder, userRegistrationDto.getPassword());

        userRepository.save(user);

        return user;
    }
}
