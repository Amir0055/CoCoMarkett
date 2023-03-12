package com.example.cocomarket.Entity;

import java.time.LocalDate;
import lombok.*;

import javax.persistence.*;



@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Comentaire {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    private Integer id;
    private String commentaire;
    private LocalDate dateCmnt;


    public void setPublication(Publication publication) {
    }
}
