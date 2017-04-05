package io.muic.ooc.service;

import io.muic.ooc.model.Cart;
import io.muic.ooc.model.CartProduct;
import io.muic.ooc.model.Product;
import io.muic.ooc.model.User;
import io.muic.ooc.repository.ProductRepository;
import io.muic.ooc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service("productService")
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveProduct(Product product,User user) {
        product.setSelling(true);
        product.setRating(0);
        product.setUser(user);
        product.setProfit(0L);
        product.setQuantitySold(0L);
        productRepository.save(product);
    }

    @Override
    public void removeProduct(Product product) {
        product.setSelling(false);
        productRepository.save(product);
    }

    @Override
    public List<Product> findProductsByUser(User user){
        return productRepository.findProductsByUserAndIsSellingIsTrue(user);
    }

    public List<Product> findAllProducts() {
       return productRepository.findAll();
    }

    public List<Product> findProductsInStock() {
        List<Product> prod = productRepository.findProductsByQuantityGreaterThanAndIsSellingIsTrue(0L);
        return prod;
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
        return productRepository.findOne(id);
    }

    public Set<Product> findProductsByKeyword(String keyword) {
        List<Product> productsInStock = findProductsInStock();
        Set<Product> productsThatMatchKeyword = new HashSet<>();
        String keywordLower = keyword.toLowerCase();
        for (Product p: productsInStock){
            String productName = p.getProductName().toLowerCase();
            String description = p.getDescription().toLowerCase();
            String sellerName = p.getUser().getName().toLowerCase();
            if (productName.contains(keywordLower) || description.contains(keywordLower) || sellerName.contains(keywordLower)) {
                productsThatMatchKeyword.add(p);
            }
        }
        return productsThatMatchKeyword;
    }

    public void updateHistory(Cart cart){
        for(CartProduct cartProduct: cart.getCartProducts()){
            Product product = cartProduct.getProduct();
            Long totalCartProduct = cartProduct.getTotal();
            Long totalProfit = product.getProfit();
            Long cpQuantity = cartProduct.getQuantity();
            Long productQuantity = product.getQuantitySold();
            product.setQuantitySold(cpQuantity + productQuantity);
            product.setProfit(totalCartProduct + totalProfit);
            productRepository.save(product);

        }
    }

}
