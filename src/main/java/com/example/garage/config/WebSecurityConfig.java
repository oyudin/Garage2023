package com.example.garage.config;

import com.example.garage.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.net.http.HttpRequest;

/**
 * Насетапить авторизацию для постман (не через form-data)
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

//    private final EncoderConfig encoderConfig;
//
//    @Autowired
//    public WebSecurityConfig(CustomUserDetailsService userDetailsService, EncoderConfig encoderConfig) {
//        this.userDetailsService = userDetailsService;
//        this.encoderConfig = encoderConfig;
//    }

    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((authorize) ->
//                                authorize
////                                .requestMatchers("/registration").permitAll()
////                                .requestMatchers("/carservice/**").hasAnyAuthority("ADMIN", "USER")
////                                .requestMatchers("/user**", "/users/**").hasAuthority("ADMIN")
////                                .requestMatchers("/carservice/**").permitAll()
////                                .anyRequest().authenticated()
////                                .and()
//                                        .anyRequest().permitAll()
//
//                ).formLogin(
//                        form -> form
//                                .loginPage("/login")
//                                .loginProcessingUrl("/login")
//                                .defaultSuccessUrl("/carservice/clients")
//                                .permitAll()
//                ).logout(
//                        logout -> logout
//                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                                .permitAll()
//                )
//                .csrf().disable() // Отключаем CSRF, если это API или тестовый режим
//                .headers().frameOptions().disable(); // Отключаем защиту от фреймов (если нужна)
//
//
////                .formLogin(
////                form -> form
////                        .loginPage("/login")
////                        .loginProcessingUrl("/login")
////                        .defaultSuccessUrl("/carservice/clients")
////                        .permitAll()
////        ).logout(
////                logout -> logout
////                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
////                        .permitAll()
//        return http.build();
//    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/carservice/clients/add",
                        "/carservice/clients/*/update",
                        "/carservice/clients/*/delete",
                        "/carservice/clients/*/cars/add",
                        "/carservice/clients/*/cars/*/update",
                        "/carservice/clients/*/cars/*/delete",
                        "/carservice/clients/*/cars/*/service-history/*/add",
                        "/carservice/clients/*/cars/*/service-history/*/update",
                        "/carservice/clients/*/cars/*/service-history/*/delete")
                .hasAnyAuthority("ADMIN")
                .requestMatchers("/login",
                        "/carservice/clients",
                        "/carservice/clients/*/cars",
                        "/carservice/clients/*/cars/*/service-history")
                .hasAnyAuthority("ADMIN", "USER")
                .and()

                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/carservice/clients")
                .permitAll()

                .and()
                .csrf().disable()  // Отключаем CSRF, если это API или тестовый режим
                .headers().frameOptions().disable();  // Отключаем защиту от фреймов (если нужна)


//        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//            http
//                    .authorizeHttpRequests()
//                    .anyRequest().permitAll() // Делаем все страницы доступными без авторизации
//
//                    .and()
//                    .csrf().disable()  // Отключаем CSRF (если API)
//                    .headers().frameOptions().disable();  // Отключаем защиту от фреймов (если нужно)

//            return http.build();

        return http.build();
    }
}