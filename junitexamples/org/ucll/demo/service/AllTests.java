package org.ucll.demo.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.ucll.demo.service.api.java.PersonServiceJavaApiTest;

@RunWith(Suite.class)
@Suite.SuiteClasses
({
	PersonServiceJavaApiTest.class, 
	ShowPatientDetailsTest.class, 
	ShowPatientDetailsRoundedBmiTest.class, 
})
public class AllTests {
	
}
