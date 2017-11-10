package fi.thepaardihub.rest.endpoint;

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

import fi.thepaardihub.controllers.UserController;
import fi.thepaardihub.dao.users.tables.UserAccounts;
import fi.thepaardihub.rest.jsonobject.SignIn;
import fi.thepaardihub.security.Password;


@Controller
public class SignInEndpoint {


	
	private UserController users;
	private Password password = new Password();

	/**
	 *
	 * @param users Set Users
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

		Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(signin.getEmail());

        if(!mat.matches()){
        	return new ResponseEntity<Object>("Invalid email.", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
		
		if(users.getUser(signin.getEmail()) != null) {
			return new ResponseEntity<Object>("This email is already registered.", new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
		
		String passwordError = password.passwordValidator(signin.getPassword(), signin.getPasswordVerify());
		if (passwordError != null) {
			return new ResponseEntity<Object>(passwordError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
			
		}
		UserAccounts newUser = users.createAccount(signin.getUsername(), signin.getPassword(), signin.getFirstname(), signin.getLastname(), signin.getEmail());
		if(newUser == null) {
			return new ResponseEntity<Object>("Something went wrong :(", new HttpHeaders(), HttpStatus.BAD_REQUEST);
			
		}

		return new ResponseEntity<Object>("Successfully Sign-in", new HttpHeaders(), HttpStatus.OK);
		
	}
}
