package com.example.btlltw2.repository;

import com.example.btlltw2.entity.Bill;
import com.example.btlltw2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public interface BillRepository extends JpaRepository<Bill,Long> {
    @Query("SELECT b FROM Bill b WHERE b.paymentTime BETWEEN :startDate AND :endDate")
    List<Bill> findAllByPaymentTimeBetweenQuery(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    List<Bill> findTop10ByOrderByPaymentTimeDesc();

    List<Bill> findAllByUser(User user);
}
