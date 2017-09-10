package fi.thepaardihub.dao.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.thepaardihub.dao.users.repsitory.*;
import fi.thepaardihub.dao.users.tables.*;

@Service
public class UsersDao {

	private UserAccountsRepository userAccRepo;
	private UserProfilesRepository userProfRepo;
	private UserRolesRepository userRolesRepo;
	
	@Autowired
	public UsersDao(UserAccountsRepository userAccRepo, UserProfilesRepository userProfRepo,
			UserRolesRepository userRolesRepo) {
		
		this.userAccRepo = userAccRepo;
		this.userProfRepo = userProfRepo;
		this.userRolesRepo = userRolesRepo;
	}
	
	
	public List<UserAccounts> allAccounts(){
		List<UserAccounts> accounts = new ArrayList<>();
		userAccRepo.findAll().forEach(accounts::add);
		return accounts;
	}
	
	public List<UserProfiles> allProfiles(){
		List<UserProfiles> profiles = new ArrayList();
		userProfRepo.findAll().forEach(profiles::add);
		return profiles;
	}
	public List<UserRoles> allRoles(){
		List<UserRoles> roles = new ArrayList<>();
		userRolesRepo.findAll().forEach(roles::add);
		return roles;
	}
	
	
}
