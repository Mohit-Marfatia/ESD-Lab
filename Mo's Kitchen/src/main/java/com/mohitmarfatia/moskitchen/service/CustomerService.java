package com.mohitmarfatia.moskitchen.service;

import com.mohitmarfatia.moskitchen.dto.CustomerRequest;
import com.mohitmarfatia.moskitchen.dto.LoginRequest;
import com.mohitmarfatia.moskitchen.entity.Customer;
import com.mohitmarfatia.moskitchen.mapper.CustomerMapper;
import com.mohitmarfatia.moskitchen.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo repo;
    private final CustomerMapper mapper;

    public String createCustomer( CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        repo.save(customer);
        return "Created";
    }

    public String loginCustomer( LoginRequest request) {
        Optional<Customer> optionalCustomer = repo.findByEmail(request.email());

        Customer customer;
        if (optionalCustomer.isPresent()) {
             customer = optionalCustomer.get();
            return customer.getPassword().equals(request.password())? "Logged in" : "Wrong password";
        }

        return "Email not found";
    }
}
