package com.example.cocomarket.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Reclamations {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    private Integer id;

    private String Description;

    private String Response;

    private Boolean Traiter;//par default 0 ela ma ybadelha l'ADMIN

    private ReclamaType TypeReclamation;
}
