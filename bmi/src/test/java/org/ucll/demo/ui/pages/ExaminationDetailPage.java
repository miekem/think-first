package org.ucll.demo.ui.pages;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExaminationDetailPage extends SeleniumPage {
	private ExaminationFieldsPage examinationfieldsPage;

	public ExaminationDetailPage(WebDriver driver) {
		super(driver);
		examinationfieldsPage = new ExaminationFieldsPage(getDriver(), "examinationForm");
	}

	public PersonDetailPage addExamination(int length, int weight, Date examinationDate) {
		examinationfieldsPage.addExaminationData(length, weight, examinationDate);
        
        WebElement saveButton = getDriver().findElement(By.id("examinationForm:saveExamination"));
        saveButton.click();
        
        return new PersonDetailPage(getDriver());
	}

}
