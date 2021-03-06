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
    public ModelAndView viewUserProducts(@RequestParam(value="search",required=false, defaultValue = "") String search) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("buyer/viewproducts");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        if (search == null | search.equals("")){
            ArrayList<Product> userProducts = new ArrayList<>(productService.findProductsInStock());
            modelAndView.addObject("products", userProducts);
        }else{
            ArrayList<Product> userProducts = new ArrayList<>(productService.findProductsByKeyword(search));
            String searchQueryTitle = user.getName() + " | " + userProducts.size() + " result(s) for " + search + "";
            modelAndView.addObject("products", userProducts);
            modelAndView.addObject("search",search);
            modelAndView.addObject("searchQueryTitle", searchQueryTitle);

        }
        Cart cart = cartService.findCartWithUnconfirmedOrderByUserId(user);
        modelAndView.addObject("cart",cart.getCartId());
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/buyer/products",method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView buyProducts(@ModelAttribute("product") Long productId,
                                    @RequestParam("quantity") Long quantity, Model model) {
        ModelAndView modelAndView = new ModelAndView("buyer/viewproducts");
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
            productService.updateProductQuantity(product,quantity);
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
