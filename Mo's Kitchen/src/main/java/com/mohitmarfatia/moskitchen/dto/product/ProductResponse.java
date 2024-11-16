package com.mohitmarfatia.moskitchen.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductResponse(
        @JsonProperty("id")
        Long id,

        @JsonProperty("name")
        String name,

        @JsonProperty("price")
        double price
) {
}

