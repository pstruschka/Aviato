package io.muic.ooc.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by gigadot on 16-Feb-17.
 */
@Controller
public class HelloController {

    @GetMapping(value = {"/", "/hello"})
    public String index(Model model) {
        return "hello";
    }
}
