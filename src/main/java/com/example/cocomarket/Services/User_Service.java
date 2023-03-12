package com.example.cocomarket.Services;

import com.example.cocomarket.Entity.Livraison;
import com.example.cocomarket.Entity.User;
import com.example.cocomarket.Interfaces.IUser;
import com.example.cocomarket.Repository.Livraison_Repository;
import com.example.cocomarket.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class User_Service implements IUser {
    @Autowired
    UserRepository ur;
    @Autowired
    Livraison_Repository lr;


    @Override
    public void assignusertodelivery(Integer LId, Integer UId) {
        User u=ur.findById(UId).orElse(null);
        Livraison l=lr.findById(LId).orElse(null);
        u.getLivraisons().add(l);
        ur.save(u);

    }
}
