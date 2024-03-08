package com.atmbank.ATMbackend.controllers;
import com.atmbank.ATMbackend.dto.ATMDtoDeposit;
import com.atmbank.ATMbackend.dto.AtmDtoWithdraw;
import com.atmbank.ATMbackend.service.AtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v8/atm")
public class ATMController {

    private AtmService atmService;

    @Autowired
    public ATMController(AtmService atmService) {
        this.atmService = atmService;
    }

    @PostMapping("/with")
    public ResponseEntity<AtmDtoWithdraw> atmDtoResponseEntity(@RequestBody AtmDtoWithdraw atmDtoWithdraw) {
        AtmDtoWithdraw atmDtoWithdraw1 = atmService.withdraw(atmDtoWithdraw);
        return new ResponseEntity<>(atmDtoWithdraw1, HttpStatus.OK);
    }

    @PostMapping("/depo")
    public ResponseEntity<ATMDtoDeposit> atmDtoDepositResponseEntity(@RequestBody ATMDtoDeposit atmDtoDeposit) {
        ATMDtoDeposit deposit = atmService.deposit(atmDtoDeposit);
        return new ResponseEntity<>(deposit, HttpStatus.OK);
    }


}
