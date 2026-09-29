package com.lesson.memo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
            	//認証（ログイン）不要
                .requestMatchers("/css/**", "/js/**", "/admin/signup", "/admin/signin").permitAll()
                // 認証（ログイン）必要
                .anyRequest().authenticated()
            )
            //ログイン処理
            .formLogin(login -> login
                .loginPage("/admin/signin")
                .loginProcessingUrl("/admin/signin")
                .defaultSuccessUrl("/memo", true)
                .permitAll()
            )
            //ログアウト処理
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/admin/signin")
                .permitAll()
            );

        return http.build();
    }

    // パスワードエンコーダー（BCrypt）のBean定義
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

