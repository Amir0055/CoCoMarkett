package com.example.cocomarket.Repository;

import com.example.cocomarket.Entity.Raiting_Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface Rainting_Product_Repository extends JpaRepository<Raiting_Product, Integer> {


}
