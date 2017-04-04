package io.muic.ooc.service;

import io.muic.ooc.model.Cart;
import io.muic.ooc.model.CartProduct;
import io.muic.ooc.model.User;
import io.muic.ooc.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service("cartService")
public class
CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartProductService cartProductService;

    @Override
    public Cart findCartWithUnconfirmedOrderByUserId(User user) {
        Cart cart = cartRepository.findCartByUserAndOrderConfirmedIsFalse(user);
        if (cart == null){
            cart = new Cart();
            cart.setUser(user);
            cart.setOrderConfirmed(false);
            cartRepository.save(cart);
        }
        return cart;
    }

    @Override
    public Set<Cart> findCartsWithConfirmedOrderByUserId(User user) {
        return cartRepository.findByUserAndOrderConfirmedIsTrue(user);
    }

    @Override
    public boolean canConfirmOrderOfCart(Cart cart) {
        Set<CartProduct> cartProductSet = cartProductService.findCartProductsByCart(cart);
        if ( cartProductSet == null || cartProductSet.size()== 0) {
            return false;
        }
        return true;
    }

    @Override
    public void confirmOrderOfCart(Cart cart) {
        cart.setOrderConfirmed(true);
        cartRepository.save(cart);
    }
}