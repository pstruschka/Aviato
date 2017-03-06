package io.muic.ooc.webapp.controllers;

import io.muic.ooc.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by DevSingh on 3/6/17.
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String index(Model model) {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public String test(HttpServletRequest req, HttpServletResponse res, Model model){
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(userService.validate(username,password)){
            return "WELCOME!";
        }else{
            return "Wrong username or password";
        }
    }
}
