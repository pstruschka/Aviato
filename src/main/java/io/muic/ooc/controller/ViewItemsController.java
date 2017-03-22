package io.muic.ooc.controller;

import com.sun.org.apache.regexp.internal.RE;
import io.muic.ooc.model.Product;
import io.muic.ooc.model.User;
import io.muic.ooc.repository.ProductRepository;
import io.muic.ooc.service.ProductService;
import io.muic.ooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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

    @RequestMapping(value="/buyer/viewproducts",method = RequestMethod.GET)
    public ModelAndView viewUserProducts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/buyer/viewproducts");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        User user = userService.findUserByUsername(auth.getName());
        ArrayList<Product> userProducts = new ArrayList<>(productService.findProductsInStock());
        modelAndView.addObject("user", user);
        modelAndView.addObject("products", userProducts);
        return modelAndView;
    }

    @RequestMapping(value = "/buyer/buy",method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView buyProducts(@ModelAttribute("product") Long productId, @RequestParam("quantity") Long quantity) {
        Product product = productService.findProductById(productId);
        productService.updateProductQuantity(product,quantity);
        return viewUserProducts();

    }
}
