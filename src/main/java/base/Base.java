package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;

import utils.DriverSetup; // Make sure this import matches your package structure

public class Base {

	  public static WebDriver driver = null;
	  
	  
	  @BeforeSuite
	  public static WebDriver launchBrowser() {
		  
		  driver = DriverSetup.initDriver();
		  
		  driver.get("https://192.168.100.102:32222/");
		  
		  return driver;
	  }
	
}