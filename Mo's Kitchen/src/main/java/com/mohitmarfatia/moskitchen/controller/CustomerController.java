package com.mohitmarfatia.moskitchen.controller;

import com.mohitmarfatia.moskitchen.dto.customer.CustomerRequest;
import com.mohitmarfatia.moskitchen.dto.customer.CustomerResponse;
import com.mohitmarfatia.moskitchen.dto.customer.CustomerUpdateRequest;
import com.mohitmarfatia.moskitchen.enums.UserRole;
import com.mohitmarfatia.moskitchen.helper.JWTHelper;
import com.mohitmarfatia.moskitchen.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final JWTHelper jwtHelper;

    @PostMapping()
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @GetMapping("/{email}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("email") String email) {
        return ResponseEntity.ok(customerService.retrieveCustomer(email));
    }

    @PatchMapping()
    public ResponseEntity<String> updateCustomer(@RequestHeader(name = "Authorization") String authToken, @RequestBody CustomerUpdateRequest request) {
        String token = authToken.split(" ")[1].trim();
        Long id = jwtHelper.extractUserId(token);
        UserRole role = jwtHelper.extractUserRole(token);
        return ResponseEntity.ok(customerService.updateCustomer(request, id, role));
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteCustomer(@RequestHeader(name = "Authorization") String authToken) {
        String token = authToken.split(" ")[1].trim();
        Long id = jwtHelper.extractUserId(token);
        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }
}
