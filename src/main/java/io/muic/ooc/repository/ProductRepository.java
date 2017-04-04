package io.muic.ooc.repository;

import io.muic.ooc.model.Product;
import io.muic.ooc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository("ProductRepository")
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByUser(User user);
    List<Product> findProductsByUserAndIsSellingIsTrue(User user);
    List<Product> findProductsByQuantityGreaterThanAndIsSellingIsTrue(long quantity);
    //Set<Product> findProductsByProductNameContainsOrDescriptionContains(String k1, String k2);
    //if (p.getProductName().contains(keyword) || p.getDescription().contains(keyword) ||
    // p.getUser().getName().contains(keyword))
    @Override
    List<Product> findAll();
}
