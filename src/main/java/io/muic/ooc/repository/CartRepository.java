package io.muic.ooc.repository;

import io.muic.ooc.model.Cart;
import org.springframework.stereotype.Repository;

/**
 * Created by Don on 3/13/2017 AD.
 */
@Repository("cartRepository")
public interface CartRepository {
    Cart create (Cart cart);
    Cart read(String cartId);
    void update(String cartId, Cart cart);
    void delete(String cartId);
}