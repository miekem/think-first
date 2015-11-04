package org.ucll.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.ucll.demo.domain.Gender;
import org.ucll.demo.repository.PersonStubRepository;
import org.ucll.demo.service.api.java.PersonServiceJavaApi;
import org.ucll.demo.service.api.java.assembler.ExaminationToAssembler;
import org.ucll.demo.service.api.java.assembler.PersonToAssembler;
import org.ucll.demo.service.api.java.to.ExaminationDetail;
import org.ucll.demo.service.api.java.to.PersonDetail;

public class ShowPatientDetailsTest {
	
	private PersonStubRepository repository = new PersonStubRepository();
	private PersonService personService = new PersonService(repository);
	private ExaminationToAssembler examinationToAssembler = new ExaminationToAssembler();
	private PersonToAssembler personToAssembler = new PersonToAssembler();
	private PersonServiceJavaApi service = new PersonServiceJavaApi(personService, examinationToAssembler, personToAssembler);
	
	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
	private static final String REGISTERED_SOCIAL_SECURITY_NUMBER = "93051822361";
	private static final String UNKNOWN_SOCIAL_SECURITY_NUMBER = "93051822363";
	private Date registeredBirthdate;
	private Gender registeredGender;
	private int registeredLength, registeredWeight;
	private Date registeredExaminationDate;
	
	@Before
	public void setUp() {
		clearTestData();
	}

	@Test
	public void when_I_ask_the_personal_details_Then_the_personal_details_and_physical_data_are_given() throws Exception{
		registerPerson(180, 75000, DATE_FORMATTER.parse("2000-04-10"));
		
		PersonDetail detailsRetrieved = service.getPerson(REGISTERED_SOCIAL_SECURITY_NUMBER);
		
		assertEquals(REGISTERED_SOCIAL_SECURITY_NUMBER, detailsRetrieved.getSocialSecurityNumber());
		assertEquals(Gender.MALE, detailsRetrieved.getGender());
		assertTrue(differNoMoreThanFewSeconds(registeredBirthdate, detailsRetrieved.getBirthdate()));
		
		ExaminationDetail examinationDetail = detailsRetrieved.getExaminationDetail();
		assertEquals(180, examinationDetail.getLength());
		assertEquals(75000, examinationDetail.getWeight());
		assertTrue(differNoMoreThanFewSeconds(registeredExaminationDate, examinationDetail.getExaminationDate()));

		assertEquals(23.15, detailsRetrieved.getBmi(), 0.0);
	}
	
	@Test
	public void when_I_ask_the_personal_details_Then_the_physical_data_of_the_most_recent_examination_are_given() throws Exception{
		registerPerson(180, 80000, DATE_FORMATTER.parse("2000-04-17"));
		addOlderExamination();
		
		PersonDetail detailsRetrieved = service.getPerson(REGISTERED_SOCIAL_SECURITY_NUMBER);
		
		ExaminationDetail examinationDetail = detailsRetrieved.getExaminationDetail();
		assertEquals(180, examinationDetail.getLength());
		assertEquals(80000, examinationDetail.getWeight());
		assertTrue(differNoMoreThanFewSeconds(registeredExaminationDate, examinationDetail.getExaminationDate()));

		assertEquals(24.69, detailsRetrieved.getBmi(), 0.0);
	}

	@Test (expected = IllegalArgumentException.class)
	public void given_a_patient_not_registered_When_I_ask_the_personal_details_Then_an_error_message_given() {
		service.getPerson(UNKNOWN_SOCIAL_SECURITY_NUMBER);
	}

	private void registerPerson(int length, int weight, Date examinationDate) throws Exception {
		//TODO replace with DBUnit, ...
		registeredGender = Gender.MALE;
		registeredBirthdate = DATE_FORMATTER.parse("1993-05-18");
		registeredLength = length;
		registeredWeight = weight;
		registeredExaminationDate = examinationDate;
		
		ExaminationDetail examinationDetail = new ExaminationDetail(registeredLength, registeredWeight, registeredExaminationDate);
		PersonDetail patient = new PersonDetail(REGISTERED_SOCIAL_SECURITY_NUMBER, registeredGender, registeredBirthdate , examinationDetail);
		
		service.addPerson(patient);
	}
	
	private void addOlderExamination() throws Exception {
		//TODO replace with DBUnit, ...
		ExaminationDetail olderExaminationDetail = new ExaminationDetail(registeredLength, registeredWeight, DATE_FORMATTER.parse("2000-04-10"));
		service.addExamination(olderExaminationDetail, REGISTERED_SOCIAL_SECURITY_NUMBER);
	}

	private void clearTestData() {
		//TODO replace with DBUnit, ...
		service.deletePerson(REGISTERED_SOCIAL_SECURITY_NUMBER);
	}
	
	private boolean differNoMoreThanFewSeconds(Date date, Date otherDate) {
		return date.compareTo(otherDate) <= 0 && date.compareTo(otherDate) >= -5;
	}

}
