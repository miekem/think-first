package org.ucll.demo.ui.pages;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExaminationFieldsPage extends SeleniumPage {
	private String formPrefix;

	public ExaminationFieldsPage(WebDriver driver, String formPrefix) {
		super(driver);
		this.formPrefix = formPrefix;
	}

	public void addExaminationData(int length, int weight, Date examinationDate) {
		WebElement lenghtField = getDriver().findElement(By.id(formPrefix + ":length"));
		lenghtField.clear();
		lenghtField.sendKeys(Integer.toString(length));

		WebElement weightField = getDriver().findElement(By.id(formPrefix + ":weight"));
		weightField.clear();
		weightField.sendKeys(Integer.toString(weight));

        WebElement dateField = getDriver().findElement(By.id(formPrefix + ":examinationDate"));
        dateField.clear();
        dateField.sendKeys(new SimpleDateFormat("yyyy-MM-dd").format(examinationDate));
	}

	public int getLength() {
		WebElement lengthField = getDriver().findElement(By.id(formPrefix + ":length"));
		String lengthAsString = lengthField.getAttribute("value");
		return Integer.parseInt(lengthAsString);
	}

	public int getWeight() {
		WebElement weightField = getDriver().findElement(By.id(formPrefix + ":weight"));
		String weightAsString = weightField.getAttribute("value");
		return Integer.parseInt(weightAsString);
	}

	public Date getExaminationDate() {
		WebElement examinationDateField = getDriver().findElement(By.id(formPrefix + ":examinationDate"));
		String dateAsString = examinationDateField.getAttribute("value");
		return convertToDate(dateAsString);
	}

}
