package fi.thepaardihub.rest.jsonobject;

public class Login {
	String email;
	String password;
	String remember;
	
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public Boolean getRemember() {
		return Boolean.valueOf(remember);
	}

}
 