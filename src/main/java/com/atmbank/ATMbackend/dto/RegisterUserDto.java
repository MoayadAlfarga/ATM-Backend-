package com.atmbank.ATMbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {
    private String firstName;
    private String lastName;
    private Integer age;
    private LocalDate dateOfBirth;
    private String address;
    private String nationalNumber;
    private String PhoneNumber;
    private String email;
    private String password;


}
