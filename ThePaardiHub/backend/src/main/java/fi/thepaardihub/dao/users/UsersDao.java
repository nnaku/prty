package fi.thepaardihub.dao.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.thepaardihub.dao.users.repository.*;
import fi.thepaardihub.dao.users.tables.*;

@Service
public class UsersDao {

	private UserAccountsRepository userAccRepo;
	
	private UserRolesRepository userRolesRepo;
	
	@Autowired
	public UsersDao(UserAccountsRepository userAccRepo, UserRolesRepository userRolesRepo) {
		
		this.userAccRepo = userAccRepo;
		this.userRolesRepo = userRolesRepo;
	}
	
	
	public List<UserAccounts> allAccounts(){
		List<UserAccounts> accounts = new ArrayList<>();
		userAccRepo.findAll().forEach(accounts::add);
		return accounts;
	}
	
	public List<UserRoles> allRoles(){
		List<UserRoles> roles = new ArrayList<>();
		userRolesRepo.findAll().forEach(roles::add);
		return roles;
	}
	
	
}
