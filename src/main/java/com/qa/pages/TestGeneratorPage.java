package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import com.qa.base.Base;
import com.qa.utils.PageUtil;

public class TestGeneratorPage extends Base {
	private WebDriver driver;
	// Scrolling

	// Locator
	By testGeneratorModue = PageUtil.getElementLocator(prop.getProperty("home.testgenerator"));
	By selectChecksumWizard = PageUtil.getElementLocator(prop.getProperty("wizard.checksumRule"));
	By clickSearchTemplateField = PageUtil.getElementLocator(prop.getProperty("wizard.searchfield.tempatename"));
	By enterTemplateName = PageUtil.getElementLocator(prop.getProperty("wizard.searchfield.enter.tempatename"));
	By clickSearchButtonIcon = PageUtil.getElementLocator(prop.getProperty("wizard.searchfield.searchButtonIcon"));
	By selectExistingDynamicChecksumTemplate = PageUtil.getElementLocator(prop.getProperty("wizard.existing.dymanic.template"));
	By clickOnWorkspaceDropdown = PageUtil.getElementLocator(prop.getProperty("wizard.workspace.dropdown"));
	By selectWorkspace = PageUtil.getElementLocator(prop.getProperty("select.Workspace"));
	By selectSearchWorkspace = PageUtil.getElementLocator(prop.getProperty("select.searchedWorspace"));
	By clickOnFolderDropdown = PageUtil.getElementLocator(prop.getProperty("wizard.folder.dropdown"));
	By searchFolderName =  PageUtil.getElementLocator(prop.getProperty("wizard.search.folderName"));

	By selectSearchFolder = PageUtil.getElementLocator(prop.getProperty("select.searchedfolder"));
	By loader = PageUtil.getElementLocator(prop.getProperty("loderIsDisplayed"));
	By sourceSchemaDropdown = PageUtil.getElementLocator(prop.getProperty("source.schema.dropdown"));
	By targetSchemaDropdown = PageUtil.getElementLocator(prop.getProperty("target.schema.dropdown"));

	public TestGeneratorPage(WebDriver driver) {
		this.driver = driver;
	}

	public void createChecksumRules(String templateName) {
		clickTestGenerator();
		clickOnChecksum();

	}

	// Page method
	public void clickTestGenerator() {
		PageUtil.isInvisibleLoader(driver, loader);
		PageUtil.clickOnElement(driver, testGeneratorModue, 30);
	}

	// Click the Checksum rule type from the wizard page.
	public void clickOnChecksum() {
		PageUtil.isInvisibleLoader(driver, loader);
		PageUtil.clickOnElement(driver, selectChecksumWizard, 30);
	}

	// Click on the search field box
	public void clickOnSearchField() {
		PageUtil.isInvisibleLoader(driver, loader);
		PageUtil.clickOnElement(driver, clickSearchTemplateField, 30);
	}

	// Enter the data on the search field
	public void enterOnSearchField() {
		PageUtil.sendkeysToElement(driver, enterTemplateName, "Search template", "Compare Record Counts - Dynamic SQL");
		PageUtil.clickOnElement(driver, clickSearchButtonIcon, 30);
	}

	// Select the existing checksum template form the list
	public void selectExistingChecksumTemplate() {
		PageUtil.isInvisibleLoader(driver, loader);
		PageUtil.clickOnElement(driver, selectExistingDynamicChecksumTemplate, 30);
	}

	public void clickOnWorkspaceField() {
		PageUtil.isInvisibleLoader(driver, loader);
		// Wait for workspace field to be visible and clickable
		PageUtil.clickOnElement(driver, clickOnWorkspaceDropdown, 30);
		// Click workspace dropdown
		PageUtil.clickOnElement(driver, selectSearchWorkspace, 30);
		// Click workspace dropdown // Click workspace dropdown
		String workspaceName = "Test_Generator-WIP";
		PageUtil.sendkeysToElement(driver, selectSearchWorkspace, "Select workspace field", workspaceName + Keys.ENTER);
		PageUtil.isInvisibleLoader(driver, loader);
		
		System.out.println("Workspace '" + workspaceName + "' is selected successfully.");
	}

	// Click on the Folder selector field

