package org.ucll.demo.service.api.java;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.ucll.demo.domain.Person;
import org.ucll.demo.service.PersonService;
import org.ucll.demo.service.api.java.assembler.ExaminationToAssembler;
import org.ucll.demo.service.api.java.assembler.PersonToAssembler;
import org.ucll.demo.service.api.java.to.PersonDetail;

public class PersonServiceJavaApiTest {
	
	private static final String SOCIAL_SECURITY_NUMBER = "93051822361";
	
	@Mock
	private PersonService mockPersonService;
	@Mock
	private ExaminationToAssembler mockExaminationToAssembler;
	@Mock
	private PersonToAssembler mockPersonToAssembler;
	@Mock
	private Person mockPerson;
	@Mock
	private PersonDetail mockPersonDetail;
	
	private PersonServiceJavaApi service;
	
	@Before
	public void setUp(){
		initMocks(this);
		service = new PersonServiceJavaApi(mockPersonService, mockExaminationToAssembler, mockPersonToAssembler);
	}

	@Test
	public void getPerson_Should_retrieve_a_person_from_the_repository_If_the_personId_is_provided(){
		given(mockPersonService.getPerson(SOCIAL_SECURITY_NUMBER)).willReturn(mockPerson);
		given(mockPersonToAssembler.createPersonDetailTo(mockPerson)).willReturn(mockPersonDetail);
		
		PersonDetail personRetrieved = service.getPerson(SOCIAL_SECURITY_NUMBER);
		
		then(mockPersonService).should().getPerson(SOCIAL_SECURITY_NUMBER);
		then(mockPersonToAssembler).should().createPersonDetailTo(mockPerson);
		assertNotNull(personRetrieved);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getPerson_Should_throw_an_IllegalArgumentException_If_an_unknown_personId_is_provided(){
		given(mockPersonService.getPerson(SOCIAL_SECURITY_NUMBER)).willThrow(new IllegalArgumentException());
		
		service.getPerson(SOCIAL_SECURITY_NUMBER);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getPerson_Should_throw_an_IllegalArgumentException_If_no_personId_is_provided(){
		given(mockPersonService.getPerson(null)).willThrow(new IllegalArgumentException());
		
		service.getPerson(null);
	}

}
