package com.embl.service;

import java.util.List;

import com.embl.domainobject.PersonDO;

public interface PersonService {

	PersonDO save(PersonDO ps);
	
	List<PersonDO> listAll();

	void delete(long personId) throws Exception;

}
