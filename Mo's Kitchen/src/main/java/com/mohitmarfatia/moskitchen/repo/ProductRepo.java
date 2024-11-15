package com.mohitmarfatia.moskitchen.repo;

import com.mohitmarfatia.moskitchen.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
}
