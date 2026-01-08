package com.rothur.UserDemo.controller;

import com.rothur.UserDemo.entity.User;
import com.rothur.UserDemo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/search")
    public List<User> getAllUser(){

        return userService.getAllUser();
    }

    @GetMapping("/search/{id}")
    public User getUser(@PathVariable String id){

        return userService.getUserById(Long.valueOf(id));
    }

    @GetMapping("/")
    public String index(){

        return "Hello world";
    }

    @PostMapping("/create")
    public User createNewUser(User user){

        return userService.saveUser(user);
    }

    @PutMapping("/update")
    public User updateUser(User user){

        return userService.saveUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable String id){

        userService.deleteUser(Long.valueOf(id));
    }
}
