package com.mohitmarfatia.moskitchen.service;

import com.mohitmarfatia.moskitchen.dto.CustomerRequest;
import com.mohitmarfatia.moskitchen.dto.LoginRequest;
import com.mohitmarfatia.moskitchen.entity.Customer;
import com.mohitmarfatia.moskitchen.enums.UserRole;
import com.mohitmarfatia.moskitchen.helper.JWTHelper;
import com.mohitmarfatia.moskitchen.helper.EncryptionService;
import com.mohitmarfatia.moskitchen.mapper.CustomerMapper;
import com.mohitmarfatia.moskitchen.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    private final EncryptionService passwordEncoder;

    private final JWTHelper jwtHelper;

    public String loginCustomer(LoginRequest request) {
        Optional<Customer> optionalCustomer = repo.findByEmail(request.email());

        Customer customer;
        if (optionalCustomer.isPresent()) {
            customer = optionalCustomer.get();
            return passwordEncoder.verifyPassword(request.password(), customer.getPassword()) ? jwtHelper.generateToken(customer.getId(), customer.getUserRole()) : "Wrong password";
        }

        return "Email not found";
    }

    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
//        if (isAdmin(token)) {
            customer.setPassword(passwordEncoder.encodePassword(customer.getPassword()));
            customer.setUserRole(UserRole.CUSTOMER);
            repo.save(customer);
            return "Created";
//        } else return "Need admin access to create customer";
    }

    private boolean isAdmin(String token) {
        UserRole role = jwtHelper.extractUserRole(token);
        return role.equals(UserRole.ADMIN);
    }

}
