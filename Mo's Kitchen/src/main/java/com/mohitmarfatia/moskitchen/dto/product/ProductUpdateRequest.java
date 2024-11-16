package com.mohitmarfatia.moskitchen.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductUpdateRequest(
        @JsonProperty("name")
        String updatedName,

        @JsonProperty("price")
        Double updatedPrice
) {
}
