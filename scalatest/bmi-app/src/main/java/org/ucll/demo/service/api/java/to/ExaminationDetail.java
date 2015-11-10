package org.ucll.demo.service.api.java.to;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.ucll.demo.domain.Examination;

public class ExaminationDetail {
	@Min(value=Examination.MINIMUM_LENGTH, message="{error.length.below.minimum}")
	@Max(value=Examination.MAXIMUM_LENGTH, message="{error.length.above.maximum}")
	private int length;
	@Min(value=Examination.MINIMUM_WEIGHT, message="{error.weight.below.minimum}")
	@Max(value=Examination.MAXIMUM_WEIGHT, message="{error.length.above.maximum}")
	private int weight;
	@NotNull
	@Past(message="{error.date.in.future}")
	private Date examinationDate = new Date();
	
	public ExaminationDetail(){
	}

	public ExaminationDetail(int length, int weight){
		this(length, weight, null);
	}
	
	public ExaminationDetail(int length, int weight, Date date){
		setLength(length);
		setWeight(weight);
		setExaminationDate(date);
	}
	
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Date getExaminationDate() {
		return examinationDate;
	}
	public void setExaminationDate(Date date) {
		this.examinationDate = date;
	}
}
