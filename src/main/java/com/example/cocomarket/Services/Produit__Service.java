package com.example.cocomarket.Services;

import com.example.cocomarket.Entity.Categorie;
import com.example.cocomarket.Entity.Produit;
import com.example.cocomarket.Entity.Raiting_Product;
import com.example.cocomarket.Entity.Shop;
import com.example.cocomarket.Interfaces.IProduit;
import com.example.cocomarket.Repository.Categorie_Repository;
import com.example.cocomarket.Repository.Produit__Repository;
import com.example.cocomarket.Repository.Rainting_Product_Repository;
import com.example.cocomarket.Repository.Shop_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Produit__Service implements IProduit {
@Autowired
    Produit__Repository prorepo ;

@Autowired
    Categorie_Repository cr ;

@Autowired
    Shop_Repository Shoprepo ;

    @Autowired
    Rainting_Product_Repository raitingProduitrepo ;


    @Override
    public Produit AddnewProduit(Produit Pr) {
        return prorepo.save(Pr);
    }
    @Override
    public void AddProduitAffeASHopAndAffeAcategorie(Integer idProduit, Integer idShop, Integer idCateg) {
        Produit produit = prorepo.findById(idProduit).orElse(null) ;
        Shop shop = Shoprepo.findById(idShop).orElse(null);
        Categorie categorie = cr.findById(idCateg).orElse(null);
        produit.setCategorie(categorie);
        produit.setShop(shop);
        prorepo.save(produit);

    }


    @Override
    public void AddRaitingtoProduit(Integer idrainting ,Integer idProduit) {

        Produit produit = prorepo.findById(idProduit).orElse(null) ;
        Raiting_Product R = raitingProduitrepo.findById(idrainting).orElse(null) ;
        //produit.getRaiting_products().add(R) ;
        System.out.println("♦♦♦♦♦♦♦♦♦♦"+R);
        System.out.println("♦♦♦♦♦♦♦♦♦♦"+produit);
        produit.getRaiting_products().add(R) ;
        prorepo.save(produit) ;

 }



    @Override
    public String SumRatting(Integer id , Integer id2 ) {

        int a=0;
        int b=0 ;
      Produit p=  prorepo.findById(id).orElse(null);
      Produit p2= prorepo.findById(id2).orElse(null) ;

      for (Raiting_Product j: p2.getRaiting_products()) {
          b += j.getScore();
      }

      for(Raiting_Product i:p.getRaiting_products()){
          a+=i.getScore();
      }
        System.out.println("♦♦♦♦♦♦♦♦♦♦"+b);

        if ( a>b )



        return ("Produit 1 Better than B " )
                ;
      else
          return (" Produit 2 Better than Produit 1 ") ;

    }

    ////////
    public List<Produit> Recomendation(Integer idproduit , Integer idCateg ) {
        List<Produit> p= prorepo.findAll();
        List<Produit> p2 = new ArrayList<>();

        Categorie cate=  cr.findById(idCateg).orElse(null) ;
        for ( Produit i : p )
        { if ( i.getCategorie()==cate )
        p2.add(i) ;

        }
        return p2;

    }




    public List<Produit> getAllProduits() {
        return prorepo.findAll();
    }


/*
    @Override
    public Set<Produit> RetriveAllProd() {
        List<Produit> Produits =  prorepo.findAll();
        Set<Produit> Produit1 = new HashSet<>();

        for (Produit s: Produits){
            for (Raiting_Product Raite:  s.getRaiting_products()) {
                if ( s.getRaiting_products().size() !=0 && Raite.getId()== ){
            float moy = prorepo.

                }
            }
        }*/
      /*  public List<Produit> getProduits() {
            List<Produit> produits = announcementRepository.retrieveAnnouncements();
            List<Announcement> announcements1 = new ArrayList<>();
            for (Announcement a: announcements) {
                if(announcementRepository.retrieveRatings(a.getId_announcement()).size() != 0)
                    float moy = announcementRepository.retrieveSumRatings(a.getId_announcement())/announcementRepository.retrieveRatings(a.getId_announcement()).size();
                a.setRatmoyenne(moy); announcementRepository.save(a);
            }else
            {a.setRatmoyenne(0);
                announcementRepository.save(a);
                //    if(a.getBoosted()) announcements1.add(0,a);
            } else  announcements1.add(a);
        }
        return announcements1;

    }


        return
        Shoprepo.findAll();*/



}
