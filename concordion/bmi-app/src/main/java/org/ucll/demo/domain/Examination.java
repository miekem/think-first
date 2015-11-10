package org.ucll.demo.domain;

import java.io.Serializable;
import java.util.Date;

import org.ucll.demo.ParameterizedIllegalArgumentException;

public class Examination implements Serializable, Comparable<Examination>{
	private static final long serialVersionUID = 1L;

	public static final int MAXIMUM_LENGTH = 300;
	public static final int MAXIMUM_WEIGHT = 700000;
	public static final int MINIMUM_WEIGHT = 20000;
	public static final int MINIMUM_LENGTH = 50;
	
	private int length;
	private int weight;
	private Date date;
	
	public Examination (){
	}
	
	public Examination (int length, int weight) {
		this(length, weight, new Date());
	}
	
	public Examination (int length, int weight, Date date) {
		this.setLength(length);
		this.setWeight(weight);
		this.setDate(date);
	}

	public int getLength() {
		return length;
	}

	public void setLength (int length) {
		if (length < MINIMUM_LENGTH || length > MAXIMUM_LENGTH) {
			Object[] parameters = {MINIMUM_LENGTH, MAXIMUM_LENGTH};
			throw new ParameterizedIllegalArgumentException("error.no.valid.length", parameters);
		}
		this.length = length;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight (int weight) {
		if (weight < MINIMUM_WEIGHT || weight > MAXIMUM_WEIGHT) {
			Object[] parameters = {MINIMUM_WEIGHT, MAXIMUM_WEIGHT};
			throw new ParameterizedIllegalArgumentException("error.no.valid.weight", parameters);
		}
		this.weight = weight;
	}

	public Date getDate() {
		return date;
	}

	public void setDate (Date date) {
		if (date == null) {
			throw new IllegalArgumentException("error.no.date");
		}
		if(date.after(new Date())) {
			throw new IllegalArgumentException("error.date.in.future");
		}
		this.date = date;
	}

	public double getBmi() {
		double weightInKilo = getWeight() / 1000;
		double lengthInMeter = (double) getLength() / 100;
		double squareLength = lengthInMeter * lengthInMeter;
		return Math.round(weightInKilo / squareLength * 100) / 100.0;
	}
	
	public boolean equals (Object o) {
		boolean equal = false;
		if (o instanceof Examination) {
			Examination examination = (Examination)o;
			if (this.getDate().equals(examination.getDate())) {
				equal = true;
			}
		}
		return equal;
	}
	
	public String toString () {
		StringBuilder toString = new StringBuilder(length);
		toString.append("cm, ");
		toString.append(weight);
		toString.append("gr");
		toString.append(date);
		return toString.toString();
	}

	public int compareTo(Examination other) {
		int difference = 1;
		if(other != null){
			difference = getDate().compareTo(other.getDate());
		}
		return difference;
	}

}
