package com.mohitmarfatia.moskitchen.mapper;

import com.mohitmarfatia.moskitchen.dto.customer.CustomerRequest;
import com.mohitmarfatia.moskitchen.dto.customer.CustomerResponse;
import com.mohitmarfatia.moskitchen.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toEntity(CustomerRequest request) {
        return Customer.builder()
                .email(request.email())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .password(request.password())
                .build();
    }

    public CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getUserRole());
    }
}
