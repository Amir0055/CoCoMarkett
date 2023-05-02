package com.example.cocomarket.Repository;

import com.example.cocomarket.Entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Produit__Repository extends JpaRepository<Produit, Integer> {

    List<Produit> findTop50ByOrderByQuantiteVendueDesc();


    List<Produit> findTop10ByOrderByDatePublicationDesc();

    List<Produit> findByPourcentagePromotionGreaterThan(int i);

    //List<Produit> findByCatalogueId(Long catalogueId);



}

