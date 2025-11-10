package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import base.Base;
import utils.PageUtil;

public class TestGeneratorPage extends Base {
	WebDriver driver;

	// Locator
	By testGeneratorModue = PageUtil.getElementLocator(prop.getProperty("home.testgenerator"));
	By templateSearch = PageUtil.getElementLocator(prop.getProperty("searchfield.tempatename"));
	By selectWorkspace = PageUtil.getElementLocator(prop.getProperty("select.Workspace"));
	By selectSearchWorkspace = PageUtil.getElementLocator(prop.getProperty("select.searchedWorspace"));
	By selectSearchFolder = PageUtil.getElementLocator(prop.getProperty("select.searchedfolder"));

	public TestGeneratorPage(WebDriver driver) {
		this.driver = driver;

	}

	// Page method
	public void clickTestGenerator() {
		PageUtil.waitForPageToLoad(driver, 10);
		// PageUtil.waitForTheElementToBeClickable1(driver, testGeneratorModue, "Test
		// Generator module...");
		PageUtil.clickOnElement(driver, By.xpath("//button[.//h2[text()='Test Generator']]"), "TestGenerator");
	}

	// Click the Checksum rule type from the wizard page.
	public void clickOnChecksum() {
		PageUtil.waitForPageToLoad(driver, 10);
		PageUtil.waitMethod(driver);
		PageUtil.clickOnElement(driver, By.xpath("//h6[text()='Checksum']"), "select checksum rule from wizard");
	}

	// Click on the search field box
	public void clickOnSearchField() {
		PageUtil.waitForPageToLoad(driver, 10);
		PageUtil.waitMethod(driver);
//		PageUtil.clickOnElement(driver, By.xpath("//input[contains(@placeholder,'Search template')]"),
//				"Search template");
		PageUtil.clickElementSafely(driver, templateSearch, "Search template");
	}

	// Enter the data on the search field
	public void enterOnSearchField() {
		PageUtil.waitForPageToLoad(driver, 60);
		PageUtil.sendkeysToElement(driver, By.xpath("//input[contains(@placeholder,'Search template')]"),
				"Search template", "Compare Record Counts - Dynamic SQL");
		PageUtil.waitForPageToLoad(driver, 50);
		PageUtil.clickOnElement(driver, By.xpath("//*[@title='Search']"), "the search button icon");
	}

