package site.cilicili.authentication.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.cilicili.authentication.user.dto.UserDto;
import site.cilicili.authentication.user.service.UserService;
import site.cilicili.common.util.R;

/**
 * @author BaiYiChen
 */
@RestController
@RequestMapping("public")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @PostMapping
    public UserDto registration(@RequestBody @Valid UserDto.Registration registration) {
        return userService.registration(registration);
    }

    @PostMapping("login")
    public R login(@RequestBody @Valid UserDto.Login login) {
        return R.yes("登录成功.").setData(userService.login(login));
    }

}
