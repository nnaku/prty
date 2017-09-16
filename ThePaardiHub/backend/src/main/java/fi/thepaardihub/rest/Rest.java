package fi.thepaardihub.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fi.thepaardihub.dao.users.UsersDao;
import fi.thepaardihub.dao.users.tables.*;

@RestController
public class Rest {
	
	//Currently this class is only used to test database connection
	private UsersDao users;

	@Autowired
	public void setUsers(UsersDao users) {
		this.users = users;
	}

	@RequestMapping("/profiles")
	public @ResponseBody Iterable<UserProfiles> listProfiles(){
		return users.allProfiles();
		
	}
	@RequestMapping("/accounts")
	public @ResponseBody Iterable<UserAccounts>listAccounts() {
		return users.allAccounts();
		
	}
	
	@RequestMapping("/roles")
	public @ResponseBody Iterable<UserRoles> listRoles() {
		return users.allRoles();
		
	}
	

}
