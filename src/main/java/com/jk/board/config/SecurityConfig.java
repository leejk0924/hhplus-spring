package com.jk.board.config;

import com.jk.board.dto.UserDto;
import com.jk.board.dto.security.JwtAuthenticationFilter;
import com.jk.board.dto.security.UserPrincipal;
import com.jk.board.repository.UserRepository;
import com.jk.board.service.TokenService;
import com.jk.board.utils.JwtHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final TokenService tokenService;
    private final JwtHelper jwtHelper;
    @Bean
    @Order(1)
    public SecurityFilterChain permitLoginChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher("/login", "/register")
                .authorizeHttpRequests(a -> a.anyRequest().permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .build();
    }
    @Bean
    @Order(2)
    public SecurityFilterChain authorizationChain(
            HttpSecurity http,
            JwtAuthenticationFilter jwtAuthFilter
    ) throws Exception {
        return http
                .authorizeHttpRequests(a -> a
                        .requestMatchers(HttpMethod.GET, "/", "/articles/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/articles/**")
                        .hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )

                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(tokenService, jwtHelper);
    }
    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> disableAutoRegister(JwtAuthenticationFilter filter) {
        FilterRegistrationBean<JwtAuthenticationFilter> reg = new FilterRegistrationBean<>(filter);
        // 필터 글로벌 등록 방지
        reg.setEnabled(false);
        return reg;
    }
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> userRepository
                .findByUsername(username)
                .map(UserDto::from)
                .map(UserPrincipal::from)
                .orElseThrow(() -> new UsernameNotFoundException("username을 찾을 수 없습니다. - username : " + username));
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
