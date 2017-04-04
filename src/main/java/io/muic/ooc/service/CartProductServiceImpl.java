package io.muic.ooc.service;

import io.muic.ooc.model.Cart;
import io.muic.ooc.model.CartProduct;
import io.muic.ooc.model.Product;
import io.muic.ooc.repository.CartProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service("CartProductService")
public class CartProductServiceImpl implements CartProductService {

    @Autowired
    private CartProductRepository cartProductRepository;

    @Override
    public CartProduct updateCartProduct(Product product, Cart cart,Long quantity) {
        CartProduct cartProduct = cartProductRepository.findByCartEqualsAndProductEquals(cart, product);
        if (cartProduct == null) {
            cartProduct = new CartProduct();
            cartProduct.setQuantity(0L);
//            cartProductRepository.save(cartProduct);
//            return cartProduct;
        }
        quantity = cartProduct.getQuantity() + quantity;
        cartProduct.setQuantity(quantity);
        cartProduct.setPriceBoughtAt(product.getPrice());
        cartProduct.setProduct(product);
        cartProduct.setCart(cart);
        cartProductRepository.save(cartProduct);
        return cartProduct;
    }

    @Override
    public Set<CartProduct> findCartProductsByCart(Cart cart) {
        Set<CartProduct> allCartProducts = cartProductRepository.findByCart(cart);
        Set<CartProduct> cartProducts = new HashSet<>();
        for (CartProduct cp:  allCartProducts) {
            if (cp.getProduct().getSelling()){
                cartProducts.add(cp);
            }else {
                cartProductRepository.delete(cp);
            }
        }
        return cartProducts;
    }

    @Override
    public Set<String> findCartProductsByCartAndCompareProductPriceVsBoughtAtPrice(Cart cart) {
        Set<String> cartProducts = new HashSet<>();
        Set<CartProduct> cartProductsOfCart = cartProductRepository.findByCart(cart);
        for (CartProduct cp:  cartProductsOfCart) {
            if (!Objects.equals(cp.getProduct().getPrice(), cp.getPriceBoughtAt())){
                Product product = cp.getProduct();
                String priceChanged = String.format("Product : %s, Old Price : %d, New Price : %d"
                        ,product.getProductName(), cp.getPriceBoughtAt(),product.getPrice());
                cartProducts.add(priceChanged);
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
        return cartProductRepository.findByCart(cart);
    }

    @Override
    public Set<CartProduct> findCartProductsByProduct(List<Product> productOfSeller) {
        Iterable<CartProduct> allCartProducts = cartProductRepository.findAll();
        Set<CartProduct> soldProducts = new HashSet<>();
        for (Product product:productOfSeller) {
            for (CartProduct cartProduct: allCartProducts) {
                if (cartProduct.getCart().isOrderConfirmed() && cartProduct.getProduct().equals(product)) {
                    soldProducts.add(cartProduct);
                }
            }
        }
        return soldProducts;
    }
}

