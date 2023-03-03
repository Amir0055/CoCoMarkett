package com.example.cocomarket.Repository;

import com.example.cocomarket.Entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface Commande_Repository extends JpaRepository<Commande, Integer> {

}
