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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import site.cilicili.common.filter.JWTAuthFilter;
import site.cilicili.common.mvcConfig.WebMvcConfig;

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
    public SecurityFilterChain securityFilterChain(HttpSecurity http, WebMvcConfig webMvcConfig) throws Exception {
        http.cors(cors -> {
                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    // 同源配置，*表示任何请求都视为同源，若需指定ip和端口可以改为如“localhost：8080”，多个以“，”分隔；
                    corsConfiguration.addAllowedOrigin("*");
                    // header，允许哪些header，本案中使用的是token，此处可将*替换为token；
                    corsConfiguration.addAllowedHeader("*");
                    // 允许的请求方法，PSOT、GET等
                    corsConfiguration.addAllowedMethod("*");
                    corsConfiguration.setMaxAge(3600L);
                    // 配置允许跨域访问的url
                    source.registerCorsConfiguration("/**", corsConfiguration);
                    cors.configurationSource(source);
                })
                .csrf(CsrfConfigurer::disable)
                .formLogin(FormLoginConfigurer::disable)
                .authorizeHttpRequests(authorizeHttpRequests -> {
                    authorizeHttpRequests
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/public/**"))
                            .permitAll()
                            .requestMatchers(
                                    AntPathRequestMatcher.antMatcher(HttpMethod.GET, String.format("/%1$s/**", webMvcConfig.getAvatarRequestPath()).trim()),
                                    AntPathRequestMatcher.antMatcher(HttpMethod.GET, String.format("/%1$s/**", webMvcConfig.getBannerRequestPath()).trim()),
                                    AntPathRequestMatcher.antMatcher(HttpMethod.GET, String.format("/%1$s/**", webMvcConfig.getFaviconRequestPath()).trim()),
                                    AntPathRequestMatcher.antMatcher(HttpMethod.GET, String.format("/%1$s/**", webMvcConfig.getLogoRequestPath()).trim())
                            )
                            .permitAll()
                            .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/articles/**"))
                            .permitAll()
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**"))
                            .permitAll()
                            .anyRequest()
                            .authenticated();
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
