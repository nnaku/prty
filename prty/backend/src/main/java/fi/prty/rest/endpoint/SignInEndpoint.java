package fi.prty.rest.endpoint;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.Gson;

import fi.prty.dao.users.tables.UserAccounts;
import fi.prty.rest.jsonobject.SignIn;
import fi.prty.security.Password;
import fi.prty.services.UserService;

@Controller
public class SignInEndpoint {

	private UserService users;
	private Password password = new Password();
	private Map<String, String> jsonMap;
	private String passwordError;
	private UserAccounts newUser;


	@Autowired
	public void setUsers(UserService users) {
		this.users = users;
	}

	/**
	 *
	 * @param signin information in json format
	 * @return Http response with various errors messages, success with http status 200
	 * @throws Exception
	 */

	@PostMapping("/register")
	public ResponseEntity<?> createAccount(@RequestBody SignIn signin) throws Exception {
		// Logger logger = LoggerFactory.getLogger(SignInEndpoint.class);
		// logger.debug("singin : {}", signin);

		// Return body
		jsonMap = new HashMap<String, String>();

		// Valid email pattern
		Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");

		// Validating email.
		Matcher mat = pattern.matcher(signin.getEmail());
		
		// Validating password layout
		passwordError = password.passwordValidator(signin.getPassword(), signin.getPasswordVerify());

		// If invalid email pattern.
		if (!mat.matches()) {
			jsonMap.put("emailPattern", "Invalid email.");
			jsonMap.put("status", "ERROR");

		}

		// If email already registered.
		if (users.getUser(signin.getEmail()) != null) {
			jsonMap.put("emailUnused", "This email is already registered.");
			jsonMap.put("status", "ERROR");
		}

		// If invalid password layout.
		if (passwordError != null) {
			jsonMap.put("validPassword", passwordError);
			jsonMap.put("status", "ERROR");
		}

		// Check if errors
		if (jsonMap.get("status") == "ERROR") {
			// return all error messages to client
			return new ResponseEntity<Object>(new Gson().toJson(jsonMap), HttpStatus.BAD_REQUEST);
		} else {
			// Generate new user
			newUser = users.createAccount(signin.getUsername(), signin.getPassword(), signin.getFirstname(),
					signin.getLastname(), signin.getEmail());
			// Validate that new user is actually added.
			if (newUser == null) {
				// For some random issue. At the moment I don't have no idea what can case this, but "just in case"!
				jsonMap.put("status", "ERROR");
				jsonMap.put("message", "Something went wrong! :(");
				return new ResponseEntity<Object>(new Gson().toJson(jsonMap), HttpStatus.BAD_REQUEST);
			} else {
				jsonMap.put("status", "SUCCESS");
				jsonMap.put("message", "Successfully Sign-in");
				return new ResponseEntity<Object>(new Gson().toJson(jsonMap), HttpStatus.OK);
			}
		}
	}
	
	/**
	 * 
	 * @param email in http body at json format
	 * @return http response with json body than contains info about email is free or taken
	 * @throws Exception
	 */
	
	@PostMapping("/register/email")
	public ResponseEntity<?> createAccount(@RequestBody Map<String,String> email) throws Exception {

		// Return body
		jsonMap = new HashMap<String, String>();

		// If email already registered.
		if (users.getUser(email.get("email")) == null) {
			jsonMap.put("message", "This email is not registered.");
			jsonMap.put("status", "SUCCESS");
		}else {
			jsonMap.put("message", "This email is already registered.");
			jsonMap.put("status", "ERROR");
		}
		
		return new ResponseEntity<Object>(new Gson().toJson(jsonMap), HttpStatus.OK);
	}
}
