package io.muic.ooc.model;

/**
 * Created by Don on 3/22/2017 AD.
 */

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

public class CartItem {

    @ManyToOne
    @JoinTable(name = "cart_product" , joinColumns = @JoinColumn(name = "cart_id", referencedColumnName = "cart_id"),
            inverseJoinColumns = @JoinColumn(name="product_id", referencedColumnName = "id", table = "product"))
    private Cart cart;

    @ManyToOne
    @JoinTable
    private User user;
    private Product product;
    private Integer quantity;
    private BigDecimal totalPrice;

    public CartItem(){

    }

    public CartItem(Product product){
        super();
        this.setProduct(product);
        this.setQuantity(1);
        this.setTotalPrice(product.getPrice());
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        this.updateTotalPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.updateTotalPrice();
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    private void updateTotalPrice() {
        totalPrice = this.product.getPrice().multiply(BigDecimal.valueOf(this.quantity));
    }
}
