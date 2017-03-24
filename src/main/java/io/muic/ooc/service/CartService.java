package io.muic.ooc.service;

import io.muic.ooc.model.Cart;
import io.muic.ooc.model.Product;
import io.muic.ooc.model.User;
import java.util.List;

public interface CartService {
    public Cart findCartWithUnconfirmedOrderByUserId(User user);

    
}
