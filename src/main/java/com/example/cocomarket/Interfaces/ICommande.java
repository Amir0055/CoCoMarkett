package com.example.cocomarket.Interfaces;

import com.example.cocomarket.Entity.Commande;

import java.util.List;

public interface ICommande {

    public Commande Confirmer_Commande(Commande commande,Integer idCart) ;

   // public Commande Modifier_Commande(Integer idCommande ) ;

    public void send_order_to_moderator(Integer idCommande,Integer idUser);

    public void Annuler_Commande(Integer idCommande) ;

    public List<Commande> Afficher_AllCommandes();

    public Commande Afficher_Commandes(Integer idCommande);


}
