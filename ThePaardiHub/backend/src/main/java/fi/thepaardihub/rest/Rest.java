package fi.thepaardihub.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fi.thepaardihub.controllers.UserController;
import fi.thepaardihub.dao.users.UsersDao;
import fi.thepaardihub.dao.users.tables.*;
import fi.thepaardihub.password.*;

@RestController
public class Rest {

	// Currently this class is only used to test database connection
	private UserController users;
	
	private Password password = new Password();

	@Autowired
	public void setUsers(UserController users) {
		this.users = users;
	}

	// Returns URL safe salted hash.
	@RequestMapping(value = "/hash")
	@ResponseBody
	public String getHash(@RequestParam("passwd") String passwd) {
		String hash = "";
		try {
			hash = password.getSaltedHash(passwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error";
		}
		return "Given password: " + passwd + "<br>Salted hash: " + hash;
	}

	// Returns boolean for password match.
	@RequestMapping(value = "/password")
	@ResponseBody
	public Boolean validPassword(@RequestParam("passwd") String passwd) {
		Boolean valid = false;
		try {
			valid = password.validPassword(passwd, password.getSaltedHash("Salasana123"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return valid;
	}


	@RequestMapping("/accounts")
	public @ResponseBody Iterable<UserAccounts> listAccounts() {
		return users.allAccounts();

	}

	@RequestMapping("/roles")
	public @ResponseBody Iterable<UserRoles> listRoles() {
		return users.allRoles();

	}
	
	@PostMapping("/createaccount")
	public void createAccount(@RequestParam("username")String userName,@RequestParam("psw") String psw, @RequestParam("firstname")String firstName,@RequestParam("lastname") String lastName, @RequestParam("email")String email) {
		users.createAccount(userName, psw, firstName, lastName, email);
	}
	
	
	@RequestMapping("/fake")
	public void fakeData() {
		
		users.fakeData();
		
	}
	
}
