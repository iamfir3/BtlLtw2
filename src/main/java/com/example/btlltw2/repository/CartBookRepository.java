package com.example.btlltw2.repository;

import com.example.btlltw2.entity.CartBook;
import com.example.btlltw2.entity.CartEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartBookRepository extends JpaRepository<CartBook,Long> {
    @Query("SELECT c FROM CartBook c WHERE c.cart.id = :cardId")
    List<CartBook> findAllByCartId(@Param("cardId") Long id);

    @Modifying
    @Query("DELETE FROM CartBook f where f.cart=:cart")
    void deleteAllByCart(@Param("cart") CartEntity cartEntity);
}
