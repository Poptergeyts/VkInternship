package com.poptergeyts.vk.config;

import com.poptergeyts.vk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").anonymous()
                        .requestMatchers("/api/posts/**").hasAnyRole("ADMIN", "POSTS")
                        .requestMatchers("/api/users/**").hasAnyRole("ADMIN", "USERS")
                        .requestMatchers("/api/albums/**").hasAnyRole("ADMIN", "ALBUMS")
                        .anyRequest().authenticated())
                .formLogin(fl -> fl.
                        loginPage("/api/login")
                        .permitAll())
                .logout(lout -> lout
                        .permitAll()
                        .logoutSuccessUrl("/api"));
        return http.build();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }
}
