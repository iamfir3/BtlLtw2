package com.example.btlltw2.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
public enum BillStatus {
    PENDING,PREPARING,SHIPPING,COMPLETED,CANCELED
}
