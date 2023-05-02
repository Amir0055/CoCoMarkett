package com.example.cocomarket.Repository;

import com.example.cocomarket.Entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource


public interface Produit__Repository extends JpaRepository<Produit, Integer> , JpaSpecificationExecutor<Produit> {


/*
    @Query("select e from Produit e where e.raiting_products.id = :id  ")
    Set<Raiting_Product> retriveRaiting(Integer id);
*/
   /* @Query ( " select R from Raiting_Product R where (R.id=:idProd ) " )
            public Set<Raiting_Product> sumRaiting ( Integer idProd ) ;

           */
  /*  @Query ( " select p from Produit  where (R.=:idProd) " )
    public Set<Raiting_Product> sumRaiting (Integer idProd ) ;
*/
    Produit findByCategorie ( Integer idCategtorie) ;



}
