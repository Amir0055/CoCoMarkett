package com.example.cocomarket.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contrat {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    private Integer id;
    private Float commision;
    private String description;
    private LocalDate dateDebutContrat;
    private LocalDate dateFinContrat;
    private Boolean archive;
    @JsonIgnore
    @OneToMany(mappedBy = "Contrat_shop",cascade = CascadeType.ALL)
    private Set<Shop> Shopes_Contrats;

}
