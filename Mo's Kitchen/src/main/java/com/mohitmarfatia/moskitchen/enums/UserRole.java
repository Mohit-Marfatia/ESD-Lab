package com.mohitmarfatia.moskitchen.enums;

public enum UserRole {
    ADMIN("ADMIN"),
    CUSTOMER("CUSTOMER");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static UserRole fromString(String role) {
        for (UserRole UserRole : UserRole.values()) {
            if (UserRole.role.equalsIgnoreCase(role)) {
                return UserRole;
            }
        }
        throw new IllegalArgumentException("No such role: " + role);
    }

}
