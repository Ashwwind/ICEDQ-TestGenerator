package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import base.Base;
import utils.PageUtil;

public class TestGeneratorPage extends Base {
	private WebDriver driver;

	// Locator
	By testGeneratorModue = PageUtil.getElementLocator(prop.getProperty("home.testgenerator"));
	By templateSearch = PageUtil.getElementLocator(prop.getProperty("searchfield.tempatename"));
	By selectWorkspace = PageUtil.getElementLocator(prop.getProperty("select.Workspace"));
	By selectSearchWorkspace = PageUtil.getElementLocator(prop.getProperty("select.searchedWorspace"));
	By selectSearchFolder = PageUtil.getElementLocator(prop.getProperty("select.searchedfolder"));
	By loader = PageUtil.getElementLocator(prop.getProperty("loderIsDisplayed"));

	
	///
	
//	By testGeneratorModue = By.xpath("//button[.//h2[text()='Test Generator']]");
//	By templateSearch = By.xpath("//input[contains(@placeholder,'Search template')]");
//	By selectWorkspace = By.xpath("//span[./input[contains(@class, 'e-input')]]");
//	By selectSearchWorkspace = By.xpath("//input[@class='e-input-filter e-input e-lib e-keyboard']");
//	By selectSearchFolder = By.xpath("//button[@aria-label=\"Select folder\"]");
//	By loader = By.xpath("//div[@id=\"sp-container\"]//div[@class='e-spinner-pane e-spin-show']//div");

	public TestGeneratorPage(WebDriver driver) {
		this.driver = driver;

	}

	// Page method
	public void clickTestGenerator() {
		PageUtil.isInvisibleLoader(driver, loader);
		PageUtil.clickOnElement(driver, testGeneratorModue, 10);
	}

	// Click the Checksum rule type from the wizard page.
	public void clickOnChecksum() {
		PageUtil.isInvisibleLoader(driver, loader);
		PageUtil.clickOnElement(driver, By.xpath("//h6[text()='Checksum']"), 10);
		PageUtil.isInvisibleLoader(driver, loader);
	}

	// Click on the search field box
	public void clickOnSearchField() {
		PageUtil.isInvisibleLoader(driver, loader);
		PageUtil.clickOnElement(driver, templateSearch, 10);
	}

	// Enter the data on the search field
	public void enterOnSearchField() {
		PageUtil.sendkeysToElement(driver, By.xpath("//input[contains(@placeholder,'Search template')]"),
				"Search template", "Compare Record Counts - Dynamic SQL");
		PageUtil.clickOnElement(driver, By.xpath("//*[@title='Search']"), 10);
	}

	// Select the existing checksum template form the list
	public void selectExistingChecksumTemplate() {
		PageUtil.isInvisibleLoader(driver, loader);
		PageUtil.clickOnElement(driver, By.xpath(
				"//td[@class='e-rowcell e-templatecell e-lastrowcell']/span[@class='dib'][contains(., 'Compare Record Counts - Dynamic SQL')]"),
				10);
	}

	public void clickOnWorkspaceField() {
		PageUtil.isInvisibleLoader(driver, loader);
		// Wait for workspace field to be visible and clickable
		PageUtil.clickOnElement(driver, By.xpath("//span[./input[contains(@class, 'e-input')]]"), 10);
		// Click workspace dropdown
		PageUtil.clickOnElement(driver, selectSearchWorkspace, 10);
		// Click workspace dropdown // Click workspace dropdown
		String workspaceName = "Test_Generator-WIP";
		PageUtil.sendkeysToElement(driver, selectSearchWorkspace, "Select workspace field", workspaceName + Keys.ENTER);
		PageUtil.isInvisibleLoader(driver, loader);
		System.out.println("Workspace '" + workspaceName + "' is selected successfully.");
	}

	// Click on the Folder selector field

