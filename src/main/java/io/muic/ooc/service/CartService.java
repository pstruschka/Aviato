package io.muic.ooc.service;

import io.muic.ooc.model.Cart;
import io.muic.ooc.model.Product;
import io.muic.ooc.model.User;
import java.util.List;
import java.util.Set;

public interface CartService {
    public Cart findCartWithUnconfirmedOrderByUserId(User user);
    public Set<Cart> findCartsWithConfrimedOrderByUserId(User user);
    public boolean confirmOrderOfCart(Cart cart);

    
}
