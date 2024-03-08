package com.atmbank.ATMbackend.exceptions;

public class TransactionException extends RuntimeException {

    public TransactionException(String message) {

        super(
                  message
        );


    }

}
