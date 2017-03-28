package io.muic.ooc.repository;

import io.muic.ooc.model.Cart;
import io.muic.ooc.model.CartProduct;
import io.muic.ooc.model.Product;
import io.muic.ooc.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by Don on 3/13/2017 AD.
 */
@Repository("cartRepository")
public interface CartRepository extends CrudRepository<Cart, Long>{
    List<Cart> findByUserId(User user);
}