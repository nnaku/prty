package fi.thepaardihub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.thepaardihub.dao.games.GamesDao;
import fi.thepaardihub.dao.users.UsersDao;
import fi.thepaardihub.dao.users.tables.UserAccounts;
import fi.thepaardihub.dao.users.tables.UserRoles;
import fi.thepaardihub.password.Password;

@Service
public class UserController {

	private UsersDao users;
	private GamesDao games;
	private Password pswHasher;

	@Autowired
	public UserController(UsersDao usersDao, GamesDao gamesDao) {
		this.users = usersDao;
		this.games = gamesDao;
		this.pswHasher = new Password();
	}

	public UserAccounts createAccount(String userName, String psw, String firstName, String lastName, String email) {
		try {
			UserAccounts add = new UserAccounts(userName, pswHasher.getSaltedHash(psw), firstName, lastName, email);
			users.saveOrUpdateAccount(add);
			return add;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<UserAccounts> allAccounts() {
		return users.allAccounts();
	}

	public List<UserRoles> allRoles() {
		return users.allRoles();
	}

	public UserAccounts login(String email, String psw) {
		try {
			UserAccounts account = users.getUser(email);

			if (account != null) {
				System.out.println("Not Null");
				String hash =account.getPasswordHash();
				if (pswHasher.validPassword(psw, hash )) {
					return account;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void fakeData() {
		try {
			UserRoles banned = new UserRoles(0, "Banned");
			UserRoles member = new UserRoles(1, "Member");
			UserRoles admin = new UserRoles(9, "Admin");
			UserRoles god = new UserRoles(99, "God");

			users.saveOrUpdateRoles(banned);
			users.saveOrUpdateRoles(member);
			users.saveOrUpdateRoles(admin);
			users.saveOrUpdateRoles(god);

			UserAccounts add1 = new UserAccounts("nAku", this.pswHasher.getSaltedHash("aku.kangas"), "Aku", "Kangas",
					"Aku.Kangas@metropolia.fi");
			UserAccounts add2 = new UserAccounts("Matti", this.pswHasher.getSaltedHash("matti.holopainen"), "Matti",
					"Holopainen", "Matti.Holopainen2@metropolia.fi");
			UserAccounts add3 = new UserAccounts("Tiina", this.pswHasher.getSaltedHash("tiina.ojala"), "Tiina", "Ojala",
					"Tiina.Ojala3@metropolia.fi");
			UserAccounts add4 = new UserAccounts("Maarit", this.pswHasher.getSaltedHash("maarit.saariniemi"), "Maarit",
					"Saariniemi", "Maarit.Saariniemi@Metropolia.fi");

			users.saveOrUpdateAccount(add1);
			users.saveOrUpdateAccount(add2);
			users.saveOrUpdateAccount(add3);
			users.saveOrUpdateAccount(add4);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
