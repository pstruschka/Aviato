package io.muic.ooc.model;

/**
 * Created by joakimnilfjord on 3/10/2017 AD.
 */

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long price;
    private String productName;
    private Integer quantity;
    private String description;
    private Integer rating;

    @ManyToOne()
    @JoinColumn(name = "User_id")
    private User user;

    @Override
    public String toString() {
        return String.format(
                "Product[productName=%s,price=%d ,description='%s', quantity='%d']",
                productName,price, description, quantity);
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public Long getPrice() { return price; }

    public void setPrice(Long price) { this.price = price; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
