package com.example.btlltw2.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDTO {
    private String name;
    private Long id;
}
