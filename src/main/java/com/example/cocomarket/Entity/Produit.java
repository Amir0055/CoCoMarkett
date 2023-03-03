package com.example.cocomarket.Entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;



@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Embeddable
public class Produit implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    private Integer id;
    private String Reference;
    private String nom;
    private String img;
    private String description;
    private Float prix;
    private Float weight;
    private Boolean EtatsProduit;//mawjoud ou non
    @Enumerated(EnumType.STRING)
    private Status status;//ywefe9 3lih lbaye3 bch ybi3ou ou non//par default Null
    private LocalDate datePublication;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Raiting_Product> raiting_prod;


    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Catalogue> Catalogues;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Categorie Categories;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produit")
    @JsonIgnore
    private Set<Produit_Cart> items;


    @ManyToOne
    @JsonIgnore
    private Shop Shopes;


   /* public void remove(Produit produit) {
        cart_product.remove(produit);
    }*/
}
