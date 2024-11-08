package com.mohitmarfatia.moskitchen.repo;

import com.mohitmarfatia.moskitchen.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//interface = defines a set of methods without implementations
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
}
