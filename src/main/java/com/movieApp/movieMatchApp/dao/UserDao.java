package com.movieApp.movieMatchApp.dao;

import com.googlecode.jmapper.JMapper;
import com.movieApp.movieMatchApp.dto.UserDto;
import com.movieApp.movieMatchApp.models.User;
import com.movieApp.movieMatchApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserDao {

    public static final JMapper<UserDto, User> TO_USER_DTO = new JMapper<>(UserDto.class, User.class);
    public static final JMapper<User, UserDto> TO_USER_ENTITY = new JMapper<>(User.class, UserDto.class);

    private UserRepository userRepository;

    @Autowired
    public UserDao(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public Optional<UserDto> findById(Long userId) {
        return this.userRepository.findById(userId).map(TO_USER_DTO::getDestination);
    }

    @Transactional(readOnly = true)
    public Optional<UserDto> findByEmail(String email) {
        return this.userRepository.findByEmail(email).map(TO_USER_DTO::getDestination);
    }

    @Transactional(readOnly = true)
    public Optional<UserDto> findByMobileNumber(String mobileNumber) {
        return this.userRepository.findByMobileNumber(mobileNumber).map(TO_USER_DTO::getDestination);
    }

    @Transactional
    public UserDto save(UserDto userDto) {
        User updatedUser = this.userRepository.save(TO_USER_ENTITY.getDestination(userDto));
        return TO_USER_DTO.getDestination(updatedUser);
    }

    @Transactional
    public void remove(Long userId) {
        this.userRepository.deleteById(userId);
    }
}
