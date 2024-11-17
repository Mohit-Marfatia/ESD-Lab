package com.mohitmarfatia.moskitchen.repo;

import com.mohitmarfatia.moskitchen.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.price >= :minPrice AND p.price <= :maxPrice ORDER BY p.price DESC LIMIT 2")
    List<Product> findTop2Products(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);
}
