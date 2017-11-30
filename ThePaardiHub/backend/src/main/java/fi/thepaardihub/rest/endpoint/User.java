package fi.thepaardihub.rest.endpoint;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import fi.thepaardihub.controllers.UserController;

import fi.thepaardihub.dao.users.tables.UserAccounts;
import fi.thepaardihub.security.JWT;
import fi.thepaardihub.security.Password;

@RestController
public class User {
	private JWT jwt = new JWT();
	private UserController userCont;
	private Password passwordTools;
	
	@Autowired
	public User(UserController userCont) {
		this.userCont = userCont;
		this.passwordTools = new Password();
	}
	/**
	 * Post endpoint for returning user information.
	 * @param headers contains jtw token in Authorization header
	 * @return Http response with json body 
	 */
	@PostMapping("/user")
	public ResponseEntity<?> getUserData(@RequestHeader HttpHeaders headers) {

		String body;

		// Get token from header and remove Bearer prefix.
		String token = headers.getFirst("Authorization");

		if (jwt.validate(token).get("status") == "SUCCESS") {
			String email = (String) jwt.validate(token).get("email");
			try {
				UserAccounts account = userCont.getUser(email);
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
	 * Put endpoint for user data updates.
	 * @param headers contains jtw token in Authorization header
	 * @param body contains new user information
	 * @return
	 */
	
	
	
	// THIS METHOD NEED REFACTORING!!!
	
	@PutMapping("/user")
	public ResponseEntity<?> updateUserData(@RequestHeader HttpHeaders headers,
			@RequestBody HashMap<String, String> body) {

		Map<String, String> responseBody = new HashMap<String, String>();
		HashMap<String, String> newUserData = new HashMap<String, String>();

		// Get token from header and remove Bearer prefix.
		String token = headers.getFirst("Authorization");

		// validate token and get user
		if (jwt.validate(token).get("status") == "SUCCESS") {
			String email = (String) jwt.validate(token).get("email");

			try {
				// get user account
				UserAccounts account = userCont.getUser(email);
				if (account != null) {

					// if user request password change.
					if (body.containsKey("newPassword")) {
						// if old password is valid
						if (passwordTools.validPassword(body.get("password"), account.getPasswordHash())) {
							// validate new password layout.
							String newPasswordStatus = passwordTools.passwordValidator(body.get("newPassword"),
									body.get("newPasswordVerify"));
							if (newPasswordStatus == null) {
								// save data to hashmap and continue!
								newUserData.put("newPassword", body.get("newPassword"));
							} else {
								responseBody.put("message", newPasswordStatus);
								responseBody.put("status", "ERROR");
								return new ResponseEntity<Object>(new Gson().toJson(responseBody),
										HttpStatus.BAD_REQUEST);
							}
						} else {
							responseBody.put("status", "ERROR");
							responseBody.put("message", "Wrong password");
							return new ResponseEntity<Object>(new Gson().toJson(responseBody), HttpStatus.BAD_REQUEST);
						}
					}

					if (body.containsKey("email")) {

						// validate email layout
						Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
						Matcher mat = pattern.matcher(body.get("email"));
						if (!mat.matches()) {
							responseBody.put("message", "Invalid email.");
							responseBody.put("status", "ERROR");
							return new ResponseEntity<Object>(new Gson().toJson(responseBody), HttpStatus.BAD_REQUEST);
						}

						// check is email exist
						else if (userCont.getUser(body.get("email")) != null) {
							responseBody.put("message", "This email is already registered.");
							responseBody.put("status", "ERROR");
							return new ResponseEntity<Object>(new Gson().toJson(responseBody), HttpStatus.BAD_REQUEST);
						} else {

							// save data to hashmap and continue
							newUserData.put("email", body.get("email"));
						}

					}
					if (body.containsKey("username")) {
						newUserData.put("username", body.get("username"));
					}
					if (body.containsKey("firstname")) {
						newUserData.put("firstname", body.get("firstname"));
					}
					if (body.containsKey("lastname")) {
						newUserData.put("lastname", body.get("lastname"));
					}

					UserAccounts updatedAccount = userCont.editAccount(email, newUserData);
					if (updatedAccount != null) {
						HttpHeaders freshToken = new HttpHeaders();
						responseBody.put("status", "SUCCESS");
						responseBody.put("message", "Successfully saved.");
						if(newUserData.containsKey("email")) {
							freshToken.add("Authorization", jwt.createToken(updatedAccount.getEmail(),30));
							return new ResponseEntity<Object>(new Gson().toJson(responseBody),freshToken, HttpStatus.OK);
						}
						return new ResponseEntity<Object>(new Gson().toJson(responseBody), HttpStatus.OK);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Object>(new Gson().toJson(e.getMessage()), HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Object>(new Gson().toJson(jwt.validate(token)), HttpStatus.UNAUTHORIZED);
		}
		return null;
	}
}
