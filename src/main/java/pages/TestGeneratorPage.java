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
		//PageUtil.waitForPageToLoad(driver, 10);
		// PageUtil.waitForTheElementToBeClickable1(driver, testGeneratorModue, "Test
		// Generator module...");
		//PageUtil.clickOnElement(driver, By.xpath("//button[.//h2[text()='Test Generator']]"), "TestGenerator");
		PageUtil.clickOnElement(driver, By.xpath("//button[.//h2[text()='Test Generator']]"), 10);
	}

	// Click the Checksum rule type from the wizard page.
	public void clickOnChecksum() {
//		PageUtil.waitForPageToLoad(driver, 10);
//		PageUtil.waitMethod(driver);
//		PageUtil.clickOnElement(driver, By.xpath("//h6[text()='Checksum']"), "select checksum rule from wizard");
		PageUtil.clickOnElement(driver, By.xpath("//h6[text()='Checksum']"), 10);
	}

	// Click on the search field box
	public void clickOnSearchField() {
		////PageUtil.waitMethod(driver);
//		PageUtil.clickOnElement(driver, By.xpath("//input[contains(@placeholder,'Search template')]"),
//				"Search template");
		////PageUtil.clickElementSafely(driver, templateSearch, "Search template");
		PageUtil.clickOnElement(driver, templateSearch, 10);
	}

	// Enter the data on the search field
	public void enterOnSearchField() {
//		PageUtil.waitForPageToLoad(driver, 60);
//		PageUtil.sendkeysToElement(driver, By.xpath("//input[contains(@placeholder,'Search template')]"),
//				"Search template", "Compare Record Counts - Dynamic SQL");
//		PageUtil.waitForPageToLoad(driver, 50);
//		PageUtil.clickOnElement(driver, By.xpath("//*[@title='Search']"), "the search button icon");
		
		////
		PageUtil.sendkeysToElement(driver, By.xpath("//input[contains(@placeholder,'Search template')]"), "Search template", "Compare Record Counts - Dynamic SQL");
		PageUtil.clickOnElement(driver, By.xpath("//*[@title='Search']"), 10);
	}

	// Select the existing checksum template form the list
	public void selectExistingChecksumTemplate() {
//		PageUtil.waitForPageToLoad(driver, 50);
//		PageUtil.waitMethod(driver);
//		PageUtil.waitForElement(driver, 20);
//		PageUtil.clickOnElement(driver, By.xpath(
//				"//td[@class='e-rowcell e-templatecell e-lastrowcell']/span[@class='dib'][contains(., 'Compare Record Counts - Dynamic SQL')]"),
//				"Selected existing checksum rule template");
		
		///
		PageUtil.clickOnElement(driver, By.xpath(
				"//td[@class='e-rowcell e-templatecell e-lastrowcell']/span[@class='dib'][contains(., 'Compare Record Counts - Dynamic SQL')]"), 10);
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
//	public void clickOnWorkspaceField123() {
//		PageUtil.waitForPageToLoad(driver, 20);
//		PageUtil.waitForElement(driver, 10);
//		// PageUtil.waitForTheElementToBeClickable(driver,
//		// By.xpath("//span[./input[contains(@class, 'e-input')]]"), // "Click the
//		// Workspace field"); PageUtil.clickElementSafely(driver, selectWorkspace,
//		// "Click the Workspace field"); PageUtil.waitForElement(driver, 20); //
//		PageUtil.waitForTheElementToBeVisible(driver, By.xpath("//span[./input[contains(@class, 'e-input')]]"),
//				"Workspace");
//		PageUtil.clickOnElement(driver, By.xpath("//span[./input[contains(@class, 'e-input')]]"),
//				"workspace dropdown field.");
//		PageUtil.waitForTheElementToBeClickable(driver,
//				By.xpath("//span[./input[contains(@class, 'e-input')]], 'Test_Generator-WIP')]"),
//				"The Workspace selected");
//		PageUtil.waitForElement(driver, 10);
//		PageUtil.waitForTheElementToBeVisible(driver, selectSearchWorkspace, "The Workspaceselected");
//		PageUtil.clickElementSafely(driver, selectSearchWorkspace, "The Workspaceselected");
//		PageUtil.waitForElement(driver, 10);
//		PageUtil.waitForTheElementToBeVisible(driver, selectSearchWorkspace, "Select the workspce field");
//		PageUtil.sendkeysToElement(driver, selectSearchWorkspace, "select workspace field",
//				"Test_Generator-WIP" + Keys.ENTER);
//		// PageUtil.clickOnElement(driver,
//		// By.xpath("//ul[@id='ej2_dropdownlist_7_options']/li[1]"), "workspace
//		// name.."); PageUtil.waitForElement(driver, 50);
//		System.out.println("The worspace is selected...");
//	}
	
	public void clickOnWorkspaceField123() {
//	    // Wait for page and base elements
//	    PageUtil.waitForPageToLoad(driver, 20);
//	    PageUtil.waitForElement(driver, 10);
//
//	    // Wait for workspace field to be visible and clickable
//	    By workspaceField = By.xpath("//span[./input[contains(@class, 'e-input')]]");
//	    PageUtil.waitForTheElementToBeVisible(driver, workspaceField, "Workspace field");
//	    PageUtil.waitForTheElementToBeClickable(driver, workspaceField, "Workspace field clickable");
//
//	    // Click workspace dropdown
//	    PageUtil.clickOnElement(driver, workspaceField, "Workspace dropdown field");
//	    PageUtil.waitForElement(driver, 5);
//
//	    // Locate workspace search input field
//	    By workspaceSearchField = selectSearchWorkspace; // assuming this is already defined
//	    PageUtil.waitForTheElementToBeVisible(driver, workspaceSearchField, "Workspace search field");
//
//	    // Type and select workspace name
//	    String workspaceName = "Test_Generator-WIP";
//	    PageUtil.sendkeysToElement(driver, workspaceSearchField, "Select workspace field", workspaceName + Keys.ENTER);
//	    PageUtil.waitForElement(driver, 5);
	    
	    ////
		
	 // Wait for workspace field to be visible and clickable
	    PageUtil.clickOnElement(driver, By.xpath("//span[./input[contains(@class, 'e-input')]]"), 10);
	 // Click workspace dropdown
	    PageUtil.clickOnElement(driver, selectSearchWorkspace, 10);
	 // Click workspace dropdown  // Click workspace dropdown 
	    String workspaceName = "Test_Generator-WIP";
	    PageUtil.sendkeysToElement(driver, selectSearchWorkspace, "Select workspace field", workspaceName + Keys.ENTER);

	    System.out.println("Workspace '" + workspaceName + "' is selected successfully.");
	}


	// Click on the Folder selector field

	public void clickOnFolderField() throws InterruptedException {
//		PageUtil.waitForElement(driver, 20);
//		PageUtil.clickOnElement(driver,
//				By.xpath("//div[@class='dropdown__trigger' and following-sibling::div[@id='pathtree']]"),
//				"Click the Folder field");
//		PageUtil.waitForPageToLoad(driver, 10);
//		PageUtil.waitForElement(driver, 20);
//		PageUtil.sendkeysToElement(driver,
//				By.xpath("//div[@class='dropdown__search-group e-input-group']/input[@id='searchFolder']"),
//				"searching folder name.", "MJ_DND");
//		PageUtil.waitForPageToLoad(driver, 10);
//		PageUtil.waitForElement(driver, 20);
//		PageUtil.clickOnElement(driver, By.xpath("//div[@id='pathtree']//span[@title='Search']"),
//				"search folder name.");
//		PageUtil.waitForPageToLoad(driver, 10);
//		PageUtil.waitForElement(driver, 20);
//		PageUtil.clickElementSafely(driver, selectSearchFolder, "select the folder.");
//		PageUtil.clickOnElement(driver, selectSearchFolder, "select the folder.");
		
		////
		
		PageUtil.clickOnElement(driver, By.xpath("//label[text()='Folder']//following::button"), 30);
		PageUtil.sendkeysToElement(driver,
				By.xpath("//div[@class='dropdown__search-group e-input-group']/input[@id='searchFolder']"),
				"searching folder name.", "MJ_DND");
		PageUtil.clickOnElement(driver, By.xpath("//div[@id='pathtree']//span[@title='Search']"), 30);
		PageUtil.clickOnElement(driver, selectSearchFolder, 10);
		
		System.out.println("The folder is selected...");
	}
	
//	public void clickOnFolderField() throws InterruptedException {
//	    // Wait for the base page to load
//	    PageUtil.waitForPageToLoad(driver, 20);
//	    PageUtil.waitForElement(driver, 10);
//
//	    // Define locators clearly
//	    By folderDropdown = By.xpath("//div[@class='dropdown__trigger' and following-sibling::div[@id='pathtree']]");
//	    By searchInput = By.xpath("//div[@class='dropdown__search-group e-input-group']/input[@id='searchFolder']");
//	    By searchButton = By.xpath("//div[@id='pathtree']//span[@title='Search']");
//
//	    String folderName = "MJ_DND";
//
//	    // Click folder dropdown
//	    PageUtil.waitForTheElementToBeClickable(driver, folderDropdown, "Folder dropdown");
//	    PageUtil.clickOnElement(driver, folderDropdown, "Click the folder field");
//	    PageUtil.waitForElement(driver, 5);
//
//	    // Enter folder name into search input
//	    PageUtil.waitForTheElementToBeVisible(driver, searchInput, "Search folder input");
//	    PageUtil.sendkeysToElement(driver, searchInput, "Enter folder name", folderName);
//	    PageUtil.waitForElement(driver, 2);
//
//	    // Click search icon
//	    PageUtil.waitForTheElementToBeClickable(driver, searchButton, "Folder search button");
//	    PageUtil.clickOnElement(driver, searchButton, "Click search button");
//	    PageUtil.waitForPageToLoad(driver, 10);
//
//	    // Wait and select the folder from search results
//	    PageUtil.waitForTheElementToBeVisible(driver, selectSearchFolder, "Folder search result");
//	    PageUtil.clickElementSafely(driver, selectSearchFolder, "Select the folder");
//
//	    System.out.println("The folder '" + folderName + "' is selected successfully.");
//	}


//	public void gotoWorkspaceNextbtn() {
//		PageUtil.waitForTheElementToBeVisible(driver, By.xpath("//button[@id='workspaceNextbtn']"), "Next button");
//		PageUtil.clickOnElement(driver, By.xpath("//button[@id='workspaceNextbtn']"), "Next button");
//		PageUtil.waitForElement(driver, 20);
//	}
//
//	public void gotoDAtaMetadataNextbtn() {
//		PageUtil.waitForPageToLoad(driver, 50);
//		PageUtil.waitForTheElementToBeVisible(driver, By.xpath("//button[@id='metadataNextbtn']"), "Next button");
//		PageUtil.clickOnElement(driver, By.xpath("//button[@id='metadataNextbtn']"), "Next button");
//		PageUtil.waitForElement(driver, 20);
//	}
//
//	public void gotoRuleMetadataNextbtn() {
//		PageUtil.waitForPageToLoad(driver, 50);
//		PageUtil.waitForTheElementToBeVisible(driver, By.xpath("//button[@id='metadataNextbtn']"), "Next button");
//		PageUtil.clickOnElement(driver, By.xpath("//button[@id='metadataNextbtn']"), "Next button");
//		PageUtil.waitForElement(driver, 20);
//	}
//
//	public void gotoCheckMetadataNextbtn() {
//		PageUtil.waitForPageToLoad(driver, 50);
//		PageUtil.waitForTheElementToBeVisible(driver, By.xpath("//button[@id='checkNextbtn']"), "Next button");
//		PageUtil.clickOnElement(driver, By.xpath("//button[@id='checkNextbtn']"), "Next button");
//		PageUtil.waitForElement(driver, 20);
//	}
//
//	public void gotoNotificationNextbtn() {
//		PageUtil.waitForPageToLoad(driver, 50);
//		PageUtil.waitForTheElementToBeVisible(driver, By.xpath("//button[@id='notificationNextbtn']"), "Next button");
//		PageUtil.clickOnElement(driver, By.xpath("//button[@id='notificationNextbtn']"), "Next button");
//		PageUtil.waitForElement(driver, 20);
//	}
//
//	public void seectConnection() {
//		PageUtil.waitForPageToLoad(driver, 50);
//		PageUtil.waitForTheElementToBeVisible(driver, By.xpath("//ejs-dropdownlist[@id='ej2_dropdownlist_27']/span[1]"),
//				"Next button");
//		PageUtil.clickOnElement(driver, By.xpath("//ejs-dropdownlist[@id='ej2_dropdownlist_27']/span[1]"),
//				"Next button");
//		// Wait
//		PageUtil.waitForTheElementToBeVisible(driver, By.xpath("//ul[@id='ej2_dropdownlist_27_options']/*[2]"),
//				"Next butto	n");
//		PageUtil.clickOnElement(driver, By.xpath("//ul[@id='ej2_dropdownlist_27_options']/*[2]"), "Next button");
//		PageUtil.waitForElement(driver, 20);
//	}

}
