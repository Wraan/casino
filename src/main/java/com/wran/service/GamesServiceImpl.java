package com.wran.service;

import java.util.Random;

import com.wran.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("gamesService")
public class GamesServiceImpl implements GamesService {

	@Autowired
	private UserService userService;

	public String tossCoin() {
		Random random = new Random();
		
		if(random.nextBoolean())
			return "Blue";
		else
			return "Red";
	}

	public String checkUsersCoins(String login, int coins) {
		User user = userService.findOne(login);
		if(user.getCoins() >= coins)
			return "";
		else
			return "Not enough coins";
	}
}
