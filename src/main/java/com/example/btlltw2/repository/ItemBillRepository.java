package com.example.btlltw2.repository;

import com.example.btlltw2.entity.ItemBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemBillRepository extends JpaRepository<ItemBill,Long> {
}
