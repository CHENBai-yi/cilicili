package site.cilicili.authentication.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.authentication.user.dto.UserDto;
import site.cilicili.authentication.user.entity.UserEntity;

public interface UserService extends IService<UserEntity> {
    UserDto registration(final UserDto.Registration registration);

    UserDto login(final UserDto.Login login);

    UserDto currentUser(final AuthUserDetails authUserDetails);

    UserDto update(final UserDto.Update update, final AuthUserDetails authUserDetails);
}
