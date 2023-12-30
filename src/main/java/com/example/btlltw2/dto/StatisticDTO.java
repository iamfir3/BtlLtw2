package com.example.btlltw2.dto;

import com.example.btlltw2.entity.BillStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDTO {
    private Map<BillStatus, Integer> orders;
    private int money;
}
