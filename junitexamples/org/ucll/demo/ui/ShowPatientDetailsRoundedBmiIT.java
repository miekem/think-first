package org.ucll.demo.ui;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.ucll.demo.domain.Gender;
import org.ucll.demo.ui.pages.PersonDetailPage;
import org.ucll.demo.ui.pages.PersonOverviewPage;

@RunWith(Parameterized.class)
public class ShowPatientDetailsRoundedBmiIT {
	private static final String SOCIAL_SECURITY_NUMBER = "93051822361";
	private int length, weight;
	private double expectedBmi;
	
	private PersonOverviewPage personOverviewPage;
	private PersonDetailPage personDetailPage;
	
	private WebDriver driver;
	private final static String HOST_AND_PORT_URL = "http://localhost:8080";
	private final static String CONTEXT_URL = HOST_AND_PORT_URL + "/bmi";

	public ShowPatientDetailsRoundedBmiIT(int length, int weight, double bmi) {
		super();
		this.length = length;
		this.weight = weight;
		this.expectedBmi = bmi;
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
	public void when_I_ask_the_personal_details_Then_the_bmi_is_given_rounded_to_2_digits() throws Exception{
		registerPerson();
		
		personDetailPage = personOverviewPage.showPerson(SOCIAL_SECURITY_NUMBER);
		
		assertEquals(expectedBmi, personDetailPage.getBmi(), 0.0);
	}

	private void registerPerson() throws Exception {
		//TODO replace with DBUnit, ...
		personOverviewPage = new PersonOverviewPage(driver);
		personDetailPage = personOverviewPage.addPerson();
		personOverviewPage = personDetailPage.addPerson(SOCIAL_SECURITY_NUMBER, new SimpleDateFormat("yyyy-MM-dd").parse("1993-05-18"), Gender.MALE, 
				length, weight, new Date());
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