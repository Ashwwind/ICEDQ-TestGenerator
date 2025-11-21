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
	// Checksum Rule
	By selectChecksumWizard = getElementLocator(prop.getProperty("wizard.checksumRule"));
	// Recon Rule
	By selectReconWizard = getElementLocator(prop.getProperty("wizard.reconRule"));
	// Validation Rule
	By selectValidationWizard = getElementLocator(prop.getProperty("wizard.validationRule"));
	// Pushdown Rule
	By selectPushdownWizard = getElementLocator(prop.getProperty("wizard.pushdownRule"));

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
	By clickNextButtonWorkspaceNextbtn = getElementLocator(
			prop.getProperty("wizard.click.nextButton.workspaceNextbtn"));
	By clickNextButtonmetadataNextbtn = getElementLocator(prop.getProperty("wizard.click.nextButton.metadataNextbtn"));
	By clickNextButtoncheckNextbtn = getElementLocator(prop.getProperty("wizard.click.nextButton.checkNextbtn"));
	By clickNextButtonnotificationNextbtn = getElementLocator(
			prop.getProperty("wizard.click.nextButton.notificationNextbtn"));

	// Source Dataset
	By clickSourcedatasetConnectionTypeDropdown = getElementLocator(
			prop.getProperty("wizard.click.sourceConnectionTypeDropdown"));
	By clickSourcedatasetConnectionDropdown = getElementLocator(
			prop.getProperty("wizard.click.sourceConnectionDropdown"));
	By enterSourceConnectionName = getElementLocator(prop.getProperty("wizard.source.connection.input"));
	By clickSourceSchemaDropdown = getElementLocator(prop.getProperty("wizard.source.schema.dropdown"));
	By enterSourceSchemaName = getElementLocator(prop.getProperty("wizard.source.schema.input"));

	// Target Dataset
	By clickTargetdatasetConnectionTypeDropdown = getElementLocator(
			prop.getProperty("wizard.click.targetConnectionTypeDropdown"));
	By clickTargetdatasetConnectionDropdown = getElementLocator(
			prop.getProperty("wizard.click.targetConnectionDropdown"));
	By enterTargetConnectionName = getElementLocator(prop.getProperty("wizard.target.connection.input"));
	By clickTargetSchemaDropdown = getElementLocator(prop.getProperty("wizard.target.schema.dropdown"));
	By enterTargetSchemaName = getElementLocator(prop.getProperty("wizard.target.schema.input"));

	By clickNextButtondatasetNextbtn = getElementLocator(prop.getProperty("wizard.click.nextButton.datasetNextbtn"));

	// Available Table
	By clickAvailableTable = getElementLocator(prop.getProperty("availableTable.click"));
	By selectAvailableTable = getElementLocator(prop.getProperty("availableTable.select.table"));
	By clickMoveToButton = getElementLocator(prop.getProperty("availableTable.moveto.click"));
	By clickNextButtonSelettableNextbtn = getElementLocator(
			prop.getProperty("availableTable.click.nextButton.SelettableNextbtn"));

	// Click on Generate
	By clickGenerateButton = getElementLocator(prop.getProperty("generateButton.click"));
	// Click on Go To Publish
	By clickGoToPreviewButton = getElementLocator(prop.getProperty("goToPreviewButton.click"));
	// Select the generated entity for publish
	By selectEntity = getElementLocator(prop.getProperty("publishEntity.select"));
	// Click on publish button to publish
	By clikPublishButton = getElementLocator(prop.getProperty("published.click"));
	// Click on Go To Publish
	By clickGoToPublishButton = getElementLocator(prop.getProperty("goToPublishButton.click"));
	// Click on hyperlink of publish entity
	By clickHyperlinkPublishRule = getElementLocator(prop.getProperty("publishRuleHyperlink.click"));

	By loader = getElementLocator(prop.getProperty("loderIsDisplayed"));

	// By targetSchemaDropdown =
	// getElementLocator(prop.getProperty("target.schema.dropdown"));

	public TestGeneratorPage(WebDriver driver) {
		this.driver = driver;
	}

	public void createRule(String ruleType, String templateName, String workspaceName, String folderName,
			String connectionName, String schemaName, String tableName) throws InterruptedException {

		// 1. Navigate to Test Generator → Checksum Wizard
		clickTestGenerator();

		if (ruleType.equalsIgnoreCase("checksum")) {
			clickOnChecksum();
		} else if (ruleType.equalsIgnoreCase("recon")) {
			clickOnRecon(); // implement this
		} else if (ruleType.equalsIgnoreCase("validation")) {
			clickOnValidation(); // implement this
		} else if (ruleType.equalsIgnoreCase("pushdown")) {
			clickOnPushdown(); // implement this
		} else {
			throw new IllegalArgumentException("Invalid Rule Type: " + ruleType);
		}

		// 2. Search & Select Template
		clickOnSearchField();
		enterOnSearchField(templateName); // UPDATED WITH PARAMETER
		selectExistingChecksumTemplate();

		// 3. Select Workspace
		clickOnWorkspaceField(workspaceName); // UPDATED WITH PARAMETER

		// 4. Select Folder
		clickOnFolderField(folderName);

		// Next Buttons
		gotoWorkspaceNextbtn();
		gotoRuleMetadataNextbtn();
		gotoCheckMetadataNextbtn();
		gotoNotificationNextbtn();

		// 5. Select Source Dataset
		selectionSourceDataset(connectionName, "public");

		// 6. Select Target Dataset
		selectionTargetDataset(connectionName, "public");

		// Next Buttons
		gotoDatasetNextbtn();

		// 7. Select Table
		selectionAvailabeTable(tableName);

		// Next Buttons
		gotoSelettableNextbtn();

		// 8. Generate → Preview → Publish
		clickOnGenerate();
		clickOnGoToPreview();
		selectGeneratedEntity();
		clickOnPublish();
		clickOnGoToPublish();
		clickOnPublishedRule();

	}

	// Page method
	public void clickTestGenerator() {
		isInvisibleLoader(driver, loader);
		validateElementIsVisible(driver, testGeneratorModue, "Test Generator", true);
		clickOnElement(driver, testGeneratorModue, 30);
	}

	// Click the Checksum rule type from the wizard page.
	public void clickOnChecksum() {
		isInvisibleLoader(driver, loader);
		validateElementIsVisible(driver, selectChecksumWizard, "Checksum", true);
		clickOnElement(driver, selectChecksumWizard, 30);
	}

	// Click the Recon rule type from the wizard page.
	public void clickOnRecon() {
		isInvisibleLoader(driver, loader);
		validateElementIsVisible(driver, selectReconWizard, "Recon", true);
		clickOnElement(driver, selectChecksumWizard, 30);
	}

	// Click the Validation rule type from the wizard page.
	public void clickOnValidation() {
		isInvisibleLoader(driver, loader);
		validateElementIsVisible(driver, selectValidationWizard, "Validation", true);
		clickOnElement(driver, selectChecksumWizard, 30);
	}

	// Click the Pushdown rule type from the wizard page.
	public void clickOnPushdown() {
		isInvisibleLoader(driver, loader);
		validateElementIsVisible(driver, selectPushdownWizard, "Pushdown", true);
		clickOnElement(driver, selectChecksumWizard, 30);
	}

	// Click on the search field box
	public void clickOnSearchField() {
		isInvisibleLoader(driver, loader);
		validateElementIsVisible(driver, clickSearchTemplateField, "Search template", true);
		clickOnElement(driver, clickSearchTemplateField, 30);
	}

	// Enter the data on the search field
	public void enterOnSearchField(String templateName) {
		sendkeysToElement(driver, enterTemplateName, "Search template", templateName);
		validateElementIsVisible(driver, clickSearchButtonIcon, "Search button icon on search template ", true);
		clickOnElement(driver, clickSearchButtonIcon, 30);
	}

	// Select the existing checksum template form the list
	public void selectExistingChecksumTemplate() {
		isInvisibleLoader(driver, loader);
		validateElementIsVisible(driver, selectExistingDynamicChecksumTemplate, "The searched template", true);
		clickOnElement(driver, selectExistingDynamicChecksumTemplate, 30);
	}

	public void clickOnWorkspaceField(String workspaceName) {
		isInvisibleLoader(driver, loader);
		// Wait for workspace field to be visible and clickable
		clickOnElement(driver, clickOnWorkspaceDropdown, 30);
		// Click workspace dropdown
		clickOnElement(driver, selectSearchWorkspace, 30);
		// Click workspace dropdown // Click workspace dropdown
		sendkeysToElement(driver, selectSearchWorkspace, "Select workspace field", workspaceName + Keys.ENTER);
		isInvisibleLoader(driver, loader);
		System.out.println("Workspace '" + workspaceName + "' is selected successfully.");
	}

	// Click on the Folder selector field

	public void clickOnFolderField(String folderName) {
		isInvisibleLoader(driver, loader);
		clickOnElement(driver, clickOnFolderDropdown, 30);
		sendkeysToElement(driver, searchFolderName, "searching folder name.", folderName);
		clickOnElement(driver, By.xpath("//div[@id='pathtree']//span[@title='Search']"), 30);
		isInvisibleLoader(driver, loader);
		clickOnElement(driver, By.xpath("//div[contains(@class, 'e-text-content')]"), 10);
		clickOnElement(driver, By.xpath("//span[@class='e-icons e-check']//parent::button"), 10);

		System.out.println("Folder '" + folderName + "' selected.");
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
	public void selectionSourceDataset(String connectionName, String schemaName) throws InterruptedException {
		// JavascriptExecutor js = (JavascriptExecutor) driver;

		// Click on the source connection type.
		clickOnElement(driver, clickSourcedatasetConnectionTypeDropdown, 10);

		// Select "Database"
		clickOnElement(driver, By.xpath("//li[text()='Database']"), 20);

		// Click Source Connection
		clickOnElement(driver, clickSourcedatasetConnectionDropdown, 20);

		// Enter connection name
		sendkeysToElement(driver, enterSourceConnectionName, "source connection name", connectionName);
		sendkeysToEnter(driver, enterSourceConnectionName, "source connection name");

		// Scroll & click schema dropdown
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
//				driver.findElement(sourceSchemaDropdown));

		clickOnElement(driver, clickSourceSchemaDropdown, 20);

//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
//				driver.findElement(By.xpath(
//						"//ejs-dropdownlist[@placeholder='Choose Schema']/following::div[contains(@class,'e-popup')]//input[@type='text']")));

		// Enter schema name
		sendkeysToElement(driver, enterSourceSchemaName, "source schema name", schemaName);
		sendkeysToEnter(driver, enterSourceSchemaName, "source schema name");

	}

	/// ********* Select the target dataset ********** ///

///// *******************************/////
/////////////// ********************************* ////////////////
///// *******************************/////
	public void selectionTargetDataset(String connectionName, String schemaName) throws InterruptedException {

		/// Click on the target connection type.
		clickOnElement(driver, clickTargetdatasetConnectionTypeDropdown, 10);

		// Select the target connection type as database.
		clickOnElement(driver, By.xpath("//li[text()='Database']"), 20);

		// Click the target connection.
		clickOnElement(driver, clickTargetdatasetConnectionDropdown, 20);

		// Entering the target connection name
		sendkeysToElement(driver, enterTargetConnectionName, "target connection name", connectionName);
		sendkeysToEnter(driver, enterTargetConnectionName, "target connection name");

		// Scroll & click schema dropdown
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
//				driver.findElement(targetSchemaDropdown));

		clickOnElement(driver, clickTargetSchemaDropdown, 20);

		// Enter schema name
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
//				driver.findElement(By.xpath(
//						"//ejs-dropdownlist[@placeholder='Choose Schema']/following::div[contains(@class,'e-popup')]//input[@type='text']")));

		// Entering the schema name.
		sendkeysToElement(driver, enterSourceSchemaName, "target schema name", schemaName);
		sendkeysToEnter(driver, enterSourceSchemaName, "target schema name");

	}

	// Click on the next button of 'select dataset' page.
	public void gotoDatasetNextbtn() {
		clickOnElement(driver, clickNextButtondatasetNextbtn, 20);
	}

	/// ********* Select the Available Table ********** ///.

	public void selectionAvailabeTable(String tableName) {
		// Click on the available table search field
		clickOnElement(driver, clickAvailableTable, 20);
		// Entering the available table name
		sendkeysToElement(driver, clickAvailableTable, "staff table name", tableName);
		// Select the table checkbox from the list.
		clickOnElement(driver, selectAvailableTable, 20);
		// Click on the 'Move to' button
		clickOnElement(driver, clickMoveToButton, 20);
	}

	// Click on the next button of 'select table' page.
	public void gotoSelettableNextbtn() {
		clickOnElement(driver, clickNextButtonSelettableNextbtn, 20);
	}

	// Click on the 'Generate' button.
	public void clickOnGenerate() {
		clickOnElement(driver, clickGenerateButton, 20);
	}

	// Click on the 'Go To Preview' button.
	public void clickOnGoToPreview() {
		clickOnElement(driver, clickGoToPreviewButton, 20);
	}

	// Select the generated entity from the 'Preview' page.
	public void selectGeneratedEntity() {
		clickOnElement(driver, selectEntity, 20);
	}

	// Click on the 'Publish' button.
	public void clickOnPublish() {
		clickOnElement(driver, clikPublishButton, 10);
	}

	// Click on the 'Go To Publish' button.
	public void clickOnGoToPublish() {
		clickOnElement(driver, clickGoToPublishButton, 30);
	}

	// Click on the hyperlink of the published rule to navigate to the data testing.
	public void clickOnPublishedRule() {
		clickOnElement(driver, clickHyperlinkPublishRule, 20);
	}
}
