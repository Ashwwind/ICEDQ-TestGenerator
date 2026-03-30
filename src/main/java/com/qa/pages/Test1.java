package com.qa.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qa.config.ConfigReader;
import com.qa.extentreportlistener.ExtentManager;
import com.qa.pages.TestGeneratorPage.FlowAbortException;
import com.qa.utils.PageUtil;

public class Test1 extends PageUtil {
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
	By selectDatabaseConnectionType = getElementLocator(prop.getProperty("wizard.Database.connectionType"));
	By selectCloudDataWarehouseConnectionType = getElementLocator(
			prop.getProperty("wizard.CloudDataWarehouse.connectionType"));
	By selectFileConnectionType = getElementLocator(prop.getProperty("wizard.File.connectionType"));

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
	By ClickNextButtonimportSQLNextbtn = getElementLocator(
			prop.getProperty("wizard.click.nextButton.importSQLNextbtn"));

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

	public Test1(WebDriver driver) {
		this.driver = driver;
	}
	
	/// Common Method ///
	
	// Common method for take screenshot and navigate the home page.
	

	public void abortCurrentFlow(String methodName, Exception e) {
		try {
			// Attempt to capture toast (if any)
			String errors = captureUIErrorIfPresent(driver);
			if (errors != null && !errors.trim().isEmpty()) {
				ExtentManager.logFail("UI Error(s) during abort: " + errors);
				// Screenshot already taken inside captureUIErrorIfPresent
			} else {
				// 📸 fallback screenshot for non-toast failures
				ExtentManager.captureScreenshot(methodName.replace(" ", "_") + "_Failure");
			}
		} catch (Exception capEx) {
			ExtentManager.logError("Failed to capture UI error during abort: " + capEx.getMessage());
			ExtentManager.captureScreenshot(methodName.replace(" ", "_") + "_Failure_Fallback");
		}

		// 🚪 Navigate to Home (if this is your reset strategy)
		try {
			navigatHomePage();
		} catch (Exception navEx) {
			ExtentManager.logError("Navigation to Home failed during abort: " + navEx.getMessage());
		}

		// 🔥 STOP FLOW IMMEDIATELY
		throw new FlowAbortException("Flow aborted: " + methodName, e);
	}


	/// ************* ///
	// Default Dynamic Template
	/// ************* ///

	public void createRuleUsingDefaultDynamicTemplate(String ruleType, String templateName, String workspaceName,
			String folderName, String connectionType, String sourceConnectionName, String targetConnectionName,
			String schemaName, String tableName) throws InterruptedException {

		// Step 1
		clickTestGenerator();

		// Step 2
		clickOnRuleType(ruleType);

		// Step 3
		clickOnSearchField();
		enterOnSearchField(templateName);
		selectExistingChecksumTemplate();

		// Step 4
		clickOnWorkspaceField(workspaceName);

		
		clickOnFolderField(folderName);

		// Step 5
		gotoWorkspaceNextbtn();
		gotoRuleMetadataNextbtn();
		gotoCheckMetadataNextbtn();
		gotoNotificationNextbtn();

		// Step 6
		selectionSourceDataset(connectionType, sourceConnectionName, schemaName);

		// Step 7 (only for recon / checksum)
		if (!ruleType.equalsIgnoreCase("validation") && !ruleType.equalsIgnoreCase("pushdown")) {
			selectionTargetDataset(connectionType, targetConnectionName, schemaName);
		}

		// Step 8
		gotoDatasetNextbtn();
		selectionAvailableTable(tableName);
		gotoSelettableNextbtn();

		// Step 9
		clickOnGenerate();
		clickOnGoToPreview();
		selectGeneratedEntity();

		// Step 10
		clickOnPublish();
		clickOnGoToPublish();

		// Step 11
		clickOnPublishedRule();

		// Step 12
		navigatHomePage();
	}

	// ============================
	// Page method
	// ============================

	

	public boolean clickTestGenerator() { // Clicking on Test Generator Module
		try {

			boolean clicked = clickOnField(driver, testGeneratorModue, "Test Generator", "button");

			return clicked;

		} catch (FlowAbortException e) {
			return false;
		} catch (Exception e) {
			ExtentManager.logFail("Unexpected error. " + testGeneratorModue + " | " + e.getMessage());
			return false;
		}
	}


