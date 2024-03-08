package com.atmbank.ATMbackend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CustomErrorResponse {
    private int status;
    private String message;
    private LocalDateTime localDateTime;

}
