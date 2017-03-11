package io.muic.ooc.model;

/**
 * Created by joakimnilfjord on 3/10/2017 AD.
 */

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getProductName() {
        return productName;
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
