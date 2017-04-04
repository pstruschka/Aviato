package io.muic.ooc.service;

import io.muic.ooc.model.Cart;
import io.muic.ooc.model.CartProduct;
import io.muic.ooc.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by joakimnilfjord on 3/24/2017 AD.
 */
public interface CartProductService {
    public CartProduct updateCartProduct(Product product, Cart cart,Long quantity);
    public Set<CartProduct> findCartProductsByCart(Cart cart);
    public Long getTotalPrice(Set<CartProduct> cartProductSet);
    public CartProduct findCartProductsById(Long cartProductId);
    public boolean remove(Long cartProductId);
    public Set<CartProduct> findCartProducts(Cart cart);
    public Set<String> findCartProductsByCartAndCompareProductPriceVsBoughtAtPrice(Cart cart);
    public Set<CartProduct> findCartProductsByProduct(List<Product> productsOfSeller);
}
