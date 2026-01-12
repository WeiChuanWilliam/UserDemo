package com.rothur.UserDemo.controller;

import com.rothur.UserDemo.entity.User;
import com.rothur.UserDemo.exception.ApplicationException;
import com.rothur.UserDemo.exception.UserNotFoundException;
import com.rothur.UserDemo.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/search")
    public List<User> getAllUser(@RequestParam(required = false, defaultValue = "salary") String sort){

        return userService.getAllUser(sort);
    }

    @GetMapping("/search/{id}")
    public User getUser(@PathVariable String id){
        System.out.println("get user controller called");

        User result = userService.getUserById(Long.valueOf(id));
        System.out.println("return user " + result.toString());
        return result;
    }

    @GetMapping("/")
    public String index(){

        return "Hello world";
    }

    @PostMapping("/create")
    public ResponseEntity<User> createNewUser(@Valid User user){

        User created = userService.saveUser(user);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@Valid User user){

        User updated = userService.saveUser(user);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable String id){

        userService.deleteUser(Long.valueOf(id));
    }

    @ExceptionHandler({UserNotFoundException.class, EntityNotFoundException.class})
    public ResponseEntity<ApplicationException> exceptionHandlerUserNotFound(Exception ex) {
        ApplicationException error = new ApplicationException(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
