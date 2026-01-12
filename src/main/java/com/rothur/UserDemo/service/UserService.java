package com.rothur.UserDemo.service;

import com.rothur.UserDemo.entity.User;
import com.rothur.UserDemo.exception.UserNotFoundException;
import com.rothur.UserDemo.repo.UserRepo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAllUser(String sort){

        List<User> users = userRepo.findAll();

        if (sort.equals("salary")) {
            Collections.sort(users, (u1, u2) -> Double.compare(u1.getSalary(), u2.getSalary()));

        } else if (sort.equals("age")) {
            Collections.sort(users, (u1, u2) -> Double.compare(u1.getAge(), u2.getAge()));
        }

        return users;
    }
    @Cacheable(value = "user", key = "#id")
    public User getUserById(Long id) throws UserNotFoundException{

        User target;
        try {
            target = userRepo.getById(id);

        } catch (Exception e) {

            throw new UserNotFoundException(id);
        }
        return target;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Async
    public User saveUser(User user) throws UserNotFoundException {

        User target;
        try {
            target = userRepo.getById(user.getId());

        } catch (Exception e) {

            throw new UserNotFoundException(user.getId());
        }
        return userRepo.save(user);
    }

    @Async
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @CacheEvict
    public void deleteUser(Long id){

        userRepo.deleteById(id);
    }
}
