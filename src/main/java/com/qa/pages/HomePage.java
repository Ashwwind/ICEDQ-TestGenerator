package com.qa.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.aventstack.extentreports.Status;
import com.qa.config.ConfigReader;
import com.qa.extentreportlistener.ExtentListener;
import com.qa.utils.PageUtil;

public class HomePage extends PageUtil {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void homepageValidation() {

		try {

			// ============================
			// URL VALIDATION
			// ============================
			String actualUrl = null;
			String expectedUrl = ConfigReader.getProperty("https://qa.onprem.icedq.com/#/");

			try {
				actualUrl = driver.getCurrentUrl();
				Assert.assertTrue(actualUrl.contains(expectedUrl));

				ExtentListener.test.get().log(Status.PASS, "The actual URL: " + actualUrl);
			} catch (Exception e) {
				ExtentListener.test.get().log(Status.FAIL,
						"The actual URL: " + actualUrl + " | The expected URL: " + expectedUrl);
			}

			// ============================
			// TITLE VALIDATION
			// ============================
			String actualTitle = null;
			String expectedTitle = "NextgenAdministration";

			try {
				actualTitle = driver.getTitle();
				Assert.assertTrue(actualTitle.contains(expectedTitle));

				ExtentListener.test.get().log(Status.PASS, "The actual title: " + actualTitle);
			} catch (Exception e) {
				ExtentListener.test.get().log(Status.FAIL,
						"The actual title: " + actualTitle + " | The expected title: " + expectedTitle);
			}

			// ============================
			// LOGO VALIDATION
			// ============================
			WebElement logo = null;
			String expectedLogo = "Application logo should be visible";

			try {
				logo = driver.findElement(By.xpath("//img[contains(@src,'logo') or contains(@alt,'iceDQ')]"));
				Assert.assertTrue(logo.isDisplayed());

				ExtentListener.test.get().log(Status.PASS, "Logo is displayed");
			} catch (Exception e) {
				ExtentListener.test.get().log(Status.FAIL, "Logo NOT displayed | Expected: " + expectedLogo);
			}

			// ============================
			// WELCOME MESSAGE
			// ============================
			WebElement welcomeMessage = null;
			String expectedWelcome = "Welcome message should be visible";

			try {
				welcomeMessage = driver.findElement(By.xpath("//*[contains(text(),'Hi')]"));
				Assert.assertTrue(welcomeMessage.isDisplayed());

				ExtentListener.test.get().log(Status.PASS, "Welcome message is displayed");
			} catch (Exception e) {
				ExtentListener.test.get().log(Status.FAIL,
						"Welcome message NOT displayed | Expected: " + expectedWelcome);
			}

			// ============================
			// PROFILE ICON
			// ============================
			WebElement profileIcon = null;
			String expectedProfile = "Profile icon should be visible";

			try {
				profileIcon = driver.findElement(By.xpath("//*[@role='menuitem']/span[1]"));
				Assert.assertTrue(profileIcon.isDisplayed());

				ExtentListener.test.get().log(Status.PASS, "Profile icon is displayed");
			} catch (Exception e) {
				ExtentListener.test.get().log(Status.FAIL, "Profile icon NOT displayed | Expected: " + expectedProfile);
			}

			// ============================
			// MODULES VALIDATION
			// ============================
			String[] expectedModules = { "Data Testing", "BI Report Testing", "Test Generator", "Dashboard",
					"Connectors", "Scheduler", "Administration" };

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			for (String module : expectedModules) {

				String actualModule = null;
				String expectedModule = module;

				try {
					WebElement moduleElement = wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'" + module + "')]")));

					actualModule = moduleElement.getText();

					Assert.assertTrue(moduleElement.isDisplayed());

					ExtentListener.test.get().log(Status.PASS, "Module displayed: " + actualModule);
				} catch (Exception e) {
					ExtentListener.test.get().log(Status.FAIL,
							"Module NOT displayed. Actual: " + actualModule + " | Expected: " + expectedModule);
				}
			}

			// ============================
			// HYPERLINK ICONS
			// ============================
			String[] expectedHyperlinkIcon = { "About", "Helpdesk", "Documentation" };

			for (String icon : expectedHyperlinkIcon) {

				String actualIcon = null;
				String expectedIcon = icon;

				try {

					WebElement hyperlink = wait.until(
							ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='" + icon + "']")));

					actualIcon = hyperlink.getAttribute("title");

					Assert.assertTrue(hyperlink.isDisplayed());

					ExtentListener.test.get().log(Status.PASS, "Hyperlink displayed: " + actualIcon);

				} catch (Exception e) {
					ExtentListener.test.get().log(Status.FAIL,
							"Hyperlink NOT displayed. Actual: " + actualIcon + " | Expected: " + expectedIcon);
				}
			}

		} catch (Exception e) {
			ExtentListener.test.get().log(Status.FAIL,
					"Unexpected error during dashboard validation: " + e.getMessage());
		}

	}

}
