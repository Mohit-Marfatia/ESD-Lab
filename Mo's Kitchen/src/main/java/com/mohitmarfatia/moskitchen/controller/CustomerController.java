package com.mohitmarfatia.moskitchen.controller;

import com.mohitmarfatia.moskitchen.dto.CustomerRequest;
import com.mohitmarfatia.moskitchen.helper.RequestInterceptor;
import com.mohitmarfatia.moskitchen.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
@RequiredArgsConstructor //inject all beans to this url
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final RequestInterceptor jwtInterceptor;

    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }
}
