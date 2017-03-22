package io.muic.ooc.model;

/**
 * Created by Don on 3/13/2017 AD.
 */

import javax.persistence.*;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="cart_id")
    private Long cartId;

//    @OneToMany
//    @JoinTable(name = "cart_product" , joinColumns = @JoinColumn(name = "cart_id", referencedColumnName = "cart_id"),
//            inverseJoinColumns = @JoinColumn(name="product_id", referencedColumnName = "id", table = "product"))
//    private HashMap<Product, Integer> cartItems;

    //private BigDecimal cartTotalPrice;

    private User user;


    public Long getCartId() { return cartId; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //public HashMap<Product, Integer> getCartItems() { return cartItems; }

    //public void setCartItems(HashMap<Product, Integer> cartItems) { this.cartItems = cartItems; }

    //public BigDecimal getCartTotalPrice() { return cartTotalPrice; }

    //public void setCartTotalPrice(BigDecimal cartTotalPrice) { this.cartTotalPrice = cartTotalPrice; }

//    public void addCartItem(Product item) {
//        if(cartItems.containsKey(item)){
//            cartItems.put(item, cartItems.get(item)+1);
//        } else {
//            cartItems.put(item,1);
//        }
//        updateCartTotal();
//    }
//
//    public void removeCartItem(Product item) {
//        cartItems.remove(item);
//        updateCartTotal();
//    }

//    private void updateCartTotal() {
//        cartTotalPrice = BigDecimal.valueOf(0);
//        for(Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
//            double quant = entry.getValue().doubleValue();
//            double price = entry.getKey().getPrice().doubleValue();
//            cartTotalPrice.add(BigDecimal.valueOf(quant*price));
//        }
//    }
}
