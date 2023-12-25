package com.example.btlltw2.repository;

import com.example.btlltw2.entity.CartBook;
import com.example.btlltw2.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartBookRepository extends JpaRepository<CartBook,Long> {
    @Query("SELECT c FROM CartBook c WHERE c.cart.id = :cardId")
    List<CartBook> findAllByCartId(@Param("cardId") Long id);



    void deleteAllByCart(CartEntity cartEntity);
}
