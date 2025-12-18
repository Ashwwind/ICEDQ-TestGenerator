package com.qa.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSetup {
	public static WebDriver driver;

	public static WebDriver initDriver() {

		if (driver == null) {
			WebDriverManager.chromedriver().setup();

			ChromeOptions options = new ChromeOptions();
			options.setAcceptInsecureCerts(true);

			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
		}
		return driver;
	}
}