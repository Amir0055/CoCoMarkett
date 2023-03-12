package com.example.cocomarket.Interfaces;

import com.example.cocomarket.Entity.User;

public interface IUser {

    User addUser(User user);

    User getUserById(Integer id);
}
