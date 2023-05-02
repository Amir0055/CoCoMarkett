package com.example.cocomarket.Entity;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

 
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;



@Entity
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Produit {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    @ToString.Include
    private Integer id;
    private String Reference;
    @ToString.Include
    private String nom;
    @ToString.Include
    private String img;
    private String description;
    private Long prix;
    private Float weight;
    private Float volume ;
    private Boolean EtatsProduit;//mawjoud ou non
    @Enumerated(EnumType.STRING)
    private Status status;//ywefe9 3lih lbaye3 bch ybi3ou ou non//par default Null
    private LocalDate datePublication;
  //@JsonIgnore
   // @OneToMany(cascade = CascadeType.ALL)
   // private Set<Raiting_Product> raiting_prod;
  @JsonIgnore
  @ManyToMany(cascade = CascadeType.ALL)
    private Set<Catalogue> Catalogues;

@JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Categorie categorie;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Shop shop;
@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Raiting_Product> raiting_products ;

    //@OneToMany(cascade = CascadeType.ALL)
   // private Set<ImageData> image ;
    @OneToMany(mappedBy = "produit",cascade = CascadeType.ALL)
    private Set<ImageData> image ;


    @ManyToMany(mappedBy = "produits")
    private Set<Catalogue> catalogues = new HashSet<>();

    private Integer quantiteVendue;

    private Integer pourcentagePromotion;



    private Boolean isFlashSale;
    private Boolean isPromotion;
    private Float prixPromotion;
    // Constructeurs
    public Produit(String Reference, String nom, String img, String description, Long prix, Boolean EtatsProduit,
                   Status status, LocalDate datePublication, Set<Raiting_Product> raiting_prod, Categorie Categories,
                   Shop Shopes, Set<Catalogue> catalogues, Integer quantiteVendue, Integer pourcentagePromotion) {
        this.Reference = Reference;
        this.nom = nom;
        this.img = img;
        this.description = description;
        this.prix = prix;
        this.EtatsProduit = EtatsProduit;
        this.status = status;
        this.datePublication = datePublication;
        this.raiting_prod = raiting_prod;
        this.Categories = Categories;
        this.Shopes = Shopes;
        this.catalogues = catalogues;
        this.quantiteVendue = quantiteVendue;
        this.pourcentagePromotion = pourcentagePromotion;
    }


}
