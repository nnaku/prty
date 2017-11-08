package fi.thepaardihub.password;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.thepaardihub.security.Password;

@RunWith(SpringRunner.class)
@SpringBootTest

public class PasswordTest {
	Password passwd = new Password();

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void basicPasswordFunctions() throws Exception {
		String validpw = "Val1dtP4ss";
		String falsepw = "F4ls3P4ss";
		String validHash  = passwd.getSaltedHash(validpw);
		String collisionHash = passwd.getSaltedHash(validpw);
		
		assertNotNull(validHash);
		assertNotNull(collisionHash);
		assertNotEquals(validHash, collisionHash);
		assertTrue(passwd.validPassword(validpw, validHash));
		assertFalse(passwd.validPassword(falsepw, validHash));
		
	}
	@Test
	public void invalidSaltHash() throws Exception {
		exception.expect(IllegalStateException.class);
		passwd.validPassword("Password123", "thisIsHashWithoutSalt");
	}
	
	@Test
	public void emptyPassword() throws Exception {
		exception.expect(IllegalArgumentException.class);
		passwd.getSaltedHash("");
	}
	
	@Test
	public void nullPassword() throws Exception {
		exception.expect(IllegalArgumentException.class);
		passwd.getSaltedHash(null);
	}
	
}
