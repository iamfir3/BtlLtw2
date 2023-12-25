package com.example.btlltw2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCartCommand {
    private Long userId;
    private Long bookId;
    private int amount;
}
