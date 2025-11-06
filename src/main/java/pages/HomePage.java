package pages;

import org.openqa.selenium.WebDriver;

public class HomePage {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void validateDashboardPage() throws InterruptedException {
		HomePage dashboardPage = new HomePage(driver);
		Thread.sleep(5000);
		dashboardPage.validateDashboardPage();

	}
	
	

}
