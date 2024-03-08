package com.atmbank.ATMbackend.service;

import com.atmbank.ATMbackend.dto.UserAccountDto;

import java.util.List;

public interface AccountService {

    UserAccountDto createAccount(UserAccountDto userAccountDto);

    List<UserAccountDto> getAllAccount();

    UserAccountDto findAccountById(Integer id);

    UserAccountDto updateAccount(UserAccountDto userAccountDto);

    void deleteAccount(Integer id);


}
