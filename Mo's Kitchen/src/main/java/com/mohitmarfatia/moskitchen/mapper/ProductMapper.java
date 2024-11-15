package com.mohitmarfatia.moskitchen.mapper;

import com.mohitmarfatia.moskitchen.dto.CustomerResponse;
import com.mohitmarfatia.moskitchen.dto.ProductRequest;
import com.mohitmarfatia.moskitchen.dto.ProductResponse;
import com.mohitmarfatia.moskitchen.entity.Customer;
import com.mohitmarfatia.moskitchen.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toEntity(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.name())
                .price(productRequest.price())
                .build();
    }

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(product.getName(), product.getPrice());
    }
}
