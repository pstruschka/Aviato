package io.muic.ooc.repository;

import io.muic.ooc.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Don on 3/13/2017 AD.
 */
@Repository("cartRepository")
public interface CartRepository extends CrudRepository<Cart, Long>{
}