package com.example.btlltw2.dto;

import com.example.btlltw2.entity.BillStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class BillItemDTO {
    private Long id;
    private int amount;
    private int price;
    private BillStatus status;
    private Date createdAt;
    private ProductDTO book;
}
