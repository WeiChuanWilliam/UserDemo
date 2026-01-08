package com.rothur.UserDemo.service;

import com.rothur.UserDemo.entity.User;
import com.rothur.UserDemo.repo.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAllUser(){

        return userRepo.findAll();
    }

    public User getUserById(Long id){

        return userRepo.getById(id);
    }

    public User saveUser(User user){

        return userRepo.save(user);
    }

    public void deleteUser(Long id){

        userRepo.deleteById(id);
    }
}
