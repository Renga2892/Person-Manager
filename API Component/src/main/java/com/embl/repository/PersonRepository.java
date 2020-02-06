package com.embl.repository;

import org.springframework.data.repository.CrudRepository;

import com.embl.domainobject.PersonDO;


public interface PersonRepository extends CrudRepository<PersonDO, Long> {
	

}
