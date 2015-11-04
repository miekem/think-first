package org.ucll.demo.domain;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ExaminationGetBmiTest {

	private Examination examination;
	private int length;
	private int weight;
	private double bmi;

	public ExaminationGetBmiTest(int length, int weight, double bmi) {
		super();
		this.length = length;
		this.weight = weight;
		this.bmi = bmi;
	}

	@Before
	public void setUp() {
		examination = new Examination(length, weight);
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

	@Test
	public void getBmi_Should_calculate_the_bmi_rounded_to_2_decimals() {
		assertEquals(bmi,  examination.getBmi(),0);
	}
}