package io.muic.ooc.webapp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by gigadot on 16-Feb-17.
 */
@Controller
public class HelloController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping(value = {"/"})
    public String index(Model model) {
        Customer customer = new Customer();
        customer.setFirstName(RandomStringUtils.randomAlphabetic(5));
        customer.setLastName(RandomStringUtils.randomAlphabetic(5));

        customerRepository.save(customer);

        Iterable<Customer> customerIterable = customerRepository.findAll();

        List<Customer> customers = new ArrayList<>();
        for (Customer c : customerIterable) {
            customers.add(c);
        }

        model.addAttribute("customers", customers);

        return "hello";
    }
}
