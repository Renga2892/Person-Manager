package com.embl.repository;

import org.springframework.data.repository.CrudRepository;

import com.embl.domainobject.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUserNameAndPassword(String userName, String password);

}
