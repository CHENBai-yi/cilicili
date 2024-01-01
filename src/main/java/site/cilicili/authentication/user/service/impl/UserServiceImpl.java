package site.cilicili.authentication.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.authentication.user.dto.UserDto;
import site.cilicili.authentication.user.entity.UserEntity;
import site.cilicili.authentication.user.repository.UserRepository;
import site.cilicili.authentication.user.service.UserService;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.JwtUtils;

import java.util.Optional;

/**
 * @author BaiYiChen
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserRepository, UserEntity> implements UserService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto registration(final UserDto.Registration registration) {
        userRepository.findByUsernameOrEmail(registration.getUsername(), registration.getEmail()).stream()
                .findAny()
                .ifPresent(entity -> {
                    throw new AppException(Error.DUPLICATED_USER);
                });
        UserEntity userEntity = UserEntity.builder()
                .username(registration.getUsername())
                .email(registration.getEmail())
                .password(passwordEncoder.encode(registration.getPassword()))
                .avatar("")
                .build();
        userRepository.insert(userEntity);
        return convertEntityToDto(userEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto login(UserDto.Login login) {
        UserEntity userEntity = userRepository
                .findByUsername(login.getUsername())
                .filter(user -> passwordEncoder.matches(login.getPassword(), user.getPassword()))
                .orElseThrow(() -> new AppException(Error.LOGIN_INFO_INVALID));
        return convertEntityToDto(userEntity);
    }

    private UserDto convertEntityToDto(UserEntity userEntity) {
        return UserDto.builder()
                .username(userEntity.getUsername())
                .nickname(userEntity.getNickname())
                .avatar(userEntity.getAvatar())
                .realName(userEntity.getNickname())
                .token(jwtUtils.encode(userEntity.getUsername()))
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto currentUser(AuthUserDetails authUserDetails) {
        UserEntity userEntity = Optional.ofNullable(userRepository.selectById(authUserDetails.getId()))
                .orElseThrow(() -> new AppException(Error.USER_NOT_FOUND));
        return convertEntityToDto(userEntity);
    }

    @Override
    public UserDto update(UserDto.Update update, AuthUserDetails authUserDetails) {
        UserEntity userEntity = Optional.ofNullable(userRepository.selectById(authUserDetails.getId()))
                .orElseThrow(() -> new AppException(Error.USER_NOT_FOUND));

        if (update.getUsername() != null) {
            userRepository
                    .findByUsername(update.getUsername())
                    .filter(found -> !found.getId().equals(userEntity.getId()))
                    .ifPresent(found -> {
                        throw new AppException(Error.DUPLICATED_USER);
                    });
            userEntity.setUsername(update.getUsername());
        }

        if (update.getEmail() != null) {
            userRepository
                    .findByEmail(update.getEmail())
                    .filter(found -> !found.getId().equals(userEntity.getId()))
                    .ifPresent(found -> {
                        throw new AppException(Error.DUPLICATED_USER);
                    });
            userEntity.setEmail(update.getEmail());
        }

        if (update.getPassword() != null) {
            userEntity.setPassword(passwordEncoder.encode(update.getPassword()));
        }

        if (update.getBio() != null) {
            userEntity.setAvatar(update.getBio());
        }

        if (update.getImage() != null) {
            userEntity.setAvatar(update.getImage());
        }

        userRepository.insert(userEntity);
        return convertEntityToDto(userEntity);
    }
}
