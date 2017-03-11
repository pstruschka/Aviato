package io.muic.ooc.repository;

/**
 * Created by joakimnilfjord on 3/10/2017 AD.
 */
import io.muic.ooc.model.Product;
import io.muic.ooc.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findProductsByUser(User user_id);

}
