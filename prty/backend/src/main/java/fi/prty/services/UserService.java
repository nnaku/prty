package fi.prty.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import fi.prty.dao.users.UsersDao;
import fi.prty.dao.users.tables.UserAccounts;
import fi.prty.dao.users.tables.UserRoles;
import fi.prty.security.Password;

@Service
@RestController
public class UserService {

	private UsersDao users;
	private Password passwordTools;

	@Autowired
	public UserService(UsersDao usersDao) {
		this.users = usersDao;
		this.passwordTools = new Password();
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

	public UserAccounts editAccount(String email, HashMap<String, String> data) {
		try {
			// Get account
			UserAccounts account = users.getUser(email);

			// If given, set new value.
			if (data.containsKey("firstname")) {
				account.setFirstName(data.get("firstname"));
			}

			if (data.containsKey("lastname")) {
				account.setLastName(data.get("lastname"));
			}

			if (data.containsKey("username")) {
				account.setUserName(data.get("username"));
			}

			if (data.containsKey("email")) {
				account.setEmail(data.get("email"));
			}

			if (data.containsKey("newPassword")) {
				account.setPasswordHash(passwordTools.getSaltedHash(data.get("newPassword")));
			}
			// Save account
			users.saveOrUpdateAccount(account);
			return account;
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

	public void sample() {
		try {
			UserRoles banned = new UserRoles(0, "Banned");
			UserRoles member = new UserRoles(1, "Member");
			UserRoles admin = new UserRoles(9, "Admin");
			UserRoles god = new UserRoles(99, "God");

			users.saveOrUpdateRoles(banned);
			users.saveOrUpdateRoles(member);
			users.saveOrUpdateRoles(admin);
			users.saveOrUpdateRoles(god);

			UserAccounts defaultUser = new UserAccounts("admin", this.passwordTools.getSaltedHash("AdminSecr3t!"), "System",
					"Operator", "admin@prty.fi");
			users.saveOrUpdateAccount(defaultUser);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
