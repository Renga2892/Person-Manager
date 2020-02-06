package com.embl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.embl.domainobject.PersonDO;
import com.embl.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {


	private final PersonRepository personRepository;


	public PersonServiceImpl(final PersonRepository personRepository)
	{
		this.personRepository = personRepository;
		
		PersonDO ps = new PersonDO();

		String[] hob = {"Chess", "Football", "Shooting"};

		ps.setFirstName("John");
		ps.setLastName("Doe");
		ps.setAge(28);
		ps.setFavouriteColor("Red");
		ps.setHobbies(hob);

		save(ps);

		ps = new PersonDO();
		hob = new String[]{"Football", "Cricket"};
		
		ps.setFirstName("Bill");
		ps.setLastName("Goldberg");
		ps.setAge(25);;
		ps.setFavouriteColor("Blue");
		ps.setHobbies(hob);

		save(ps);
		
	}

	@Override
	public PersonDO save(PersonDO ps) {

		return personRepository.save(ps);

	}

	public List<PersonDO> listAll(){

		return (List<PersonDO>) personRepository.findAll();
	}

	@Override
	public void delete(long personId) throws Exception {

		personRepository.deleteById(personId);

	}

}
