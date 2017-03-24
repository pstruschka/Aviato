package io.muic.ooc.controller;

import io.muic.ooc.model.Cart;
import io.muic.ooc.model.CartProduct;
import io.muic.ooc.model.Product;
import io.muic.ooc.model.User;
import io.muic.ooc.service.CartProductService;
import io.muic.ooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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

    @RequestMapping(value="/buyer/viewcart",method = RequestMethod.GET)
    public ModelAndView viewCart(@RequestParam("cart") Cart cart ){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/buyer/viewcart");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());

        Set<CartProduct> cartProducts = new HashSet<>(cartProductService.findCartProductByCart(cart));
        modelAndView.addObject("cartProducts",cartProducts);
        modelAndView.addObject("user", user);
        modelAndView.addObject("cartProducts",cartProducts);
        return modelAndView;
    }

}
