package com.example.btlltw2.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ProductDTO {
    private Long id;

    private String title;
    private String author;
    private String des;
    private Date day;
    private int count;
    private String image;
    private float price;
    private int quantity;
    private int sold;
}
