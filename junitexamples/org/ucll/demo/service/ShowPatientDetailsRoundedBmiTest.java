package org.ucll.demo.service;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.ucll.demo.domain.Gender;
import org.ucll.demo.repository.PersonRepository;
import org.ucll.demo.repository.PersonStubRepository;
import org.ucll.demo.service.api.java.PersonServiceJavaApi;
import org.ucll.demo.service.api.java.assembler.ExaminationToAssembler;
import org.ucll.demo.service.api.java.assembler.PersonToAssembler;
import org.ucll.demo.service.api.java.to.ExaminationDetail;
import org.ucll.demo.service.api.java.to.PersonDetail;

@RunWith(Parameterized.class)
public class ShowPatientDetailsRoundedBmiTest {
	
	private PersonRepository repository = new PersonStubRepository();
	private PersonService personService = new PersonService(repository);
	private ExaminationToAssembler examinationToAssembler = new ExaminationToAssembler();
	private PersonToAssembler personToAssembler = new PersonToAssembler();
	private PersonServiceJavaApi service = new PersonServiceJavaApi(personService, examinationToAssembler, personToAssembler);
	
	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
	private static final String SOCIAL_SECURITY_NUMBER = "93051822361";

	private int length;
	private int weight;
	private double bmi;
	
	public ShowPatientDetailsRoundedBmiTest(int length, int weight, double bmi) {
		super();
		this.length = length;
		this.weight = weight;
		this.bmi = bmi;
	}

	@Before
	public void setUp() {
		clearTestData();
	}
	
	@Parameters(name = "lenght {0} and weight {1} will result in a bmi of {2}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][] { 
				{ 160, 65000, 25.39 },
				{ 160, 65001, 25.39 }, 
				{ 160, 65009, 25.39 },
				{ 180, 75000, 23.15 }, 
				{ 180, 75009, 23.15 } 
			});
	}
	
	@Test
	public void when_I_ask_the_personal_details_Then_the_bmi_is_given_rounded_to_2_digits() throws Exception{
		PersonDetail patient = new PersonDetail(SOCIAL_SECURITY_NUMBER, Gender.MALE, DATE_FORMATTER.parse("1993-05-18"), 
				new ExaminationDetail(length, weight, DATE_FORMATTER.parse("2000-04-10")));
		
		service.addPerson(patient);
		
		assertEquals(bmi, service.getPerson(SOCIAL_SECURITY_NUMBER).getBmi(), 0.0);
	}

	private void clearTestData() {
		//TODO replace with DBUnit, ...
		service.deletePerson(SOCIAL_SECURITY_NUMBER);
	}
	
}
