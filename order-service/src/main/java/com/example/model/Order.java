package com.example.model;

import jakarta.annotation.Nonnull;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (!products.equals(order.products)) return false;
        return userEmail.equals(order.userEmail);
    }

    @Override
    public int hashCode() {
        int result = products.hashCode();
        result = 31 * result + userEmail.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", products='" + products + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Order order;

        private Builder() {
            order = new Order();
        }

        public Builder product(@Nonnull String products) {
            order.setProducts(products);
            return this;
        }

        public Builder userEmail(@Nonnull String userEmail) {
            order.setUserEmail(userEmail);
            return this;
        }

        public Order build() {
            return order;
        }
    }
}
