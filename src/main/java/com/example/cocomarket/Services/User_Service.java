package com.example.cocomarket.Services;


import com.example.cocomarket.Entity.Commande;
import com.example.cocomarket.Entity.Etat;

import com.example.cocomarket.Interfaces.IUser;
import com.example.cocomarket.Repository.Cart_Repository;
import com.example.cocomarket.Repository.Commande_Repository;
import com.example.cocomarket.Repository.User_Repository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class User_Service implements IUser {

    @Autowired
    User_Repository ur ;

    @Autowired
    Cart_Repository cr ;
    @Autowired
    Commande_Repository comr ;

    @Override
    public void traiter_commande(Integer idcommande){
        Commande commande = comr.findById(idcommande).orElse(null);
        if(commande.getEtat()== Etat.WAITING){
            //accepter
            //send mail avec les details
            //affectation livreur
            commande.setEtat(Etat.VALIDATED);

            //refuser
            //send mail avec motif de refus
            commande.setEtat(Etat.REFUSED);
        }

    }

}
