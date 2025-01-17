package site.cilicili.common.filter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import site.cilicili.common.config.dynamicDb.dataSource.DbInitialization;
import site.cilicili.common.exception.AppExceptionHandler;
import site.cilicili.common.security.jwt.AuthenticationProvider;
import site.cilicili.common.util.JwtUtils;

import java.util.Optional;

/**
 * @author BaiYiChen
 */
@Component
@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {
    public static final String TOKEN_PREFIX = "Token ";
    public static final String TOKEN_KEY = "Cili-Token";
    private final JwtUtils jwtUtils;
    private final AuthenticationProvider authenticationProvider;
    private final Logger logger = LoggerFactory.getLogger(JWTAuthFilter.class);
    private final AppExceptionHandler appExceptionHandler;
    private final DbInitialization dbInitialization;

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain)
            throws ExpiredJwtException {
        try {
            final String token = Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                    .orElse(request.getHeader(TOKEN_KEY));
            Optional.ofNullable(token)
                    .filter(authHeader -> authHeader.startsWith(TOKEN_PREFIX) && dbInitialization.isValid())
                    .map(authHeader -> authHeader.substring(TOKEN_PREFIX.length()))
                    .map(jwtUtils::refreshJwt)
                    .filter(jwtUtils::validateToken)
                    .map(jwtUtils::getSub)
                    .map(authenticationProvider::getAuthentication)
                    .ifPresent(SecurityContextHolder.getContext()::setAuthentication);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            logger.error(e.getMessage());
            appExceptionHandler.handleAppException(e);
        }
    }
}
