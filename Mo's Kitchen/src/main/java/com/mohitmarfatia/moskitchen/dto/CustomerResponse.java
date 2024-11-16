package com.mohitmarfatia.moskitchen.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.mohitmarfatia.moskitchen.enums.UserRole;

public record CustomerResponse(
        @JsonProperty("first_name")
        String firstName,

        @JsonProperty("last_name")
        String lastName,

        @JsonProperty("email")
        String email,

        @JsonProperty("user_role")
        UserRole user_role
) {
}
