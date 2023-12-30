package com.example.btlltw2.controller;

import com.example.btlltw2.dto.LoginCommand;
import com.example.btlltw2.dto.LoginResponse;
import com.example.btlltw2.dto.RegisterCommand;
import com.example.btlltw2.entity.CartEntity;
import com.example.btlltw2.entity.User;
import com.example.btlltw2.exception.ApiException;
import com.example.btlltw2.repository.CartRepository;
import com.example.btlltw2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/login")
    private ResponseEntity<?> login(@RequestBody LoginCommand loginCommand) {
        User user = userRepository.findByEmail(loginCommand.getEmail());
        if (!user.getPassword().equals(loginCommand.getPassword())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(LoginResponse.builder().id(user.getId()).email(user.getEmail()).position(user.getPosition()).build());
    }

    @PostMapping("/register")
    private ResponseEntity<?> register(@RequestBody RegisterCommand registerCommand) {
        User user = userRepository.findByEmail(registerCommand.getEmail());
        if (user != null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Tài khoản đã tồn tại! Hãy đăng kí bằng email khác");
        }

        CartEntity cartEntity = new CartEntity();

        User user1 = User.builder()
                .email(registerCommand.getEmail())
                .name(registerCommand.getName())
                .password(registerCommand.getPassword())
                .cart(cartEntity)
                .position("client")
                .build();

        cartEntity.setUser(user1);

        userRepository.save(user1);
        return ResponseEntity.ok("ok");
    }
}
