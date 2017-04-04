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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service("cartService")
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    CartProductService cartProductService;

    @Autowired
    ProductService productService;

    @Override
    public Cart findCartWithUnconfirmedOrderByUserId(User user) {
        Cart newCart= new Cart();
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

    @Override
    public Set<Cart> findCartsWithConfrimedOrderByUserId(User user) {
        Iterable<Cart> carts = cartRepository.findAll();
        Set<Cart> cartSet = new HashSet<>();
        for (Cart cart: carts) {
            if (cart.isOrderConfirmed() && cart.getUser()==user) {
                cartSet.add(cart);
            }
        }
        return cartSet;
    }


    @Override
    public boolean canConfirmOrderOfCart(Cart cart) {
        Set<CartProduct> cartProductSet = cartProductService.findCartProductsByCart(cart);
        if ( cartProductSet == null || cartProductSet.size()== 0) {
            return false;
        }

        cart.setOrderConfirmed(true);
        cartRepository.save(cart);
        return true;
    }

    @Override
    public void confirmOrderOfCart(Cart cart) {
        cart.setOrderConfirmed(true);
        cartRepository.save(cart);
    }

    @Override
    public Set<Set<CartProduct>> getAllCartProducts(Set<Cart> carts){
        Set<Set<CartProduct>> set = new HashSet<>();
        for (Cart c : carts){
            set.add(cartProductService.findCartProducts(c));
        }
        return set;
    }


}