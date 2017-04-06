package io.muic.ooc.controller;

import io.muic.ooc.model.Cart;
import io.muic.ooc.model.Product;
import io.muic.ooc.model.User;
import io.muic.ooc.service.ProductService;
import io.muic.ooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

/**
 * Created by DevSingh on 4/4/17.
 */
@Controller
public class SellerHistoryController {
    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/seller/sellerhistory",method = RequestMethod.GET)
    public ModelAndView viewSellerHistory() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        List<Product> products = productService.findProductsByUser(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products",products);
        modelAndView.setViewName("seller/sellerhistory");
        return modelAndView;
    }
}
