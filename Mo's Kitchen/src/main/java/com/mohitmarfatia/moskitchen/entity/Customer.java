package com.mohitmarfatia.moskitchen.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder //object
@NoArgsConstructor
@AllArgsConstructor
@Entity  //persisted into db?
@Table(name="customer")
public class Customer {
    @Id // PK
    @GeneratedValue(strategy = GenerationType.AUTO) //auto generate id into mysqldb
    private Long id;

    @Column(name= "first_name", nullable = false)
    private String firstName;

    @Column(name= "last_name")
    private String lastName;

    @Column(name ="email", nullable = false, unique = true)
    private String email;

    @Column(name="password", nullable = false)
    private String password;
}