	public boolean clickOnRuleType(String ruleType) {

		if (ruleType == null || ruleType.trim().isEmpty()) {
			ExtentManager.logFail("Rule type is null or empty");
			return false;
		}

		By locator;

		switch (ruleType.trim().toLowerCase()) {

		case "checksum":
			locator = selectChecksumWizard;
			break;

		case "recon":
			locator = selectReconWizard;
			break;

		case "validation":
			locator = selectValidationWizard;
			break;

		case "pushdown":
			locator = selectPushdownWizard;
			break;

		default:
			ExtentManager.logFail("Invalid Rule Type provided: " + ruleType);
			return false;
		}

		boolean isClicked = clickOnField(driver, locator, ruleType + " rule type", "tab");

		if (!isClicked) {
			ExtentManager.logFail("Failed to click on rule type: " + ruleType);
		}

		return isClicked;
	}

	// Click on the search field box
	public boolean clickOnSearchField() { /// Clicking on Search Template Field

		try {

			return clickOnField(driver, clickSearchTemplateField, "Search Template", "search field");

		} catch (Exception e) {
			ExtentManager.logFail("Element is not clicable. " + clickSearchTemplateField + e.getMessage());
			return false;
		}

	}



	// Enter the data on the search field
	public boolean enterOnSearchField(String templateName) {

		try {
			boolean isTextEntered = sendkeysToElement1(driver, enterTemplateName, "Search template field", templateName);

			if (!isTextEntered) {
				ExtentManager.logFail("Failed to enter template name: " + templateName);
				return false;
			}

			boolean isSearchClicked = clickOnField(driver, clickSearchButtonIcon, "Search button icon", "button");

			if (!isSearchClicked) {
				ExtentManager.logFail("Failed to click Search button icon");
				return false;
			}

			ExtentManager.logInfo("Search performed successfully for template: " + templateName);
			return true;

		} catch (Exception e) {
			ExtentManager.logFail(
					"Exception while performing search for template '" + templateName + "' : " + e.getMessage());
			return false;
		}
	}


	// Select the existing template form the list
	public boolean selectExistingChecksumTemplate() { /// Selecting searched template

		try {

			return clickOnField(driver, selectExistingDynamicChecksumTemplate, "Searched Template Item", "search item");

		} catch (Exception e) {
			ExtentManager.logFail("Element is not clicable. " + selectExistingDynamicChecksumTemplate + e.getMessage());
			return false;
		}

	}

	public boolean clickOnWorkspaceField(String workspaceName) { // Selecting workspace name from the drop down.

		try {
			isInvisibleLoader(driver, loader);

			clickOnField(driver, clickOnWorkspaceDropdown, "Workspace Dropdown", "dropdown"); // Open workspace dropdown
			ExtentManager.logInfo("Workspace dropdown opened.");

			clickOnField(driver, selectSearchWorkspace, "Search Workspace Field", "search field"); // Click search field

			sendkeysToElement1(driver, selectSearchWorkspace, "Select workspace field", workspaceName); // Enter workspace name.

			sendKeysEnterToElement2(driver, selectSearchWorkspace, "Text Field"); // Press enter.

			isInvisibleLoader(driver, loader);

			ExtentManager.logPass("Workspace name selected: " + workspaceName);

			return true;

		} catch (Exception e) {
			System.out.println("Error selecting workspace: " + e.getMessage());

			ExtentManager.logFail("Error selecting workspace: " + workspaceName + " " + e.getMessage());
			return false;
		}

	}
	
	public boolean clickOnFolderField(String folderName) { // Selecting folder name from the drop down.

		try {
			isInvisibleLoader(driver, loader);

			// Open folder dropdown

			clickOnField(driver, clickOnFolderDropdown, "Folder Dropdown", "dropdown"); // Open folder dropdown
			ExtentManager.logInfo("Folder dropdown opened.");

			clickOnField(driver, searchFolderName, "Search Folder Field", "search field"); // Click search field

			sendkeysToElement1(driver, searchFolderName, "Search Folder Field", folderName); // Enter folder name.

			clickOnField(driver, By.xpath("//div[@id='pathtree']//span[@title='Search']"), "Search Folder", "Search button icon"); // Click search icon

			isInvisibleLoader(driver, loader);

			clickOnField(driver, By.xpath("//div[contains(@class, 'e-text-content')]"), "Folder Result", "folder"); // Click the searched item.

			clickOnField(driver, By.xpath("//span[@class='e-icons e-check']//parent::button"), "Confirm Folder Selection", "button"); // Folder selection.

			isInvisibleLoader(driver, loader);

			ExtentManager.logPass("Folder name selected: " + folderName);
			return true;

		} catch (Exception e) {
			ExtentManager.logFail("Error selecting folder: " + folderName + " " + e.getMessage());
			return false;
		}

	}

