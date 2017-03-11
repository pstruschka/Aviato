package io.muic.ooc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import io.muic.ooc.model.User;
import io.muic.ooc.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		ModelAndView modelAndView = new ModelAndView();
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser" ) {
			modelAndView.setViewName("login");
			return modelAndView;
		} else {

			return new ModelAndView("redirect:/admin/home");
		}

	}

	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView home(){
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUsername(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getUsername() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}

	@RequestMapping(value="/seller/home", method = RequestMethod.GET)
	public ModelAndView sellerHome(){
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUsername(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getUsername() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.setViewName("seller/home");
		return modelAndView;
	}


	

	

}
