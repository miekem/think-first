package org.ucll.demo.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ucll.demo.domain.Person;

public class PersonStubRepository implements PersonRepository {

	private static Map<String, Person> db;
	
	public PersonStubRepository() {
		if(db == null){
			db = new HashMap<String, Person>();
		}
	}

	public void add(Person object) {
		if(object == null){
			throw new IllegalArgumentException("error.no.person");
		}
		if(get(object.getSocialSecurityNumber()) == null){
			db.put(object.getSocialSecurityNumber(), object);
		}
	}

	public void update(Person object) {
		if(object == null){
			throw new IllegalArgumentException("error.person.not.found");
		}
		db.put(object.getSocialSecurityNumber(), object);
	}

	public List<Person> getAll() {
		return new ArrayList<Person>(db.values());
	}

	public Person get(String id) {
		if(id == null){
			throw new IllegalArgumentException("error.no.socialsecuritynumber");
		}
		return (Person) db.get(id);
	}

	public void delete(Person object) {
		if(object == null){
			throw new IllegalArgumentException("error.no.person");
		}
		db.remove(object.getSocialSecurityNumber());
	}

	@Override
	public void delete(String id) {
		if(id == null){
			throw new IllegalArgumentException("error.no.socialsecuritynumber");
		}
		db.remove(id);
	}

}
