package org.ucll.demo.ui.pages;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.ucll.demo.domain.Gender;

public class PersonDetailPage extends SeleniumPage {
	private ExaminationFieldsPage examinationfieldsPage;

	public PersonDetailPage(WebDriver driver) {
		super(driver);
		examinationfieldsPage = new ExaminationFieldsPage(getDriver(), "personForm");
	}

	public PersonOverviewPage addPerson(String socialSecurityNumber, Date birthdate,
			Gender gender, int length, int weight, Date examinationDate) {
		WebElement socialSecurityNumberField = getDriver().findElement(By.id("personForm:number"));
		socialSecurityNumberField.clear();
		socialSecurityNumberField.sendKeys(socialSecurityNumber);

        WebElement birthdateField = getDriver().findElement(By.id("personForm:birthDate"));
        birthdateField.clear();
        birthdateField.sendKeys(new SimpleDateFormat("yyyy-MM-dd").format(birthdate));

		Select genderField = new Select(getDriver().findElement(By.id("personForm:gender")));
		genderField.selectByVisibleText("MALE");

		examinationfieldsPage.addExaminationData(length, weight, examinationDate);
        
        WebElement saveButton = getDriver().findElement(By.id("personForm:actionSave"));
        saveButton.click();
        
        return new PersonOverviewPage(getDriver());
		
	}

	public String getSocialSecurityNumber() {
		WebElement socialSecurityNumberField = getDriver().findElement(By.id("personForm:number"));
		return socialSecurityNumberField.getAttribute("value");
	}

	public Gender getGender() {
		Select genderField = new Select(getDriver().findElement(By.id("personForm:gender")));
		String genderAsString = genderField.getFirstSelectedOption().getText();

		return Gender.valueOf(genderAsString.toUpperCase());
	}

	public Date getBirthdate() {
		WebElement birhtdateField = getDriver().findElement(By.id("personForm:birthDate"));
		String dateAsString = birhtdateField.getAttribute("value");
		return convertToDate(dateAsString);
	}

	public int getLength() {
		return examinationfieldsPage.getLength();
	}

	public int getWeight() {
		return examinationfieldsPage.getWeight();
	}

	public Date getExaminationDate() {
		return examinationfieldsPage.getExaminationDate();
	}

	public double getBmi() {
		WebElement bmiField = getDriver().findElement(By.id("personForm:bmi"));
		String bmiAsString = bmiField.getAttribute("value");
		return Double.parseDouble(bmiAsString);
	}

	public ExaminationDetailPage addExamination() {
		WebElement buttonExamination = getDriver().findElement(By.id("personToExaminationForm:addExamination"));
		buttonExamination.click();
		return new ExaminationDetailPage(getDriver());
	}

	public PersonOverviewPage cancel() {
		WebElement buttonCancel = getDriver().findElement(By.id("personToExaminationForm:cancel"));
		buttonCancel.click();
		return new PersonOverviewPage(getDriver());
	}

}
