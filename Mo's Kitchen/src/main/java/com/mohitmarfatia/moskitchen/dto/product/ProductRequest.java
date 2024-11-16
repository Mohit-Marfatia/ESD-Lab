package com.mohitmarfatia.moskitchen.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record ProductRequest(
        @NotNull(message = "Product name should be present")
        @JsonProperty("name")
        String name,

        @NotNull(message = "Product should have a price")
        @JsonProperty("price")
        double price
) {
}
