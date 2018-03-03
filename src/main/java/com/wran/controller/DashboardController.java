package com.wran.controller;

import javax.servlet.http.HttpSession;

import com.wran.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wran.model.PasswordChanger;
import com.wran.service.UserService;

@Controller
public class DashboardController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/dashboard", method=RequestMethod.GET)
	public String showDashboard(Model model) {
		return "dashboard";
	}
	
	@RequestMapping(value="/deposit", method=RequestMethod.GET)
	public String depositCoins(Model model) {
		return "deposit";
	}
	
	@RequestMapping(value="/deposit", method=RequestMethod.POST)
	public String depositCoins(@RequestParam("deposit") int deposit, HttpSession session) {
		User loggedUser = (User)session.getAttribute("loggedUser");
		loggedUser = userService.depositCoins(loggedUser.getLogin(), deposit);
		session.setAttribute("loggedUser", loggedUser);
		return "dashboard";
	}

	
	@RequestMapping(value="/user/{login}", method=RequestMethod.GET)
	public String showUser(@PathVariable String login, Model model) {
		User user = userService.findOne(login);
		if(user != null) 
			model.addAttribute("user", user);
	
		return "showUserProfile";
	}
	
	@RequestMapping(value="/findUser", method=RequestMethod.GET)
	public String showFindUser(Model model) {
		return "findUser";
	}
	
	@RequestMapping(value="/findUser", method=RequestMethod.POST)
	public String findUser(@RequestParam("userFindLogin") String userFindLogin) {
		return "redirect:/user/" + userFindLogin;
	}
	@RequestMapping(value="/settings", method=RequestMethod.GET)
	public String showSettings(@ModelAttribute("passwordError") String passwordError, Model model) {
		model.addAttribute("passwordChanger",new PasswordChanger());
		model.addAttribute("passwordError", passwordError);
	
		return "settings";
	}


}
