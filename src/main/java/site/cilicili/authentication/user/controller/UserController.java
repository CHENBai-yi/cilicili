package site.cilicili.authentication.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.authentication.user.dto.UserDto;
import site.cilicili.authentication.user.service.UserService;

/**
 * @author BaiYiChen
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public UserDto currentUser(@AuthenticationPrincipal AuthUserDetails authUserDetails) {
        return userService.currentUser(authUserDetails);
    }

    @PutMapping
    public UserDto update(
            @Valid @RequestBody UserDto.Update update,
            @AuthenticationPrincipal AuthUserDetails authUserDetails
    ) {
        return userService.update(update, authUserDetails);
    }
}