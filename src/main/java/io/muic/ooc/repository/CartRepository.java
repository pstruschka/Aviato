package io.muic.ooc.repository;

import io.muic.ooc.model.Cart;
import io.muic.ooc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
    Set<Cart> findByUserAndOrderConfirmedIsTrue(User user);
    Cart findCartByUserAndOrderConfirmedIsFalse(User user);
}