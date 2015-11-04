package org.ucll.demo.ui;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.ucll.demo.service.api.java.PersonServiceJavaApiTest;

@RunWith(Suite.class)
@Suite.SuiteClasses
({
	PersonServiceJavaApiTest.class, 
	ShowPatientDetailsIT.class, 
	ShowPatientDetailsRoundedBmiIT.class, 
})
public class AllTests {
	
}
