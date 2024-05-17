package site.cilicili.authentication.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.http.HttpHeaders;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.authentication.user.dto.UserDto;
import site.cilicili.authentication.user.entity.UserEntity;
import site.cilicili.backend.user.domain.dto.KickOnlineUserRequest;
import site.cilicili.common.util.R;

/**
 * @author BaiYiChen
 */
public interface UserService extends IService<UserEntity> {
    UserDto registration(final UserDto.Registration registration);

    R login(final UserDto.Login login, final HttpHeaders headers);

    UserDto currentUser(final AuthUserDetails authUserDetails);

    UserDto update(final UserDto.Update update, final AuthUserDetails authUserDetails);

    R logout(final AuthUserDetails authUserDetails, KickOnlineUserRequest kickOnlineUserRequest);

    R frontendRegistration(UserDto.FrontendRegistration registration);

    R getEmailCodeForReg(UserDto.GetEmailCode email);
}
