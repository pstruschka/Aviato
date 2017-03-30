package io.muic.ooc.controller;


import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AccessDeniedController implements ErrorController{
    @RequestMapping(value="/access-denied")
    public String denied(){
//        System.out.printlnhome(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("/access-denied");
        return "redirect:/default";
    }

    @RequestMapping("/error")
    public String  error404(RedirectAttributes redirectAttributes, HttpServletResponse response){
        redirectAttributes.addFlashAttribute("errorCode", response.getStatus());
        redirectAttributes.addFlashAttribute("message", "404 error");
        return "redirect:/404";
    }

    @RequestMapping("/404")
    public ModelAndView error(@ModelAttribute("message") String message){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/error/error");
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
