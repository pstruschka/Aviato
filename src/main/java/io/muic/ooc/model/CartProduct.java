package io.muic.ooc.model;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "cart_product")
public class CartProduct implements Serializable{


    public Long getCartProductId() {
        return cartProductId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="cart_product_id")
    Long cartProductId;

    @ManyToOne()
    @JoinColumn(name = "cart_id")
    private Cart cart;


    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private Long quantity;



    private Long priceBoughtAt;

//    public CartProduct(){
//    }
//    public CartProduct( productId,Long CartId ){
//        super();
//        this.setCartId(getCartId());
//        this.setProductId(productId);
//        this.setQuantity(1L);
//    }
//    public Cart getCart() {
//        return cart;
//    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getPriceBoughtAt() {
        return priceBoughtAt;
    }

    public void setPriceBoughtAt(Long priceBoughtAt) {
        this.priceBoughtAt = priceBoughtAt;
    }

    public Long getTotal(){
        Long total =  this.getQuantity() * this.getPriceBoughtAt();
        return total;
    }

}
