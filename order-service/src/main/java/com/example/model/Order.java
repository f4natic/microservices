package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "products")
    private String products;

    @Column(name = "user_id")
    private long user_id;
}
