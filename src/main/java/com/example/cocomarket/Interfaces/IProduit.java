package com.example.cocomarket.Interfaces;

import com.example.cocomarket.Entity.Produit;
import com.example.cocomarket.Entity.Raiting_Product;
import org.hibernate.mapping.List;

import java.util.Set;

public interface IProduit {

    Produit AddnewProduit (Produit Pr ) ;

    void AddProduitAffeASHopAndAffeAcategorie ( Integer idProduit, Integer idShop , Integer idCateg );

     void AddRaitingtoProduit(Integer idRaiting, Integer idProduit) ;
     String  SumRatting(Integer id , Integer id2);



}
