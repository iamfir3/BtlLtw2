package com.example.btlltw2.controller;

import com.example.btlltw2.dto.StatisticDTO;
import com.example.btlltw2.entity.Bill;
import com.example.btlltw2.entity.BillStatus;
import com.example.btlltw2.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("statistic")
public class StatisticController {

    @Autowired
    private BillRepository billRepository;
    @GetMapping("/getOrderStatistic")
    public ResponseEntity<?> getOrderStatistic(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        startCalendar.set(Calendar.HOUR_OF_DAY, 0);
        startCalendar.set(Calendar.MINUTE, 0);
        startCalendar.set(Calendar.SECOND, 0);
        startDate = startCalendar.getTime();
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        endCalendar.set(Calendar.HOUR_OF_DAY, 23);
        endCalendar.set(Calendar.MINUTE, 59);
        endCalendar.set(Calendar.SECOND, 59);
        endDate = endCalendar.getTime();
        List<Bill> billList=billRepository.findAllByPaymentTimeBetweenQuery(startDate,endDate);
        StatisticDTO statisticDTO=new StatisticDTO();
        AtomicInteger pendingCount= new AtomicInteger();
        AtomicInteger preparingCount= new AtomicInteger();
        AtomicInteger shoppingCount= new AtomicInteger();
        AtomicInteger completedCount= new AtomicInteger();
        AtomicInteger canceledCount= new AtomicInteger();
        AtomicInteger money= new AtomicInteger();
        billList.forEach(bill -> {
            if(bill.getStatus().equals(BillStatus.PENDING)) pendingCount.getAndIncrement();
            if(bill.getStatus().equals(BillStatus.PREPARING)) preparingCount.getAndIncrement();
            if(bill.getStatus().equals(BillStatus.SHIPPING)) shoppingCount.getAndIncrement();
            if(bill.getStatus().equals(BillStatus.COMPLETED)) completedCount.getAndIncrement();
            if(bill.getStatus().equals(BillStatus.CANCELED)) canceledCount.getAndIncrement();
            if(bill.getStatus().equals(BillStatus.COMPLETED))
                bill.getItemBills().forEach(bi -> {
                    money.addAndGet(bi.getAmount() * bi.getPrice());
                });
        });
        statisticDTO.setMoney(money.get());
        Map<BillStatus, Integer> orders=new HashMap<>();
        orders.put(BillStatus.PENDING,pendingCount.get());
        orders.put(BillStatus.PREPARING,preparingCount.get());
        orders.put(BillStatus.SHIPPING,shoppingCount.get());
        orders.put(BillStatus.COMPLETED,completedCount.get());
        orders.put(BillStatus.CANCELED,canceledCount.get());
        statisticDTO.setOrders(orders);
        return ResponseEntity.ok(statisticDTO);
    }
}
