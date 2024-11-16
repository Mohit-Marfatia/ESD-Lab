package com.mohitmarfatia.moskitchen.mapper;

import com.mohitmarfatia.moskitchen.dto.product.ProductRequest;
import com.mohitmarfatia.moskitchen.dto.product.ProductResponse;
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
        return new ProductResponse(product.getId(), product.getName(), product.getPrice());
    }
}
