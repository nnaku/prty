package fi.thepaardihub.controllers;

import static org.junit.Assert.*;

import fi.thepaardihub.dao.users.UsersDao;
import fi.thepaardihub.dao.users.tables.UserAccounts;
import fi.thepaardihub.services.UserService;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService controller;
    @Autowired
    private UsersDao usersDao;

    @Test
    public void test() {

        UserAccounts test1 = controller.createAccount("LeeroyJenkins", "leeroymeme",
                "Leeroy", "Jenkins", "Leeroy.Jenkins@wow.com");
        assertNotNull(test1);

        UserAccounts test2 = controller.login("Leeroy.Jenkins@wow.com", "leeroymeme");
        assertNotNull(test2);


    }

    @After
    public void delete() {
        usersDao.deleteUser("Leeroy.Jenkins@wow.com");
    }
}
