package com.example.btlltw2.repository;

import com.example.btlltw2.entity.ItemBill;
import com.example.btlltw2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemBillRepository extends JpaRepository<ItemBill,Long> {
    List<ItemBill> findByBill_UserAndIsVotedFalse(User user);
}
