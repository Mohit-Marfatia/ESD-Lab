package com.mohitmarfatia.moskitchen.service;

import com.mohitmarfatia.moskitchen.dto.ProductRequest;
import com.mohitmarfatia.moskitchen.entity.Product;
import com.mohitmarfatia.moskitchen.enums.UserRole;
import com.mohitmarfatia.moskitchen.helper.JWTHelper;
import com.mohitmarfatia.moskitchen.mapper.ProductMapper;
import com.mohitmarfatia.moskitchen.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo repo;
    private final ProductMapper mapper;
    private final JWTHelper jwtHelper;

    public String createProduct(ProductRequest request) {
//        if (isAdmin(token)) {
            Product product = mapper.toEntity(request);
            repo.save(product);
            return "Created";
//        } else return "Need admin access to create product";
    }

    private boolean isAdmin(String token) {
        UserRole role = jwtHelper.extractUserRole(token);
        return role.equals(UserRole.ADMIN);
    }

}
