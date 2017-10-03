package fi.thepaardihub.rest.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import fi.thepaardihub.controllers.UserController;
import fi.thepaardihub.dao.users.tables.UserAccounts;
import fi.thepaardihub.rest.jsonobject.Login;

@Controller
public class LoginEndpoint {
	private UserController users;
	
	@Autowired
	public void setUsers(UserController users) {
		this.users = users;
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> createAccount(@RequestBody Login login) {
		
		UserAccounts user = users.login(login.getUsername(), login.getPsw());
		if(user == null) {
			return new ResponseEntity<Object>("Bad login, check your email or password", new HttpHeaders(), HttpStatus.UNAUTHORIZED);
		}
		// CREATE SESSION HERE!!!!!!!!!!!!!!!!!!!
		return new ResponseEntity<Object>("Successfull login", new HttpHeaders(), HttpStatus.OK);
	}

}
