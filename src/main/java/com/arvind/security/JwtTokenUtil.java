package com.arvind.security;



import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.arvind.security.model.CustomUserDetail;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3836804824356259226L;
	private static final String CLAIM_KEY_USERNAME = "userName";
	private static final String CLAIM_KEY_JTI = "userId";
	
	
	@Value("${jwt.claimSecret}")
	private String claimSecret;
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;

	public String generateToken(CustomUserDetail userDetails) {
		Map<String, Object> claims = new HashMap<String,Object>();

		claims.put(CLAIM_KEY_JTI,userDetails.getConsumerStageId());
		claims.put(CLAIM_KEY_USERNAME,userDetails.getUserEmail());
		
		/*claims.put(CLAIM_KEY_JTI, JwtCryptoUtil.generateSecureToken(String.valueOf(userDetails.getConsumerStageId()), claimSecret));
		claims.put(CLAIM_KEY_USERNAME, JwtCryptoUtil.generateSecureToken(userDetails.getUserEmail(), claimSecret));
		String roles = userDetails.getRole().stream().map(i -> i.toString())
				.collect(Collectors.joining(CLAIM_LIST_DELIMITERS));
		claims.put(CLAIM_KEY_SCOPE,JwtCryptoUtil.generateSecureToken(roles, claimSecret));*/
		/*claims.put(CLAIM_KEY_COUNTRY,
				JwtCryptoUtil.generateSecureToken(Integer.toString(userDetails.getCountryId()), claimSecret));
		claims.put(CLAIM_KEY_CREATED, new Date());*/

		return generateToken(claims);
		//return null;
	}
	
	private String generateToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate())
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	private Date generateExpirationDate() {
		return new Date(System.currentTimeMillis() + expiration * 1000);
	}
	


	
	public Boolean validateToken(String token, CustomUserDetail userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUserEmail()) && !isTokenExpired(token));
	}
	
	public Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}
	
	public String getUsernameFromToken(String token) {
		String username;
		try {
			final Claims claims = getClaimsFromToken(token);
			username = JwtCryptoUtil.parseSecureToken(claims.getSubject(), claimSecret);
		} catch (Exception e) {
			username = null;
		}
		return username;
	}
	
	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

}
