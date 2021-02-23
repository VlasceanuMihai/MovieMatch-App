package com.movieApp.movieMatchApp.services;

import com.movieApp.movieMatchApp.exceptions.UserNotFoundException;
import com.movieApp.movieMatchApp.models.User;
import com.movieApp.movieMatchApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AuthUserService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public AuthUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with username: " + email + " not found."));

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

//        return org.springframework.security.core.userdetails.User(
//                user.getEmail(),
//                user.getPassword().toLowerCase(),
//                enabled,
//                accountNonExpired,
//                credentialsNonExpired,
//                accountNonLocked,
//                getAuthorities(user.getRole().name()));
        return null;
    }

    private static GrantedAuthority getAuthorities(String role) {
        return new SimpleGrantedAuthority(role);
    }
}
