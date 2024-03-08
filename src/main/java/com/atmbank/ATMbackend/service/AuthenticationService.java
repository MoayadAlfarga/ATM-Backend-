package com.atmbank.ATMbackend.service;

import com.atmbank.ATMbackend.dto.AuthenticationRequest;
import com.atmbank.ATMbackend.dto.AuthenticationResponse;
import com.atmbank.ATMbackend.dto.RegisterUserDto;
import com.atmbank.ATMbackend.dto.UserDto;
import com.atmbank.ATMbackend.exceptions.AlreadyExists;
import com.atmbank.ATMbackend.exceptions.NotFoundException;
import com.atmbank.ATMbackend.model.Role;
import com.atmbank.ATMbackend.model.User;
import com.atmbank.ATMbackend.repository.UserRepository;
import com.atmbank.ATMbackend.security.JwtGenerationToken;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

    private JwtGenerationToken jwtGenerationToken;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private ModelMapper modelMapper;

    @Autowired
    public AuthenticationService(JwtGenerationToken jwtGenerationToken, PasswordEncoder passwordEncoder,
                                 UserRepository userRepository, AuthenticationManager authenticationManager
              , ModelMapper modelMapper) {
        this.jwtGenerationToken = jwtGenerationToken;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;
    }
    public AuthenticationResponse register(RegisterUserDto registerUserDto) {
        if (userRepository.existsByEmail(registerUserDto.getEmail())) {
            throw new AlreadyExists("User Account Bank  Is Already Exists !! :" + registerUserDto.getEmail());
        }

        User user = User.builder()
                  .firstName(registerUserDto.getFirstName())
                  .lastName(registerUserDto.getLastName())
                  .age(registerUserDto.getAge())
                  .dateOfBirth(registerUserDto.getDateOfBirth())
                  .address(registerUserDto.getAddress())
                  .nationalNumber(registerUserDto.getNationalNumber())
                  .email(registerUserDto.getEmail())
                  .password(passwordEncoder.encode(registerUserDto.getPassword()))
                  .role(Role.USER)
                  .build();
        userRepository.save(user);
        String jwtToken = jwtGenerationToken.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse loginUser(AuthenticationRequest authenticationRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                  authenticationRequest.getPassword()));
        var user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
        var jwtToken = jwtGenerationToken.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public List<UserDto> getAllUser() {

        List<User> userList = userRepository.findAll();
        if (userList == null) {

            throw new EntityNotFoundException("There are no users : User  is Empty ");

        }
        return userList.stream().map((user) ->
                  modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    public UserDto findUserById(Integer id) {

        Optional<User> optionalUserDto = userRepository.findById(id);
        if (optionalUserDto.isPresent()) {

            User user = optionalUserDto.get();
            return modelMapper.map(user, UserDto.class);
        } else {
            throw new NotFoundException("User Not Found !!!!!!!!!!! :" + id);
        }
    }


}
