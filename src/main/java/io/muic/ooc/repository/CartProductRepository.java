package io.muic.ooc.repository;

import io.muic.ooc.model.Cart;
import io.muic.ooc.model.CartProduct;
import io.muic.ooc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct , Long> {
    Set<CartProduct> findByCart(Cart cart);
    CartProduct findByCartEqualsAndProductEquals(Cart cart, Product product);
}
