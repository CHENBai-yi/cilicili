package site.cilicili.common.util;

import cn.hutool.json.JSONUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import site.cilicili.common.filter.JWTAuthFilter;

import java.io.IOException;
import java.io.PrintWriter;
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
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        Instant now = Instant.now();
        Date exp = claims.getExpiration();
        return exp.after(Date.from(now));
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

    public String refreshJwt(String jwt, HttpServletResponse response) {
        return Optional.ofNullable(refreshToken(jwt))
                .map(newToken -> {
                    response.addHeader("Access-Control-Expose-Headers", "refresh-token");
                    response.addHeader("refresh-token", newToken);
                    response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    try (final PrintWriter writer = response.getWriter()) {
                        writer.write(JSONUtil.toJsonStr(R.yes("token已刷新").setData("refresh", true)));
                        writer.flush();
                    } catch (IOException ignored) {
                        logger.error("token刷新失败--->{}", this.getClass().toString());
                    }
                    return newToken;
                })
                .orElse(jwt);
    }
}