	public void clickOnFolderField() throws InterruptedException {
		PageUtil.isInvisibleLoader(driver, loader);
		PageUtil.clickOnElement(driver, clickOnFolderDropdown, 30);
		PageUtil.sendkeysToElement(driver, searchFolderName, "searching folder name.", "MJ_DND");
		PageUtil.clickOnElement(driver, By.xpath("//div[@id='pathtree']//span[@title='Search']"), 30);
		PageUtil.isInvisibleLoader(driver, loader);
		PageUtil.clickOnElement(driver, By.xpath("//div[contains(@class, 'e-text-content')]"), 10);
		PageUtil.clickOnElement(driver, By.xpath("//span[@class='e-icons e-check']//parent::button"), 10);

		System.out.println("The folder is selected...");
	}

	public void gotoWorkspaceNextbtn() {
		PageUtil.clickOnElement(driver, By.xpath("//button[@id='workspaceNextbtn']"), 20);
	}

	public void gotoRuleMetadataNextbtn() {
		PageUtil.clickOnElement(driver, By.xpath("//button[@id='metadataNextbtn']"), 20);
	}

	public void gotoCheckMetadataNextbtn() {
		PageUtil.clickOnElement(driver, By.xpath("//button[@id='checkNextbtn']"), 20);
	}

	public void gotoNotificationNextbtn() {
		PageUtil.clickOnElement(driver, By.xpath("//button[@id='notificationNextbtn']"), 20);
	}

	/// ********* Select the source dataset ********** ///

///// *******************************/////
/////////////// ********************************* ////////////////
///// *******************************/////
	public void selectionSourceDataset() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Click on the source connection type.
		PageUtil.clickOnElement(driver, By.xpath(
				"(//div[contains(text(),'Source Dataset')]//parent::form//following::ejs-dropdownlist[@placeholder='Select connection type']//span[@formcontrolname='connectionType'])[1]"),
				10);

		// Select "Database"
		PageUtil.clickOnElement(driver, By.xpath("//li[text()='Database']"), 20);

		// Click Source Connection
		try {
			PageUtil.clickOnElement(driver, By.xpath(
					"(//div[contains(text(),'Source Dataset')]//parent::form//following::ejs-dropdownlist[@placeholder='Select connection']//span[@formcontrolname='connectionId'])[1]"),
					20);
			System.out.println("source connection is clickable");
		} catch (Exception e) {
			System.out.println("source connection is not clickable");
		}

		// Enter connection name
		try {
			PageUtil.sendkeysToElement(driver, By.xpath(
					"//*[@placeholder='Select connection']/following::div[contains(@class,'e-popup')]//input[@type='text']"),
					"source connection name", "postgreSQL");
			PageUtil.sendkeysToEnter(driver, By.xpath(
					"//*[@placeholder='Select connection']/following::div[contains(@class,'e-popup')]//input[@type='text']"),
					"source connection name");
			System.out.println("the postgresql source connection is clickable");
		} catch (Exception e) {
			System.out.println("the postgresql source connection is not clickable");
		}

		// Scroll & click schema dropdown
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
				driver.findElement(sourceSchemaDropdown));

		PageUtil.clickOnElement(driver, sourceSchemaDropdown, 20);

		// Enter schema name
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
				driver.findElement(By.xpath(
						"//ejs-dropdownlist[@placeholder='Choose Schema']/following::div[contains(@class,'e-popup')]//input[@type='text']")));

		PageUtil.sendkeysToElement(driver, By.xpath(
				"//ejs-dropdownlist[@placeholder='Choose Schema']/following::div[contains(@class,'e-popup')]//input[@type='text']"),
				"source schema name", "public");

		PageUtil.sendkeysToEnter(driver, By.xpath(
				"//ejs-dropdownlist[@placeholder='Choose Schema']/following::div[contains(@class,'e-popup')]//input[@type='text']"),
				"source schema name");

	}

	/// ********* Select the target dataset ********** ///

