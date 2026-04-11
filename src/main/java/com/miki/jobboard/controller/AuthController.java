package com.miki.jobboard.controller;

import com.miki.jobboard.dto.AuthRequest;
import com.miki.jobboard.dto.AuthResponse;
import com.miki.jobboard.entity.User;
import com.miki.jobboard.exception.BadRequestException;
import com.miki.jobboard.repository.UserRepository;
import com.miki.jobboard.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody AuthRequest authRequest) {
        if (userRepository.findByEmail(authRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Пользователь уже существует!");
        }
        if (authRequest.getRole() == null) {
            throw new BadRequestException("Роль не должна быть пустой");
        }

        var user = new User();
        user.setEmail(authRequest.getEmail());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        user.setRole(authRequest.getRole());
        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
        var user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден!"));

        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Неверный пароль!");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
