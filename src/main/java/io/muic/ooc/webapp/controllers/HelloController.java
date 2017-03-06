package io.muic.ooc.webapp.controllers;

import java.util.ArrayList;
import java.util.List;

import io.muic.ooc.webapp.entities.Customer;
import io.muic.ooc.webapp.entities.User;
import io.muic.ooc.webapp.entities.UserGroup;
import io.muic.ooc.webapp.repositories.CustomerRepository;
import io.muic.ooc.webapp.repositories.UserGroupRepository;
import io.muic.ooc.webapp.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by gigadot on 16-Feb-17.
 */
@Controller
public class HelloController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserGroupRepository userGroupRepository;

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

    @GetMapping(value = {"/addUser"})
    //@Transactional
    @ResponseBody
    public List<User> addUser(Model model) {

        UserGroup userGroup = new UserGroup();
        userGroup.setDescription("xxx");
        userGroup.setName(RandomStringUtils.randomAlphabetic(5));
        userGroupRepository.save(userGroup);

        User user = new User();
        user.setUsername(RandomStringUtils.randomAlphabetic(5));
        user.setName(RandomStringUtils.randomAlphabetic(5));
        user.setHashedPassword(RandomStringUtils.randomAlphabetic(20));
        user.getRoles().add("ADMIN");
        user.getGroups().add(userGroup);
        userRepository.save(user);

        Iterable<User> userIterable = userRepository.findAll();

        List<User> users = new ArrayList<>();
        for (User c : userIterable) {
            users.add(c);
        }

        return users;
    }
}
