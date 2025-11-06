package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.PageUtil;

public class LoginPage {
	WebDriver driver;

	By usernameField = By.name("username");
	By passwordField = By.name("password");
	By loginButton = By.xpath("//input[@value='Sign In']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterUsername(String username) {
		PageUtil.sendkeysToElement(driver, usernameField, "Username", username);
	}

	public void enterPassword(String password) {
		
		PageUtil.sendkeysToElement(driver, passwordField, "password", password);
	}

	public void clickLogin() {
		try {
			PageUtil.waitForElement(driver, 10);
			Thread.sleep(5000);
			PageUtil.clickOnElement(driver, loginButton, "Sign In");
		} catch (Exception e) {
			System.out.println("Loging button is not displayed" + e.getStackTrace());
			e.printStackTrace();
		}
	}

	public void login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLogin();
	}

}
