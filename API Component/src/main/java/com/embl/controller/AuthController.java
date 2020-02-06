package com.embl.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.embl.domainobject.User;
import com.embl.service.AuthService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
	
		
	private final AuthService authService;

	@Autowired
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
			
	@PostMapping("/validate")
    public ResponseEntity<?> validateToken(@Valid @RequestBody String token)
    {
		String tokenValidity = "";
		ResponseEntity<?> validityResponse;
    	try {
    		tokenValidity =  authService.isValid(token);
    		validityResponse =  new ResponseEntity<String>(
    				tokenValidity ,HttpStatus.OK);
    		
		} catch (Exception e) {
			tokenValidity = "Error occoured";
			validityResponse =  new ResponseEntity<String>(
    				tokenValidity ,HttpStatus.FORBIDDEN);
		}
		return validityResponse;	
    }
        
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody User user)
    {
    	 String token = ""; 
    	 ResponseEntity<?> authResponse;
    	 
    	 token = authService.authenticate(user);
    	 
    	 if(token.length() > 0) {
    		 authResponse = new ResponseEntity<String>(
 					token,HttpStatus.OK);
    	 }else {
    		 authResponse = new ResponseEntity<String>(
  					"Authorised",HttpStatus.UNAUTHORIZED);
    	 }
    	 
    	 return authResponse;
    }
	

}
