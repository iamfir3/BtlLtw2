package com.example.btlltw2.controller;

import com.example.btlltw2.dto.LoginCommand;
import com.example.btlltw2.dto.LoginResponse;
import com.example.btlltw2.entity.User;
import com.example.btlltw2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    private ResponseEntity<?> login (@RequestBody LoginCommand loginCommand){
        User user=userRepository.findByEmail(loginCommand.getEmail());
        if (!user.getPassword().equals(loginCommand.getPassword())){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(LoginResponse.builder().id(user.getId()).build());
    }
    @PostMapping("/register")
    private ResponseEntity<?> register (@RequestBody LoginCommand loginCommand){
        User user=userRepository.findByEmail(loginCommand.getEmail());
        if (!user.getPassword().equals(loginCommand.getPassword())){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(LoginResponse.builder().id(user.getId()).build());
    }
}
