package io.muic.ooc.service;

import io.muic.ooc.model.Product;
import io.muic.ooc.model.User;

import java.util.List;


/**
 * Created by joakimnilfjord on 3/11/2017 AD.
 */
public interface ProductService {
    //public Product findProductByProductName(String productname);
    public List<Product> findProductsByUser(User user);
    public void saveProduct(Product product,User user);
    public void removeProduct(Product product,User user);
    public Product findProductByIdAndUser(long id, User user);
    public Iterable<Product> findAllProducts();



}
