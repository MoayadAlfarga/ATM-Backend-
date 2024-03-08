package com.atmbank.ATMbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtmDtoWithdraw {
    private String accountNumber;
    private String PIN;
    private Double amount;
    private Double balanceBeforeTransaction;
    private Double balanceAfterTransaction;
    private LocalDateTime dateTimeTransaction;
    private String statusTransaction;
}
