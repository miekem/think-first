package org.ucll.demo.service.api.java

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import java.text.SimpleDateFormat
import org.ucll.demo.domain.Gender
import org.ucll.demo.service.api.java.to.PersonDetail
import org.ucll.demo.service.api.java.to.ExaminationDetail

class ShowPatientDetails extends FeatureSpec with GivenWhenThen {
  val service = new PersonServiceJavaApi
  val dateFormatter= new SimpleDateFormat("yyyy-MM-dd")
    
  feature("Show patient details") {
    info("In order to check the physical condition of a patient")
    info("As a caretaker")
    info("I want to consult his/her personal details")

    scenario("the personal details of a registered patient are given")({
      Given("a patient with the social security number 93051822361, gender male and birthdate 1993-05-18")
      val socialSecurityNumber = "93051822361"
      val gender = Gender.MALE
      val birthDate = dateFormatter.parse("1993-05-18")
      val patient = new PersonDetail(socialSecurityNumber, gender, birthDate)

      And("on 2000–04-10 the patient had a length of 180 cm and a weight of 75000 gr")
      val examinationDate = dateFormatter.parse("2000-04-10")
      val examination = new ExaminationDetail(180, 75000, examinationDate)
      patient.setExaminationDetail(examination)
      
      And("the patient is registered")
      service.addPerson(patient)
      
      When("I ask for the details of the patient using his social security number")
      val detailsRetrieved = service.getPerson(socialSecurityNumber)
    
      Then("the personal details social security number, gender and birthdate are given")
      assert(socialSecurityNumber == detailsRetrieved.getSocialSecurityNumber)
      assert(gender == detailsRetrieved.getGender);
      assert(birthDate.compareTo(detailsRetrieved.getBirthdate) == 0)
    
      And("the examination details length, weight and last examination date are given")
      val examinationData = detailsRetrieved.getExaminationDetail
      assert(180 == examinationData.getLength)
      assert(75000 == examinationData.getWeight);
      assert(examinationDate.compareTo(examinationData.getExaminationDate) == 0)
      
      And("the calculated bmi 23.15 is given")
      assert(23.15 == detailsRetrieved.getBmi);
    })
  }
}