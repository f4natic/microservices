package com.example.controller;

import com.example.model.ExceptionMessage;
import com.example.model.User;
import com.example.service.UserService;
import com.example.service.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{email}")
    public User findUserByEmail(@PathVariable String email) {
        return userService.findUserByEmail(email);
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userService.create(user);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserException.class)
    @ResponseBody
    ExceptionMessage handleBadRequest(Exception exception) {
        return new ExceptionMessage(exception.getMessage());
    }
}
