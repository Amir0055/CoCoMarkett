package com.example.cocomarket.Entity;

<<<<<<< Updated upstream
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
=======
 import com.fasterxml.jackson.annotation.JsonIgnore;
 import com.sun.istack.NotNull;
 import lombok.*;
>>>>>>> Stashed changes
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
 import java.util.*;

@Entity
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    @ToString.Include
    private Integer id;
    @ToString.Include
    private String last_name;
    @ToString.Include
    private String first_name;
    private Boolean premium;//par default 0
    @ToString.Include
    private String email;

    private Date sleep_time;
    @NotNull
    @ToString.Include
    private String password;
    private Float loyalty_point;
    private String Assosiation_info;
    private String Files;
    @Lob
    @ToString.Include
    private String img;
    private String region;
    private Boolean enabled;
    private Integer nbr_tentatives;
    private Boolean availability;
<<<<<<< Updated upstream
    @OneToMany(mappedBy = "userQuestions" ,cascade = CascadeType.ALL)
    private Set<Question> Questions;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Response> Responses;
=======
    @OneToMany(mappedBy = "userPublication" ,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Publication> publications;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Comentaire> Respons;
>>>>>>> Stashed changes
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<MSG> MSGs;
    @OneToMany(mappedBy = "userShop")
    @JsonIgnore
    private Set<Shop> Shops;
    @OneToMany(mappedBy = "userKoffa")
    @JsonIgnore
    private Set<Koffa> Koffas;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private CART cart;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Raiting_Product> raiting_products;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Vehicule car;

    @OneToMany(mappedBy = "UserAuth" ,fetch = FetchType.EAGER)
    @ToString.Include
    @JsonIgnore
    private Set<Autority> autority;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>() ;
        for (Autority authority : autority) {
            if (authority !=null)
                authorities.add(new SimpleGrantedAuthority(authority.getName()));
            else
                System.out.println("----- U have no AUtority Bro ----");
        }
        return authorities;
    }
    public Set<Autority> getAuthFromBase(){
        return this.autority;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }








}
