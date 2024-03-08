package com.atmbank.ATMbackend.dto;

import com.atmbank.ATMbackend.model.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer userId;
    private String firstName;
    private String lastName;
    private UserAccount userAccount;


}
