package com.mohitmarfatia.moskitchen.service;

import com.mohitmarfatia.moskitchen.dto.CustomerRequest;
import com.mohitmarfatia.moskitchen.dto.CustomerResponse;
import com.mohitmarfatia.moskitchen.dto.LoginRequest;
import com.mohitmarfatia.moskitchen.entity.Customer;
import com.mohitmarfatia.moskitchen.enums.UserRole;
import com.mohitmarfatia.moskitchen.exception.CustomerNotFoundException;
import com.mohitmarfatia.moskitchen.helper.JWTHelper;
import com.mohitmarfatia.moskitchen.helper.EncryptionService;
import com.mohitmarfatia.moskitchen.mapper.CustomerMapper;
import com.mohitmarfatia.moskitchen.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;

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
        customer.setPassword(passwordEncoder.encodePassword(customer.getPassword()));
        customer.setUserRole(UserRole.CUSTOMER);
        repo.save(customer);
        return "Created";
    }

    public CustomerResponse retrieveCustomer(String email) {
        Customer customer = repo.findByEmail(email).orElseThrow(() -> new CustomerNotFoundException(
                format("Cannot update Customer:: No customer found with the provided ID:: %s", email)
        ));
        return mapper.toResponse(customer);
    }
}
