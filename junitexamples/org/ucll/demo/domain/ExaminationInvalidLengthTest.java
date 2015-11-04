package org.ucll.demo.domain;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.ucll.demo.domain.ExaminationTest.*;

@RunWith(Parameterized.class)
public class ExaminationInvalidLengthTest {

	private Examination examination;
	private int invalidLength;
	
	public ExaminationInvalidLengthTest(int length, String motivation) {
		super();
		this.invalidLength = length;
	}
	
	@Before
	public void setUp(){
		examination = new Examination();
	}
	
	@Parameters(name = "lenght {0} is invalid because it is {1}")
	public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
        		{49, "just too small"},
        		{-180, "far too small"},
        		{301, "just too big"}
           });
    }
	
	@Test(expected = IllegalArgumentException.class)
	public void Examination_Should_throw_an_IllegalArgumentException_If_a_valid_weight_but_an_invalid_length_is_provided(){
		new Examination(invalidLength, VALID_WEIGHT_IN_GRAM);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void Examination_Should_throw_an_IllegalArgumentException_If_a_valid_weight_and_date_but_an_invalid_length_is_provided(){
		new Examination(invalidLength, VALID_WEIGHT_IN_GRAM, DATE_CURRENT);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setLength_Should_throw_an_IllegalArgumentException_If_an_invalid_length_is_provided(){
		examination.setLength(invalidLength);
	}
}