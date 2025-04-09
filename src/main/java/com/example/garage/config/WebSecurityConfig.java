package com.example.garage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                // Администратор имеет доступ ко всем специфическим маршрутам
                                .requestMatchers(
                                        "/carservice/clients",
                                        "/carservice/clients/add",
                                        "/carservice/clients/*/update",
                                        "/carservice/clients/*/delete",
                                        "/carservice/clients/*/cars/add",
                                        "/carservice/clients/*/cars/*/update",
                                        "/carservice/clients/*/cars/*/delete",
                                        "/carservice/clients/*/cars/*/service-history/*/add",
                                        "/carservice/clients/*/cars/*/service-history/*/update",
                                        "/carservice/clients/*/cars/*/service-history/*/delete"
                                ).hasAuthority("ADMIN")
                                // Для пользователей и администраторов доступ к основным страницам
                                .requestMatchers(
                                        "/login",
                                        "/carservice/clients",
                                        "/carservice/clients/*/cars",
                                        "/carservice/clients/*/cars/*/service-history"
                                ).hasAnyAuthority("ADMIN", "USER")
                                // Все остальные маршруты требуют аутентификации
                                .anyRequest().authenticated()
                )
                // Включаем Basic Authentication
                .httpBasic()
                .and()
                .formLogin()  // Если вы хотите оставить возможность form login
                .loginPage("/login")
                .defaultSuccessUrl("/carservice/clients", true) // Страница по умолчанию после успешного входа
                .permitAll()
                .and()
                .csrf().disable();  // Отключаем CSRF, если используем Postman для тестов

        return http.build();
    }
}