///// *******************************/////
/////////////// ********************************* ////////////////
///// *******************************/////
	public void selectionTargetDataset() throws InterruptedException {

		/// Click on the target connection type.
		PageUtil.clickOnElement(driver, By.xpath(
				"(//div[contains(text(),'Source Dataset')]//parent::form//following::ejs-dropdownlist[@placeholder='Select connection type']//span[@formcontrolname='connectionType'])[2]"),
				10);

		// Select the target connection type as database.
		PageUtil.clickOnElement(driver, By.xpath("//li[text()='Database']"), 20);

		// Click the target connection.
		PageUtil.clickOnElement(driver, By.xpath(
				"(//div[contains(text(),'Source Dataset')]//parent::form//following::ejs-dropdownlist[@placeholder='Select connection']//span[@formcontrolname='connectionId'])[2]"),
				20);

		// Entering the target connection name
		PageUtil.sendkeysToElement(driver, By.xpath(
				"//ejs-dropdownlist[@placeholder='Select connection']/following::div[contains(@class,'e-popup')]//input[@type='text']"),
				"target connection name", "postgreSQL");

		PageUtil.sendkeysToEnter(driver, By.xpath(
				"//ejs-dropdownlist[@placeholder='Select connection']/following::div[contains(@class,'e-popup')]//input[@type='text']"),
				"target connection name");

		// Scroll & click schema dropdown
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
				driver.findElement(targetSchemaDropdown));

		PageUtil.clickOnElement(driver, targetSchemaDropdown, 20);

		// Enter schema name
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
				driver.findElement(By.xpath(
						"//ejs-dropdownlist[@placeholder='Choose Schema']/following::div[contains(@class,'e-popup')]//input[@type='text']")));

		// Entering the schema name.
		PageUtil.sendkeysToElement(driver, By.xpath(
				"//ejs-dropdownlist[@placeholder='Choose Schema']/following::div[contains(@class,'e-popup')]//input[@type='text']"),
				"target schema name", "public");
		// Click the schema name
		PageUtil.sendkeysToEnter(driver, By.xpath(
				"//ejs-dropdownlist[@placeholder='Choose Schema']/following::div[contains(@class,'e-popup')]//input[@type='text']"),
				"target schema name");

	}

	// Click on the next button of 'select dataset' page.
	public void gotoDatasetNextbtn() {
		PageUtil.clickOnElement(driver, By.xpath("//button[@id='datasetNextbtn']"), 20);
	}

	/// ********* Select the Available Table ********** ///.

	public void selectionAvailabeTable() {

		// Click on the available table search field
		PageUtil.clickOnElement(driver, By.xpath("//input[@placeholder='Search available table']"), 20);

		// Entering the available table name
		PageUtil.sendkeysToElement(driver, By.xpath("//input[@placeholder='Search available table']"),
				"staff table name", "staff");
		// Click to enter
		PageUtil.sendkeysToEnter(driver, By.xpath("//input[@placeholder='Search available table']"),
				"staff table name");

		// Select the table checkbox from the list.
		PageUtil.clickOnElement(driver, By.xpath("//ul//li[@id='staff']"), 20);

		// Click on the 'Move to' button
		PageUtil.clickOnElement(driver, By.xpath("//button[@aria-label='Move To']"), 20);
	}

	// Click on the next button of 'select table' page.

	public void gotoSelettableNextbtn() {
		PageUtil.clickOnElement(driver, By.xpath("//div[@id='e-content-element_6']//button[normalize-space()='Next']"),
				20);
	}

	// Click on the 'Generate' button.
	public void clickOnGenerate() {
		PageUtil.clickOnElement(driver, By.xpath("//button[@id='reviewNextbtn']"), 20);
	}

	// Click on the 'Go To Preview' button.
	public void clickOnGoToPreview() {
		PageUtil.clickOnElement(driver, By.xpath("//button[normalize-space()= 'Go To Preview']"), 20);
	}

	// Select the generated entity from the 'Preview' page.
	public void selectGeneratedEntity() {
		PageUtil.clickOnElement(driver,
				By.xpath("//tbody[@role='rowgroup']/tr/td/div/input[@type='checkbox']/following-sibling::span[1]"), 20);
	}

	// Click on the 'Publish' button.
	public void clickOnPublish() {
		PageUtil.clickOnElement(driver, By.xpath("//button[@title='Publish']"), 10);
	}

	// Click on the 'Go To Publish' button.
	public void clickOnGoToPublish() {
		try {
			PageUtil.clickOnElement(driver,
					By.xpath("//*[@id='publishLogMessageSidebar']//button[contains(text(),'Go To Publish')]"), 30);
			System.out.println("The Go To Publish button is clickable.");
		} catch (Exception e) {
			System.out.println("The Go To Publish button is not clickable.");
		}
	}

	// Click on the hyperlink of the published rule to navigate to the data testing.
	public void clickOnPublishedRule() {
		try {
			PageUtil.clickOnElement(driver, By.xpath("//tbody[@role='rowgroup']/tr/td/span/following-sibling::a[1]"),
					20);
			System.out.println("The published rule is clickable.");
		} catch (Exception e) {
			System.out.println("The published rule is not clickable.");
		}
	}
}
