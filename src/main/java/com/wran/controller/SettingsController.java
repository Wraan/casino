package com.wran.controller;

import javax.servlet.http.HttpSession;

import com.wran.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wran.model.PasswordChanger;
import com.wran.service.UserService;

@Controller
public class SettingsController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/changeAvatar", method = RequestMethod.POST)
	public String changeAvatarSettings(@RequestParam("newAvatar") String newAvatar, HttpSession session) {
		User user = (User) session.getAttribute("loggedUser");
		user = userService.changeAvatar(user.getLogin(), newAvatar);
		session.setAttribute("loggedUser", user);

		return "redirect:/settings";
	}

	@RequestMapping(value = "/changeEmail", method = RequestMethod.POST)
	public String changeEmailSettings(@RequestParam("newEmail") String newEmail,
			@RequestParam("password") String password, HttpSession session, RedirectAttributes ra) {
		User user = (User) session.getAttribute("loggedUser");
		String error = "";
		
		user.setPassword(password);
		
		if(!userService.exists(user))
			error = "Invalid password";

		if (error.equals("")) {
			user = userService.changeEmail(user.getLogin(), newEmail);
			session.setAttribute("loggedUser", user);
		} else {
			ra.addFlashAttribute("emailError", error);
		}

		return "redirect:/settings";
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String changePasswordSettings(@ModelAttribute("passwordChanger") PasswordChanger passwordChanger,
			HttpSession session, Model model, RedirectAttributes ra) {
		User user = (User) session.getAttribute("loggedUser");

		String error = userService.checkChangePassword(user.getLogin(), passwordChanger);

		if (error.equals("")) {
			user = userService.changePassword(user.getLogin(), passwordChanger);
			session.setAttribute("loggedUser", user);
		} else {
			ra.addFlashAttribute("passwordError", error);
		}

		return "redirect:/settings";
	}
}
