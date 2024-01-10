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

    @Column(name = "user_email")
    private String userEmail;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String user_email) {
        this.userEmail = user_email;
    }
}
