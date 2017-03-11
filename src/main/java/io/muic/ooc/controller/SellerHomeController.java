package io.muic.ooc.controller;

import io.muic.ooc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by joakimnilfjord on 3/11/2017 AD.
 */
@Controller
public class SellerHomeController {
    @RequestMapping(value="/additem", method = RequestMethod.GET)
    public ModelAndView add(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("seller/addItem");
        return modelAndView;
    }
}
