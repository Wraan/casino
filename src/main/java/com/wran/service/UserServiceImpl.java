package com.wran.service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wran.model.PasswordChanger;
import com.wran.model.UserRegister;
import com.wran.model.User;
import com.wran.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Transactional
	public User save(User user) {
		return userRepository.saveAndFlush(user);
	}

	public boolean isRegistered(UserRegister userRegister) {
		User user = userRepository.findOne(userRegister.getLogin());
		
		if(user != null)
			return false;
		else {
			if(!userRegister.getPassword().equals(userRegister.getRePassword()))
				return false;
			
			user = new User();
			user.setLogin(userRegister.getLogin());
			user.setPassword(userRegister.getPassword());
			user.setEmail(userRegister.getEmail());
			user.setAvatar("https://i.imgur.com/ohOPjgU.png");
			save(user);
			return true;
		}
	}

	public boolean exists(User user) {
		User existingUser = userRepository.findOne(user.getLogin());
		
		if(existingUser == null)
			return false;
		
		if(existingUser.getPassword().equals(user.getPassword()))
			return true;
		
		return false;
	}

	public User findOne(String login) {
		return userRepository.findOne(login);
	}

	public User depositCoins(String login, int coins) {
		User user = userRepository.findOne(login);
		if(user != null) {
			user.setCoins(user.getCoins() + coins);
			save(user);
		}		
		return user;
	}

	public User changeAvatar(String login, String newAvatar) {
		User user = findOne(login);
		if(user != null) {
			user.setAvatar(newAvatar);
			save(user);	
		}
		
		return user;
		
	}

	public String checkChangePassword(String login, PasswordChanger passwordChanger) {
		User user = findOne(login);
		
		if(!user.getPassword().equals(passwordChanger.getOldPassword()))
			return "Invalid password";
		
		if(!passwordChanger.getNewPassword().equals(passwordChanger.getNewRePassword()))
			return "New passwords are not equal";
		
		if(passwordChanger.getOldPassword().equals(passwordChanger.getNewPassword()))
			return "Old and new passwords are the same";
		
		return "";
	}

	public User changePassword(String login, PasswordChanger passwordChanger) {
		User user = findOne(login);
		if(user != null) {
			user.setPassword(passwordChanger.getNewPassword());
			save(user);
		}
		
		return user;
	}

	public User changeEmail(String login, String newEmail) {
		User user = findOne(login);
		if(user != null) {
			user.setEmail(newEmail);
			save(user);
		}
		
		return user;
	}

	public User addCoins(String login, int coins) {
		User user = findOne(login);
		if(user != null) {
			user.setCoins(user.getCoins() + coins);
			save(user);
		}
		
		return user;
	}

	public boolean isLoggedIn(HttpSession session) {
		User user = (User) session.getAttribute("loggedUser");
		if(user != null)
			return true;
		else
			return false;
	}


}
