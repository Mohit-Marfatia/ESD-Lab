package com.mohitmarfatia.moskitchen.controller;

import com.mohitmarfatia.moskitchen.dto.LoginRequest;
import com.mohitmarfatia.moskitchen.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final CustomerService customerService;

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity.ok(customerService.loginCustomer(loginRequest));
    }
}
