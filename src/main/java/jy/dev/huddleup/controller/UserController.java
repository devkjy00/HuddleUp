package jy.dev.huddleup.controller;

import jy.dev.huddleup.dto.UserResponseDto;
import jy.dev.huddleup.model.User;
import jy.dev.huddleup.security.UserDetailsImpl;
import jy.dev.huddleup.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{userId}")
    public UserResponseDto getUser(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long userId) {

        User user = userService.getUserInfo(userId);
        return new UserResponseDto(user);
    }
}
