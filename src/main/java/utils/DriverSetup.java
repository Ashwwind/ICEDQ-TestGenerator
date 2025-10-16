package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSetup {
	private static WebDriver driver;

	public static WebDriver initDriver() {

		if (driver == null) {
			WebDriverManager.chromedriver().setup();

			ChromeOptions options = new ChromeOptions();
			options.setAcceptInsecureCerts(true); // Accept untrusted certificates

			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
		}
		return driver;
	}
}