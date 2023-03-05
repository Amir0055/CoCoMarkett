package com.example.cocomarket.Services;

import com.example.cocomarket.Entity.CART;
import com.example.cocomarket.Entity.Commande;
import com.example.cocomarket.Entity.Etat;
import com.example.cocomarket.Entity.User;
import com.example.cocomarket.Entity.Livraison;
import com.example.cocomarket.Interfaces.ICommande;
import com.example.cocomarket.Repository.Cart_Repository;
import com.example.cocomarket.Repository.Commande_Repository;
import com.example.cocomarket.Repository.User_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.cocomarket.Repository.Commande_Repository;
import com.example.cocomarket.Repository.Livraison_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

import java.util.List;

@Service
    public class Commande_Service implements ICommande {

    @Autowired
    Commande_Repository cr ;

    @Autowired
    User_Repository ur ;

    @Autowired
    Cart_Repository car ;

    @Override
    public Commande Confirmer_Commande(Commande commande,Integer idCart) {
        CART cart= car.findById(idCart).orElse(null);
        commande.setCommande_cart(cart);
        commande.setTotal_price(cart.getTotal_price());
        commande.setTotal_weight(cart.getTotal_weight());
        commande.setBuyer_email(cart.getUser().getEmail());
        commande.setShop_address(String.valueOf(cart.getUser().getShops()));
        commande.setNbProd(cart.getNbProd());
        commande.setTax(cart.getTotal_price()+18%-cart.getTotal_price());
       // commande.setShop_name(String.valueOf(cart.getUser()));
        return cr.save(commande);
    }


    @Override
    public List<Commande> Afficher_AllCommandes() {
        return  cr.findAll();

    }
public class Commande_Service implements ICommande {
    @Autowired
    Commande_Repository cr;
    @Autowired
    Livraison_Repository lr;
    @Override
    public Livraison affectercamandtolivaison(Integer id_c,Livraison l) {
        Commande c=cr.getnotaffectedCommand(id_c);
       //c.setLivraison_commande(l);
        return lr.save(l);

        }

    @Override
    public Commande Afficher_Commandes(Integer idCommande) {
        return cr.findById(idCommande).get();
    }

    /*@Override
    public Commande Modifier_Commande(Integer idCommande ) {
        Commande commande = cr.findById(idCommande).orElse(null);
        LocalDateTime currentTimeNow = LocalDateTime.now();
        LocalDateTime Limite = commande.getDateCmd().plusMinutes(420);
        if(currentTimeNow.isBefore(Limite)){
           return cr.save(commande);
        } else {
            throw new RuntimeException("La commande ne peut plus être modifiée.");
        }

    }*/

    @Override
    public void Annuler_Commande(Integer idCommande ) {
        Commande commande = cr.findById(idCommande).orElse(null);
        LocalDateTime currentTimeNow = LocalDateTime.now();
        LocalDateTime Limite = commande.getDateCmd().plusMinutes(420);
        if(currentTimeNow.isBefore(Limite)){
            cr.delete(commande);
        } else {
            throw new RuntimeException("La commande ne peut plus être annulée.");
        }
    }

    @Override
    public void send_order_to_moderator(Integer idCommande,Integer idUser) {

        Commande commande = cr.findById(idCommande).orElse(null);
        User user = ur.findById(idUser).orElse(null);

        LocalDateTime currentTimeNow = LocalDateTime.now();
        LocalDateTime Limit = commande.getDateCmd().plusMinutes(420);
        if (currentTimeNow.isAfter(Limit)) {
            System.out.println("commande envoyer au moderateur");
            commande.getCommande_cart().setUser(user);
            commande.setEtat(Etat.WAITING);
            commande.setArchive(true);
            cr.save(commande);
        }
        else {
            System.out.println("commande ne peut pas etre envoyer au moderateur maintenant");
        }
    }



    }







    }

