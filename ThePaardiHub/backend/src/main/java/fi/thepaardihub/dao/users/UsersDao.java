package fi.thepaardihub.dao.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.thepaardihub.dao.users.repository.*;
import fi.thepaardihub.dao.users.tables.*;
import fi.thepaardihub.security.Password;

@Service
public class UsersDao {

    private UserAccountsRepository userAccRepo;
    private Password password;
    private UserRolesRepository userRolesRepo;

    @Autowired
    public UsersDao(UserAccountsRepository userAccRepo, UserRolesRepository userRolesRepo) {
        this.password = new Password();
        this.userAccRepo = userAccRepo;
        this.userRolesRepo = userRolesRepo;
    }

    /**
     * @return contents of UserAccounts table
     */
    public List<UserAccounts> allAccounts() {
        List<UserAccounts> accounts = new ArrayList<>();
        userAccRepo.findAll().forEach(accounts::add);
        return accounts;
    }

    /**
     * @return content of UserRoles table
     */
    public List<UserRoles> allRoles() {
        List<UserRoles> roles = new ArrayList<>();
        userRolesRepo.findAll().forEach(roles::add);
        return roles;
    }

    /**
     * Used to add or update user in the UserAccount table
     *
     * @param add that will be added or updated
     * @return added or updated userAccount
     */
    public UserAccounts saveOrUpdateAccount(UserAccounts add) {
        userAccRepo.save(add);
        return add;
    }

    /**
     * Used to add or update role in UserRole table
     *
     * @param add is UserRole to be updated or added
     * @return added or update userRoles
     */
    public UserRoles saveOrUpdateRoles(UserRoles add) {
        userRolesRepo.save(add);
        return add;
    }

    /**
     * Search a user with a given id
     *
     * @param id of the account
     * @return userAccount with given id or null
     */
    public UserAccounts getUser(String id) {
        return userAccRepo.findOne(id);
    }

    public void deleteUser(String id) {
        userAccRepo.delete(id);
    }

    public void deletedRole(int id) {
        userRolesRepo.delete(id);
    }
}
