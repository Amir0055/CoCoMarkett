package com.example.cocomarket.Entity;

import java.time.LocalDate;
import java.util.Set;

import lombok.*;

import javax.persistence.*;



@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Produit {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    private Integer id;
    private String Reference;
    private String nom;
    private String img;
    private String description;
    private Float prix;
    private Boolean EtatsProduit;//mawjoud ou non
    @Enumerated(EnumType.STRING)
    private Status status;//ywefe9 3lih lbaye3 bch ybi3ou ou non//par default Null
    private LocalDate datePublication;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Raiting_Product> raiting_prod;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Catalogue> Catalogues;
    @ManyToOne(cascade = CascadeType.ALL)
    private Categorie Categories;

    @ManyToOne
    private Shop Shopes;

}
