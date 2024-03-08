package com.atmbank.ATMbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class AtmTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String accountNumber;
    private Double amount;
    private Double balanceBeforeTransaction;
    private Double balanceAfterTransaction;
    private LocalDateTime dateTimeTransaction;
    private String statusTransaction;


}
