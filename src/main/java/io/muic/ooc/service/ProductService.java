package io.muic.ooc.service;

import io.muic.ooc.model.Cart;
import io.muic.ooc.model.Product;
import io.muic.ooc.model.User;

import java.util.List;
import java.util.Set;


/**
 * Created by joakimnilfjord on 3/11/2017 AD.
 */
public interface ProductService {

    List<Product> findProductsByUser(User user);
    void saveProduct(Product product,User user);
    void removeProduct(Product product);
    List<Product> findAllProducts();
    Product findProductById(Long id);
    Boolean updateProductQuantity(Product product,Long quantity);
    List<Product> findProductsInStock();
    void updateHistory(Cart cart);
    Set<Product> findProductsByKeyword(String keyword);

    //public Product findProductByProductName(String productname);
//    public List<Product> findProductsByUser(User user);
//    public void saveProduct(Product product,User user);
//    public List<Product> findAllProducts();
//    public Product findProductById(Long id);
//    public Boolean updateProductQuantity(Product product,Long quantity);
//    public List<Product> findProductsInStock();
}
