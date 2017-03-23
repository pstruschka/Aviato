package io.muic.ooc.service;

import io.muic.ooc.model.Product;
import io.muic.ooc.model.User;

import java.util.List;


/**
 * Created by joakimnilfjord on 3/11/2017 AD.
 */
public interface ProductService {
    List<Product> findProductsByUser(User user);
    void saveProduct(Product product,User user);
    Iterable<Product> findAllProducts();



}
