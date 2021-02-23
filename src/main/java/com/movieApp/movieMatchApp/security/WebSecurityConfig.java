package com.movieApp.movieMatchApp.security;

import com.movieApp.movieMatchApp.services.AuthUserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Profile("!https")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthUserService authUserService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public WebSecurityConfig(AuthUserService authUserService, PasswordEncoder passwordEncoder) {
        this.authUserService = authUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @SneakyThrows
    @Override
    protected void configure(HttpSecurity http) {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/api/v1/registration*").permitAll();
//                .anyRequest().authenticated().and().httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {

    }
}
