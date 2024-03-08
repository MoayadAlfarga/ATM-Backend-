package com.atmbank.ATMbackend.service;

import com.atmbank.ATMbackend.dto.ATMDtoDeposit;
import com.atmbank.ATMbackend.dto.AtmDtoWithdraw;

public interface AtmService {

    public AtmDtoWithdraw withdraw(AtmDtoWithdraw atmDtoWithdraw);
    public ATMDtoDeposit deposit(ATMDtoDeposit atmDtoDeposit);


}
