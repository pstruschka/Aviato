package io.muic.ooc.webapp;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/register")
    public String index(Model model) {
        return "register";
    }

    //@RequestMapping(value="/register",method= RequestMethod.POST)
    @PostMapping("/register")
    public String test(HttpServletRequest req, HttpServletResponse res, Model model){
        String user = req.getParameter("username");
        String password = req.getParameter("password");
        Seller seller = new Seller();
        seller.setUsername(user);
        seller.setPassword(password);
        sellerRepository.save(seller);

        return "redirect:/";
    }
}
