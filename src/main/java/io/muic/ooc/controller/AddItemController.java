package io.muic.ooc.controller;

import io.muic.ooc.model.User;
import io.muic.ooc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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




}
