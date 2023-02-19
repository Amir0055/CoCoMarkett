package com.example.cocomarket.Repository;

import com.example.cocomarket.Entity.Catalogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Catalogue_Repository extends JpaRepository<Catalogue, Integer> {
}
