package org.ucll.demo.ui;

import org.ucll.demo.repository.PersonRepository;
import org.ucll.demo.repository.PersonStubRepository;
import org.ucll.demo.service.PersonService;
import org.ucll.demo.service.api.java.PersonServiceJavaApi;
import org.ucll.demo.service.api.java.assembler.ExaminationToAssembler;
import org.ucll.demo.service.api.java.assembler.PersonToAssembler;

public class ServiceBean {

	private PersonServiceJavaApi service;

	public ServiceBean() {
		super();
		PersonRepository repository = new PersonStubRepository();
		PersonService personService = new PersonService(repository);
		ExaminationToAssembler examinationToAssembler = new ExaminationToAssembler();
		PersonToAssembler personToAssembler = new PersonToAssembler();
		service = new PersonServiceJavaApi(personService, examinationToAssembler, personToAssembler); //TODO Use CDI
	}

	public PersonServiceJavaApi getService() {
		return service;
	}

}