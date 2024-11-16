package com.mohitmarfatia.moskitchen.service;

import com.mohitmarfatia.moskitchen.dto.CustomerResponse;
import com.mohitmarfatia.moskitchen.dto.ProductRequest;
import com.mohitmarfatia.moskitchen.dto.ProductResponse;
import com.mohitmarfatia.moskitchen.entity.Customer;
import com.mohitmarfatia.moskitchen.entity.Product;
import com.mohitmarfatia.moskitchen.enums.UserRole;
import com.mohitmarfatia.moskitchen.exception.CustomerNotFoundException;
import com.mohitmarfatia.moskitchen.helper.JWTHelper;
import com.mohitmarfatia.moskitchen.mapper.ProductMapper;
import com.mohitmarfatia.moskitchen.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

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

    public List<ProductResponse> retrieveProducts() {
        List<Product> products = repo.findAll();
        return products.stream()
                .map(mapper::toResponse
                )
                .toList();
    }

    private boolean isAdmin(String token) {
        UserRole role = jwtHelper.extractUserRole(token);
        return role.equals(UserRole.ADMIN);
    }

}
