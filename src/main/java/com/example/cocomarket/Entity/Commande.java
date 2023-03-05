package com.example.cocomarket.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Commande {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    private Integer id;

    private LocalDateTime dateCmd;

    private String shop_name;
    private String shop_address;

    private String buyer_email;
    private String buyer_address;

    private Float tax;

    private Integer nbProd;
    private Float total_price;
    private float total_weight ;

    private Boolean archive;

    private String description;

    @Enumerated(EnumType.STRING)
    private Etat etat;//1 bch twali livraison

    @Enumerated(EnumType.STRING)
    private Payment_Mode payment_mode;//1 bch twali livraison



    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private CART Commande_cart;
@ManyToMany(cascade = CascadeType.ALL)
    private Set<Livraison> Livraison_commande;


   /* @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private Livraison Livraison_commande;*/


}
