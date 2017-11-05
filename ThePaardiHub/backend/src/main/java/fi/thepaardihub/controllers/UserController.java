package fi.thepaardihub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.thepaardihub.dao.games.GamesDao;
import fi.thepaardihub.dao.users.UsersDao;
import fi.thepaardihub.dao.users.tables.UserAccounts;
import fi.thepaardihub.dao.users.tables.UserRoles;
import fi.thepaardihub.password.Password;

@Service
public class UserController {

    private UsersDao users;
    private Password pswHasher;

    @Autowired
    public UserController(UsersDao usersDao, GamesDao gamesDao) {
        this.users = usersDao;
        this.pswHasher = new Password();
    }

    /**
     * Creates an userAccount for UserAccounts table
     *
     * @param userName  UserName of account that will be created
     * @param psw       password of account that will be created
     * @param firstName first name of account that will be created
     * @param lastName  last name of account that will be created
     * @param email     email of account that will be created
     * @return created userAccount or null if creating failed
     */
    public UserAccounts createAccount(String userName, String psw, String firstName, String lastName, String email) {
        try {
            UserAccounts add = new UserAccounts();
            add.setUserName(userName);
            add.setPasswordHash(pswHasher.getSaltedHash(psw));
            add.setFirstName(firstName);
            add.setLastName(lastName);
            add.setEmail(email);
            users.saveOrUpdateAccount(add);
            return add;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<UserAccounts> allAccounts() {
        return users.allAccounts();
    }

    public List<UserRoles> allRoles() {
        return users.allRoles();
    }

    /**
     * Search the database for the email and check if the passwords matches
     *
     * @param email email of account user is trying to login
     * @param psw   password of account user is trying to login
     * @return UserAccount if login is successfully else null
     */
    public UserAccounts login(String email, String psw) {
        try {
            UserAccounts account = users.getUser(email);

            if (account != null) {
                System.out.println("Not Null");
                String hash = account.getPasswordHash();
                if (pswHasher.validPassword(psw, hash)) {
                    return account;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public UserAccounts getUser(String email) {
        try {
            UserAccounts account = users.getUser(email);

            if (account != null) {
            	return account;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void fakeData() {
        try {
            UserRoles banned = new UserRoles(0, "Banned");
            UserRoles member = new UserRoles(1, "Member");
            UserRoles admin = new UserRoles(9, "Admin");
            UserRoles god = new UserRoles(99, "God");

            users.saveOrUpdateRoles(banned);
            users.saveOrUpdateRoles(member);
            users.saveOrUpdateRoles(admin);
            users.saveOrUpdateRoles(god);

            UserAccounts add1 = new UserAccounts("nAku", this.pswHasher.getSaltedHash("aku.kangas"), "Aku", "Kangas",
                    "Aku.Kangas@metropolia.fi");
            UserAccounts add2 = new UserAccounts("Matti", this.pswHasher.getSaltedHash("matti.holopainen"), "Matti",
                    "Holopainen", "Matti.Holopainen2@metropolia.fi");
            UserAccounts add3 = new UserAccounts("Tiina", this.pswHasher.getSaltedHash("tiina.ojala"), "Tiina", "Ojala",
                    "Tiina.Ojala3@metropolia.fi");
            UserAccounts add4 = new UserAccounts("Maarit", this.pswHasher.getSaltedHash("maarit.saariniemi"), "Maarit",
                    "Saariniemi", "Maarit.Saariniemi@Metropolia.fi");

            users.saveOrUpdateAccount(add1);
            users.saveOrUpdateAccount(add2);
            users.saveOrUpdateAccount(add3);
            users.saveOrUpdateAccount(add4);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
