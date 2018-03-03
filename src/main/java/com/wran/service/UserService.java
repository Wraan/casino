package com.wran.service;

import com.wran.model.PasswordChanger;
import com.wran.model.User;
import com.wran.model.UserRegister;

public interface UserService {
	
	User save(User user);

	boolean isRegistered(UserRegister userRegister);

	boolean exists(User user);
	
	User findOne(String login);

	User depositCoins(String login, int coins);

	User changeAvatar(String login, String newAvatar);

	String checkChangePassword(String login, PasswordChanger passwordChanger);

	User changePassword(String login, PasswordChanger passwordChanger);

	User changeEmail(String login, String newEmail);

	User addCoins(String login, int coins);

}
