package site.cilicili.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.AppExceptionHandler;
import site.cilicili.common.filter.JWTAuthFilter;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

/**
 * @author BaiYiChen
 */
public class JwtUtils {
    private final Long validSeconds;
    private final Key key;
    private final Long refreshAt;
    private final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    private AppExceptionHandler appExceptionHandler;

    public JwtUtils(String signKey, Long validSeconds, Long refreshAt, final AppExceptionHandler appExceptionHandler) {
        this.validSeconds = validSeconds;
        this.refreshAt = refreshAt;
        key = Keys.hmacShaKeyFor(signKey.getBytes(StandardCharsets.UTF_8));
        this.appExceptionHandler = appExceptionHandler;
    }

    public JwtUtils(String signKey, Long validSeconds, Long refreshAt) {
        this.validSeconds = validSeconds;
        this.refreshAt = refreshAt;
        key = Keys.hmacShaKeyFor(signKey.getBytes(StandardCharsets.UTF_8));
    }

    public String encode(String sub) {
        if (sub == null || "".equals(sub)) {
            return null;
        }
        Instant exp = Instant.now();
        final Date exp1 = new Date(exp.toEpochMilli() + validSeconds * 1000);
        System.out.println(exp1.getTime());
        return JWTAuthFilter.TOKEN_PREFIX
                + Jwts.builder()
                .setSubject(sub)
                .claim("RefreshAt", new Date(exp.toEpochMilli() + (validSeconds - refreshAt) * 1000))
                .setIssuedAt(new Date(exp.toEpochMilli()))
                .setExpiration(exp1)
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String jwt) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
            Instant now = Instant.now();
            Date exp = claims.getExpiration();
            return exp.after(Date.from(now));
        } catch (JwtException e) {
            throw new JwtException(JWTAuthFilter.TOKEN_PREFIX + jwt);
        }
    }

    public String refreshToken(String jwt) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
            return Optional.ofNullable(claims.get("RefreshAt"))
                    .map(obj -> {
                        final Date date = new Date(Long.parseLong(obj + ""));
                        Date exp = claims.getExpiration();
                        final Date now = Date.from(Instant.now());
                        if (exp.after(now) && date.before(now)) {
                            return encode(getSub(jwt));
                        }
                        return null;
                    })
                    .orElse(null);
        } catch (JwtException e) {
            return null;
        }
    }

    public String getSub(String jwt) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
            return claims.getSubject();
        } catch (JwtException e) {
            return null;
        }
    }

    public String refreshJwt(String jwt) {
        return Optional.ofNullable(refreshToken(jwt))
                .map(newToken -> {
                    appExceptionHandler.handleAppException(new AppException.RefreshTokenException(newToken));
                    return newToken;
                })
                .orElse(jwt);
    }
}
