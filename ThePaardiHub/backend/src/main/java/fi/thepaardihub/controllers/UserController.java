package fi.thepaardihub.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestHeader;

import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import fi.thepaardihub.dao.users.UsersDao;
import fi.thepaardihub.dao.users.tables.UserAccounts;
import fi.thepaardihub.dao.users.tables.UserRoles;
import fi.thepaardihub.security.JWT;
import fi.thepaardihub.security.Password;

@Service
@RestController
public class UserController {

	private JWT jwt;
	private UsersDao users;
	private Password passwordTools;

	@Autowired
	public UserController(UsersDao usersDao) {
		this.users = usersDao;
		this.passwordTools = new Password();
	}

	/* Maps to all HTTP actions by default (GET,POST,..) */

	@PostMapping("/user")
	public ResponseEntity<?> renewToken(@RequestHeader HttpHeaders headers) {
		
		jwt = new JWT();
		String body;
		
		// Get token from header and remove Bearer prefix.
		String token = headers.getFirst("Authorization");

		if (jwt.validate(token).get("status") == "SUCCESS") {
			String email = (String) jwt.validate(token).get("email");
			try {
				UserAccounts account = users.getUser(email);
				if (account != null) {
					body = new Gson().toJson(account.toMap());
					return new ResponseEntity<Object>(body, HttpStatus.OK);
				}
			} catch (Exception e) {
				e.printStackTrace();
				body = new Gson().toJson(e.getMessage());
				return new ResponseEntity<Object>(body, HttpStatus.NOT_FOUND);
			}
		} else {
			body = new Gson().toJson(jwt.validate(token));
			return new ResponseEntity<Object>(body, HttpStatus.UNAUTHORIZED);
		}
		return null;
	}

	/**
	 * Creates an userAccount for UserAccounts table
	 *
	 * @param userName
	 *            UserName of account that will be created
	 * @param psw
	 *            password of account that will be created
	 * @param firstName
	 *            first name of account that will be created
	 * @param lastName
	 *            last name of account that will be created
	 * @param email
	 *            email of account that will be created
	 * @return created userAccount or null if creating failed
	 */
	public UserAccounts createAccount(String userName, String psw, String firstName, String lastName, String email) {
		try {
			UserAccounts add = new UserAccounts();
			add.setUserName(userName);
			add.setPasswordHash(passwordTools.getSaltedHash(psw));
			add.setFirstName(firstName);
			add.setLastName(lastName);
			add.setEmail(email);
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

	/**
	 * Search the database for the email and check if the passwords matches
	 *
	 * @param email
	 *            email of account user is trying to login
	 * @param psw
	 *            password of account user is trying to login
	 * @return UserAccount if login is successfully else null
	 */
	public UserAccounts login(String email, String psw) {
		try {
			UserAccounts account = users.getUser(email);

			if (account != null) {
				System.out.println("Not Null");
				String hash = account.getPasswordHash();
				if (passwordTools.validPassword(psw, hash)) {
					return account;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public UserAccounts getUser(String email) {
		try {
			UserAccounts account = users.getUser(email);

			if (account != null) {
				return account;
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

			UserAccounts add1 = new UserAccounts("nAku", this.passwordTools.getSaltedHash("aku.kangas"), "Aku",
					"Kangas", "Aku.Kangas@metropolia.fi");
			UserAccounts add2 = new UserAccounts("Matti", this.passwordTools.getSaltedHash("matti.holopainen"), "Matti",
					"Holopainen", "Matti.Holopainen2@metropolia.fi");
			UserAccounts add3 = new UserAccounts("Tiina", this.passwordTools.getSaltedHash("tiina.ojala"), "Tiina",
					"Ojala", "Tiina.Ojala3@metropolia.fi");
			UserAccounts add4 = new UserAccounts("Maarit", this.passwordTools.getSaltedHash("maarit.saariniemi"),
					"Maarit", "Saariniemi", "Maarit.Saariniemi@Metropolia.fi");

			users.saveOrUpdateAccount(add1);
			users.saveOrUpdateAccount(add2);
			users.saveOrUpdateAccount(add3);
			users.saveOrUpdateAccount(add4);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
