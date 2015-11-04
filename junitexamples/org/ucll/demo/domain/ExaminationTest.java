package org.ucll.demo.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class ExaminationTest {
	public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
	public static final int VALID_LENGTH_IN_CM = 180;
	public static final int OTHER_VALID_LENGTH_IN_CM = 160;
	public static final int VALID_WEIGHT_IN_GRAM = 75000;
	public static final int OTHER_VALID_WEIGHT_IN_GRAM = 65000;
	public static final Date DATE_CURRENT = new Date();
	
	private Date dateInPast, dateInFuture;
	
	public ExaminationTest() throws Exception {
		dateInPast = DATE_FORMATTER.parse("1996-07-07");
		dateInFuture = DATE_FORMATTER.parse("3996-07-07");
	}
	
	@Test
	public void Examination_Should_create_an_examination_If_a_valid_length_and_weight_is_provided(){
		Examination examination = new Examination(VALID_LENGTH_IN_CM, VALID_WEIGHT_IN_GRAM);
		
		assertEquals(VALID_LENGTH_IN_CM, examination.getLength());
		assertEquals(VALID_WEIGHT_IN_GRAM, examination.getWeight());
	}

	@Test
	public void Examination_Should_create_an_examination_If_a_valid_length_and_weight_and_a_date_is_provided(){
		Examination examination = new Examination(VALID_LENGTH_IN_CM, VALID_WEIGHT_IN_GRAM, DATE_CURRENT);
		
		assertEquals(VALID_LENGTH_IN_CM, examination.getLength());
		assertEquals(VALID_WEIGHT_IN_GRAM, examination.getWeight());
		assertEquals(DATE_CURRENT, examination.getDate());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void Examination_Should_throw_an_IllegalArgumentException_If_no_date_is_provided(){
		new Examination(VALID_LENGTH_IN_CM, VALID_WEIGHT_IN_GRAM, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void Examination_Should_throw_an_IllegalArgumentException_If_a_date_in_the_future_is_provided(){
		new Examination(VALID_LENGTH_IN_CM, VALID_WEIGHT_IN_GRAM, dateInFuture);
	}
	
	@Test
	public void setLength_Should_replace_the_length_If_a_valid_length_is_provided(){
		Examination examination = createExamination();
		examination.setLength(OTHER_VALID_LENGTH_IN_CM);

		assertEquals(OTHER_VALID_LENGTH_IN_CM, examination.getLength());
	}

	@Test
	public void setWeight_Should_replace_the_weight_If_a_valid_weight_is_provided(){
		Examination examination = createExamination();
		examination.setWeight(OTHER_VALID_WEIGHT_IN_GRAM);

		assertEquals(OTHER_VALID_WEIGHT_IN_GRAM, examination.getWeight());
	}
	
	@Test
	public void setDate_Should_replace_the_date_If_a_valid_date_is_provided() throws Exception{
		Examination examination = createExamination();
		Date otherDateInPast = DATE_FORMATTER.parse("1996-08-08");
		
		examination.setDate(otherDateInPast);
		
		assertEquals(otherDateInPast, examination.getDate());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setDate_Should_throw_an_IllegalArgumentException_If_no_date_is_provided(){
		Examination examination = createExamination();
		
		examination.setDate(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setDate_Should_throw_an_IllegalArgumentException_If_a_date_in_the_future_is_provided(){
		Examination examination = createExamination();
		
		examination.setDate(dateInFuture);
	}
	
	@Test
	public void compare_to_Should_return_a_positive_value_If_a_examination_with_a_older_date_is_provided() throws Exception{
		Examination examination = createExamination(DATE_CURRENT);
		Examination olderExamination = createExamination(dateInPast);
		
		assertTrue(examination.compareTo(olderExamination) > 0);
	}
	
	@Test
	public void compare_to_Should_return_a_negative_value_If_a_examination_with_a_more_recent_date_is_provided() throws Exception{
		Examination examination = createExamination(DATE_CURRENT);
		Examination olderExamination = createExamination(dateInPast);
		
		assertTrue(olderExamination.compareTo(examination) < 0);
	}
	
	@Test
	public void compare_to_Should_return_0_If_a_examination_with_the_same_date_is_provided(){
		Examination examination = createExamination(dateInPast);
		Examination examinationWithSameDate = new Examination(OTHER_VALID_LENGTH_IN_CM, OTHER_VALID_WEIGHT_IN_GRAM, dateInPast);
		
		assertEquals(0, examinationWithSameDate.compareTo(examination));
		assertEquals(0, examination.compareTo(examinationWithSameDate));
	}
	
	@Test
	public void equals_Should_return_true_If_a_examination_with_the_same_date_is_provided_even_if_the_length_or_weight_differ(){
		Examination examination = createExamination(dateInPast);
		Examination otherExamination = new Examination(OTHER_VALID_LENGTH_IN_CM, OTHER_VALID_WEIGHT_IN_GRAM, dateInPast);
		
		assertTrue(examination.equals(otherExamination));
		assertTrue(otherExamination.equals(examination));
	}
	
	@Test
	public void equals_Should_return_false_If_a_examination_with_another_date_is_provided_even_if_the_length_or_weight_are_the_same() throws Exception{
		Examination examination = createExamination(DATE_CURRENT);
		Examination otherExamination = createExamination(dateInPast);
		
		assertFalse(examination.equals(otherExamination));
		assertFalse(otherExamination.equals(examination));
	}
	
	private Examination createExamination() {
		return createExamination(DATE_CURRENT);
	}
	
	public static Examination createExamination(Date date) {
		return new Examination(VALID_LENGTH_IN_CM, VALID_WEIGHT_IN_GRAM, date);
	}
}
