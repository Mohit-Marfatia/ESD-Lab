package com.mohitmarfatia.moskitchen.repo;

import com.mohitmarfatia.moskitchen.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//interface = defines a set of methods without implementations
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
}
