package com.example.cocomarket.Controller;

import com.example.cocomarket.Entity.Reaction;
import com.example.cocomarket.Entity.User;
import com.example.cocomarket.Interfaces.IReaction;
import com.example.cocomarket.Services.User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/user")
public class User_controller {
    @Autowired
    private User_Service userService;
    @Autowired
    IReaction reactionService ;

    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }





}
