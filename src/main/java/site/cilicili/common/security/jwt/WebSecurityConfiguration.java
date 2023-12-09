package site.cilicili.common.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import site.cilicili.common.filter.JWTAuthFilter;

/**
 * @author BaiYiChen
 */
@RequiredArgsConstructor
// @EnableWebSecurity(debug = true)
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@Configuration
public class WebSecurityConfiguration {

    private final JWTAuthFilter jwtAuthFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(CsrfConfigurer::disable)
                .formLogin(FormLoginConfigurer::disable)
                .authorizeHttpRequests(authorizeHttpRequests -> {
                    authorizeHttpRequests.requestMatchers(AntPathRequestMatcher.antMatcher("/public/**"))
                            .permitAll()
                            .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/articles/**"))
                            .permitAll()
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**"))
                            .permitAll()
                            .anyRequest().authenticated();
                })
                .exceptionHandling(exceptionHandling -> {
                    exceptionHandling.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
                })
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                // 在这里禁用X-Frame-Options,以便在网页上访问H2数据库
                .headers(headers -> {
                    headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable);
                });
        return http.build();
    }
}
