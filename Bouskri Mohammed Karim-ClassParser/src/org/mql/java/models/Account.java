package org.mql.java.models;

public class Account extends TestInheritance {
	
	private String login;
	private String password;

	public Account() {
		// TODO Auto-generated constructor stub
	}

	public Account(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
