package com.hbo.blue_oasis.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtUtils {

	@Value("{security.jwt.key.private}")
	private String privateKey;

	@Value("{security.jwt.user.generator}")
	private String userGenerator;

	public String createToken(Authentication authentication) {
		Algorithm algorithm = Algorithm.HMAC256(this.privateKey);

		String username = authentication.getPrincipal().toString();

		String authorities = authentication.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		String jwtToken = JWT.create()
				.withIssuer(this.userGenerator)
				.withSubject(username)
				.withClaim("authorities", authorities)
				.withIssuedAt(new Date())
				.withExpiresAt(new Date(System.currentTimeMillis() + 1800000000))
				.withJWTId(UUID.randomUUID().toString())
				.withNotBefore(new Date(System.currentTimeMillis()))
				.sign(algorithm);

		return jwtToken;

	}

	public DecodedJWT validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
			JWTVerifier verifier = JWT.require(algorithm)
					.withIssuer(this.userGenerator)
					.build();
			DecodedJWT decodedJWT = verifier.verify(token);
			return decodedJWT;
		} catch (JWTVerificationException exception) {
			throw new JWTVerificationException("Token invalid, not Authori");
		}
	}

	public String extractUsername(DecodedJWT decodedJWT) {
		return decodedJWT.getSubject();
	}

	public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName) {
		return decodedJWT.getClaim(claimName);
	}

	public Map<String, Claim> returnAllClaim(DecodedJWT decodedJWT) {
		return decodedJWT.getClaims();
	}

	public ArrayList<String> getAuthorities(DecodedJWT decodedJWT, String token) {
		String[] authorities = null;
		StringBuilder authoritiesStr = this.getAuthoritiesStr(decodedJWT, token);
		System.out.println(authoritiesStr);
		authorities = authoritiesStr.toString().split(",");
		ArrayList<String> authoritiesList = new ArrayList<String>(Arrays.asList(authorities));
		return authoritiesList;
	}

	public StringBuilder getAuthoritiesStr(DecodedJWT decodedJWT, String token) {
		Claim authoritiesClaim = null;
		authoritiesClaim = this.getSpecificClaim(decodedJWT, "authorities");
		StringBuilder authoritiesStr = new StringBuilder(authoritiesClaim.toString());
		authoritiesStr.deleteCharAt(0);
		authoritiesStr.deleteCharAt((authoritiesStr.length() - 1));
		return authoritiesStr;
	}

	public String getUserName(DecodedJWT decodedJWT, String token) {
		Claim usernameClaim = null;
		String username = null;
		usernameClaim = this.getSpecificClaim(decodedJWT, "sub");
		String usernamestr = usernameClaim.toString();
		username = usernamestr.replace("\"", "");
		return username;
	}

	public String getRole(DecodedJWT decodedJWT, String token) {
		StringBuilder authoritiesStr = this.getAuthoritiesStr(decodedJWT, token);
		String searchStr = "ROLE_";
		int init = authoritiesStr.indexOf(searchStr);
		int end = authoritiesStr.indexOf(",", init);
		String roleStr = authoritiesStr.substring(init, end);
		String[] roleSplit = roleStr.split("_");
		String role = roleSplit[1];
		return role;
	}

}
