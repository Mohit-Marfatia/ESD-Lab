package com.mohitmarfatia.moskitchen.controller;

import com.mohitmarfatia.moskitchen.dto.ProductRequest;
import com.mohitmarfatia.moskitchen.dto.ProductResponse;
import com.mohitmarfatia.moskitchen.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody @Valid ProductRequest request, @RequestHeader(name = "Authorization") String authToken) {
        String token = authToken.split(" ")[1].trim();
        return ResponseEntity.ok(productService.createProduct(request, token));
    }

    @GetMapping("/all-products")
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return ResponseEntity.ok(productService.retrieveProducts());
    }
}
