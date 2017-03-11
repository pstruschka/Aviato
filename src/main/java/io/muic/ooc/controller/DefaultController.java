package io.muic.ooc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DefaultController {
    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ADMIN")) {
            return "redirect:/admin/home";
        } else if (request.isUserInRole("BUYER")) {
            return "redirect:/buyer/home";
        } else if (request.isUserInRole("SELLER")) {
            return "redirect:/admin/home";
        } else {
            System.out.println("OTHERLOG");
            return "redirect:/access-denied";
        }
    }
}
