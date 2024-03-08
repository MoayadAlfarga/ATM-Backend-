package com.atmbank.ATMbackend.repository;

import com.atmbank.ATMbackend.model.AtmTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ATMRepository extends JpaRepository<AtmTransaction,Integer> {


}
