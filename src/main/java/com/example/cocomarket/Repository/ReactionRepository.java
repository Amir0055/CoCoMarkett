package com.example.cocomarket.Repository;

 import com.example.cocomarket.Entity.Publication;
 import com.example.cocomarket.Entity.Reaction;
 import com.example.cocomarket.Entity.User;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 import java.util.Set;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Integer> {


}
