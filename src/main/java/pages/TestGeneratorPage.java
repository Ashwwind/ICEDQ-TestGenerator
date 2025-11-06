package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.PageUtil;

public class TestGeneratorPage {
	WebDriver driver;

	public TestGeneratorPage(WebDriver driver) {
		this.driver = driver;
	}

	// Page method
	public void clickTestGenerator() {
		PageUtil.waitForTheElementToBeClickable(driver,By.xpath("//*[@id='imaster']/app-home/div[3]/div/ul[1]/li[3]/button"),
				"TestGenerator");	PageUtil.clickOnElement(driver, By.xpath("//*[@id='imaster']/app-home/div[3]/div/ul[1]/li[3]/button"),
			"TestGenerator");
	}

	// Click the Checksum rule type from the wizard page.
	public void clickOnChecksum() {
		PageUtil.waitForElement(driver, 50);
		PageUtil.clickOnElement(driver, By.xpath("//h6[text()='Checksum']"), "select checksum rule from wizard");
	}

	// Click on the search field box
	public void clickOnSearchField() {
		PageUtil.waitForElement(driver, 50);
		PageUtil.clickOnElement(driver, By.xpath("//input[contains(@placeholder,'Search template')]"),
				"Search template");
	}

	// Enter the data on the search field
	public void enterOnSearchField() {
		PageUtil.waitForElement(driver, 50);
		PageUtil.sendkeysToElement(driver, By.xpath("//input[contains(@placeholder,'Search template')]"),
				"Search template", "DND - Checksum -- Dynamic Template");
		PageUtil.clickOnElement(driver, By.xpath("//*[@title='Search']"), "the search button icon");
	}

	// Select the existing checksum template form the list
	public void selectExistingChecksumTemplate() {
		PageUtil.waitForElement(driver, 50);
		PageUtil.clickOnElement(driver, By.xpath("//table//span[text()='DND - Checksum -- Dynamic Template']"),
				"Selected existing checksum rule template");
	}

//	// Click on the Workspace selector field
//	public void clickOnWorkspaceField() throws InterruptedException {
//		PageUtil.waitForElement(driver, 50);
//		Thread.sleep(500);
//		PageUtil.clickOnElement(driver, By.xpath("//input[@placeholder='Select workspace']"),
//				"Click the Workspace field");
//		Thread.sleep(500);
//		PageUtil.waitForElement(driver, 50);
//		Thread.sleep(500);
//		PageUtil.clickOnElement(driver,
//				By.xpath("//*[@id=\"ej2_dropdownlist_3_options\"]/li[contains(text(), 'TestGenerator-workspace')]"),
//				"The Workspace selected");
//	}
	
	// Test workspace selection.
	public void clickOnWorkspaceField123()
	{
		PageUtil.waitForElement(driver, 50);
		PageUtil.waitForTheElementToBeClickable(driver, By.xpath("//input[@placeholder='Select workspace']"),
				"Click the Workspace field");
		PageUtil.waitForElement(driver, 50);
		PageUtil.waitForTheElementToBeClickable(driver,
				By.xpath("//*[@id=\"ej2_dropdownlist_3_options\"]/li[contains(text(), 'TestGenerator-workspace')]"),
				"The Workspace selected");
	}

	// Click on the Folder selector field
	public void clickOnFolderFiled() throws InterruptedException {
		PageUtil.waitForElement(driver, 50);
		Thread.sleep(500);
		PageUtil.clickOnElement(driver, By.xpath(
				"//div[@id='e-content-element_1']//div[contains(@class, 'dropdown__trigger')]/button[@aria-label='Folder']"),
				"Click the Folder field");
		PageUtil.waitForElement(driver, 50);
		Thread.sleep(500);
		PageUtil.sendkeysToElement(driver, By.xpath("//input[@placeholder='Search folder']"), "searching folder name.",
				"API");
		PageUtil.waitForElement(driver, 50);
		Thread.sleep(500);
		PageUtil.clickOnElement(driver, By.xpath("//div[@id='pathtree']//span[@title='Search']"),
				"search folder name.");
		PageUtil.waitForElement(driver, 50);
		Thread.sleep(500);
		PageUtil.clickOnElement(driver, By.xpath("//span[@class='e-icons e-check']"), "select the folder.");

	}
}
