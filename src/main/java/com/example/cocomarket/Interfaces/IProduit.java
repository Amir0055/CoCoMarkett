package com.example.cocomarket.Interfaces;

import com.example.cocomarket.Entity.Produit;

import java.util.List;

public interface IProduit {

    Produit AddnewProduit (Produit Pr ) ;

    void AddProduitAffeASHopAndAffeAcategorie ( Integer idProduit, Integer idShop , Integer idCateg );

     void AddRaitingtoProduit(Integer idRaiting, Integer idProduit) ;
     String  SumRatting(Integer id , Integer id2);

    public List<Produit> getAllProduits() ;
    public List<Produit> Recomendation(Integer idproduit , Integer idCateg ) ;



}
