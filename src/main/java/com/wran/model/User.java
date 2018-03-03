package com.wran.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "USERS")
public class User {

	@Id
	@NotBlank
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "activities_seq_gen")
	@SequenceGenerator(name="activities_seq_gen", sequenceName = "ACTIVITIES_SEQ")
	private String login;

	@NotBlank
	private String password;

	@Email
	private String email;
	
	@Lob
	private String avatar;

	@NotNull
	private int coins;

	public String getAvatar() {
		return avatar;
	}

	public int getCoins() {
		return coins;
	}

	public String getEmail() {
		return email;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
