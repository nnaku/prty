package fi.thepaardihub.security;

import javax.crypto.spec.SecretKeySpec;

import com.google.gson.Gson;

import java.security.Key;
import io.jsonwebtoken.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

public class JWT {

	// This must be kept secret!
	private final String SINGSECRET = "bT1%bI1)eF1{lC2*qB9_lJ5|bC1[bJ";
	// Public sign as token issuer.
	private final String ISSUER = "prty.fi";
	
	/**
	 * 
	 * @param id		token owner id (user email)
	 * @param minutes	ttl at minutes! If set to 0, token will not expire.
	 * @return 			URL-safe and compact JWT with Bearer prefix ("Bearer token...") 
	 */
	
	// Construct a JWT
	public String createToken(String id, int minutes) {
		
		// The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		long ttlMillis = TimeUnit.MINUTES.toMillis(minutes);
		Date now = new Date(nowMillis);

		// We will sign our JWT with our secret
		Key signingKey = new SecretKeySpec(SINGSECRET.getBytes(), signatureAlgorithm.getJcaName());

		// Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder();
		builder.setId(id);
		builder.setIssuedAt(now);
		builder.setIssuer(ISSUER);
		builder.signWith(signatureAlgorithm, signingKey);

		// if it has been specified, let's add the expiration
		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		// Builds the JWT and serializes it to a compact, URL-safe string
		return "Bearer "+builder.compact();
	}

	// Validate and return the jti. (jti = user email)
	public Map<String,Object> validate(String token) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		// Remove "Bearer " prefix
		token = token.replace("Bearer ", "");
		Claims claims;
		try {
			// This line will throw an exception if it is not a signed JWS (as expected)
			claims = Jwts.parser().setSigningKey(SINGSECRET.getBytes()).parseClaimsJws(token).getBody();
			returnMap.put("status", "SUCCESS");
			returnMap.put("email", claims.getId());
		} catch (Exception e) {
			returnMap.put("status", "ERROR");
			returnMap.put("message", new Gson().toJson(e));
		}
			
		return returnMap;

	}


}
