package fi.thepaardihub.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import fi.thepaardihub.dao.users.UsersDao;
import fi.thepaardihub.dao.users.tables.UserAccounts;
import fi.thepaardihub.password.Password;

public class UserController {
	
	private UsersDao users;
	private Password pswHasher;
	
	public UserController() {
		this.pswHasher = new Password();
	}
	@Autowired
	public void setUsers(UsersDao users) {
		this.users = users;
	}
	

	public UserAccounts createAccount(String userName, String psw, String firstName, String lastName, String email) {
		try {
			UserAccounts add = new UserAccounts(userName,pswHasher.getSaltedHash(psw),firstName,lastName,email);
			users.saveOrUpdateAccount(add);
			return add;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
