package io.muic.ooc.controller;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(value="/buyer/products",method = RequestMethod.GET)
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

    @RequestMapping(value = "/buyer/searchproducts",method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView searchProducts(@RequestParam("search") String search) {
        ModelAndView modelAndView = new ModelAndView("/buyer/searchproducts");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        System.out.println(user.getUsername()+" queried for "+ search);
        ArrayList<Product> userProducts = new ArrayList<>(productService.findProductsByKeyword(search));
        modelAndView.addObject("products", userProducts);
        modelAndView.addObject("user",user);
        modelAndView.addObject("search",search);
        return modelAndView;
    }

    @RequestMapping(value = "/buyer/products",method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView buyProducts(@ModelAttribute("product") Long productId,
                                    @RequestParam("quantity") Long quantity, Model model) {
        ModelAndView modelAndView = new ModelAndView("/buyer/viewproducts");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        Product product = productService.findProductById(productId);
        Cart cart = cartService.findCartWithUnconfirmedOrderByUserId(user);
        if (quantity > product.getQuantity()){
            modelAndView.addObject("errorProduct", product.getId());
            modelAndView.addObject("errorMessage","Not enough in stock!");
        }else if (quantity <= 0){
            modelAndView.addObject("errorProduct", product.getId());
            modelAndView.addObject("errorMessage","Put a positive value.");
        }
        else{
            modelAndView.addObject("successProduct", product.getId());
            modelAndView.addObject("successMessage","Added " + quantity.toString() + " to Cart");
            productService.updateProductQuantity(product,quantity,"subtract");
            cartProductService.updateCartProduct(product, cart, quantity);
        }
        // productService.updateProductQuantity(product,quantity);
        ArrayList<Product> userProducts = new ArrayList<>(productService.findProductsInStock());
        modelAndView.addObject("products", userProducts);
        modelAndView.addObject("cart",cart.getCartId());
        modelAndView.addObject("user",user);
        return modelAndView;
    }
}
