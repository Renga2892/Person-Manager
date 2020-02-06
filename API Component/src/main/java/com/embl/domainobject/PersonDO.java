package com.embl.domainobject;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
    name = "PERSON"
)
public class PersonDO {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PERSON_ID")
	//@JsonIgnore
    private Long id;
	
	@Column(name="FIRSTNAME")
	private String firstName;
	
	@Column(name="LASTNAME")
	private String lastName;
	
	@Column(name="AGE")
	private int age;
	
	@Column(name="FAVOURITE_COLOR")
	private String favouriteColor;
	
	@Column(name="HOBBIES")
	private String[] hobbies;

	public PersonDO() {
		
	}
	
	public PersonDO(Long id, String firstName, String lastName, int age, String favouriteColor, String[] hobbies) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.favouriteColor = favouriteColor;
		this.hobbies = hobbies;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getFavouriteColor() {
		return favouriteColor;
	}

	public void setFavouriteColor(String favouriteColor) {
		this.favouriteColor = favouriteColor;
	}

	public String[] getHobbies() {
		return hobbies;
	}

	public void setHobbies(String[] hobbies) {
		this.hobbies = hobbies;
	}

	@Override
	public String toString() {
		return "PeopleDO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", favouriteColor=" + favouriteColor + ", hobbies=" + Arrays.toString(hobbies) + "]";
	}
	
}
