package com.mohitmarfatia.moskitchen.dto.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mohitmarfatia.moskitchen.enums.UserRole;

public record CustomerUpdateRequest(
        @JsonProperty("first_name")
        String updatedFirstName,

        @JsonProperty("last_name")
        String updatedLastName,

        @JsonProperty("user_role")
        UserRole updatedUserRole
) {
}
