package io.muic.ooc.service;

/**
 * Created by Don on 3/13/2017 AD.
 */


import io.muic.ooc.model.Cart;
import io.muic.ooc.model.CartProduct;
import io.muic.ooc.model.Product;
import io.muic.ooc.model.User;
import io.muic.ooc.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("cartService")
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;


    @Override
    public Cart findCartWithUnconfirmedOrderByUserId(User user) {
        Cart newCart= new Cart();
        System.out.println("INSIDE FUNCTION:  "+ user);
        Iterable<Cart> allCarts = cartRepository.findAll();
        for (Cart cart: allCarts) {
            if (!cart.isOrderConfirmed() && cart.getUser().equals(user)) {
                return cart;
            }
        }
        newCart.setUser(user);
        newCart.setOrderConfirmed(false);
        cartRepository.save(newCart);
        return newCart;
    }













}