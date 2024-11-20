package com.mohitmarfatia.moskitchen.entity;

import com.mohitmarfatia.moskitchen.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //auto generate id into mysqldb
    private Long id;

    @Column(name= "first_name", nullable = false)
    private String firstName;

    @Column(name= "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name="userRole", columnDefinition = "varchar(50) default 'CUSTOMER'")
    private UserRole userRole;

    @Column(name ="email", nullable = false, unique = true)
    private String email;

    @Column(name="password", nullable = false)
    private String password;
}
