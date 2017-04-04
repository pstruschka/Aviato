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

/**
 * Created by joakimnilfjord on 3/11/2017 AD.
 */

@Controller
public class AddItemController {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/additem", method = RequestMethod.POST)
    public ModelAndView createNewProduct(@Valid Product product,BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("seller/additem");

        if (!bindingResult.hasErrors()) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        product.setQuantitySold(0L);
        product.setProfit(0L);
        productService.saveProduct(product,user);
        modelAndView.addObject("successMessage", "Product added successfully");
        modelAndView.addObject("product", new Product());
        }
        return modelAndView;

    }








}
