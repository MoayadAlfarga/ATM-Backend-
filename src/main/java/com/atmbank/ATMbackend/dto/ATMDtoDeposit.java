package com.atmbank.ATMbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ATMDtoDeposit {
    private String accountNumber;
    private Double amount;
    private String PIN;
    private Double balanceBeforeTransaction;
    private Double balanceAfterTransaction;
    private LocalDateTime dateTimeTransaction;
    private String statusTransaction;
}
