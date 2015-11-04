package org.ucll.demo.ui.pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;

public abstract class SeleniumPage {
	private final WebDriver driver;

	public SeleniumPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}
	
	protected Date convertToDate(String dateAsString) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dateAsString);
		} catch (ParseException e) {
			throw new IllegalArgumentException("error.no.date");
		}
	}

}
