package org.ucll.demo.service.api.java;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.ucll.demo.domain.Gender;
import org.ucll.demo.service.api.java.PersonServiceJavaApi;
import org.ucll.demo.service.api.java.to.ExaminationDetail;
import org.ucll.demo.service.api.java.to.PersonDetail;

@RunWith(ConcordionRunner.class)
public class ShowPatientDetailsTest {

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

	public void initializePersonDetails (String socialSecurityNumber, String gender, String birthDate) throws Exception {
		this.socialSecurityNumber = socialSecurityNumber;
		this.gender = Gender.valueOf(gender.toUpperCase());
		this.birthDate = DATE_FORMATTER.parse(birthDate);
		patient = new PersonDetail(this.socialSecurityNumber, this.gender, this.birthDate);
	}
	
	public void initializeExaminationDetails (String examinationDate, int length, int weight) throws Exception {
		this.examinationDate = DATE_FORMATTER.parse(examinationDate);
		this.length = length;
		this.weight = weight;
		patient.setExaminationDetail(new ExaminationDetail(length, weight, this.examinationDate));
	}
	
	public void registerPatient () {
		service.addPerson(patient);
	}
		
	public void askPatientDetails () {
		detailsRetrieved = service.getPerson(this.socialSecurityNumber);
	}
	
	public boolean verifyPatientDetails () {
		return (this.socialSecurityNumber.equals(detailsRetrieved.getSocialSecurityNumber()) &&
				this.gender.equals(detailsRetrieved.getGender()) &&
				differNoMoreThanFewSeconds(this.birthDate, detailsRetrieved.getBirthdate()));
	}
	
	public boolean verifyExaminationDetails () {
		ExaminationDetail examinationDetailsRetrieved = service.getPerson(this.socialSecurityNumber).getExaminationDetail();
		return (this.length == examinationDetailsRetrieved.getLength() &&
				this.weight == examinationDetailsRetrieved.getWeight() &&
				differNoMoreThanFewSeconds(this.examinationDate, examinationDetailsRetrieved.getExaminationDate()));
	}
	
	public double verifyBmi() {
		return detailsRetrieved.getBmi();
	}
	
	private boolean differNoMoreThanFewSeconds(Date date, Date otherDate) {
		return date.compareTo(otherDate) <= 0 && date.compareTo(otherDate) >= -5;
	}
	
}


