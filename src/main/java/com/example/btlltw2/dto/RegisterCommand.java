package com.example.btlltw2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class RegisterCommand {
    private String name;
    private String email;
    private String password;
    private String position;
}
