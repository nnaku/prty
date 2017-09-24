package fi.thepaardihub.dao.users.repository;

import static org.junit.Assert.*;
import static org.mockito.Matchers.intThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.thepaardihub.dao.users.repository.UserAccountsRepository;
import fi.thepaardihub.dao.users.tables.UserAccounts;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAccountsRepositoryTest {

	@Autowired
	private UserAccountsRepository userAccRepo;

	@Before
	public void setUp() throws Exception {
		UserAccounts user1 = new UserAccounts("Legend", "hkjadzfzghadjkgfddkua", "Bobby", "NotLegend",
				"Bobby@confuced.com");
		UserAccounts user2 = new UserAccounts("LeeroyJenkins", "ewqtrqerteaaedfae", "Leeroy", "Jenkins",
				"Leeroy.Jenkins@wow.com");

		this.userAccRepo.save(user1);
		this.userAccRepo.save(user2);
	}

	@Test
	public void test() {
		UserAccounts testUser1 = userAccRepo.findOne("Leeroy.Jenkins@wow.com");
		assertNotNull(testUser1);
		assertEquals("LeeroyJenkins", testUser1.getUserName());
		assertEquals("ewqtrqerteaaedfae", testUser1.getPasswordHash());
		assertEquals("Leeroy", testUser1.getFirstName());
		assertEquals("Jenkins", testUser1.getLastName());
		assertEquals(1, testUser1.getUserRolesId());

		UserAccounts testUser2 = userAccRepo.findOne("Bobby@confuced.com");
		assertNotNull(testUser2);
		assertEquals("Legend", testUser2.getUserName());
		assertEquals("hkjadzfzghadjkgfddkua", testUser2.getPasswordHash());
		assertEquals("Bobby", testUser2.getFirstName());
		assertEquals("NotLegend", testUser2.getLastName());
		assertEquals(1, testUser2.getUserRolesId());

		Iterable<UserAccounts> allUsers = this.userAccRepo.findAll();
		int count = 0;
		for (UserAccounts i : allUsers) {
			count++;
		}
		assertTrue(count >= 2);
		this.userAccRepo.delete("Leeroy.Jenkins@wow.com");
		testUser1 = userAccRepo.findOne("Leeroy.Jenkins@wow.com");
		assertNull(testUser1);

		this.userAccRepo.delete("Bobby@confuced.com");
		testUser2 = userAccRepo.findOne("Bobby@confuced.com");
		assertNull(testUser2);

	}

}
