package org.ucll.demo.repository;

import java.util.List;

import org.ucll.demo.domain.Person;

public interface PersonRepository {

	public abstract void add(Person object);
	
	public abstract void update(Person object);

	public abstract List<Person> getAll();

	public abstract Person get(String id);

	public abstract void delete(Person object);

	public abstract void delete(String id);
}
