package com.example.cocomarket.Repository;

import com.example.cocomarket.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Query(value = " SELECT * FROM _user WHERE email = :mail  ",
            nativeQuery = true)
    User FoundAcountBYMail(@Param("mail") String mail);


}
