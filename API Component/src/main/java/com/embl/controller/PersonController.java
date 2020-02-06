package com.embl.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.embl.domainobject.PersonDO;
import com.embl.service.PersonService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1/person")
public class PersonController {
	
	private final PersonService personService;


	@Autowired
	public PersonController(final PersonService PersonService)
	{
		this.personService = PersonService;
	}

    
    @GetMapping("/list")
    public List<PersonDO> listAllPerson()
    {
    	return personService.listAll();
		
    }
        
    @PostMapping("/person")
    public ResponseEntity<?>  addUpdatePerson(@Valid @RequestBody PersonDO personDO)
    {
    	ResponseEntity<?> addUpdateResponse;
    	
    	PersonDO response =  personService.save(personDO);
    	
    	if(response.getId() != 0) {
    		addUpdateResponse = new ResponseEntity<String>(
    				response.toString(),HttpStatus.CREATED);
    	}else {
    		addUpdateResponse = new ResponseEntity<String>(
					"Conflict",HttpStatus.CONFLICT);
    	}
    	
    	return addUpdateResponse;
    }
    
    @DeleteMapping("/deletePerson/{personId}")
    public ResponseEntity<?>  deletePerson(@PathVariable long personId) 
    {
    	ResponseEntity<?> deleteResponse;
    	try {
			personService.delete(personId);
			deleteResponse = new ResponseEntity<String>(
					"Deleted",HttpStatus.OK);
		} catch (Exception e) {
			deleteResponse = new ResponseEntity<String>(
					"Bad Request",HttpStatus.BAD_REQUEST);
		}
    	
    	return deleteResponse;
    	
    }
    

}
