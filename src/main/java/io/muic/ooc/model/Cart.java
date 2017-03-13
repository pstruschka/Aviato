package io.muic.ooc.model;

/**
 * Created by Don on 3/13/2017 AD.
 */

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="cart_id")
    private Long cartId;

    @ManyToMany
    @JoinTable(name = "cart_product" , joinColumns = @JoinColumn(name = "cart_id", referencedColumnName = "cart_id"),
            inverseJoinColumns = @JoinColumn(name="product_id", referencedColumnName = "id"))
    private HashMap<Product, Long> cartItems;

    private Long cartTotalPrice;

    public Cart() {
        setCartItems(new HashMap<Product, Long>());
        setCartTotalPrice(0L);
    }

    public Long getCartId() { return cartId; }

    public HashMap<Product, Long> getCartItems() { return cartItems; }

    public void setCartItems(HashMap<Product, Long> cartItems) { this.cartItems = cartItems; }

    public Long getCartTotalPrice() { return cartTotalPrice; }

    public void setCartTotalPrice(Long cartTotalPrice) { this.cartTotalPrice = cartTotalPrice; }

    public void addCartItem(Product item) {
        if(cartItems.containsKey(item)){
            cartItems.put(item, cartItems.get(item) + 1);
        } else {
            cartItems.put(item,1L);
        }
        updateCartTotal();
    }

    public void removeCartItem(Product item) {
        cartItems.remove(item);
        updateCartTotal();
    }

    private void updateCartTotal() {
        cartTotalPrice = 0L;
        for(Map.Entry<Product, Long> entry : cartItems.entrySet())
            cartTotalPrice += (entry.getValue() * entry.getKey().getPrice());
    }
}
