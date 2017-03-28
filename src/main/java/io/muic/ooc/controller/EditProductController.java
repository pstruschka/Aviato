package io.muic.ooc.controller;


import io.muic.ooc.model.Product;
import io.muic.ooc.model.User;
import io.muic.ooc.service.ProductService;
import io.muic.ooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * Created by joakimnilfjord on 3/11/2017 AD.
 */

@Controller
public class EditProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/edit_product", method = RequestMethod.POST)
    public ModelAndView editCurrentProduct(@Valid Product product,BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("seller/myproducts");


        if (!bindingResult.hasErrors()) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByUsername(auth.getName());
            //Product originalProduct = productService.findProductByIdAndUser(product.getId(),user);
            //productService.removeProduct(originalProduct, user);
            productService.saveProduct(product,user);
            modelAndView.addObject("successMessage", "Product edited successfully");
            //modelAndView.addObject("product", new Product());
            ArrayList<Product> userProducts = new ArrayList<>(productService.findProductsByUser(user));
            modelAndView.addObject("user", user);
            modelAndView.addObject("products", userProducts);
        }
        return modelAndView;

    }








}
