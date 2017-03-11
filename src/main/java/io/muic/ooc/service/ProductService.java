package io.muic.ooc.service;

import io.muic.ooc.model.Product;
import io.muic.ooc.model.User;

import java.util.Set;

/**
 * Created by joakimnilfjord on 3/11/2017 AD.
 */
public interface ProductService {
    //public Product findProductByProductName(String productname);
    public void saveProduct(Product product,User user);


}
