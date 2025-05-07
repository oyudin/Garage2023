//package com.example.garage.config;
//
//import com.example.garage.service.CustomUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig {
//
//    private final CustomUserDetailsService userDetailsService;
//
//    private final EncoderConfig encoderConfig;
//
//    @Autowired
//    public WebSecurityConfig(CustomUserDetailsService userDetailsService, EncoderConfig encoderConfig) {
//        this.userDetailsService = userDetailsService;
//        this.encoderConfig = encoderConfig;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((authorize) ->
//                        authorize
//                                .requestMatchers("/registration").permitAll()
//                                .requestMatchers("/garage/**").hasAnyAuthority("ADMIN", "USER")
//                                .requestMatchers("/user**", "/users/**").hasAuthority("ADMIN")
//                                .anyRequest().authenticated()
//                                .and()
//                ).formLogin(
//                        form -> form
//                                .loginPage("/login")
//                                .loginProcessingUrl("/login")
//                                .defaultSuccessUrl("/garage")
//                                .permitAll()
//                ).logout(
//                        logout -> logout
//                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                                .permitAll()
//                );
//        return http.build();
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(encoderConfig.passwordEncoder());
//    }
//}