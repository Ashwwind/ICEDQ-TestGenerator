package com.qa.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qa.config.ConfigReader;
import com.qa.extentreportlistener.ExtentManager;
import com.qa.utils.PageUtil;

public class HomePage extends PageUtil {

	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void homepageValidation() {

		// URL VALIDATION
		verifyUrlOfPage();

		// TITLE VALIDATION
		verifyTitleOfPage();

		// LOGO VALIDATION
		verifyLogoIsDisplayed();

		// WELCOME MESSAGE VALIDATION
		verifyWelcomeMessage();

		// USER PROFILE VALIDATION
		verifyProfileIcon();

		// MODULES VALIDATION
		verifyModules();

		// Hyperlinks ICON BUTTON VALIDATION
		verifyHyperlinkIcons();

	}

	// ============================
	// URL VALIDATION
	// ============================
	public void verifyUrlOfPage() {

		String expectedUrl = ConfigReader.getProperty("homePageUrl");
		String actualUrl = driver.getCurrentUrl();

		// check point
        assert actualUrl != null;
        if (actualUrl.contains(expectedUrl)) {
			System.out.println("The actual URL is correct.");
			ExtentManager.logPass(STR."The actual URL is correct: \{actualUrl}");
		} else {
			System.out.println("The actual URL is incorrect.");
			ExtentManager.logFail(
                    STR."The actual URL is incorrect. Expected to contain: \{expectedUrl} | Actual URL: \{actualUrl}");
		}
	}

	// ============================
	// TITLE VALIDATION
	// ============================
	public void verifyTitleOfPage() {

		String expectedTitle = "NextgenAdministration";
		String actualTitle = driver.getTitle();

		// check point
        assert actualTitle != null;
        if (actualTitle.contains(expectedTitle)) {
			System.out.println("The page title is correct.");
			ExtentManager.logPass(STR."The page title is correct: \{actualTitle}");
		} else {
			System.out.println("The page title is incorrect.");
			ExtentManager.logFail(STR."The page title is incorrect. Expected to contain: \{expectedTitle} | Actual title: \{actualTitle}");
		}
	}

	// ============================
	// LOGO VALIDATION
	// ============================
	public void verifyLogoIsDisplayed() {

		WebElement logo = driver.findElement(By.xpath("//img[contains(@src,'logo') or contains(@alt,'iceDQ')]"));
		String expectedLogo = "Application logo should be visible";

		// check point
		if (logo.isDisplayed()) {
			System.out.println("Logo is displayed.");
			ExtentManager.logPass("Logo is displayed.");
		} else {
			System.out.println("Logo is NOT displayed.");
			ExtentManager.logFail(STR."Logo is NOT displayed | Expected: \{expectedLogo}");
		}
	}

	// ============================
	// WELCOME MESSAGE
	// ============================
	public void verifyWelcomeMessage() {

		WebElement welcomeMessage = driver.findElement(By.xpath("//*[contains(text(),'Hi')]"));
		String expectedMessagePart = "welcome to iceDQ";

		// check point
		if (welcomeMessage.isDisplayed() && welcomeMessage.getText().contains(expectedMessagePart)) {
			System.out.println(STR."Welcome message is displayed: \{welcomeMessage.getText()}");
			ExtentManager.logPass(STR."Welcome message is displayed: \{welcomeMessage.getText()}");
		} else {
			System.out.println("Welcome message is NOT displayed or text is incorrect.");
			ExtentManager.logFail(
                    STR."Welcome message is NOT displayed or does not contain expected text: \{expectedMessagePart}");
		}
	}

	// ============================
	// PROFILE ICON
	// ============================
	public void verifyProfileIcon() {

		WebElement profileIcon = driver.findElement(By.xpath("//*[@role='menuitem']/span[1]"));
		String expectedProfile = "Profile icon should be visible";

		// check point
		if (profileIcon.isDisplayed()) {
			System.out.println("Profile icon is displayed.");
			ExtentManager.logPass("Profile icon is displayed.");
		} else {
			System.out.println("Profile icon is NOT displayed.");
			ExtentManager.logFail(STR."Profile icon NOT displayed | Expected: \{expectedProfile}");
		}
	}

	// ============================
	// MODULES VALIDATION
	// ============================
	public void verifyModules() {

		String[] expectedModules = { "Data Testing", "BI Report Testing", "Test Generator", "Dashboard", "Connectors",
				"Scheduler", "Administration" };

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		for (String expectedModule : expectedModules) {

            WebElement moduleElement = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(STR."//h2[contains(text(),'\{expectedModule}')]")));

			// check point
            assert moduleElement != null;
            if (moduleElement.isDisplayed()) {
				String actualModule = moduleElement.getText();
				System.out.println(STR."Module displayed: \{actualModule}");
				ExtentManager.logPass(STR."Module displayed: \{actualModule}");
			} else {
				System.out.println(STR."Module NOT displayed: \{expectedModule}");
				ExtentManager.logFail(STR."Module NOT displayed | Expected: \{expectedModule}");
			}
		}
	}

	/// ============================
	// HYPERLINK ICONS
	// ============================
	public void verifyHyperlinkIcons() {

		String[] expectedHyperlinkIcons = { "About", "Helpdesk", "Documentation" };
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		for (String icon : expectedHyperlinkIcons) {
			try {
				WebElement hyperlink = wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(STR."//div[@class='help-text']//a[@title='\{icon}']")));

				// Scroll into view in case it is off-screen
				scrollToElement(driver, hyperlink);

				if (hyperlink.isDisplayed()) {
					String actualIcon = hyperlink.getAttribute("title");
					System.out.println(STR."Hyperlink displayed: \{actualIcon}");
					ExtentManager.logPass(STR."Hyperlink displayed: \{actualIcon}");
				} else {
					System.out.println(STR."Hyperlink NOT displayed (hidden): \{icon}");
					ExtentManager.logFail(STR."Hyperlink NOT displayed (hidden) | Expected: \{icon}");
				}

			} catch (TimeoutException e) {
				System.out.println(STR."Hyperlink NOT found in DOM: \{icon}");
				ExtentManager.logFail(STR."Hyperlink NOT found in DOM | Expected: \{icon}");
			}
		}
	}

}
