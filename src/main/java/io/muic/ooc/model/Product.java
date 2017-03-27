package io.muic.ooc.model;

/**
 * Created by joakimnilfjord on 3/10/2017 AD.
 */

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    @Column(name = "price")
    @NotNull(message = "*Please provide a Price for your product")
    @DecimalMin(value = "0",message = "*Please provide a positive value for Price for your product")
    private Long price;
    @Column(name = "product_name")
    @NotEmpty(message = "*Please provide a Name for your product")
    private String productName;
    @Column(name = "quantity")
    @DecimalMin(value = "0",message = "*Please provide a positive value for Quantity for your product")
    @NotNull(message = "*Please provide a quantity for your product")
    private Long quantity;
    @Column(name = "description")
    @NotEmpty(message = "*Please provide a Description for your product")
    private String description;
    private Integer rating;


    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "User_id")
    private User user;

    @OneToMany(mappedBy = "product",cascade = {CascadeType.ALL})
    private Set<CartProduct> cartProducts;





    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
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

    public Set<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(Set<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public Long getId() {
        return id;
    }
}
