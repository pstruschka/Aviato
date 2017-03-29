package io.muic.ooc.service;

import io.muic.ooc.model.Cart;
import io.muic.ooc.model.CartProduct;
import io.muic.ooc.model.Product;
import io.muic.ooc.repository.CartProductRepository;
import io.muic.ooc.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
            cartProductRepository.save(cartProduct);
            return cartProduct;
        }
        for (CartProduct cp:  allCartProducts) {
            if (cp.getCart().equals(cart) && cp.getProduct().equals(product)) {
                quantity = cp.getQuantity() + quantity;
                cp.setQuantity(quantity);
                cartProductRepository.save(cp);
                return cp;
            }
        }

        CartProduct cartProduct = new CartProduct();
        cartProduct.setProduct(product);
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
}

