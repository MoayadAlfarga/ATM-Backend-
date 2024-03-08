package com.atmbank.ATMbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleUserNotFoundException(NotFoundException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExists.class)
    public ResponseEntity<CustomErrorResponseUser> errorResponseUserEntityResponse(AlreadyExists alreadyExists) {

        CustomErrorResponseUser customErrorResponseUser = new CustomErrorResponseUser(HttpStatus.BAD_REQUEST.name()
                  , alreadyExists.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(customErrorResponseUser, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionException.class)

    public ResponseEntity<CustomErrorResponse> responseResponseEntity(TransactionException transactionException) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(),
                  transactionException.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);

    }


}
