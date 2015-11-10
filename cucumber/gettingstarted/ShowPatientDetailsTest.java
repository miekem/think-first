package org.ucll.demo.service;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
features={"src/test/resources/org/ucll/demo/service/ShowPatientDetails.feature"},
plugin={"html:target/cucumber", "json:target/cucumber.json"}
)
public class ShowPatientDetailsTest {

}
