package site.cilicili.common.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author BaiYiChen
 */
@Component
@RequiredArgsConstructor
public class AuthenticationProvider {
    private final UserDetailsService userDetailsService;

    public Authentication getAuthentication(String username) {
        return Optional.ofNullable(username)
                .map(userDetailsService::loadUserByUsername)
                .map(userDetails -> UsernamePasswordAuthenticationToken.authenticated(
                        userDetails, userDetails.getPassword(), userDetails.getAuthorities()))
                .orElse(null);
    }
}
