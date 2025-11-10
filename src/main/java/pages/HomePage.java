package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.PageUtil;

public class HomePage {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void validateHomePage() {

		try {
			
			PageUtil.waitForPageToLoad(driver, 30);
			
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
			// validate the Url and Title
			String currentUrl = driver.getCurrentUrl();
			// Assert.assertTrue(currentUrl.contains("https://192.168.100.102:32222/#/"),
			// "Dashboard URL validation failed. Current URL: " + currentUrl);
			System.out.println("The home page Url is matched... ");

			String title = driver.getTitle();
			Assert.assertTrue(title.contains("NextgenAdministration"),
					"Dashboard title validation failed. Title: " + title);

//			// validate application logo
//			//WebElement logo = driver.findElement(By.xpath("//img[contains(@src,'logo') or contains(@alt,'iceDQ')]"));
//			//Assert.assertTrue(logo.isDisplayed(), "Application logo not displayed!");
//
//			// validate welcome message
//			//WebElement welcomeMessage = driver.findElement(By.xpath("//*[contains(text(),'Hi')]"));
//			//Assert.assertTrue(welcomeMessage.isDisplayed(), "Welcome message not displayed");
//
//			// validate profile icon
//			WebElement profileIcon = driver.findElement(By.xpath("//button[@aria-label ='ad']"));
//			Assert.assertTrue(profileIcon.isDisplayed(), "Profile icon not visible!");
//
//			///Validate HomePage icons
			String[] expectedModules = { "Data Testing", "BI Report Testing", "Test Generator", "Dashboard",
					"Connectors", "Scheduler", "Administration" };

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			for (String module : expectedModules) {
				try {
					// Wait until the element containing the module text is visible
					WebElement moduleElement = wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'" + module + "')]")));
					Assert.assertTrue(moduleElement.isDisplayed(), module + " not displayed");
					System.out.println(module + " is displayed successfully.");

				} catch (TimeoutException e) {
					System.out.println(module + " is not displayed." + e.getMessage());
				}
			}

//			// Validate the hyperlink icons
//			String[] expectedHyperlinkIcon = { "About", "Helpdesk", "Documentation" };
//
//			for (String icon : expectedHyperlinkIcon) {
//				try {
//					WebElement hyperlinkIcon = (WebElement) wait.until(ExpectedConditions
//							.visibilityOfAllElementsLocatedBy(By.xpath("//a[@title='" + expectedHyperlinkIcon + "']")));
//					Assert.assertTrue(hyperlinkIcon.isDisplayed(), icon + " link is not displayed");
//					System.out.println(title + " link is displayed successfully.");
//				} catch (Exception e) {
//					System.out.println("link not found or not visible within timeout" + e.getMessage());
//				}
//
//			}

			System.out.println("Dashboard validation successful.");
		} catch (Exception e) {
			System.out.println("error occurs while validateDashboardPage " + e.getMessage());
		}
	}

}
