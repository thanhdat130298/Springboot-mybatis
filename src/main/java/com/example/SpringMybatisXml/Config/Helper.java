package com.example.SpringMybatisXml.Config;

import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.SpringMybatisXml.Exception.UnAuthorized401;
import com.example.SpringMybatisXml.Models.Users.UserInfoModel;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class Helper {
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public String GenerateToken(UserInfoModel user) {

		Algorithm algorithm = Algorithm.HMAC256("this is secret key");
		String jwt = JWT.create().withClaim("username", user.getUsername())
				.withClaim("displayName", user.getDisplayName()).withClaim("userId", user.getUserId())
				.withClaim("roleName", user.getRoleName())
				.withClaim("password", user.getPassword())
				.withExpiresAt(new Date(System.currentTimeMillis() + 999999999))
//				1 Phút Andiez = 60000
//				Forever alone = 999999999
				.withIssuedAt(new Date(System.currentTimeMillis())).sign(algorithm);
		return jwt;
	}

	public UserInfoModel VerifyToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256("this is secret key"); // use more secure key
//			verify token with secret key
			JWTVerifier verifier = JWT.require(algorithm).build(); // Reusable verifier instance
			verifier.verify(token);
			// decode to get payload
			String username = JWT.decode(token).getClaims().get("username") != null
					? JWT.decode(token).getClaims().get("username").asString()
					: "";
			String displayName = JWT.decode(token).getClaims().get("displayName") != null
					? JWT.decode(token).getClaims().get("displayName").asString()
					: "";
			String roleName = JWT.decode(token).getClaims().get("roleName") != null
					? JWT.decode(token).getClaims().get("roleName").asString()
					: "";
			String password = JWT.decode(token).getClaims().get("password") != null
					? JWT.decode(token).getClaims().get("password").asString()
							: "";
			int userId = JWT.decode(token).getClaims().get("userId").asInt();
			UserInfoModel user = new UserInfoModel(username, displayName, userId, roleName, password);
			return user;
		} catch (JWTVerificationException exception) {
			System.out.println("Error: " + exception);
			throw new UnAuthorized401(exception.getMessage());
		}
	}

	public String EncodePassword(String password) {

		String encodedPassword = passwordEncoder.encode(password);
		return encodedPassword;
	}

	public Boolean isMatchPassword(String password, String pwEncoded) {
		boolean isPasswordMatch = passwordEncoder.matches(password, pwEncoded);
		return isPasswordMatch;
	}
}