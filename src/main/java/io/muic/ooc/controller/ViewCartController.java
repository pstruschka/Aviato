package io.muic.ooc.controller;

import io.muic.ooc.model.Cart;
import io.muic.ooc.model.CartProduct;
import io.muic.ooc.model.Product;
import io.muic.ooc.model.User;
import io.muic.ooc.service.CartProductService;
import io.muic.ooc.service.CartService;
import io.muic.ooc.service.ProductService;
import io.muic.ooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by joakimnilfjord on 3/24/2017 AD.
 */
@Controller
public class ViewCartController {
    @Autowired
    UserService userService;

    @Autowired
    CartProductService cartProductService;

    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    @RequestMapping(value="/buyer/viewcart",method = RequestMethod.GET)
    public ModelAndView viewCart(@RequestParam("cart") Cart cart ){
        Long totalPrice;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/buyer/viewcart");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        Set<CartProduct> cartProducts = new HashSet<>(cartProductService.findCartProductsByCart(cart));
        totalPrice = cartProductService.getTotalPrice(cartProducts);
        modelAndView.addObject("totalPrice",totalPrice);
        modelAndView.addObject("cart",cart);
        modelAndView.addObject("cartProducts",cartProducts);
        modelAndView.addObject("user", user);
        modelAndView.addObject("cartProducts",cartProducts);

        return modelAndView;
    }
    @RequestMapping(value = "/buyer/viewcart",method = RequestMethod.POST)
    public ModelAndView confirmCart(@RequestParam("cart") Cart cart){
        cart.setDateTimeField(new Date());
        cartService.confirmOrderOfCart(cart);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        Cart cart1 = cartService.findCartWithUnconfirmedOrderByUserId(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",user);
        modelAndView.addObject("cart",cart1);
        modelAndView.addObject("totalPrice",0);
        productService.updateHistory(cart);
        modelAndView.setViewName("buyer/viewcart");
        return modelAndView;
    }

    @RequestMapping(value = "/buyer/payment",method = RequestMethod.GET)
    public ModelAndView checkout(@RequestParam("cart") Cart cart){
        Long totalPrice;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        ModelAndView modelAndView = new ModelAndView();
        Set<CartProduct> cartProducts = new HashSet<>(cartProductService.findCartProductsByCart(cart));
        totalPrice = cartProductService.getTotalPrice(cartProducts);
        modelAndView.addObject("totalPrice",totalPrice);
        modelAndView.addObject("user",user);
        modelAndView.addObject("cart",cart);

        Set<String> diffProductPricethanPriceBoughtAt = cartProductService.findCartProductsByCartAndCompareProductPriceVsBoughtAtPrice(cart);

        if (diffProductPricethanPriceBoughtAt.size() != 0) {
            modelAndView.addObject("cartProducts",cartProducts);
            modelAndView.setViewName("/buyer/viewcart");
            modelAndView.addObject("priceChanged",diffProductPricethanPriceBoughtAt);
            return modelAndView;
        }
        boolean canBeConfirmed = cartService.canConfirmOrderOfCart(cart);

        if (!canBeConfirmed) {
            String emptyMsg = "Your cart is empty";
            modelAndView.addObject("cart",cart);
            modelAndView.addObject("emptyMsg",emptyMsg);
            modelAndView.setViewName("/buyer/viewcart");
            return modelAndView;
        }
        modelAndView.setViewName("/buyer/payment");
        return modelAndView;
    }

    @RequestMapping(value = "/buyer/delete",method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam("cart_product_id") Long cartProductId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        ModelAndView modelAndView = new ModelAndView();
        CartProduct cartProduct =  cartProductService.findCartProductsById(cartProductId);
        productService.updateProductQuantity(cartProduct.getProduct(),-cartProduct.getQuantity());
        cartProductService.remove(cartProductId);


        Cart cart = cartService.findCartWithUnconfirmedOrderByUserId(user);
        Long totalPrice = 0L;
        Set<CartProduct> cartProducts = new HashSet<>(cartProductService.findCartProductsByCart(cart));
        totalPrice = cartProductService.getTotalPrice(cartProducts);

        modelAndView.addObject("user",user);
        modelAndView.addObject("cartProducts",cartProducts);
        modelAndView.addObject("totalPrice",totalPrice);
        modelAndView.addObject("cart",cart);
        modelAndView.setViewName("buyer/viewcart");
        return modelAndView;

    }
}
