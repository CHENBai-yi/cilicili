package site.cilicili.common.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site.cilicili.common.exception.AppExceptionHandler;
import site.cilicili.common.util.JwtUtils;

/**
 * @author BaiYiChen
 */
@Configuration
public class JwtUtilsConfiguration {

    @Bean
    public JwtUtils getJwtUtils(
            @Value("${cilicili.auth.token.sign-key}") String signKey,
            @Value("${cilicili.auth.token.valid-time}") Long validTime,
            @Value("${cilicili.auth.token.refresh-at}") Long refreshAt,
            AppExceptionHandler appExceptionHandler)
            throws Exception {
        if (signKey.length() < 32) {
            throw new Exception("signKey must have length at least 32");
        }
        return new JwtUtils(signKey, validTime, refreshAt, appExceptionHandler);
    }
}
