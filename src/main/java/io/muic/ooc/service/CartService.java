package io.muic.ooc.service;

import io.muic.ooc.model.Cart;
import io.muic.ooc.model.CartProduct;
import io.muic.ooc.model.Product;
import io.muic.ooc.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface CartService {
    public Cart findCartWithUnconfirmedOrderByUserId(User user);
    public Set<Cart> findCartsWithConfrimedOrderByUserId(User user);
    public boolean canConfirmOrderOfCart(Cart cart);
    public void confirmOrderOfCart(Cart cart);
    public Set<Set<CartProduct>> getAllCartProducts(Set<Cart> carts);

    
}
