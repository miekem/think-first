package org.ucll.demo.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses
({
	ExaminationTest.class, 
	ExaminationInvalidLengthTest.class, 
	ExaminationInvalidWeightTest.class, 
	ExaminationGetBmiTest.class
})

public class AllTests {}