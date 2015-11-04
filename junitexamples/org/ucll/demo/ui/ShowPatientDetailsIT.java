package org.ucll.demo.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.ucll.demo.domain.Gender;
import org.ucll.demo.ui.pages.ExaminationDetailPage;
import org.ucll.demo.ui.pages.PersonDetailPage;
import org.ucll.demo.ui.pages.PersonOverviewPage;

public class ShowPatientDetailsIT {
	private String registeredSocialSecurityNumber;
	private Date registeredBirthdate;
	private Gender registeredGender;
	private int registeredLength, registeredWeight;
	private Date registeredExaminationDate;
	
	private PersonOverviewPage personOverviewPage;
	private PersonDetailPage personDetailPage;
	private ExaminationDetailPage examinationDetailPage;
	
	private WebDriver driver;
	private final static String HOST_AND_PORT_URL = "http://localhost:8080";
	private final static String CONTEXT_URL = HOST_AND_PORT_URL + "/bmi";
	
	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
	
	@Before
	public void setUp(){
		driver = new FirefoxDriver();
		driver.get(CONTEXT_URL);
	}
	
	@After
	public void clean(){
		driver.get(CONTEXT_URL);
		clearTestData();
		
		driver.quit();
	}
	
	@Test
	public void when_I_ask_the_personal_details_Then_the_personal_details_and_physical_data_are_given() throws Exception {
		registerPerson(180, 75000, DATE_FORMATTER.parse("2000-04-10"));
		
		personDetailPage = personOverviewPage.showPerson(registeredSocialSecurityNumber);
		
		assertEquals(registeredSocialSecurityNumber, personDetailPage.getSocialSecurityNumber());
		assertEquals(registeredGender, personDetailPage.getGender());
		assertTrue(differNoMoreThanFewSeconds(registeredBirthdate, personDetailPage.getBirthdate()));
		assertEquals(registeredLength, personDetailPage.getLength());
		assertEquals(registeredWeight, personDetailPage.getWeight());
		assertTrue(differNoMoreThanFewSeconds(registeredExaminationDate, personDetailPage.getExaminationDate()));
		assertEquals(23.15, personDetailPage.getBmi(), 0.0);
	}
	
	@Test
	public void when_I_ask_the_personal_details_Then_the_physical_data_of_the_most_recent_examination_are_given() throws Exception{
		registerPerson(180, 80000, DATE_FORMATTER.parse("2000-04-17"));
		addOlderExamination();

		personDetailPage = personOverviewPage.showPerson(registeredSocialSecurityNumber);
		
		assertEquals(registeredLength, personDetailPage.getLength());
		assertEquals(registeredWeight, personDetailPage.getWeight());
		assertTrue(differNoMoreThanFewSeconds(registeredExaminationDate, personDetailPage.getExaminationDate()));
		assertEquals(24.69, personDetailPage.getBmi(), 0.0);
	}

	private void addOlderExamination() throws Exception {
		//TODO replace with DBUnit, ...
		personDetailPage = personOverviewPage.showPerson(registeredSocialSecurityNumber);
		examinationDetailPage = personDetailPage.addExamination();
		personDetailPage = examinationDetailPage.addExamination(registeredLength, registeredWeight - 5000, DATE_FORMATTER.parse("2000-04-10"));
		personOverviewPage = personDetailPage.cancel();
	}

	private boolean differNoMoreThanFewSeconds(Date date, Date otherDate) {
		return date.compareTo(otherDate) <= 0 && date.compareTo(otherDate) >= -5;
	}

	private void registerPerson(int length, int weight, Date examinationDate) throws Exception {
		//TODO replace with DBUnit, ...
		registeredSocialSecurityNumber = "93051822361";
		registeredGender = Gender.MALE;
		registeredBirthdate = DATE_FORMATTER.parse("1993-05-18");
		registeredLength = length;
		registeredWeight = weight;
		registeredExaminationDate = examinationDate;

		personOverviewPage = new PersonOverviewPage(driver);
		personDetailPage = personOverviewPage.addPerson();
		personOverviewPage = personDetailPage.addPerson(registeredSocialSecurityNumber, registeredBirthdate, registeredGender, registeredLength, registeredWeight, registeredExaminationDate);
	}

	private void clearTestData() {
		//TODO replace with DBUnit, ...
		personOverviewPage = new PersonOverviewPage(driver);
		try {
			personOverviewPage.deletePerson();
		} catch (NoSuchElementException mightBeExpectedIfPersonWasNotRegistered) {
		}
	}

}