	public boolean gotoWorkspaceNextbtn() { /// Clicking Next button on Select Container page

		try {

			return clickOnField(driver, clickNextButtonWorkspaceNextbtn, "Next Button of Select Container Page", "button");

		} catch (Exception e) {
			ExtentManager.logFail("Element is not clicable. " + clickNextButtonWorkspaceNextbtn + e.getMessage());
			return false;
		}

	}

	public boolean gotoRuleMetadataNextbtn() { /// Clicking Next button on Define Rule Metadata page

		try {

			return clickOnField(driver, clickNextButtonmetadataNextbtn, "Next Button of Define Rule Metadata Page", "button");

		} catch (Exception e) {
			ExtentManager.logFail("Element is not clicable. " + clickNextButtonmetadataNextbtn + e.getMessage());
			return false;
		}

	}

	public boolean gotoCheckMetadataNextbtn() { /// Clicking Next button on Define Check Metadata page

		try {

			return clickOnField(driver, clickNextButtoncheckNextbtn, "Next Button of Define Check Metadata Page", "button");

		} catch (Exception e) {
			ExtentManager.logFail("Element is not clicable. " + clickNextButtoncheckNextbtn + e.getMessage());
			return false;
		}

	}


	public boolean gotoNotificationNextbtn() { /// Clicking Next button on Configure Notifications page

		try {

			return clickOnField(driver, clickNextButtonnotificationNextbtn, "Next Button of Configure Notifications Page", "button");

		} catch (Exception e) {
			ExtentManager.logFail("Element is not clicable. " + clickNextButtonnotificationNextbtn + e.getMessage());
			return false;
		}

	}


	/// ********* Select the source dataset ********** ///
	///// *******************************/////
	/////////////// ********************************* ////////////////
	///// *******************************/////

	public boolean selectionSourceDataset(String connectionType, String connectionName, String schemaName) { /// Selecting source dataset

		try {

			clickOnField(driver, clickSourcedatasetConnectionTypeDropdown, "Source Connection Type Dropdown", "dropdown"); // Click on the source connection type dropdown

			if (connectionType.equalsIgnoreCase("Database")) {

				clickOnField(driver, selectDatabaseConnectionType, "Source Database Connection", "list");
				isInvisibleLoader(driver, loader);

				clickOnField(driver, clickSourcedatasetConnectionDropdown, "Select Connection Dropdown", "list");

				sendkeysToElement1(driver, enterSourceConnectionName, "Source Connection Name", connectionName);
				sendKeysEnterToElement2(driver, enterSourceConnectionName, "text field");
				isInvisibleLoader(driver, loader);

				ExtentManager.logInfo("SOURCE Database connection name selected successfully.");

			} else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse")) {

				clickOnField(driver, selectCloudDataWarehouseConnectionType, "Cloud Data Warehouse Connection Type", "dropdown");
				isInvisibleLoader(driver, loader);

				clickOnField(driver, clickSourcedatasetConnectionDropdown, "Select Connection Dropdown", "list");

				sendkeysToElement1(driver, enterSourceConnectionName, "Source Connection Name", connectionName);
				sendKeysEnterToElement2(driver, enterSourceConnectionName, "text field");
				isInvisibleLoader(driver, loader);

				ExtentManager.logInfo("SOURCE Cloud warehouse connection name selected successfully.");

				By useMetadataCache = By.xpath(
						"//*[@id='e-content-element_5']/div/div[2]/div[1]/form/div[2]/div[3]/div/ejs-checkbox/label/span[2]");
				clickOnField(driver, useMetadataCache, "Use Metadata Cache Checkbox", "checkbox");
				isInvisibleLoader(driver, loader);

				// Click database dropdown
				clickOnField(driver, By.xpath(
						"(//div[contains(text(),'Source Dataset')]/parent::form//following::ejs-dropdownlist[@placeholder='Choose Database']//span[@formcontrolname='database'])[1]"),
						"Database Dropdown", "dropdown");
				isInvisibleLoader(driver, loader);

				By databaseInput = By.xpath(
						"//*[@placeholder='Select connection']/following::div[contains(@class,'e-popup')]//input[@type='text']");
				sendkeysToElement1(driver, databaseInput, "Source Database Name", "icedqrs");
				sendKeysEnterToElement2(driver, databaseInput, "text field");
				isInvisibleLoader(driver, loader);

				ExtentManager.logInfo("SOURCE Database name selected successfully.");
			}

			// Schema selection
			clickOnField(driver, clickSourceSchemaDropdown, "Source Schema Dropdown", "dropdown");
			isInvisibleLoader(driver, loader);

			sendkeysToElement1(driver, enterSourceSchemaName, "Source Schema Name", schemaName);
			sendKeysEnterToElement2(driver, enterSourceSchemaName, "text field");
			isInvisibleLoader(driver, loader);

			ExtentManager.logInfo("SOURCE Schema name selected successfully.");

			return true;

		} catch (Exception e) {
			ExtentManager.logFail("Failed to select source dataset: " + e.getMessage());
			
			abortCurrentFlow("selectionSourceDataset", e);
	
			return false;
		}

	}
