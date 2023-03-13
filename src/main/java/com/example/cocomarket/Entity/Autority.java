package com.example.cocomarket.Entity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Autority {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    private Integer id;
    @ToString.Include
    private String name;
    // @ManyToMany(mappedBy = "autoritys")
    // private Set<Roles> RolesAutority;
    @ManyToOne
  //  @JsonIgnore
    private User UserAuth;

}
