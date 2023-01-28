package com.example.garage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final PasswordEncoder passwordEncoder;
    public WebSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception {
        https
                .formLogin()
                .and()
                .authorizeHttpRequests((requests) -> requests
                        .anyRequest().authenticated())
                .logout((logout) -> logout.permitAll());
//                .logout().logoutUrl("/logout");
//                .logout().permitAll();
//                .logout(LogoutConfigurer::permitAll);
        return https.build();
    }

    @Bean
    public UserDetailsService userDetailsService() { // преобразовать в процесс регистрации пользователя
        UserDetails user =
                User.withUsername("admin")
                        .password(passwordEncoder.encode("password")
                        ).roles("ADMIN")
                        .build();
        return new InMemoryUserDetailsManager(user);
    }
}
