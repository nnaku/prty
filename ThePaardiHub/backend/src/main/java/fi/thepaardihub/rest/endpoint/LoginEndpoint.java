package fi.thepaardihub.rest.endpoint;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.google.gson.Gson;
import fi.thepaardihub.controllers.UserController;
import fi.thepaardihub.dao.users.tables.UserAccounts;
import fi.thepaardihub.rest.jsonobject.Login;
import fi.thepaardihub.security.JWT;

@Controller
public class LoginEndpoint {
	private UserController users;
	private UserAccounts user;
	private JWT jwt = new JWT();
	private Map<String, String> jsonMap;
	private HttpHeaders headers;

	@Autowired
	public void setUsers(UserController users) {
		this.users = users;
	}

	@PostMapping("/login")
	public ResponseEntity<?> createAccount(@RequestBody Login login) {

		user = users.login(login.getEmail(), login.getPassword());
		jsonMap = new HashMap<String, String>();

		if (user == null) {
			jsonMap.put("status", "ERROR");
			jsonMap.put("message", "Bad login, check your email or password");

			return new ResponseEntity<Object>(new Gson().toJson(jsonMap), HttpStatus.UNAUTHORIZED);
		} else {

			// int ttl = login.getRemember() ? 0 : 30;
			String token = jwt.createToken(user.getEmail(), 30);
			headers = new HttpHeaders();
			headers.add("Authorization", token);
			jsonMap.put("status", "SUCCESS");
			jsonMap.put("message", "Successful login");

			return new ResponseEntity<Object>(new Gson().toJson(jsonMap), headers, HttpStatus.OK);
		}
	}
}