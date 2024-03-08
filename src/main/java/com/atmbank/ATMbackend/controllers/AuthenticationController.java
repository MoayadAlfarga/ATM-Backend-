package com.atmbank.ATMbackend.controllers;

import com.atmbank.ATMbackend.dto.AuthenticationRequest;
import com.atmbank.ATMbackend.dto.AuthenticationResponse;
import com.atmbank.ATMbackend.dto.RegisterUserDto;
import com.atmbank.ATMbackend.dto.UserDto;
import com.atmbank.ATMbackend.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v6/auth")
public class AuthenticationController {
    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> authenticationResponseResponseEntity(@RequestBody RegisterUserDto registerUserDto) {
        return ResponseEntity.ok(authenticationService.register(registerUserDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest authenticationRequest) {

        return ResponseEntity.ok(authenticationService.loginUser(authenticationRequest));
    }

    @GetMapping("/find-all-users")
    public ResponseEntity<?> getAll() {
        List<UserDto> find = authenticationService.getAllUser();
        return new ResponseEntity<>(find, HttpStatus.OK);
    }

    @GetMapping("/find-user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        UserDto userDto = authenticationService.findUserById(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);

    }
}
