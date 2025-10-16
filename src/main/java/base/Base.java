package base;

import org.openqa.selenium.WebDriver;
import utils.DriverSetup; // Make sure this import matches your package structure

public class Base {

	public static void main(String[] args) {
		
        // Initialize WebDriver using DriverSetup
        WebDriver driver = DriverSetup.initDriver();

        // Open a website
        driver.get("https://52.20.42.154:32222/");

    }
}