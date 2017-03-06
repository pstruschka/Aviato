package io.muic.ooc.webapp.controllers;

import io.muic.ooc.webapp.entities.Seller;
import io.muic.ooc.webapp.entities.User;
import io.muic.ooc.webapp.entities.UserGroup;
import io.muic.ooc.webapp.repositories.SellerRepository;
import io.muic.ooc.webapp.repositories.UserGroupRepository;
import io.muic.ooc.webapp.repositories.UserRepository;
import io.muic.ooc.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by joakimnilfjord on 3/4/2017 AD.
 */
@Controller
public class RegisterController {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private UserGroupRepository userGroupRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping("/register")
    public String index(Model model) {
        return "register";
    }

    //@RequestMapping(value="/register",method= RequestMethod.POST)
    @PostMapping("/register")
    public String test(HttpServletRequest req, HttpServletResponse res, Model model){

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String type = req.getParameter("type");
        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setHashedPassword(password);
        if (type.equals("Seller")) {
            System.out.println(type);
            user.getRoles().add(type);
            userService.save(user);
        }
        else if (type.equals("Buyer")) {
            System.out.println(type);
            user.getRoles().add(type);
            userService.save(user);
        }
        else {
            System.out.println("NOT A VALID TYPE");
        }
        return "redirect:/";
    }
}
