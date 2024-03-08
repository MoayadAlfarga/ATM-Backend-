package com.atmbank.ATMbackend.service;

import com.atmbank.ATMbackend.dto.ATMDtoDeposit;
import com.atmbank.ATMbackend.dto.AtmDtoWithdraw;
import com.atmbank.ATMbackend.exceptions.NotFoundException;
import com.atmbank.ATMbackend.exceptions.TransactionException;
import com.atmbank.ATMbackend.model.AtmTransaction;
import com.atmbank.ATMbackend.model.UserAccount;
import com.atmbank.ATMbackend.repository.ATMRepository;
import com.atmbank.ATMbackend.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AtmServiceImpl implements AtmService {


    private UserAccountRepository userAccountRepository;
    private ATMRepository atmRepository;
//    private AtmTransaction atmTransaction;

    @Autowired
    public AtmServiceImpl(UserAccountRepository userAccountRepository, ATMRepository atmRepository) {
        this.userAccountRepository = userAccountRepository;
        this.atmRepository = atmRepository;
    }

    @Override
    public AtmDtoWithdraw withdraw(AtmDtoWithdraw atmDtoWithdraw) {
        UserAccount userAccount = userAccountRepository.findByAccountNumber(atmDtoWithdraw.getAccountNumber()).orElseThrow(() ->
                  new NotFoundException("User Account User Not Found Please try  Again  "));
        if (!userAccount.getAccountNumber().equals(atmDtoWithdraw.getAccountNumber())) {
            throw new NotFoundException("User Account Number Not Correct !!");
        }
        if (atmDtoWithdraw.getAmount() <= 0) {
            throw new TransactionException("The Amount  Withdrawal  Should Be Greater Than 0 ");
        }
        if (atmDtoWithdraw.getAmount() > 5000) {
            throw new TransactionException("Amount Allowed for Withdrawal  5000 The Maximum ");
        }
        if (userAccount.getBalance() < atmDtoWithdraw.getAmount()) {
            throw new TransactionException("The balance is insufficient for the withdrawal process " + userAccount.getBalance());
        }
        Double currentBalance = userAccount.getBalance();
        Double newBalance = (currentBalance) - (atmDtoWithdraw.getAmount());
        userAccount.setBalance(newBalance);
        userAccountRepository.save(userAccount);
        AtmTransaction atmTransaction = new AtmTransaction();
        atmTransaction.setAccountNumber(atmDtoWithdraw.getAccountNumber());
        atmTransaction.setAmount(atmDtoWithdraw.getAmount());
        atmTransaction.setBalanceBeforeTransaction(currentBalance);
        atmTransaction.setBalanceAfterTransaction(newBalance);
        atmTransaction.setDateTimeTransaction(LocalDateTime.now());
        atmTransaction.setStatusTransaction(" The withdrawal process was completed successfully");
        atmRepository.save(atmTransaction);
        atmDtoWithdraw.setBalanceBeforeTransaction(currentBalance);
        atmDtoWithdraw.setBalanceAfterTransaction(newBalance);
        atmDtoWithdraw.setDateTimeTransaction(LocalDateTime.now());
        atmDtoWithdraw.setStatusTransaction("The withdrawal process was completed successfully");
        return atmDtoWithdraw;
    }

    @Override
    public ATMDtoDeposit deposit(ATMDtoDeposit atmDtoDeposit) {
        UserAccount userAccount = userAccountRepository.findByAccountNumber(atmDtoDeposit.getAccountNumber())
                  .orElseThrow(() -> new NotFoundException("User Account User Not Found"));
        if (!userAccount.getAccountNumber().equals(atmDtoDeposit.getAccountNumber())) {
            throw new NotFoundException("User Account Number Not Correct !!");
        }
        Double depositAmount = atmDtoDeposit.getAmount();
        Double currentBalance = userAccount.getBalance();
        if (atmDtoDeposit.getAmount() <= 0) {
            throw new TransactionException("The Amount  Deposit  Should Be Greater Than 0 ");
        }
        if (atmDtoDeposit.getAmount() > 3000) {
            throw new TransactionException("Amount Allowed for deposit 3000 The Maximum ");
        }
        Double newBalance = (currentBalance) + (depositAmount);
        userAccount.setBalance(newBalance);
        userAccountRepository.save(userAccount);
        AtmTransaction atmTransaction = new AtmTransaction();
        atmTransaction.setAccountNumber(atmDtoDeposit.getAccountNumber());
        atmTransaction.setAmount(atmDtoDeposit.getAmount());
        atmTransaction.setBalanceBeforeTransaction(currentBalance);
        atmTransaction.setBalanceAfterTransaction(newBalance);
        atmTransaction.setDateTimeTransaction(LocalDateTime.now());
        atmTransaction.setStatusTransaction("Success Deposit Transaction");
        atmRepository.save(atmTransaction);
        atmDtoDeposit.setBalanceBeforeTransaction(currentBalance);
        atmDtoDeposit.setBalanceAfterTransaction(newBalance);
        atmDtoDeposit.setDateTimeTransaction(LocalDateTime.now());
        atmDtoDeposit.setStatusTransaction("Success Deposit Transaction ");
        return atmDtoDeposit;
    }
}
