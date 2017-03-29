package io.muic.ooc.controller;

import io.muic.ooc.model.Cart;
import io.muic.ooc.model.CartProduct;
import io.muic.ooc.model.User;
import io.muic.ooc.service.CartService;
import io.muic.ooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by joakimnilfjord on 3/25/2017 AD.
 */
@Controller
public class PurchaseHistoryController {
    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/buyer/purchasehistory",method = RequestMethod.GET)
    public ModelAndView viewPurchaseHistory() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        System.out.println("HELLO1----------------------------------");
        Set<Cart> cartHashSet = cartService.findCartsWithConfrimedOrderByUserId(user);
        System.out.println("HELLO2----------------------------------");
        Set<Set<CartProduct>> all = cartService.getAllCartProducts(cartHashSet);
        System.out.println("HELLO3----------------------------------");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("carts",all);
        modelAndView.setViewName("/buyer/purchasehistory");
        System.out.println("HELLO4----------------------------------");
        return modelAndView;
    }
}
