package io.muic.ooc.controller;


import com.sun.org.apache.xpath.internal.operations.Mod;
import io.muic.ooc.model.Cart;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;

/**
 * Created by joakimnilfjord on 3/13/2017 AD.
 */
@Controller
public class ViewItemsController {

    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;


    @Autowired
    CartProductService cartProductService;

    @Autowired
    CartService cartService;



    @RequestMapping(value="/buyer/viewproducts",method = RequestMethod.GET)
    public ModelAndView viewUserProducts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/buyer/viewproducts");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userService.findUserByUsername(auth.getName());
        ArrayList<Product> userProducts = new ArrayList<>(productService.findProductsInStock());
        Cart cart = cartService.findCartWithUnconfirmedOrderByUserId(user);
        modelAndView.addObject("cart",cart.getCartId());


        modelAndView.addObject("user", user);
        modelAndView.addObject("products", userProducts);
        return modelAndView;
    }

    @RequestMapping(value = "/buyer/buy",method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView buyProducts(@ModelAttribute("product") Long productId,
                                    @RequestParam("quantity") Long quantity) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        Product product = productService.findProductById(productId);
        Cart cart = cartService.findCartWithUnconfirmedOrderByUserId(user);
        cartProductService.updateCartProduct(product, cart, quantity);
        // productService.updateProductQuantity(product,quantity);
        ModelAndView modelAndView = new ModelAndView();
        ArrayList<Product> userProducts = new ArrayList<>(productService.findProductsInStock());
        modelAndView.addObject("products", userProducts);


        modelAndView.addObject("cart",cart.getCartId());


        modelAndView.addObject("user",user);
        modelAndView.setViewName("/buyer/viewproducts");
        return modelAndView;

    }

}