	public void clickOnFolderField() throws InterruptedException {
		PageUtil.isInvisibleLoader(driver, loader);
		PageUtil.clickOnElement(driver, By.xpath("//button[@aria-label='Folder']//parent::div"), 30);
		PageUtil.sendkeysToElement(driver,
				By.xpath("//div[@class='dropdown__search-group e-input-group']/input[@id='searchFolder']"),
				"searching folder name.", "MJ_DND");
		PageUtil.clickOnElement(driver, By.xpath("//div[@id='pathtree']//span[@title='Search']"), 30);
		PageUtil.isInvisibleLoader(driver, loader);
		PageUtil.clickOnElement(driver, By.xpath("//div[contains(@class, 'e-text-content')]"), 10);
		PageUtil.clickOnElement(driver, By.xpath("//span[@class='e-icons e-check']//parent::button"), 10);

		System.out.println("The folder is selected...");
	}

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
//	public void selectConnection() {
//		PageUtil.waitForPageToLoad(driver, 50);
	
	/// Click on the source connection type.
//		PageUtil.clickOnElement(driver, By.xpath("//span[@aria-label="dropdownlist"]/parent::*[@id="ej2_dropdownlist_45"]"),"the source connection type.");
	
	/// Select the source connection type as database.
//		PageUtil.clickOnElement(driver, By.xpath("//li[text()='Database']"),"the database connection type.");
//		
	/// Select the connection.
	//PageUtil.clickOnElement(driver, By.xpath("//li[text()='Database']") ,"the database connection type.");
	
	// Entering the connection name
	//PageUtil.sendkeysToElement(driver, By.xpath("//div[@id=\"ej2_dropdownlist_161_popup\"]//input[@type=\"text\"]"),"", "postgreSQL"  +  Keys.ENTER);
	
	
	// Click  on the schema dropdown
	//PageUtil.clickOnElement(driver, By.xpath("//span[@aria-label="dropdownlist"]/parent::*[@id="ej2_dropdownlist_162"]") ,"the schema.");
	
	// Entering the schema name
	//PageUtil.sendkeysToElement(driver, By.xpath("//div[@id="ej2_dropdownlist_162_popup"]//input[@type="text"]"), "", "public"  +  Keys.ENTER);
	
	/////////////////////////////////////////
	
	/// Click on the target connection type.
//	PageUtil.clickOnElement(driver, By.xpath("//span[@aria-label="dropdownlist"]/parent::*[@id="ej2_dropdownlist_45"]"),"the target connection type.");

/// Select the target connection type as database.
//	PageUtil.clickOnElement(driver, By.xpath("//li[text()='Database']"),"the database connection type.");
//	
/// Select the connection.
//PageUtil.clickOnElement(driver, By.xpath("//li[text()='Database']") ,"the database connection type.");

// Entering the connection name
//PageUtil.sendkeysToElement(driver, By.xpath("//div[@id=\"ej2_dropdownlist_161_popup\"]//input[@type=\"text\"]"),"", "postgreSQL"  +  Keys.ENTER);


// Click  on the schema dropdown
//PageUtil.clickOnElement(driver, By.xpath("//span[@aria-label="dropdownlist"]/parent::*[@id="ej2_dropdownlist_162"]") ,"the schema.");

// Entering the schema name
//PageUtil.sendkeysToElement(driver, By.xpath("//div[@id="ej2_dropdownlist_162_popup"]//input[@type="text"]"), "", "public"  +  Keys.ENTER);

//	}
	
	///  Click on thre 'Select Dataset' next button.
//	public void gotoDatasetNextbtn() {
//		PageUtil.waitForPageToLoad(driver, 50);
//		PageUtil.waitForTheElementToBeVisible(driver, By.xpath("//button[@id='datasetNextbtn']"), "Next button");
//		PageUtil.clickOnElement(driver, By.xpath("//button[@id='notificationNextbtn']"), "Next button");
//		PageUtil.waitForElement(driver, 20);
//	}

	////
	
	//Click on the available table search field
	
	//PageUtil.clickOnElement(driver, By.xpath("//input[@placeholder="Search available table"]") ,"the search available table field.");
	
	// Entering the available table name
	//PageUtil.sendkeysToElement(driver, By.xpath("//input[@placeholder="Search available table"]"), "", "staff"  +  Keys.ENTER);
	
	
	// Select the table checkbox from the list.
	//PageUtil.clickOnElement(driver, By.xpath("//ul//li[@id="staff"]") ,"the table name from the list.");
	
	
	// Click on the 'Movee to' button
	//PageUtil.clickOnElement(driver, By.xpath("//button[@aria-label="Move To"]") ,"the table name from the list.");
	
	
}
