package com.mohitmarfatia.moskitchen.service;

import com.mohitmarfatia.moskitchen.dto.customer.CustomerRequest;
import com.mohitmarfatia.moskitchen.dto.customer.CustomerResponse;
import com.mohitmarfatia.moskitchen.dto.customer.CustomerUpdateRequest;
import com.mohitmarfatia.moskitchen.dto.customer.LoginRequest;
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

    public String updateCustomer(CustomerUpdateRequest request, Long id, UserRole userRole) {
//            System.out.println("----------");
//            System.out.println(id);
        Optional<Customer> optionalCustomer = repo.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            if (request.updatedFirstName() != null) {
                customer.setFirstName(request.updatedFirstName());
                repo.save(customer);
            }
            if (request.updatedLastName() != null) {
                customer.setLastName(request.updatedLastName());
                repo.save(customer);
            }
//            System.out.println("----------");
//            System.out.println(request.updatedUserRole());
            if (request.updatedUserRole() != null) {
                if (userRole == UserRole.ADMIN) {
                    customer.setUserRole(request.updatedUserRole());
                    repo.save(customer);
                } else return "You need admin access to change your role";
            }
            return "Updated";
        } else return "Customer not found";
    }

    public String deleteCustomer(Long id) {
        if(repo.existsById(id)) {
            repo.deleteById(id);
            return "Deleted";
        } else {
            return "Customer not found";
        }
    }
}
