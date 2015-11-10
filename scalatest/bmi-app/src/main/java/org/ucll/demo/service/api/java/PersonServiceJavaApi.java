package org.ucll.demo.service.api.java;

import java.util.List;

import org.ucll.demo.domain.Examination;
import org.ucll.demo.domain.Person;
import org.ucll.demo.repository.PersonRepository;
import org.ucll.demo.repository.PersonStubRepository;
import org.ucll.demo.service.PersonService;
import org.ucll.demo.service.api.java.assembler.ExaminationToAssembler;
import org.ucll.demo.service.api.java.assembler.PersonToAssembler;
import org.ucll.demo.service.api.java.to.ExaminationDetail;
import org.ucll.demo.service.api.java.to.PersonDetail;
import org.ucll.demo.service.api.java.to.PersonOverview;

public class PersonServiceJavaApi {
	private PersonService personService;
	private ExaminationToAssembler examinationToAssembler;
	private PersonToAssembler personToAssembler;

	public PersonServiceJavaApi(){
		//TODO use CDI, ...
		PersonRepository repository = new PersonStubRepository();
		personService = new PersonService(repository);
		examinationToAssembler = new ExaminationToAssembler();
		personToAssembler = new PersonToAssembler();
	}

	public PersonServiceJavaApi(PersonRepository repository){
		//TODO remove
		//TODO use CDI, ...
		personService = new PersonService(repository);
		examinationToAssembler = new ExaminationToAssembler();
		personToAssembler = new PersonToAssembler();
	}
	
	public PersonServiceJavaApi(PersonService personService,
			ExaminationToAssembler examinationToAssembler,
			PersonToAssembler personToAssembler) {
		// TODO use CDI, ...
		this.personService = personService;
		this.examinationToAssembler = examinationToAssembler;
		this.personToAssembler = personToAssembler;
	}
	
	public void addPerson(PersonDetail personDetail) {
		Person person = personToAssembler.createPerson(personDetail);
		getPersonService().addPerson(person);
	}

	public void addExamination(ExaminationDetail to, String personId) {
		Examination examination = examinationToAssembler.createExamination(to);
		getPersonService().addExamination(examination, personId);
	}

	public PersonDetail getPerson(String id) {
		Person person = getPersonService().getPerson(id);
		if (person == null) {
			throw new IllegalArgumentException("error.person.not.found");
		}
		return personToAssembler.createPersonDetailTo(person);
	}

	public double getAverageBmi()  {
		return getPersonService().getAverageBmi();
	}

	public List<PersonOverview> getPersons()  {
		List<Person> persons = getPersonService().getPersons();
		List<PersonOverview> personOverviewList = personToAssembler.createPersonOverviewTos(persons);
		return personOverviewList;
	}

	public void deletePerson(String id){
		getPersonService().deletePerson(id);
	}
	
	protected PersonService getPersonService() {
		return personService;
	}

	protected void setPersonService(PersonService personService) {
		this.personService = personService;
	}
}
