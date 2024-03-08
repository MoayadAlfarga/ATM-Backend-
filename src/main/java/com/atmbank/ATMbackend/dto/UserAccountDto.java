package com.atmbank.ATMbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAccountDto {
    private Integer userId;
    private String accountNumber;
    private Double balance;
}
