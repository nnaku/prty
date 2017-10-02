package fi.thepaardihub.password;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import org.apache.commons.codec.binary.Base64;

public class Password {

	private final int ITERATIONS = 20000;
	private final int SALTLEN = 32;
	private final int KEYLEN = 256;

	private String hash(String password, byte[] salt) throws Exception {
		if (password == null || password.length() == 0) {
			throw new IllegalArgumentException("Empty passwords are not supported.");
		}
		SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		SecretKey key = f.generateSecret(new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEYLEN));
		return Base64.encodeBase64URLSafeString(key.getEncoded());
	}

	public String getSaltedHash(String password) throws Exception {
		byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(SALTLEN);
		// store the salt with the password
		return Base64.encodeBase64URLSafeString(salt) + "$" + hash(password, salt);
	}

	public boolean validPassword(String password, String stored) throws Exception {
		String[] saltAndPass = stored.split("\\$");
		if (saltAndPass.length != 2) {
			throw new IllegalStateException("The stored password have the form 'salt$hash'");
		}
		String hashOfInput = hash(password, Base64.decodeBase64(saltAndPass[0]));
		return hashOfInput.equals(saltAndPass[1]);
	}

	public String passwordValidator(String password, String passwordVerify) {
		// Checks for password misspelling
		if (!password.equals(passwordVerify)) {
			return "Passwords does not match!";
		}
		// Checks for at least 8 characters
		else if (password.length() < 8) {
			return "You should use at least eight characters!";
		}
		// Checks at least one char is uppercase
		else if (password.equals(password.toLowerCase())) {
			return "You should use at least one uppercase character!";
		}
		// Checks at least one char is lowercase
		else if (password.equals(password.toUpperCase())) {
			return "You should use at least one lowercase character!";
		}
		// Checks at least one char is not alpha
		else if (!password.matches("(?=.*[0-9]).*")) {
			return "You should use at least one numeric or special character!";
		}
		// Check that it doesn't contain AND or NOT
		else if (password.contains("AND") || password.contains("NOT")) {
			return "I think SQL syntax is not good for passwords!? I mean AND or NOT.";
		}
		// Valid password
		else {
			return null;
		}
	}

}