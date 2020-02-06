package com.embl.service;

import java.util.Date;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.embl.domainobject.User;
import com.embl.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepo;
	
	public static final String AUTH_TOKEN_SECRET = "EMBL007";
	
	static final long EXPIRATIONTIME = 999999;
	
	@Autowired	
	public AuthServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
		
		User us = new User("Joey", "pizza");
		userRepo.save(us);
		
		us = new User("admin", "password");
		userRepo.save(us);
		
	}

	@Override
	public String isValid(String token) throws Exception {
		
		boolean valid = isTokenValid(token);
		
		if(valid) {
			return "validToken";
		}else {
			return "tokenInValid";
		}
	}

	@Override
	public String authenticate(@Valid User user) {
		
		String generatedToken = "";
		User userfound = userRepo.findByUserNameAndPassword(user.getUserName(), user.getPassword());
		
		if(userfound.getUserName() != "") {
			 generatedToken = createToken(userfound.getUserName());
		}
		
		return generatedToken;
	}
	
	
	
	private String createToken(String userName) {
		try {
				
			String jwtToken = Jwts.builder()
					.setSubject(userName)
					.setIssuedAt(new Date())
					.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
					.signWith(SignatureAlgorithm.HS384, AUTH_TOKEN_SECRET).compact();
			
			
			return jwtToken;
		} catch (JwtException exception) {
			exception.printStackTrace();
		}
		return null;
	}
	
		
	private boolean isTokenValid(String token) throws Exception{
		
		boolean validToken = false;
		
		try {
			Claims body = Jwts.parser().setSigningKey(AUTH_TOKEN_SECRET).parseClaimsJws(token).getBody();
			long expiryTime = body.getExpiration().getTime();
			long currentTime  = new Date().getTime();

			if(expiryTime > currentTime) {
				validToken = true;
			}else {
				validToken = false;
			}
		} catch (ExpiredJwtException e) {
			throw new Exception("Authorization header passed has been expired");
		} 
		
		return validToken;
	}

}
