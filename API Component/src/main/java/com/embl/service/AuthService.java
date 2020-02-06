package com.embl.service;

import javax.validation.Valid;

import com.embl.domainobject.User;

public interface AuthService {

	String isValid(@Valid String token) throws Exception;

	String authenticate(@Valid User user);

}
