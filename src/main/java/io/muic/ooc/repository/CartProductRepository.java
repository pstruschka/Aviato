package io.muic.ooc.repository;
import io.muic.ooc.model.Cart;
import io.muic.ooc.model.CartProduct;
import io.muic.ooc.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by joakimnilfjord on 3/24/2017 AD.
 */
@Repository
public interface CartProductRepository extends CrudRepository<CartProduct , Long>{




}
