package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import utils.DriverSetup; // Make sure this import matches your package structure

public class Base {

	protected static WebDriver driver;

	@BeforeSuite
	public WebDriver launchBrowser() {

		driver = DriverSetup.initDriver();
		driver.get("https://192.168.100.102:32222/");

		return driver;
	}
	
	// Driver getter method
	public WebDriver getDriver()
	{
		return driver;
	}
	
	// Driver setter method
	public void setDriver(WebDriver driver)
	{
		Base.driver = driver;
	}

}