package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import config.ConfigReader;
import utils.DriverSetup;

public class Base {

	protected static WebDriver driver;
	private static final Logger log = LogManager.getLogger(Base.class);
	protected static final Properties prop = new Properties();

	@BeforeSuite(alwaysRun = true)
	public WebDriver launchBrowser() {
		locatotFind();
		driver = DriverSetup.initDriver();
		driver.get(ConfigReader.getProperty("baseUrl"));
		return driver;

	}


	public static void locatotFind() {
		String locatorDirPath = System.getProperty("user.dir") + "/src/main/resources/locator/";
		File folder = new File(locatorDirPath);

		if (!folder.exists() || !folder.isDirectory()) {
			log.error("Locator directory not found: {}", locatorDirPath);

		}

		File[] listOfFiles = folder.listFiles((dir, name) -> name.endsWith(".properties"));
		if (listOfFiles == null || listOfFiles.length == 0) {
			log.warn("No locator property files found in {}", locatorDirPath);
			return;
		}

		log.info("Loading locator files from directory: {}", locatorDirPath);

		for (File file : listOfFiles) {
			log.info("Loading locator file: {}", file.getName());

			try (FileInputStream fis = new FileInputStream(file)) {
				Properties tempProps = new Properties();
				tempProps.load(fis);
				prop.putAll(tempProps);
				log.info("Loaded {} locators from {}", tempProps.size(), file.getName());
			} catch (IOException e) {
				log.error("Failed to load locator file '{}': {}", file.getName(), e.getMessage());
			}
		}

		log.info("Total locators loaded: {}", prop.size());
	}

	// Driver getter method
	public WebDriver getDriver() {
		return driver;
	}

	// Driver setter method
	public void setDriver(WebDriver driver) {
		Base.driver = driver;
	}

}