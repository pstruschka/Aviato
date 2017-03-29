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
        List<Cart> cartsByUser = cartRepository.findByUserId(user);
        Set<Cart> cartSet = new HashSet<>();
        for (Cart cart: cartsByUser) {
            if (cart.isOrderConfirmed()) {
                cartSet.add(cart);
            }
        }
        return cartSet;
    }


    @Override
    public boolean confirmOrderOfCart(Cart cart) {
        Set<CartProduct> cartProductSet = cartProductService.findCartProductsByCart(cart);
        if ( cartProductSet == null || cartProductSet.size()== 0) {
            return false;
        }

        cart.setOrderConfirmed(true);
        cartRepository.save(cart);
        return true;
    }


}