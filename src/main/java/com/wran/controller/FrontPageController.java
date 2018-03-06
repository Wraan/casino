
package com.wran.controller;

import javax.servlet.http.HttpSession;

import com.wran.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wran.model.UserRegister;
import com.wran.service.UserService;

@Controller
public class FrontPageController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showFrontPage(Model model) {
		model.addAttribute("user", new User());
		return "index";
	}
	
	@RequestMapping(value="/signin", method=RequestMethod.POST)
	public String signIn(@ModelAttribute("user") User user, HttpSession session) {
		if(userService.exists(user)) {
			User loggedUser = userService.findOne(user.getLogin());
			session.setAttribute("loggedUser", loggedUser);
			return "redirect:/dashboard";
		}
		return "signin";
	}

	@RequestMapping(value="/signin", method=RequestMethod.GET)
	public String signIn(Model model) {
		model.addAttribute("user", new User());
		return "signin";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String showRegister(Model model) {
		model.addAttribute("userRegister", new UserRegister());		
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(@ModelAttribute("userRegister") UserRegister userRegister, Model model) {
		if(userService.isRegistered(userRegister)) {
			model.addAttribute("user", new User());
			return "signin";
		}
		else {
			model.addAttribute("error", "Try again");
			return "register";
		}
		
	}

}
