package com.movieApp.movieMatchApp.security;

import com.movieApp.movieMatchApp.models.User;
import com.movieApp.movieMatchApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoadUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    protected LoadUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by credential: " + username));

        return new UserPrincipal(
                user.getId(),
                username,
                user.getPassword(),
                user.getRole());
    }

//    protected void populateAuthorities(User user, UserPrincipal userPrincipal){
//        userPrincipal.set
//    }
}


