package com.mohitmarfatia.moskitchen.entity;

import com.mohitmarfatia.moskitchen.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder //object
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="product")
public class Product {
    @Id // PK
    @GeneratedValue(strategy = GenerationType.AUTO) //auto generate id into mysqldb
    private Long id;

    @Column(name= "name", nullable = false)
    private String name;

    @Column(name="price", nullable = false)
    private double price;
}