	// Select the existing checksum template form the list
	public void selectExistingChecksumTemplate() {
		PageUtil.waitForPageToLoad(driver, 50);
		PageUtil.waitMethod(driver);
		PageUtil.waitForElement(driver, 20);
		PageUtil.clickOnElement(driver, By.xpath(
				"//td[@class='e-rowcell e-templatecell e-lastrowcell']/span[@class='dib'][contains(., 'Compare Record Counts - Dynamic SQL')]"),
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
	public void clickOnWorkspaceField123() {
		PageUtil.waitForPageToLoad(driver, 20);
		PageUtil.waitForElement(driver, 10);
		// PageUtil.waitForTheElementToBeClickable(driver,
		// By.xpath("//span[./input[contains(@class, 'e-input')]]"), // "Click the
		// Workspace field"); PageUtil.clickElementSafely(driver, selectWorkspace,
		// "Click the Workspace field"); PageUtil.waitForElement(driver, 20); //
		PageUtil.waitForTheElementToBeClickable(driver,
				By.xpath("//span[./input[contains(@class, 'e-input')]], 'Test_Generator-WIP')]"),
				"The Workspace selected");
		PageUtil.clickElementSafely(driver, selectSearchWorkspace, "The Workspaceselected");
		PageUtil.sendkeysToElement(driver, selectSearchWorkspace, "select workspace field",
				"Test_Generator-WIP" + Keys.ENTER);
		// PageUtil.clickOnElement(driver,
		// By.xpath("//ul[@id='ej2_dropdownlist_7_options']/li[1]"), "workspace
		// name.."); PageUtil.waitForElement(driver, 50);
		System.out.println("The worspace is selected...");
	}

	// Click on the Folder selector field

	public void clickOnFolderFiled() throws InterruptedException {
		PageUtil.waitForElement(driver, 20);
		PageUtil.clickOnElement(driver,
				By.xpath("//div[@class='dropdown__trigger' and following-sibling::div[@id='pathtree']]"),
				"Click the Folder field");
		PageUtil.waitForPageToLoad(driver, 10);
		PageUtil.waitForElement(driver, 20);
		PageUtil.sendkeysToElement(driver,
				By.xpath("//div[@class='dropdown__search-group e-input-group']/input[@id='searchFolder']"),
				"searching folder name.", "MJ_DND");
		PageUtil.waitForPageToLoad(driver, 10);
		PageUtil.waitForElement(driver, 20);
		PageUtil.clickOnElement(driver, By.xpath("//div[@id='pathtree']//span[@title='Search']"),
				"search folder name.");
		PageUtil.waitForPageToLoad(driver, 10);
		PageUtil.waitForElement(driver, 20);
		PageUtil.clickElementSafely(driver, selectSearchFolder, "select the folder.");
		PageUtil.clickOnElement(driver, selectSearchFolder, "select the folder.");

		System.out.println("The folder is selected...");
	}

	public void gotoWorkspaceNextbtn() {
		PageUtil.waitForTheElementToBeVisible(driver, By.xpath("//button[@id='workspaceNextbtn']"), "Next button");
		PageUtil.clickOnElement(driver, By.xpath("//button[@id='workspaceNextbtn']"), "Next button");
		PageUtil.waitForElement(driver, 20);
	}

	public void gotoDAtaMetadataNextbtn() {
		PageUtil.waitForPageToLoad(driver, 50);
		PageUtil.waitForTheElementToBeVisible(driver, By.xpath("//button[@id='metadataNextbtn']"), "Next button");
		PageUtil.clickOnElement(driver, By.xpath("//button[@id='metadataNextbtn']"), "Next button");
		PageUtil.waitForElement(driver, 20);
	}

	public void gotoRuleMetadataNextbtn() {
		PageUtil.waitForPageToLoad(driver, 50);
		PageUtil.waitForTheElementToBeVisible(driver, By.xpath("//button[@id='metadataNextbtn']"), "Next button");
		PageUtil.clickOnElement(driver, By.xpath("//button[@id='metadataNextbtn']"), "Next button");
		PageUtil.waitForElement(driver, 20);
	}

	public void gotoCheckMetadataNextbtn() {
		PageUtil.waitForPageToLoad(driver, 50);
		PageUtil.waitForTheElementToBeVisible(driver, By.xpath("//button[@id='checkNextbtn']"), "Next button");
		PageUtil.clickOnElement(driver, By.xpath("//button[@id='checkNextbtn']"), "Next button");
		PageUtil.waitForElement(driver, 20);
	}

	public void gotoNotificationNextbtn() {
		PageUtil.waitForPageToLoad(driver, 50);
		PageUtil.waitForTheElementToBeVisible(driver, By.xpath("//button[@id='notificationNextbtn']"), "Next button");
		PageUtil.clickOnElement(driver, By.xpath("//button[@id='notificationNextbtn']"), "Next button");
		PageUtil.waitForElement(driver, 20);
	}

	public void seectConnection() {
		PageUtil.waitForPageToLoad(driver, 50);
		PageUtil.waitForTheElementToBeVisible(driver, By.xpath("//ejs-dropdownlist[@id='ej2_dropdownlist_27']/span[1]"),
				"Next button");
		PageUtil.clickOnElement(driver, By.xpath("//ejs-dropdownlist[@id='ej2_dropdownlist_27']/span[1]"),
				"Next button");
		// Wait
		PageUtil.waitForTheElementToBeVisible(driver, By.xpath("//ul[@id='ej2_dropdownlist_27_options']/*[2]"),
				"Next butto	n");
		PageUtil.clickOnElement(driver, By.xpath("//ul[@id='ej2_dropdownlist_27_options']/*[2]"), "Next button");
		PageUtil.waitForElement(driver, 20);
	}

}
