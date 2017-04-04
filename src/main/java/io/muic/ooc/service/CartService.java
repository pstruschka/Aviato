package io.muic.ooc.service;

import io.muic.ooc.model.Cart;
import io.muic.ooc.model.CartProduct;
import io.muic.ooc.model.Product;
import io.muic.ooc.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface CartService {
    Cart findCartWithUnconfirmedOrderByUserId(User user);
    Set<Cart> findCartsWithConfirmedOrderByUserId(User user);
    boolean canConfirmOrderOfCart(Cart cart);
    void confirmOrderOfCart(Cart cart);
}
