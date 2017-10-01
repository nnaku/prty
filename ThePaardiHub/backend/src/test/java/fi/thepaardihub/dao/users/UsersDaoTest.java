package fi.thepaardihub.dao.users;

import static org.junit.Assert.*;

import fi.thepaardihub.dao.users.repository.UserRolesRepository;
import fi.thepaardihub.dao.users.tables.UserAccounts;
import fi.thepaardihub.dao.users.tables.UserRoles;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersDaoTest {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private UserRolesRepository userRolesRepo;

    @Before
    public void setUp() {

    }

    @Test
    public void test() {
        UserAccounts account = new UserAccounts("LeeroyJenkins", "ewqtrqerteaaedfae",
                "Leeroy", "Jenkins", "Leeroy.Jenkins@wow.com");

        usersDao.saveOrUpdateAccount(account);

        UserAccounts test1 = usersDao.getUser("Leeroy.Jenkins@wow.com");
        assertNotNull(test1);


        UserRoles role = new UserRoles(12, "Village idiot");
        usersDao.saveOrUpdateRoles(role);

        UserRoles test2 = userRolesRepo.findOne(12);
        assertNotNull(test2);


        usersDao.deleteUser("Leeroy.Jenkins@wow.com");
        UserAccounts test3 = usersDao.getUser("Leeroy.Jenkins@wow.com");
        assertNull(test3);

        usersDao.deletedRole(12);
        UserRoles test4 = userRolesRepo.findOne(12);
        assertNull(test4);
    }
}
