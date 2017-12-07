package fi.thepaardihub.rest.endpoint;



import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.google.gson.Gson;

import fi.thepaardihub.security.JWT;

@Controller
public class RenewToken {
	
	JWT jwt = new JWT();
	/**
	 * 
	 * @param headers contains current jwt token in Authorization header
	 * @return new header if old one was valid.
	 */
	@PostMapping("/refresh")
	public ResponseEntity<?> renewToken(@RequestHeader HttpHeaders headers) {
		// Get token from header and remove Bearer prefix.
		String token = headers.getFirst("Authorization");

		if (jwt.validate(token).get("status") == "SUCCESS") {
			String email = (String) jwt.validate(token).get("email");
			token = jwt.createToken(email,30);
			HttpHeaders freshToken = new HttpHeaders();
			freshToken.add("Authorization", token);
		
			return new ResponseEntity<Object>(email,freshToken, HttpStatus.OK);
		} else {
			String message = new Gson().toJson(jwt.validate(token));
			
			return new ResponseEntity<Object>(message,HttpStatus.UNAUTHORIZED);
		}		
	}
}
