package org.example.webchamcongbe.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Tắt CSRF (thường dùng cho form, không cần khi test API)
                .csrf(csrf -> csrf.disable())

                // Cho phép tất cả request mà không cần đăng nhập
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )

                // Tắt form login mặc định
                .formLogin(login -> login.disable())

                // Tắt HTTP Basic Auth (nếu có)
                .httpBasic(basic -> basic.disable());

        return http.build();
    }
}
