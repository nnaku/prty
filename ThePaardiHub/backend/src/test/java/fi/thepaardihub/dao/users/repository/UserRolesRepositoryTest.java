package fi.thepaardihub.dao.users.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.thepaardihub.dao.users.repository.UserRolesRepository;
import fi.thepaardihub.dao.users.tables.UserRoles;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRolesRepositoryTest {

    @Autowired
    private UserRolesRepository userRolesRepo;


    @Before
    public void setUp() throws Exception {
        UserRoles role1 = new UserRoles(12, "Village idiot");
        UserRoles role2 = new UserRoles(13, "Potato");

        userRolesRepo.save(role1);
        userRolesRepo.save(role2);
    }

    @Test
    public void test() {
        UserRoles test1 = userRolesRepo.findOne(12);
        assertNotNull(test1);
        assertEquals("Village idiot", test1.getRoleName());

        UserRoles test2 = userRolesRepo.findOne(13);
        assertNotNull(test2);
        assertEquals("Potato", test2.getRoleName());

        Iterable<UserRoles> allRoles = userRolesRepo.findAll();
        int count = 0;
        for (UserRoles i : allRoles) {
            count++;
        }
        assertTrue(count >= 2);

        userRolesRepo.delete(12);
        test1 = userRolesRepo.findOne(12);
        assertNull(test1);

        userRolesRepo.delete(13);
        test2 = userRolesRepo.findOne(13);
        assertNull(test2);

    }

}
