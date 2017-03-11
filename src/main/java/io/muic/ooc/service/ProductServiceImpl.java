package io.muic.ooc.service;

import io.muic.ooc.model.Product;
import io.muic.ooc.model.Role;
import io.muic.ooc.model.User;
import io.muic.ooc.repository.ProductRepository;
import io.muic.ooc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by joakimnilfjord on 3/11/2017 AD.
 */
@Service("productService")
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void saveProduct(Product product,User user) {
        product.setUser(user);
        productRepository.save(product);

    }






}