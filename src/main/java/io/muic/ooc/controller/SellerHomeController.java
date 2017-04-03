package io.muic.ooc.controller;

import io.muic.ooc.model.Product;
import io.muic.ooc.model.User;
import io.muic.ooc.service.ProductService;
import io.muic.ooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class SellerHomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value="/seller/home", method = RequestMethod.GET)
    public ModelAndView sellerHome(){
        //System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getUsername() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("/seller/home");
        return modelAndView;
    }
    @RequestMapping(value="/seller/additem", method = RequestMethod.GET)
    public ModelAndView add(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/seller/additem");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        Product product = new Product();
        modelAndView.addObject("user", user);
        modelAndView.addObject("product",product);
        return modelAndView;
    }


    @RequestMapping(value="/seller/myproducts",method = RequestMethod.GET)
    public ModelAndView viewUserProducts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/seller/myproducts");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //System.out.println(auth.getName());
        User user = userService.findUserByUsername(auth.getName());
        ArrayList<Product> userProducts = new ArrayList<>(productService.findProductsByUser(user));
        MultipartFile image = null;
        modelAndView.addObject("user", user);
        modelAndView.addObject("image", image);
        modelAndView.addObject("products", userProducts);
        return modelAndView;
    }

    @RequestMapping(value="/seller/edit_product",method = RequestMethod.POST)
    public ModelAndView editUserProduct(@RequestParam("product") long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        Product product = productService.findProductById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/seller/edit_product");
        modelAndView.addObject("user", user);
        modelAndView.addObject("product",product);
        return modelAndView;
    }


    @RequestMapping(value="/seller/delete_product",method = RequestMethod.POST)
    public ModelAndView deleteUserProduct(@RequestParam("product") Long productId) {
        System.out.println("HERE METHOD DELETE YO");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/seller/myproducts");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        Product product = productService.findProductById(productId);
        productService.notSelling(product);
//        ArrayList<Product> userProducts = new ArrayList<>(productService.findProductsByUser(user));
        modelAndView.addObject("user", user);
//        modelAndView.addObject("products", userProducts);
        modelAndView.setViewName("redirect:/seller/myproducts");
        return modelAndView;
    }



}
