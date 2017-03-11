package io.muic.ooc.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public class AccessDeniedController {
    @RequestMapping(value="/access-denied", method = RequestMethod.GET)
    public ModelAndView denied(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/access-denied");
        return modelAndView;
    }
}
