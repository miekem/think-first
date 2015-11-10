package org.ucll.demo.service.api.java.to;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.ucll.demo.domain.Gender;

public class PersonDetail extends PersonOverview {
	@NotNull(message="{error.no.gender}")
	private Gender gender = Gender.MALE;
	@NotNull
	@Past(message="{error.date.in.future}")
	private Date birthdate = new Date();
	private double bmi;
	private ExaminationDetail examinationDetail = new ExaminationDetail();
	
	public PersonDetail(){
	}
	
	public PersonDetail(String socialSecurityNumber, Gender gender, Date birthDate) {
		this(socialSecurityNumber, gender, birthDate, 0, null);
	}

	public PersonDetail(String socialSecurityNumber, Gender gender, Date birthDate, ExaminationDetail examinationDetail) {
		this(socialSecurityNumber, gender, birthDate, 0, examinationDetail);
	}
	public PersonDetail(String socialSecurityNumber, Gender gender, Date birthDate, double bmi,
			ExaminationDetail examinationDetail) {
		super(socialSecurityNumber);
		setGender(gender);
		setBirthdate(birthDate);
		setBmi(bmi);
		setExaminationDetail(examinationDetail);
	}

	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthDate) {
		this.birthdate = birthDate;
	}
	public double getBmi() {
		return bmi;
	}
	public void setBmi(double bmi) {
		this.bmi = bmi;
	}
	public ExaminationDetail getExaminationDetail() {
		return examinationDetail;
	}
	public void setExaminationDetail(ExaminationDetail examinationDetailTo) {
		this.examinationDetail = examinationDetailTo;
	}
}
