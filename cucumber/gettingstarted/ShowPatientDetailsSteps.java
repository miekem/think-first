package org.ucll.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.ucll.demo.domain.Gender;
import org.ucll.demo.service.api.java.PersonServiceJavaApi;
import org.ucll.demo.service.api.java.to.ExaminationDetail;
import org.ucll.demo.service.api.java.to.PersonDetail;

import cucumber.api.Format;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ShowPatientDetailsSteps {
	
	private PersonServiceJavaApi service = new PersonServiceJavaApi();
	
	private String socialSecurityNumber;
	private Gender gender = Gender.MALE;
	private Date birthDate;
	private int length;
	private int weight;
	private Date examinationDate;
	
	private PersonDetail detailsRetrieved;
	
	private PersonDetail patient;

	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

	@Given("^a patient with the social security number (\\d+), gender male and birthdate (\\d+)-(\\d+)-(\\d+)$")
	public void a_patient_with_the_social_security_number_gender_male_and_birthdate(int arg1, int arg2, int arg3, int arg4) throws Throwable {
		this.socialSecurityNumber = Integer.toString(arg1);
		this.gender = Gender.MALE;
		this.birthDate = DATE_FORMATTER.parse(arg2+"-"+arg3+"-"+arg4);
		patient = new PersonDetail(this.socialSecurityNumber, this.gender, this.birthDate);
	}

	@Given("^on (\\d+)-(\\d+)-(\\d+) the patient had a length of (\\d+) cm and a weight of (\\d+) gr$")
	public void on_the_patient_had_a_length_of_cm_and_a_weight_of_gr(int arg1, int arg2, int arg3, int arg4, int arg5) throws Throwable {
		this.examinationDate = DATE_FORMATTER.parse(arg1+"-"+arg2+"-"+arg3);
		this.length = arg4;
		this.weight = arg5;
		patient.setExaminationDetail(new ExaminationDetail(length, weight, this.examinationDate));
	}

	@Given("^the patient is registered$")
	public void the_patient_is_registered() throws Throwable {
		service.addPerson(patient);
	}

	@When("^I ask for the details of the patient using his social security number$")
	public void i_ask_for_the_details_of_the_patient_using_his_social_security_number() throws Throwable {
		detailsRetrieved = service.getPerson(this.socialSecurityNumber);
	}

	@Then("^the personal details social security number, gender and birthdate are given$")
	public void the_personal_details_social_security_number_gender_and_birthdate_are_given() throws Throwable {
		assertEquals(this.socialSecurityNumber, detailsRetrieved.getSocialSecurityNumber());
		assertEquals(this.gender, detailsRetrieved.getGender());
		assertTrue(differNoMoreThanFewSeconds(this.birthDate, detailsRetrieved.getBirthdate()));
	}

	@Then("^the examination details length, weight and last examination date are given$")
	public void the_examination_details_length_weight_and_last_examination_date_are_given() throws Throwable {
		ExaminationDetail examinationDetails = service.getPerson(this.socialSecurityNumber).getExaminationDetail();
		assertEquals(this.length, examinationDetails.getLength());
		assertEquals(this.weight, examinationDetails.getWeight());
		assertTrue(differNoMoreThanFewSeconds(this.examinationDate, examinationDetails.getExaminationDate()));
	}

	@Then("^the calculated bmi (\\d+)\\.(\\d+) is given$")
	public void the_calculated_bmi_is_given(int arg1, int arg2) throws Throwable {
		assertEquals(Double.parseDouble(""+arg1+"."+arg2+""), detailsRetrieved.getBmi(), 0.0);
	}

	private boolean differNoMoreThanFewSeconds(Date date, Date otherDate) {
		return date.compareTo(otherDate) <= 0 && date.compareTo(otherDate) >= -5;
	}

}
