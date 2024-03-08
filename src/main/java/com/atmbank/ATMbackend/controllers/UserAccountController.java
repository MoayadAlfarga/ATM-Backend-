package com.atmbank.ATMbackend.controllers;

import com.atmbank.ATMbackend.dto.UserAccountDto;
import com.atmbank.ATMbackend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v7/account")
public class UserAccountController {
    private AccountService accountService;

    @Autowired
    public UserAccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/account-add")
    public ResponseEntity<UserAccountDto> createAccount(@RequestBody UserAccountDto userAccountDto) {
        UserAccountDto save = accountService.createAccount(userAccountDto);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping("/find-all")

    public ResponseEntity<?> getAllAccount() {
        List<UserAccountDto> find = accountService.getAllAccount();
        return new ResponseEntity<>(find, HttpStatus.OK);
    }
    @GetMapping("/find-account/{id}")
    public ResponseEntity<UserAccountDto> findByIdAccount(@PathVariable Integer id) {
        UserAccountDto userAccountDto = accountService.findAccountById(id);
        return new ResponseEntity<>(userAccountDto, HttpStatus.OK);
    }




}
