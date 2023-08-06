package com.example.demo.controllers.admin;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.GetUserListService;
import com.example.demo.application.GetUserService;
import com.example.demo.dtos.UserDto;
import com.example.demo.dtos.admin.AdminUserListDto;
import com.example.demo.models.User;
import com.example.demo.models.UserId;
import com.example.demo.security.AdminRequired;
import com.example.demo.security.AuthUser;

@AdminRequired
@RestController
@RequestMapping("/admin/users")
public class AdminUserController {
    private final GetUserListService getUserListService;
    private final GetUserService getUserService;

    public AdminUserController(GetUserListService getUserListService,
                               GetUserService getUserService) {
        this.getUserListService = getUserListService;
        this.getUserService = getUserService;
    }

    @GetMapping
    public AdminUserListDto list() {
        List<User> users = getUserListService.getUserList();
        return AdminUserListDto.of(users);
    }

    @GetMapping("/me")
    public UserDto me(Authentication authentication) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        UserId id = new UserId(authUser.id());
        User user = getUserService.getAdminUser(id);
        return UserDto.of(user);
    }
}
