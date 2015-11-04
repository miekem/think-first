package org.ucll.demo.domain;

import static org.ucll.demo.domain.ExaminationTest.DATE_CURRENT;
import static org.ucll.demo.domain.ExaminationTest.VALID_LENGTH_IN_CM;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ExaminationInvalidWeightTest {

	private Examination examination;
	private int invalidWeight;
	
	public ExaminationInvalidWeightTest(int weight, String motivation) {
		super();
		this.invalidWeight = weight;
	}
	
	@Before
	public void setUp(){
		examination = new Examination();
	}
	
	@Parameters(name = "lenght {0} is invalid because it is {1}")
	public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
        		{299, "just too small"},
        		{-75000, "far too small"},
        		{7000001, "just too big"}
           });
    }
	
	@Test(expected = IllegalArgumentException.class)
	public void Examination_Should_throw_an_IllegalArgumentException_If_a_valid_length_but_an_invalid_weight_is_provided(){
		new Examination(VALID_LENGTH_IN_CM, invalidWeight);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void Examination_Should_throw_an_IllegalArgumentException_If_a_valid_length_and_date_but_an_invalid_weight_is_provided(){
		new Examination(VALID_LENGTH_IN_CM, invalidWeight, DATE_CURRENT);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setWeight_Should_throw_an_IllegalArgumentException_If_an_invalid_weight_is_provided(){
		examination.setWeight(invalidWeight);
	}
}