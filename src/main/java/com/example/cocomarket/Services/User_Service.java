package com.example.cocomarket.Services;

import com.example.cocomarket.Entity.User;
import com.example.cocomarket.Interfaces.IUser;
import com.example.cocomarket.Repository.User_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class User_Service implements IUser {
    @Autowired
    User_Repository userRepository;
@Override
    public User addUser(User user) {
        return userRepository.save(user);
    }
@Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
}
