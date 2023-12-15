package site.cilicili.common.filter;

import cn.hutool.json.JSONUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import site.cilicili.common.security.jwt.AuthenticationProvider;
import site.cilicili.common.util.JwtUtils;
import site.cilicili.common.util.R;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * @author BaiYiChen
 */
@Component
@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {
    public static final String TOKEN_PREFIX = "Token ";
    private final JwtUtils jwtUtils;
    private final AuthenticationProvider authenticationProvider;
    private final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException, ExpiredJwtException {
        try {
            Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                    .filter(authHeader -> authHeader.startsWith(TOKEN_PREFIX))
                    .map(authHeader -> jwtUtils.refreshJwt(authHeader.substring(TOKEN_PREFIX.length()), response))
                    .filter(jwtUtils::validateToken)
                    .map(jwtUtils::getSub)
                    .map(authenticationProvider::getAuthentication)
                    .ifPresent(SecurityContextHolder.getContext()::setAuthentication);
            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_OK);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            try (final PrintWriter writer = response.getWriter()) {
                writer.write(JSONUtil.toJsonStr(R.no("token已失效，登录过期").setData("reload", true)));
                writer.flush();
            } catch (IOException exception) {
                logger.error("token过期--->{}", e.getMessage());
            }
        }
    }
}