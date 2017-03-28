package io.muic.ooc.service;

import io.muic.ooc.model.Product;
import io.muic.ooc.model.User;
import io.muic.ooc.repository.ProductRepository;
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
        product.setSelling(true);
        product.setRating(0);
        product.setUser(user);
        productRepository.save(product);
    }

    @Override
    public void removeProduct(Product product) {
        product.setSelling(false);
        productRepository.save(product);
    }

    @Override
    public List<Product> findProductsByUser(User user){
        List<Product> products = productRepository.findProductsByUser(user);
        List<Product> sellingProducts  = new ArrayList<>();
        for (Product p: products) {
            if (p.getSelling() == true) {
                sellingProducts.add(p);
            }
        }
        return sellingProducts;
    }

    public List<Product> findAllProducts() {
       List<Product> allProducts = new ArrayList<>();
       for (Product p: productRepository.findAll()) {
           allProducts.add(p);
       }
       return allProducts;
    }
    public List<Product> findProductsInStock() {
        List<Product> allProducts = new ArrayList<>();
        for (Product p: productRepository.findAll()) {
            if (p.getQuantity() > 0 && p.getSelling() == true) { allProducts.add(p);}

        }
        return allProducts;
    }

    public Boolean updateProductQuantity(Product product,Long selectedQuantity) {
        if (selectedQuantity <= product.getQuantity()) {
        Long quantity = product.getQuantity();
        quantity -=selectedQuantity;
        product.setQuantity(quantity);
        productRepository.save(product);
        return true;
        }
        else {
            return false;
        }
    }

    public Product findProductById(Long id) {
        Product product = productRepository.findOne(id);
        return product;
    }
}
