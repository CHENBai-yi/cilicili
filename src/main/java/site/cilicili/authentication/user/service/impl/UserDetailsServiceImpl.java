package site.cilicili.authentication.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.authentication.user.repository.UserRepository;
import site.cilicili.common.exception.Error;

/**
 * @author BaiYiChen
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final StringRedisTemplate stringRedisTemplate;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByOnlineUsername(username)
                .filter(userEntity -> stringRedisTemplate.hasKey(username))
                .map(userEntity -> AuthUserDetails.builder()
                        .id(userEntity.getId())
                        .username(userEntity.getUsername())
                        .roleCode(userEntity.getRoleCode())
                        .realName(userEntity.getRealName())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException(Error.KICKED_USER.getMessage()));
    }
}
