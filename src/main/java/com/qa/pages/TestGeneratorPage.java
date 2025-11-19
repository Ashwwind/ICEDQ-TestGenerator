package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import com.qa.utils.PageUtil;

public class TestGeneratorPage extends PageUtil {
	private WebDriver driver;

	// Locator
	By testGeneratorModue = getElementLocator(prop.getProperty("home.testgenerator"));
	By selectChecksumWizard = getElementLocator(prop.getProperty("wizard.checksumRule"));
	By clickSearchTemplateField = getElementLocator(prop.getProperty("wizard.searchfield.tempatename"));
	By enterTemplateName = getElementLocator(prop.getProperty("wizard.searchfield.enter.tempatename"));
	By clickSearchButtonIcon = getElementLocator(prop.getProperty("wizard.searchfield.searchButtonIcon"));
	By selectExistingDynamicChecksumTemplate = getElementLocator(prop.getProperty("wizard.existing.dymanic.template"));
	By clickOnWorkspaceDropdown = getElementLocator(prop.getProperty("wizard.workspace.dropdown"));
	By selectWorkspace = getElementLocator(prop.getProperty("select.Workspace"));
	By selectSearchWorkspace = getElementLocator(prop.getProperty("select.searchedWorspace"));
	By clickOnFolderDropdown = getElementLocator(prop.getProperty("wizard.folder.dropdown"));
	By searchFolderName = getElementLocator(prop.getProperty("wizard.search.folderName"));
	By selectSearchFolder = getElementLocator(prop.getProperty("select.searchedfolder"));
	By clickNextButtonWorkspaceNextbtn = getElementLocator(prop.getProperty("wizard.click.nextButton.workspaceNextbtn"));
	By clickNextButtonmetadataNextbtn = getElementLocator(prop.getProperty("wizard.click.nextButton.metadataNextbtn"));
	By clickNextButtoncheckNextbtn = getElementLocator(prop.getProperty("wizard.click.nextButton.checkNextbtn"));
	By clickNextButtonnotificationNextbtn = getElementLocator(prop.getProperty("wizard.click.nextButton.notificationNextbtn"));
	
	// Source Dataset
	By clickSourcedatasetConnectionTypeDropdown = getElementLocator(prop.getProperty("wizard.click.sourceConnectionTypeDropdown"));
	By clickSourcedatasetConnectionDropdown = getElementLocator(prop.getProperty("wizard.click.sourceConnectionDropdown"));
	By enterSourceConnectionName = getElementLocator(prop.getProperty("wizard.source.connection.input"));
	By clickSourceSchemaDropdown = getElementLocator(prop.getProperty("wizard.source.schema.dropdown"));
	By enterSourceSchemaName = getElementLocator(prop.getProperty("wizard.source.schema.input"));
	
	// Target Dataset
	By clickTargetdatasetConnectionTypeDropdown = getElementLocator(prop.getProperty("wizard.click.targetConnectionTypeDropdown"));
	By clickTargetdatasetConnectionDropdown = getElementLocator(prop.getProperty("wizard.click.targetConnectionDropdown"));
	By enterTargetConnectionName = getElementLocator(prop.getProperty("wizard.target.connection.input"));
	By clickTargetSchemaDropdown = getElementLocator(prop.getProperty("wizard.target.schema.dropdown"));
	By enterTargetSchemaName = getElementLocator(prop.getProperty("wizard.target.schema.input"));
	
	
	By loader = getElementLocator(prop.getProperty("loderIsDisplayed"));
	
	By targetSchemaDropdown = getElementLocator(prop.getProperty("target.schema.dropdown"));

	public TestGeneratorPage(WebDriver driver) {
		this.driver = driver;
	}

	public void createChecksumRules(String templateName) throws InterruptedException {
		clickTestGenerator();
		clickOnChecksum();

		clickOnSearchField();
		enterOnSearchField();
		selectExistingChecksumTemplate();

		clickOnWorkspaceField();
		clickOnFolderField();
		gotoWorkspaceNextbtn();
		gotoRuleMetadataNextbtn();
		gotoCheckMetadataNextbtn();
		gotoNotificationNextbtn();
		selectionSourceDataset();
		selectionTargetDataset();
		gotoDatasetNextbtn();
		selectionAvailabeTable();
		gotoSelettableNextbtn();
		clickOnGenerate();
		clickOnGoToPreview();
		clickOnPublishedRule();
		selectGeneratedEntity();
		clickOnGoToPublish();

	}

	// Page method
	public void clickTestGenerator() {
		isInvisibleLoader(driver, loader);
		clickOnElement(driver, testGeneratorModue, 30);
	}

