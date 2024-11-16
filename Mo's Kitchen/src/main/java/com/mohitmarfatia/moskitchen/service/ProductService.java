package com.mohitmarfatia.moskitchen.service;

import com.mohitmarfatia.moskitchen.dto.product.ProductRequest;
import com.mohitmarfatia.moskitchen.dto.product.ProductResponse;
import com.mohitmarfatia.moskitchen.dto.product.ProductUpdateRequest;
import com.mohitmarfatia.moskitchen.entity.Product;
import com.mohitmarfatia.moskitchen.enums.UserRole;
import com.mohitmarfatia.moskitchen.helper.JWTHelper;
import com.mohitmarfatia.moskitchen.mapper.ProductMapper;
import com.mohitmarfatia.moskitchen.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo repo;
    private final ProductMapper mapper;
    private final JWTHelper jwtHelper;

    public String createProduct(ProductRequest request, String token) {
        if (isAdmin(token)) {
            Product product = mapper.toEntity(request);
            repo.save(product);
            return "Created";
        } else return "Need admin access to create product";
    }

    public List<ProductResponse> retrieveProducts() {
        List<Product> products = repo.findAll();
        return products.stream()
                .map(mapper::toResponse
                )
                .toList();
    }

    public String updateProduct(ProductUpdateRequest request, String token, Long productId) {
        if (isAdmin(token)) {
            Optional<Product> optionalProduct = repo.findById(productId);

            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                if(request.updatedName() != null) {
                    product.setName(request.updatedName());
                    repo.save(product);
                }
                if(request.updatedPrice() != null) {
                    product.setPrice(request.updatedPrice());
                    repo.save(product);
                }
//            Product product = mapper.toEntity(request);

                return "Updated";
            } else return "Product not found";

        } else return "Need admin access to create product";
    }

    public String deleteProduct(Long id, String token) {
        if(isAdmin(token)){
            if (repo.existsById(id)) {
                repo.deleteById(id);
                return "Deleted";
            } else {
                return "Product not found";
            }
        } else return "Need admin access to delete product";
    }

    private boolean isAdmin(String token) {
        UserRole role = jwtHelper.extractUserRole(token);
        return role.equals(UserRole.ADMIN);
    }

}
