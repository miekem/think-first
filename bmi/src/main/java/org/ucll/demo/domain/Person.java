package org.ucll.demo.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	private String socialSecurityNumber;
	private Gender gender;
	private Map<Date, Examination> examinations = new TreeMap<>();
	private Date birthDate;

	public Person() {
	}

	public Person(String socialSecurityNumber, Date birthDate,
			Gender gender, Examination examination) {
		setSocialSecurityNumber(socialSecurityNumber);
		setBirthDate(birthDate);
		setGender(gender);
		addExamination(examination);
	}

	public void addExamination(Examination examination) {
		if (examination == null) {
			throw new IllegalArgumentException("error.no.examination");
		}
		if (examinations.containsKey(examination.getDate())) {
			throw new IllegalArgumentException("error.examination.already.registered");
		}
		examinations.put(examination.getDate(), examination);
	}

	public void removeExamination(Examination examination) {
		if (examination == null) {
			throw new IllegalArgumentException("error.examination.not.found");
		}
		examinations.remove(examination.getDate());
	}

	private Examination getLastExamination() {
		Examination lastExamination;
		try{
			lastExamination = (Examination) getExaminations().lastEntry().getValue();
		} catch(NoSuchElementException e){
			lastExamination = null;
		}
		return lastExamination;
		
	}

	public double getBmi() {
		double weightInKilo = getWeight() / 1000;
		double lengthInMeter = (double) getLength() / 100;
		double squareLength = lengthInMeter * lengthInMeter;
		return Math.round(weightInKilo / squareLength * 100) / 100.0;
	}

	public boolean equals(Object object) {
		boolean equal = false;
		if (object instanceof Person) {
			Person person = (Person) object;
			if (person != null
					&& this.getSocialSecurityNumber().equals(
							person.getSocialSecurityNumber())) {
				equal = true;
			}
		}
		return equal;
	}

	public String toString() {
		return "Person with number " + this.getSocialSecurityNumber() + ".";
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		if (socialSecurityNumber == null || socialSecurityNumber.isEmpty()) {
			throw new IllegalArgumentException("error.no.socialsecuritynumber");
		}
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		if (gender == null) {
			throw new IllegalArgumentException("error.no.gender");
		}
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		if (birthDate == null) {
			throw new IllegalArgumentException("error.no.date");
		}
		if(birthDate.after(new Date())) {
			throw new IllegalArgumentException("error.date.in.future");
		}
		this.birthDate = birthDate;
	}

	public int getNumberOfExaminations() {
		return examinations.size();
	}

	public int getLength() {
		int length = 0;
		if(getLastExamination() != null){
			length = getLastExamination().getLength();
		}
		return length;
	}

	public int getWeight() {
		int weight = 0;
		if(getLastExamination() != null){
			weight = getLastExamination().getWeight();
		}
		return weight;
	}

	public Date getLastExaminationDate() {
		Date date = null;
		if(getLastExamination() != null){
			date = getLastExamination().getDate();
		}
		return date;
	}

	private TreeMap<Date, Examination> getExaminations() {
		TreeMap<Date, Examination> sortedExaminations = new TreeMap<>(examinations);
		return sortedExaminations;
	}

}
