package org.ucll.demo.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PersonOverviewPage extends SeleniumPage {

	public PersonOverviewPage(WebDriver driver) {
		super(driver);
	}

	public PersonDetailPage showPerson(String id) {
		WebElement userLink = getDriver().findElement(By.partialLinkText(id));
		userLink.click();
		return new PersonDetailPage(getDriver());
	}

	public PersonDetailPage addPerson() {
		WebElement addButton = getDriver().findElement(By.id("personOverviewForm:addPersonButton"));
		addButton.click();
		return new PersonDetailPage(getDriver());
	}

	public PersonDetailPage deletePerson() { // TODO do better
		WebElement deleteLink = getDriver().findElement(By.id("personOverviewForm:personTable:0:delete"));
		deleteLink.click();
		return new PersonDetailPage(getDriver());
	}

}
