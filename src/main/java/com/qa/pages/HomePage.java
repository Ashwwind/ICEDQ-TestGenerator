package com.qa.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.qa.config.ConfigReader;
import com.qa.extentreportlistener.ExtentManager;
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

				ExtentManager.logPass( "The actual URL: " + actualUrl);
			} catch (Exception e) {
				ExtentManager.logFail("The actual URL: " + actualUrl + " | The expected URL: " + expectedUrl);
			}

			// ============================
			// TITLE VALIDATION
			// ============================
			String actualTitle = null;
			String expectedTitle = "NextgenAdministration";

			try {
				actualTitle = driver.getTitle();
				Assert.assertTrue(actualTitle.contains(expectedTitle));

				ExtentManager.logPass( "The actual title: " + actualTitle);
			} catch (Exception e) {
				ExtentManager.logFail("The actual title: " + actualTitle + " | The expected title: " + expectedTitle);
			}

			// ============================
			// LOGO VALIDATION
			// ============================
			WebElement logo = null;
			String expectedLogo = "Application logo should be visible";

			try {
				logo = driver.findElement(By.xpath("//img[contains(@src,'logo') or contains(@alt,'iceDQ')]"));
				Assert.assertTrue(logo.isDisplayed());

				ExtentManager.logPass( "Logo is displayed");
			} catch (Exception e) {
				ExtentManager.logFail("Logo NOT displayed | Expected: " + expectedLogo);
			}

			// ============================
			// WELCOME MESSAGE
			// ============================
			WebElement welcomeMessage = null;
			String expectedWelcome = "Welcome message should be visible";

			try {
				welcomeMessage = driver.findElement(By.xpath("//*[contains(text(),'Hi')]"));
				Assert.assertTrue(welcomeMessage.isDisplayed());

				ExtentManager.logPass( "Welcome message is displayed");
			} catch (Exception e) {
				ExtentManager.logFail("Welcome message NOT displayed | Expected: " + expectedWelcome);
			}

			// ============================
			// PROFILE ICON
			// ============================
			WebElement profileIcon = null;
			String expectedProfile = "Profile icon should be visible";

			try {
				profileIcon = driver.findElement(By.xpath("//*[@role='menuitem']/span[1]"));
				Assert.assertTrue(profileIcon.isDisplayed());

				ExtentManager.logPass( "Profile icon is displayed");
			} catch (Exception e) {
				ExtentManager.logFail("Profile icon NOT displayed | Expected: " + expectedProfile);
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

					ExtentManager.logPass( "Module displayed: " + actualModule);
				} catch (Exception e) {
					ExtentManager.logFail("Module NOT displayed. Actual: " + actualModule + " | Expected: " + expectedModule);
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

					ExtentManager.logPass( "Hyperlink displayed: " + actualIcon);

				} catch (Exception e) {
					ExtentManager.logFail("Hyperlink NOT displayed. Actual: " + actualIcon + " | Expected: " + expectedIcon);
				}
			}

		} catch (Exception e) {
			ExtentManager.logFail("Unexpected error during dashboard validation: " + e.getMessage());
		}

	}

}
