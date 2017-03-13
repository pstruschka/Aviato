package io.muic.ooc.service;

/**
 * Created by Don on 3/13/2017 AD.
 */

import io.muic.ooc.model.Cart;
import io.muic.ooc.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("cartService")
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart create(Cart cart) { return cartRepository.create(cart); }

    @Override
    public Cart read(String cartId) { return cartRepository.read(cartId); }

    @Override
    public void update(String cartId, Cart cart) { cartRepository.update(cartId, cart); }

    @Override
    public void delete(String cartId) { cartRepository.delete(cartId); }

}