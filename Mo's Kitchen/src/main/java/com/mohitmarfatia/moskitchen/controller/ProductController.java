package com.mohitmarfatia.moskitchen.controller;

import com.mohitmarfatia.moskitchen.dto.product.ProductRequest;
import com.mohitmarfatia.moskitchen.dto.product.ProductResponse;
import com.mohitmarfatia.moskitchen.dto.product.ProductUpdateRequest;
import com.mohitmarfatia.moskitchen.entity.Product;
import com.mohitmarfatia.moskitchen.enums.UserRole;
import com.mohitmarfatia.moskitchen.helper.JWTHelper;
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
    private final JWTHelper jwtHelper;

    @PostMapping()
    public ResponseEntity<String> createProduct(@RequestBody @Valid ProductRequest request, @RequestHeader(name = "Authorization") String authToken) {
        String token = authToken.split(" ")[1].trim();
        return ResponseEntity.ok(productService.createProduct(request, token));
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return ResponseEntity.ok(productService.retrieveProducts());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateProduct(@RequestBody @Valid ProductUpdateRequest request, @RequestHeader(name = "Authorization") String authToken, @PathVariable Long id) {
        String token = authToken.split(" ")[1].trim();
        return ResponseEntity.ok(productService.updateProduct(request, token, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@RequestHeader(name="Authorization") String authToken, @PathVariable Long id) {
        String token = authToken.split(" ")[1].trim();
        return ResponseEntity.ok(productService.deleteProduct(id, token));
    }

    @GetMapping("/top")
    public ResponseEntity<List<ProductResponse>> getTopProducts(@RequestParam double minPrice, @RequestParam double maxPrice) {
        return ResponseEntity.ok(productService.getTop2Products(minPrice, maxPrice));
    }
}
