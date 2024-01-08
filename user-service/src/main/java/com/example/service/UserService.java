package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.exception.UserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    @Autowired
    private UserRepository userRepository;

    public User create(User user) {
        logger.info("user: " + user.toString());
        if(user.getUsername() == null) {
            throw new UserException("Username cannot be null");
        }else if(user.getEmail() == null) {
            throw new UserException("Email cannot be null");
        }else if(user.getPassword() == null) {
            throw new UserException("Password cannot be null");
        }

        if(!pattern.matcher(user.getEmail()).matches()) {
            throw new UserException("Email not equal name@domain");
        }

        return userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        logger.info("email: " + email);

        if(!pattern.matcher(email).matches()) {
            throw new UserException("Email not equal name@domain");
        }

        User user = userRepository.findUserByEmail(email);
        if(user == null) {
            throw new UserException(String.format("User with email %s - not found", email));
        }
        return user;
    }
}