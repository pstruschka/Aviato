package io.muic.ooc.service;

import io.muic.ooc.model.Product;
import io.muic.ooc.model.Role;
import io.muic.ooc.model.User;
import io.muic.ooc.repository.ProductRepository;
import io.muic.ooc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("productService")
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void saveProduct(Product product,User user) {
        product.setRating(0);
        product.setUser(user);
        productRepository.save(product);
    }

    @Override
    public List<Product> findProductsByUser(User user){
        List<Product> products = productRepository.findProductsByUser(user);
        return products;
    }

    public List<Product> findAllProducts() {
       List<Product> allProducts = new ArrayList<>();
       for (Product p: productRepository.findAll()) {
           allProducts.add(p);
       }
       return allProducts;
    }

    public void updateProductQuantity(Product product) {
        Long quantity = product.getQuantity();
        quantity -=1;
        product.setQuantity(quantity);
        productRepository.save(product);
    }

    public Product findProductById(Long id) {
        Product product = productRepository.findOne(id);
        return product;
    }
}