	// Click the Checksum rule type from the wizard page.
	public void clickOnChecksum() {
		isInvisibleLoader(driver, loader);
		clickOnElement(driver, selectChecksumWizard, 30);
	}

	// Click on the search field box
	public void clickOnSearchField() {
		isInvisibleLoader(driver, loader);
		clickOnElement(driver, clickSearchTemplateField, 30);
	}

	// Enter the data on the search field
	public void enterOnSearchField() {
		sendkeysToElement(driver, enterTemplateName, "Search template", "Compare Record Counts - Dynamic SQL");
		clickOnElement(driver, clickSearchButtonIcon, 30);
	}

	// Select the existing checksum template form the list
	public void selectExistingChecksumTemplate() {
		isInvisibleLoader(driver, loader);
		clickOnElement(driver, selectExistingDynamicChecksumTemplate, 30);
	}

	public void clickOnWorkspaceField() {
		isInvisibleLoader(driver, loader);
		// Wait for workspace field to be visible and clickable
		clickOnElement(driver, clickOnWorkspaceDropdown, 30);
		// Click workspace dropdown
		clickOnElement(driver, selectSearchWorkspace, 30);
		// Click workspace dropdown // Click workspace dropdown
		String workspaceName = "Test_Generator-WIP";
		sendkeysToElement(driver, selectSearchWorkspace, "Select workspace field", workspaceName + Keys.ENTER);
		isInvisibleLoader(driver, loader);

		System.out.println("Workspace '" + workspaceName + "' is selected successfully.");
	}

	// Click on the Folder selector field

	public void clickOnFolderField() {
		isInvisibleLoader(driver, loader);
		clickOnElement(driver, clickOnFolderDropdown, 30);
		sendkeysToElement(driver, searchFolderName, "searching folder name.", "MJ_DND");
		clickOnElement(driver, By.xpath("//div[@id='pathtree']//span[@title='Search']"), 30);
		isInvisibleLoader(driver, loader);
		clickOnElement(driver, By.xpath("//div[contains(@class, 'e-text-content')]"), 10);
		clickOnElement(driver, By.xpath("//span[@class='e-icons e-check']//parent::button"), 10);

		System.out.println("The folder is selected...");
	}

	public void gotoWorkspaceNextbtn() {
		clickOnElement(driver, clickNextButtonWorkspaceNextbtn, 20);
	}

	public void gotoRuleMetadataNextbtn() {
		clickOnElement(driver, clickNextButtonmetadataNextbtn, 20);
	}

	public void gotoCheckMetadataNextbtn() {
		clickOnElement(driver, clickNextButtoncheckNextbtn, 20);
	}

	public void gotoNotificationNextbtn() {
		clickOnElement(driver, clickNextButtonnotificationNextbtn, 20);
	}

	/// ********* Select the source dataset ********** ///

///// *******************************/////
/////////////// ********************************* ////////////////
///// *******************************/////
	public void selectionSourceDataset() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Click on the source connection type.
		clickOnElement(driver, clickSourcedatasetConnectionTypeDropdown, 10);

		// Select "Database"
		clickOnElement(driver, By.xpath("//li[text()='Database']"), 20);

		// Click Source Connection
		try {
			clickOnElement(driver, clickSourcedatasetConnectionDropdown, 20);
			System.out.println("source connection is clickable");
		} catch (Exception e) {
			System.out.println("source connection is not clickable");
		}

		// Enter connection name
		try {
			sendkeysToElement(driver, enterSourceConnectionName, "source connection name", "postgreSQL");
			sendkeysToEnter(driver, enterSourceConnectionName, "source connection name");
			System.out.println("the postgresql source connection is clickable");
		} catch (Exception e) {
			System.out.println("the postgresql source connection is not clickable");
		}

		// Scroll & click schema dropdown
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
//				driver.findElement(sourceSchemaDropdown));

		clickOnElement(driver, clickSourceSchemaDropdown, 20);

		// Enter schema name
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
//				driver.findElement(By.xpath(
//						"//ejs-dropdownlist[@placeholder='Choose Schema']/following::div[contains(@class,'e-popup')]//input[@type='text']")));

		sendkeysToElement(driver, enterSourceSchemaName, "source schema name", "public");

		sendkeysToEnter(driver, enterSourceSchemaName, "source schema name");

	}

	/// ********* Select the target dataset ********** ///

