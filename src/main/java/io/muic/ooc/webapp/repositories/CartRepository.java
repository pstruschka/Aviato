package io.muic.ooc.webapp.repositories;

import io.muic.ooc.webapp.entities.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long>{

    List<Cart> findByStatus(String status);
    List<Cart> findById(long id);
}
