package fi.thepaardihub.rest.endpoint;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import fi.thepaardihub.controllers.UserController;
import fi.thepaardihub.dao.users.tables.UserAccounts;
import fi.thepaardihub.rest.jsonobject.SignIn;
import fi.thepaardihub.security.Password;

@Controller
public class SignInEndpoint {

	private UserController users;
	private Password password = new Password();
	private Map<String, String> jsonMap;
	private String passwordError;
	private UserAccounts newUser;

	/**
	 *
	 * @param users
	 *            Set Users
	 */

	@Autowired
	public void setUsers(UserController users) {
		this.users = users;
	}

	/**
	 *
	 * @param signin
	 * @return
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
}
