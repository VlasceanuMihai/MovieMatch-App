package com.movieApp.movieMatchApp.security;

import com.movieApp.movieMatchApp.services.UserAuthService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Profile("!https")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserAuthService userAuthService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public WebSecurityConfig(UserAuthService userAuthService, PasswordEncoder passwordEncoder) {
        this.userAuthService = userAuthService;
        this.passwordEncoder = passwordEncoder;
    }

    @SneakyThrows
    @Override
    protected void configure(HttpSecurity http) {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/api/v1/registration*").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/api/v1/login*").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/api/v1/logout*").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/api/v1/movies*").permitAll();
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage("/login").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAuthService).passwordEncoder(passwordEncoder);
    }
}
