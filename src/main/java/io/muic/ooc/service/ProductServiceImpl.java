package io.muic.ooc.service;

import io.muic.ooc.model.Product;
import io.muic.ooc.model.Role;
import io.muic.ooc.model.User;
import io.muic.ooc.repository.ProductRepository;
import io.muic.ooc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void removeProduct(Product product,User user) {
        product.setRating(0);
        product.setUser(user);
        productRepository.delete(product);
    }

    @Override
    public void removeProductByProductNameAndUser(String productName,User user) {
        List<Product> products = productRepository.findProductsByUser(user);
        for (Product product : products) {
            System.out.println(product.getProductName());
            if (product.getProductName().equals(productName)) {
                productRepository.delete(product);
            }
        }
    }

    @Override
    public Product findProductByProductNameAndUser(String productName,User user) {
        List<Product> products = productRepository.findProductsByUser(user);
        Product searchedProduct = null;
        for (Product product : products) {
            System.out.println(product.getProductName());
            if (product.getProductName().equals(productName)) {
                searchedProduct = product;
            }
            else{
                System.out.println("ERROR: Couldn't find product "+ productName + " for user " + user.getUsername()+ "." );
            }
        }
        return searchedProduct;
    }



    @Override
    public List<Product> findProductsByUser(User user){
        List<Product> products = productRepository.findProductsByUser(user);
        return products;
    }

    public Iterable<Product> findAllProducts() {
       Iterable<Product> allProducts = productRepository.findAll();
       return allProducts;
    }
}
