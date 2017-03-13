package io.muic.ooc.service;

import io.muic.ooc.model.Cart;

/**
 * Created by Don on 3/13/2017 AD.
 */
public interface CartService {
    Cart create(Cart cart);
    Cart read(String cartId);
    void update(String cartId, Cart cart);
    void delete(String cartId);
}
