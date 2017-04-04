package io.muic.ooc.service;

import io.muic.ooc.model.Cart;
import io.muic.ooc.model.CartProduct;
import io.muic.ooc.model.Product;
import io.muic.ooc.repository.CartProductRepository;
import io.muic.ooc.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by joakimnilfjord on 3/24/2017 AD.
 */
@Service("CartProductService")
public class CartProductServiceImpl implements  CartProductService {
    @Autowired
    CartProductRepository cartProductRepository;

    @Autowired
    CartRepository cartRepository;

    @Override
    public CartProduct updateCartProduct(Product product, Cart cart,Long quantity) {
        Iterable<CartProduct> allCartProducts = cartProductRepository.findAll();
        if (allCartProducts == null) {
            CartProduct cartProduct = new CartProduct();
            cartProduct.setProduct(product);
            cartProduct.setCart(cart);
            cartProduct.setQuantity(quantity);
            cartProduct.setPriceBoughtAt(product.getPrice());
            cartProductRepository.save(cartProduct);
            return cartProduct;
        }
        for (CartProduct cp:  allCartProducts) {
            if (cp.getCart().equals(cart) && cp.getProduct().equals(product)) {
                quantity = cp.getQuantity() + quantity;
                cp.setQuantity(quantity);
                cp.setPriceBoughtAt(product.getPrice());
                cartProductRepository.save(cp);
                return cp;
            }
        }

        CartProduct cartProduct = new CartProduct();
        cartProduct.setProduct(product);
        cartProduct.setPriceBoughtAt(product.getPrice());
        cartProduct.setCart(cart);
        cartProduct.setQuantity(quantity);
        cartProductRepository.save(cartProduct);
        return cartProduct;

        }

    @Override
    public Set<CartProduct> findCartProductsByCart(Cart cart) {
        Iterable<CartProduct> allCartProducts = cartProductRepository.findAll();
        Set<CartProduct> cartProducts = new HashSet<>();
        for (CartProduct cp:  allCartProducts) {
            if (cp.getCart().equals(cart)) {
                if (cp.getProduct().getSelling()){
                    cartProducts.add(cp);
                }else {
                    cartProductRepository.delete(cp);
                }
            }
        }
        return cartProducts;
    }


    @Override
    public Set<String> findCartProductsByCartAndCompareProductPricevsBoughtAtPrice(Cart cart) {
        Iterable<CartProduct> allCartProducts = cartProductRepository.findAll();
        Set<String> cartProducts = new HashSet<>();
        for (CartProduct cp:  allCartProducts) {
            if (cp.getCart().equals(cart)) {
                if (!Objects.equals(cp.getProduct().getPrice(), cp.getPriceBoughtAt())){
                    Product product = cp.getProduct();
                    String priceChanged = String.format("Product : %s, Old Price : %d, New Price : %d"
                            ,product.getProductName(), cp.getPriceBoughtAt(),product.getPrice());
                    cartProducts.add(priceChanged);
                }
            }
        }
        return cartProducts;
    }

    @Override
    public Long getTotalPrice(Set<CartProduct> cartProductSet) {
       Long totalPrice = 0L;
        if (cartProductSet != null) {
            for (CartProduct cartProduct: cartProductSet) {
                totalPrice +=  cartProduct.getQuantity() * cartProduct.getProduct().getPrice();

            }
        }
        return totalPrice;

    }

    @Override
    public CartProduct findCartProductsById(Long cartProductId) {
        return cartProductRepository.findOne(cartProductId);

    }

    @Override
    public boolean remove(Long cartProductId){
        cartProductRepository.delete(cartProductId);
        return true;
    }

    @Override
    public Set<CartProduct> findCartProducts(Cart cart) {
        Iterable<CartProduct> allCartProducts = cartProductRepository.findAll();
        Set<CartProduct> cartProducts = new HashSet<>();
        for (CartProduct cp:  allCartProducts) {
            if (cp.getCart().equals(cart)) {
                cartProducts.add(cp);
            }
        }
        return cartProducts;
    }


}