//
//	/// ********* Select the target dataset ********** ///
//	///// *******************************/////
//	/////////////// ********************************* ////////////////
//	///// *******************************/////
	public boolean selectionTargetDataset(String connectionType, String connectionName, String schemaName) { /// Selecting target dataset

		try {

			// Click target connection type dropdown
			clickOnField(driver, clickTargetdatasetConnectionTypeDropdown, "Target Connection Type Dropdown", "dropdown");

			if (connectionType.equalsIgnoreCase("Database")) {

				clickOnField(driver, selectDatabaseConnectionType, "Target Database Connection", "list");
				isInvisibleLoader(driver, loader);

				clickOnField(driver, clickTargetdatasetConnectionDropdown, "Select Connection Dropdown", "list");

				sendkeysToElement1(driver, enterTargetConnectionName, "Target Connection Name", connectionName);
				sendKeysEnterToElement2(driver, enterTargetConnectionName, "text field");
				isInvisibleLoader(driver, loader);

				ExtentManager.logInfo("TARGET Database connection selected successfully.");

			} else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse")) {

				clickOnField(driver, selectCloudDataWarehouseConnectionType, "Target Cloud Data Warehouse Connection", "dropdown");
				isInvisibleLoader(driver, loader);

				clickOnField(driver, clickTargetdatasetConnectionDropdown, "Select Connection Dropdown", "list");

				sendkeysToElement1(driver, enterTargetConnectionName, "Target Connection Name", connectionName);
				sendKeysEnterToElement2(driver, enterTargetConnectionName, "text field");
				isInvisibleLoader(driver, loader);

				ExtentManager.logInfo("TARGET Cloud connection selected successfully.");

				// Click database dropdown
				clickOnField(driver, By.xpath(
					"(//div[contains(text(),'Source Dataset')]/parent::form//following::ejs-dropdownlist[@placeholder='Choose Database']//span[@formcontrolname='database'])[2]"),
					"Database Dropdown", "dropdown");
				isInvisibleLoader(driver, loader);

				By databaseInput = By.xpath(
					"//*[@placeholder='Select connection']/following::div[contains(@class,'e-popup')]//input[@type='text']");
				sendkeysToElement1(driver, databaseInput, "Target Database Name", "icedqrs");
				sendKeysEnterToElement2(driver, databaseInput, "text field");
				isInvisibleLoader(driver, loader);

				ExtentManager.logInfo("TARGET Database selected successfully.");
			}

			// Schema selection
			clickOnField(driver, clickTargetSchemaDropdown, "Target Schema Dropdown", "dropdown");
			isInvisibleLoader(driver, loader);

			sendkeysToElement1(driver, enterSourceSchemaName, "Target Schema Name", schemaName);
			sendKeysEnterToElement2(driver, enterSourceSchemaName, "text field");
			isInvisibleLoader(driver, loader);

			ExtentManager.logInfo("TARGET schema selected successfully.");

			return true;

		} catch (Exception e) {
			ExtentManager.logFail("Failed to select target dataset: " + e.getMessage());
			return false;
		}

	}

	public boolean gotoDatasetNextbtn() { /// Clicking Next button on Select Dataset page

		try {

			return clickOnField(driver, clickNextButtondatasetNextbtn, "Next Button of Select Dataset Page", "button");

		} catch (Exception e) {
			ExtentManager.logFail("Element is not clicable. "
					+ clickNextButtondatasetNextbtn + e.getMessage());
			return false;
		}

	}
	
	
	/// ********* Select the Available Table ********** ///
	public boolean selectionAvailableTable(String tableName) { /// Selecting available table

		try {

			clickOnField(driver, clickAvailableTable, "Available Table Search Field", "search field"); // Click on the available table search field.

			sendkeysToElement1(driver, clickAvailableTable, "Available Table Name", tableName); // Enter the table name.
			sendKeysEnterToElement2(driver, clickAvailableTable, "text field");  // Press enter key.

			clickOnField(driver, selectAvailableTable, "Table Checkbox", "checkbox"); // Select the table checkbox from the list

			clickOnField(driver, clickMoveToButton, "'Move To' Button", "button"); // Click on the 'Move to' button

			ExtentManager.logInfo("Table '" + tableName + "' selected successfully.");

			return true;

		} catch (Exception e) {
			ExtentManager.logFail("Failed to select table '" + tableName + "': " + e.getMessage());
			return false;
		}

	}

	public boolean gotoSelettableNextbtn() { /// Clicking Next button on Select Tables page

		try {

			return clickOnField(driver, clickNextButtonSelettableNextbtn, "Next Button of Select Tables Page", "button");

		} catch (Exception e) {
			ExtentManager.logFail("Element is not clicable. " + clickNextButtonSelettableNextbtn + e.getMessage());
			return false;
		}

	}

	public boolean clickOnGenerate() { /// Clicking on Generate button

		try {

			return clickOnField(driver, clickGenerateButton, "Generate Button", "button");

		} catch (Exception e) {
			ExtentManager.logFail("Element is not clicable. " + clickGenerateButton + e.getMessage());
			return false;
		}

	}


	public boolean clickOnGoToPreview() { /// Clicking on Go To Preview button

		try {

			return clickOnField(driver, clickGoToPreviewButton, "Go To Preview Button", "button");

		} catch (Exception e) {
			ExtentManager.logFail("Element is not clicable. " + clickGoToPreviewButton + e.getMessage());
			return false;
		}

	}

	public boolean selectGeneratedEntity() { /// Selecting generated entity

		try {

			return clickOnField(driver, selectEntity, "Checkbox for Generated Entity", "checkbox");

		} catch (Exception e) {
			ExtentManager.logFail("Element is not clicable. " + selectEntity + e.getMessage());
			return false;
		}

	}


	public boolean clickOnPublish() { /// Clicking on Publish button

		try {

			return clickOnField(driver, clikPublishButton, "Publish Button", "button");

		} catch (Exception e) {
			ExtentManager.logFail("Element is not clicable. " + clikPublishButton + e.getMessage());
			return false;
		}

	}
	
	public boolean clickOnGoToPublish() { /// Clicking on Go To Publish button

		try {

			return clickOnField(driver, clickGoToPublishButton, "Go To Publish Button", "button");

		} catch (Exception e) {
			ExtentManager.logFail("Element is not clicable. " + clickGoToPublishButton + e.getMessage());
			return false;
		}

	}

	// Click on the hyperlink of the published rule to navigate to Data Testing
	public boolean clickOnPublishedRule() { /// Clicking on Published Rule and validating rule name

		try {

			// Capture parent window
			String parent = PageUtil.getParentWindow(driver);

			isInvisibleLoader(driver, loader);

			// Get rule name from Published page
			By ruleNameLocator1 = By.xpath("//tbody[@role='rowgroup']/tr/td/a");
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(60));
			WebElement ruleElement = wait1.until(ExpectedConditions.visibilityOfElementLocated(ruleNameLocator1));
			wait1.until(d -> !ruleElement.getText().trim().isEmpty());

			String ruleNamePublishPage = ruleElement.getText().trim();
			ExtentManager.logInfo("Rule Name from Published Page: " + ruleNamePublishPage);

			// Click hyperlink
			clickOnField(driver, clickHyperlinkPublishRule, "Published Rule Hyperlink", "link");

			// Switch to child window
			String child = switchToNewWindow(driver, parent, 30);
			isInvisibleLoader(driver, loader);

			// Get rule name from Data Testing page
			By ruleNameLocator2 = By.xpath("//*[@placeholder='Enter rule name']");
			WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(30));
			WebElement ruleName2 = wait2.until(ExpectedConditions.presenceOfElementLocated(ruleNameLocator2));
			wait2.until(d -> !ruleName2.getDomProperty("value").isEmpty());

			String ruleNameDataTestingPage = ruleName2.getDomProperty("value").trim();
			ExtentManager.logInfo("Rule Name from Data Testing Page: " + ruleNameDataTestingPage);
			
			waitForSeconds(2);

			// Rule name validation
			if (!ruleNamePublishPage.equals(ruleNameDataTestingPage)) {
				throw new AssertionError("Rule name mismatch. Expected: " + ruleNamePublishPage
						+ " but found: " + ruleNameDataTestingPage);
			}

			ExtentManager.logPass("Rule Name validated successfully.");
			waitForSeconds(1);

			// Run rule
			runPublishRule();

			// Validate instance ID
			clickOnRuleRecentrun();
			validateRuleInstanceID(driver);

			// Close child and return to parent
			closeChildAndReturn(driver, child, parent);

			return true;

		} catch (Exception e) {
			ExtentManager.logFail("Failed to click and validate published rule: " + e.getMessage());
			return false;
		}

	}

	// Go to the Data Testing
	public static void navigatHomePage() { // Go to the Data Testing
		//goTo(ConfigReader.getProperty("homePageUrl"));
	}
	
	
	// Run the publish rule on the Data Testing module
	public boolean runPublishRule() { /// Clicking Run button on Data Testing module

		try {

			// Click on the Run button
			clickOnField(driver, By.xpath("//*[@id='scrollPane']/app-flow-diagram/div[1]/button[1]"), "Run Button", "button");
			isInvisibleLoader(driver, loader);

			ExtentManager.logInfo("Run button clicked successfully.");

			return true;

		} catch (Exception e) {
			ExtentManager.logFail("Failed to click Run button: " + e.getMessage());
			return false;
		}

	}

	// Click on the Rule Recent Runs button
	public boolean clickOnRuleRecentrun() { /// Clicking on Recent Runs button

		try {

			// Click on the Recent Runs button
			clickOnField(driver, By.xpath("//*[@id='scrollPane']/app-flow-diagram/div[1]/button[3]"), "Recent Runs Button", "button");

			waitForSeconds(1); // optional small wait

			ExtentManager.logInfo("Recent Runs button clicked successfully.");

			return true;

		} catch (Exception e) {
			ExtentManager.logFail("Failed to click Recent Runs button: " + e.getMessage());
			return false;
		}

	}
	
	// Validate Rule Instance ID
	public boolean validateRuleInstanceID(WebDriver driver) { /// Validating rule instance ID until final status

		try {

			By refreshBtn = By.xpath("//*[@id='rrElement']/app-recent-run/div[1]/div[1]/div[2]/button");
			By instanceIdLocator = By.xpath("//*[@id='parentGrid_content_table']/tbody/tr/td[3]");
			By statusLocator = By.xpath("//*[@id='parentGrid_content_table']/tbody/tr/td[4]");

			int maxRetries = 10;
			int retryCount = 0;

			while (retryCount < maxRetries) {

				clickOnField(driver, refreshBtn, "Refresh Button", "button");
				waitForSeconds(1);

				if (isElementDisplayed(driver, instanceIdLocator, 5)) {

					String instanceId = driver.findElement(instanceIdLocator).getText();
					String status = driver.findElement(statusLocator).getText().trim();

					ExtentManager.logInfo("Instance ID: " + instanceId);
					ExtentManager.logInfo("Current Status: " + status);

					// Exit if final status is reached
					if (!status.equalsIgnoreCase("Submitted") && !status.equalsIgnoreCase("Running")) {
						ExtentManager.logPass("Final execution status: " + status);
						return true;
					}
				}

				retryCount++;
			}

			ExtentManager.logFail("Status did not change from Submitted/Running after " + maxRetries + " retries.");
			return false;

		} catch (Exception e) {
			ExtentManager.logFail("Exception while validating rule instance ID: " + e.getMessage());
			return false;
		}

	}

}
