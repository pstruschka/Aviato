package io.muic.ooc.model;

/**
 * Created by Don on 3/13/2017 AD.
 */

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="cart_id")
    private Long cartId;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart")
    private Set<CartProduct> cartProducts;

    private boolean orderConfirmed;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATETIME_FIELD")
    private java.util.Date dateTimeField;

    public String getDateTimeField() {
        return dateTimeField.toString();
    }

    public void setDateTimeField(Date dateTimeField) {
        this.dateTimeField = dateTimeField;
    }

    public Long getCartId() { return cartId; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(Set<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public boolean isOrderConfirmed() {
        return orderConfirmed;
    }

    public void setOrderConfirmed(boolean orderConfirmed) {
        this.orderConfirmed = orderConfirmed;
    }
}
