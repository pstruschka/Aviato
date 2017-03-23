package io.muic.ooc.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "cart_product")
public class CartProduct implements Serializable{

    @Id
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    public CartProduct(){

    }

    public CartProduct(Product product){
        super();
        this.setProduct(product);
        this.setQuantity(1);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
