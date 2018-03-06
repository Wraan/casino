package com.wran.controller;

import javax.servlet.http.HttpSession;

import com.wran.model.User;
import org.apache.coyote.http11.HttpOutputBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.wran.model.PasswordChanger;
import com.wran.service.UserService;

@Controller
public class DashboardController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/dashboard", method=RequestMethod.GET)
	public String showDashboard(Model model, HttpSession session) {
		if(!userService.isLoggedIn(session))
			return "redirect:/signin";
		else
			return "dashboard";
	}
	
	@RequestMapping(value="/deposit", method=RequestMethod.GET)
	public String depositCoins(Model model, HttpSession session) {
		if(!userService.isLoggedIn(session))
			return "redirect:/signin";

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
	public String showUser(@PathVariable String login, Model model, HttpSession session) {
		if(!userService.isLoggedIn(session))
			return "redirect:/signin";

		User user = userService.findOne(login);
		if(user != null) 
			model.addAttribute("user", user);
	
		return "showUserProfile";
	}
	
	@RequestMapping(value="/findUser", method=RequestMethod.GET)
	public String showFindUser(Model model, HttpSession session) {
		if(!userService.isLoggedIn(session))
			return "redirect:/signin";

		return "findUser";
	}
	
	@RequestMapping(value="/findUser", method=RequestMethod.POST)
	public String findUser(@RequestParam("userFindLogin") String userFindLogin) {
		return "redirect:/user/" + userFindLogin;
	}
	@RequestMapping(value="/settings", method=RequestMethod.GET)
	public String showSettings(@ModelAttribute("passwordError") String passwordError, Model model, HttpSession session) {
		if(!userService.isLoggedIn(session))
			return "redirect:/signin";

		model.addAttribute("passwordChanger",new PasswordChanger());
		model.addAttribute("passwordError", passwordError);
	
		return "settings";
	}
}
