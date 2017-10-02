package fi.thepaardihub.rest;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;
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

	/**
	 * returnDataAsJson return data as JSON if any is needed
	 * 
	 * @param status
	 *            basic boolean indicates successful/failed requests
	 * @param error
	 *            number indicator if any errors consist
	 *            0 no error
	 *            1 password condition
	 *            2 email condition 
	 *            3 email registered
	 *            4 wrong password
	 *            5
	 *            6
	 *            7
	 *            8
	 *            9
	 * @param message
	 *            Short message for logging.
	 * @param json
	 *            Any additional data can be added here as JSONObject
	 * @return data is converted to String in JSON object format
	 * @throws JSONException
	 */
	private String returnDataAsJson(boolean status, int error, String message, String json) throws JSONException {
		JSONObject returnData = new JSONObject();
		returnData.put("status", status);
		returnData.put("error", error);
		returnData.put("message", message);
		returnData.put("data", json);
		return returnData.toString();
	}

	// Returns URL safe salted hash.
	@RequestMapping(value = "/hash")
	@ResponseBody
	public String getHash(@RequestParam("passwd") String passwd) {
		String hash = "";
		try {
			hash = password.getSaltedHash(passwd);
		} catch (Exception e) {

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

	/**
	 * Rest for userAccount creation
	 *
	 * @param userName
	 *            user name of account that will be created
	 * @param psw
	 *            password of account that will be created
	 * @param firstName
	 *            first name of account that will be created
	 * @param lastName
	 *            last name of account that will be created
	 * @param email
	 *            email of account that will be created
	 * @throws Exception
	 */
	@PostMapping("/createaccount")
	public String createAccount(@RequestParam("username") String userName, @RequestParam("psw") String psw,
			@RequestParam("pswVerify") String pswVerify, @RequestParam("firstname") String firstName,
			@RequestParam("lastname") String lastName, @RequestParam("email") String email) throws Exception {
		
		Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);

        if(!mat.matches()){
        	return returnDataAsJson(false, 2, "Invalid email address.", null);
        }
		
		if(users.getUser(email) != null) {
			return returnDataAsJson(false, 3, "This email address is already registered.", null);
		}
		
		String passwordError = password.passwordValidator(psw, pswVerify);
		if (passwordError != null) {
			return returnDataAsJson(false, 1, passwordError, null);
		}
		UserAccounts newUser = users.createAccount(userName, psw, firstName, lastName, email);
		if(newUser == null) {
			return returnDataAsJson(false, 0, "Something went wrong :(", null);
		}
		newUser.setPasswordHash(":)");
		return returnDataAsJson(true, 0, "Account successfully created!", newUser.toJson());
	}
	


	/**
	 * Rest for login
	 *
	 * @param email
	 *            email of account user is trying to login
	 * @param psw
	 *            password of account user is trying to login
	 * @return UserAccount if login is successfully else null
	 */
	@PostMapping("/login")
	public UserAccounts login(@RequestParam("email") String email, @RequestParam("psw") String psw) {
		UserAccounts retVal = users.login(email, psw);
		System.out.println(retVal);
		return retVal;
	}

	@RequestMapping("/fake")
	public void fakeData() {

		users.fakeData();

	}

}
