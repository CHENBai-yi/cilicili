package site.cilicili.authentication.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.authentication.user.dto.UserDto;
import site.cilicili.authentication.user.service.UserService;
import site.cilicili.backend.user.domain.dto.KickOnlineUserRequest;
import site.cilicili.common.util.R;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author BaiYiChen
 */
@RestController
@RequestMapping("public")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @PostMapping("reg")
    public UserDto registration(@RequestBody @Valid UserDto.Registration registration) {
        return userService.registration(registration);
    }

    @PostMapping("login")
    public R login(@RequestBody @Valid UserDto.Login login, @RequestHeader HttpHeaders headers, Errors errors) {
        return Optional.of(errors.getFieldErrors())
                .filter(e -> !e.isEmpty())
                .map(e -> R.no(e.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining())))
                .orElse(userService.login(login, headers));
    }

    @PostMapping("logout")
    public R logout(@AuthenticationPrincipal AuthUserDetails authUserDetails, final @RequestBody @Validated KickOnlineUserRequest kickOnlineUserRequest, Errors errors) {
        return Optional.of(errors.getFieldErrors())
                .filter(e -> !e.isEmpty())
                .map(e -> R.no(e.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining())))
                .orElse(this.userService.logout(authUserDetails, kickOnlineUserRequest));
    }

    @PostMapping("get-captcha")
    public R getCaptcha() {
        return R.yes("Success").setData("captcha_image", "");
    }
}
