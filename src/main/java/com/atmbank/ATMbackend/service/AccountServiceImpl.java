package com.atmbank.ATMbackend.service;

import com.atmbank.ATMbackend.dto.UserAccountDto;
import com.atmbank.ATMbackend.exceptions.AlreadyExists;
import com.atmbank.ATMbackend.exceptions.NotFoundException;
import com.atmbank.ATMbackend.model.UserAccount;
import com.atmbank.ATMbackend.repository.UserAccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AccountServiceImpl implements AccountService {
    private UserAccountRepository userAccountRepository;
    private ModelMapper modelMapper;

    @Autowired

    public AccountServiceImpl(UserAccountRepository userAccountRepository, ModelMapper modelMapper) {
        this.userAccountRepository = userAccountRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public UserAccountDto createAccount(UserAccountDto userAccountDto) {
        if (userAccountRepository.existsByAccountNumber(userAccountDto.getAccountNumber())) {
            throw new AlreadyExists("Account User Bank  Is Already Exists !!!! :"
                      + userAccountDto.getAccountNumber());
        }

        UserAccount userAccount = modelMapper.map(userAccountDto, UserAccount.class);
        UserAccount save = userAccountRepository.save(userAccount);
        UserAccountDto saveDto = modelMapper.map(save, UserAccountDto.class);
        return saveDto;
    }


    @Override
    public List<UserAccountDto> getAllAccount() {
        List<UserAccount> getAll = userAccountRepository.findAll();
        return getAll.stream().map((account) -> modelMapper.map(account, UserAccountDto.class))
                  .collect(Collectors.toList());
    }

    @Override
    public UserAccountDto findAccountById(Integer id) {
        Optional<UserAccount> optionalUserAccount = userAccountRepository.findById(id);
        if (optionalUserAccount.isPresent()) {
            UserAccount userAccount = optionalUserAccount.get();
            return modelMapper.map(userAccount, UserAccountDto.class);
        } else {
            throw new NotFoundException("Account  Not Found !!!!!!!!! :" + id);
        }
    }

    @Override
    public UserAccountDto updateAccount(UserAccountDto userAccountDto) {
        UserAccount updateUserAccount = userAccountRepository.findById(userAccountDto.getUserId()).get();
        updateUserAccount.setAccountNumber(userAccountDto.getAccountNumber());
        updateUserAccount.setBalance(userAccountDto.getBalance());
        UserAccount userAccount = userAccountRepository.save(updateUserAccount);
        return modelMapper.map(userAccount, UserAccountDto.class);
    }

    @Override
    public void deleteAccount(Integer id) {
        Optional<UserAccount> optionalUserAccount = userAccountRepository.findById(id);
        if (optionalUserAccount.isPresent()) {
            UserAccount userAccount = optionalUserAccount.get();
        } else {
            throw new NotFoundException("User Account  Not Found !!!!!!!! :" + id);
        }
        userAccountRepository.deleteById(id);
    }
}