///// *******************************/////
/////////////// ********************************* ////////////////
///// *******************************/////
	public void selectionTargetDataset() throws InterruptedException {

		/// Click on the target connection type.
		clickOnElement(driver, clickTargetdatasetConnectionTypeDropdown, 10);

		// Select the target connection type as database.
		clickOnElement(driver, By.xpath("//li[text()='Database']"), 20);

		// Click the target connection.
		clickOnElement(driver, By.xpath(
				"(//div[contains(text(),'Source Dataset')]//parent::form//following::ejs-dropdownlist[@placeholder='Select connection']//span[@formcontrolname='connectionId'])[2]"),
				20);

		// Entering the target connection name
		sendkeysToElement(driver, By.xpath(
				"//ejs-dropdownlist[@placeholder='Select connection']/following::div[contains(@class,'e-popup')]//input[@type='text']"),
				"target connection name", "postgreSQL");

		sendkeysToEnter(driver, By.xpath(
				"//ejs-dropdownlist[@placeholder='Select connection']/following::div[contains(@class,'e-popup')]//input[@type='text']"),
				"target connection name");

		// Scroll & click schema dropdown
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
				driver.findElement(targetSchemaDropdown));

		clickOnElement(driver, targetSchemaDropdown, 20);

		// Enter schema name
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
				driver.findElement(By.xpath(
						"//ejs-dropdownlist[@placeholder='Choose Schema']/following::div[contains(@class,'e-popup')]//input[@type='text']")));

		// Entering the schema name.
		sendkeysToElement(driver, By.xpath(
				"//ejs-dropdownlist[@placeholder='Choose Schema']/following::div[contains(@class,'e-popup')]//input[@type='text']"),
				"target schema name", "public");
		// Click the schema name
		sendkeysToEnter(driver, By.xpath(
				"//ejs-dropdownlist[@placeholder='Choose Schema']/following::div[contains(@class,'e-popup')]//input[@type='text']"),
				"target schema name");

	}

	// Click on the next button of 'select dataset' page.
	public void gotoDatasetNextbtn() {
		clickOnElement(driver, By.xpath("//button[@id='datasetNextbtn']"), 20);
	}

	/// ********* Select the Available Table ********** ///.

	public void selectionAvailabeTable() {

		// Click on the available table search field
		clickOnElement(driver, By.xpath("//input[@placeholder='Search available table']"), 20);

		// Entering the available table name
		sendkeysToElement(driver, By.xpath("//input[@placeholder='Search available table']"),
				"staff table name", "staff");
		// Click to enter
		sendkeysToEnter(driver, By.xpath("//input[@placeholder='Search available table']"),
				"staff table name");

		// Select the table checkbox from the list.
		clickOnElement(driver, By.xpath("//ul//li[@id='staff']"), 20);

		// Click on the 'Move to' button
		clickOnElement(driver, By.xpath("//button[@aria-label='Move To']"), 20);
	}

	// Click on the next button of 'select table' page.

	public void gotoSelettableNextbtn() {
		clickOnElement(driver, By.xpath("//div[@id='e-content-element_6']//button[normalize-space()='Next']"),
				20);
	}

	// Click on the 'Generate' button.
	public void clickOnGenerate() {
		clickOnElement(driver, By.xpath("//button[@id='reviewNextbtn']"), 20);
	}

	// Click on the 'Go To Preview' button.
	public void clickOnGoToPreview() {
		clickOnElement(driver, By.xpath("//button[normalize-space()= 'Go To Preview']"), 20);
	}

	// Select the generated entity from the 'Preview' page.
	public void selectGeneratedEntity() {
		clickOnElement(driver,
				By.xpath("//tbody[@role='rowgroup']/tr/td/div/input[@type='checkbox']/following-sibling::span[1]"), 20);
	}

	// Click on the 'Publish' button.
	public void clickOnPublish() {
		clickOnElement(driver, By.xpath("//button[@title='Publish']"), 10);
	}

	// Click on the 'Go To Publish' button.
	public void clickOnGoToPublish() {
		try {
			clickOnElement(driver,
					By.xpath("//*[@id='publishLogMessageSidebar']//button[contains(text(),'Go To Publish')]"), 30);
			System.out.println("The Go To Publish button is clickable.");
		} catch (Exception e) {
			System.out.println("The Go To Publish button is not clickable.");
		}
	}

	// Click on the hyperlink of the published rule to navigate to the data testing.
	public void clickOnPublishedRule() {
		try {
			clickOnElement(driver, By.xpath("//tbody[@role='rowgroup']/tr/td/span/following-sibling::a[1]"),
					20);
			System.out.println("The published rule is clickable.");
		} catch (Exception e) {
			System.out.println("The published rule is not clickable.");
		}
	}
}
