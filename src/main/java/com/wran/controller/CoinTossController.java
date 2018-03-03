package com.wran.controller;

import javax.servlet.http.HttpSession;

import com.wran.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wran.service.GamesService;
import com.wran.service.UserService;

@Controller
public class CoinTossController {
	
	@Autowired
	GamesService gamesService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/coinToss", method=RequestMethod.GET)
	public String showCoinToss(Model model) {
		return "coinToss";
	}
	
	@RequestMapping(value="/coinToss", method=RequestMethod.POST)
	public String userCoinToss(@RequestParam("coins") int coins, @RequestParam("choice") String choice, HttpSession session, Model model) {
		User user = (User) session.getAttribute("loggedUser");
		String error = gamesService.checkUsersCoins(user.getLogin(), coins);
		if(error.equals("")) {
			String result = gamesService.tossCoin();
			
			if(result.equals(choice)){
				user = userService.addCoins(user.getLogin(), coins);
				model.addAttribute("result", "You won " + coins + "coins" );
			}
			else {
				user = userService.addCoins(user.getLogin(), -coins);
				model.addAttribute("result", "You lost " + coins + "coins" );
			}
			
			session.setAttribute("loggedUser", user);
		}
		else {
			model.addAttribute("coinsError", error);
		}
		
		return "coinToss";
	}

}
