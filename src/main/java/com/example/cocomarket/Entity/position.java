package com.example.cocomarket.Entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
public class position {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Long latitude;
    private Long longitude;


    @OneToOne
    private Shop shop;

    protected position() {}

    public position(long latitude,long  longitude , Shop shop) {
        this.latitude = latitude;
        this.longitude = longitude;
     //   this.shop = shop;
    }

    // Standard Getters and Setters
}

