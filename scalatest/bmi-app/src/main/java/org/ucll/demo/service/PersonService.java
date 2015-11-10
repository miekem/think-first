package org.ucll.demo.service;

import java.util.List;

import org.ucll.demo.domain.Examination;
import org.ucll.demo.domain.Person;
import org.ucll.demo.repository.PersonRepository;

public class PersonService {
	private PersonRepository personRepository;

	public PersonService(PersonRepository personRepository) {
		setPersonRepository(personRepository);
	}

	public void addPerson(Person person) {
		getPersonRepository().add(person);
	}

	public void addExamination(Examination examination, String personId) {
		Person person = getPerson(personId);
		if (person == null) {
			throw new IllegalArgumentException("error.person.not.found");
		}
		person.addExamination(examination);
		getPersonRepository().update(person);
	}

	public Person getPerson(String id) {
		return (Person) getPersonRepository().get(id);
	}

	public double getAverageBmi()  {
		List<Person> persons = getPersons();
		double averageBmi = 0;

		if (persons.size() > 0) {
			double totalBmi = 0;
			for (Person person : persons) {
				totalBmi += person.getBmi();
			}
			averageBmi = totalBmi / persons.size();
		}
		return averageBmi;
	}

	public List<Person> getPersons()  {
		return getPersonRepository().getAll();
	}
	
	public void deletePerson(String id){
		getPersonRepository().delete(id);
	}

	private void setPersonRepository(PersonRepository personDb) {
		this.personRepository = personDb;
	}

	protected PersonRepository getPersonRepository() {
		return personRepository;
	}
}
