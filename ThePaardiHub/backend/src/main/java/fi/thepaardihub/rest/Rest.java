package fi.thepaardihub.rest;


import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import com.google.gson.*;

import fi.thepaardihub.controllers.UserController;
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
	private String returnDataAsJson(boolean status, int error, String message, Object data)  {
		HashMap<String,Object> returnData = new HashMap<String,Object>();
		returnData.put("status", status);
		returnData.put("error", error);
		returnData.put("message", message);
		returnData.put("data", data);
		return new Gson().toJson(returnData);
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
	
	
	
//	public String createAccount(@RequestHeader("Accept: application/json") @RequestParam("username") String userName, @RequestParam("psw") String psw,
//			@RequestParam("pswVerify") String pswVerify, @RequestParam("firstname") String firstName,
//			@RequestParam("lastname") String lastName, @RequestParam("email") String email){
//		
//		
//	}
	


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
