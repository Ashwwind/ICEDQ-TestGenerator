package com.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.qa.base.Base;
import com.qa.config.ConfigReader;
import com.qa.extentreportlistener.ExtentManager;
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

	public TestGeneratorPage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean executeStep(String stepName, Runnable action) {
		try {
			action.run();
			ExtentManager.logPass(stepName + " ➝ PASSED");
			return true;

		} catch (Exception e) {

			// Log failure
			ExtentManager.logFail(stepName + " -> FAILED due to: " + e.getMessage());
		
			return false; // important → stop further execution of that step
		}
	}

	public void createRuleUsingDefaultDynamicTemplate(String ruleType, String templateName, String workspaceName,
			String folderName, String connectionType, String connectionName, String schemaName, String tableName)
			throws InterruptedException {

// 1. Navigate to Test Generator → Wizard
		clickTestGenerator();

// switch case
		switch (ruleType.toLowerCase()) {
		case "checksum":
			clickOnChecksum();
			break;

		case "recon":
			clickOnRecon();
			break;

		case "validation":
			clickOnValidation();
			break;

		case "pushdown":
			clickOnPushdown();
			break;

		default:
			throw new IllegalArgumentException("Invalid Rule Type: " + ruleType);
		}

// 2. Search & Select Template
		clickOnSearchField();
		enterOnSearchField(templateName);
		selectExistingChecksumTemplate();

// 3. Select Workspace
		clickOnWorkspaceField(workspaceName);

// 4. Select Folder
		clickOnFolderField(folderName);

// Next Buttons
		gotoWorkspaceNextbtn();
		gotoRuleMetadataNextbtn();
		gotoCheckMetadataNextbtn();
		gotoNotificationNextbtn();

// 5. Select Source Dataset
		selectionSourceDataset(connectionType, connectionName, schemaName);

// 6. Select Target Dataset
		if (!ruleType.equalsIgnoreCase("validation") && !ruleType.equalsIgnoreCase("pushdown")) {

			selectionTargetDataset(connectionType, connectionName, schemaName);

		} else {
			System.out.println("Skipping Target Dataset for rule type: " + ruleType);
		}

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

// 9. Navigate Data Testing
		navigatHomePage();
	}

	// ============================
	// Import Template
	// ============================

	public void createRuleUsingDefaultImportTemplate(String ruleType, String templateName, String workspaceName,
			String folderName, String connectionType, String connectionName) throws InterruptedException {

// 1. Navigate to Test Generator → Wizard
		clickTestGenerator();
		boolean isReconOrChecksum = ruleType.equalsIgnoreCase("checksum") || ruleType.equalsIgnoreCase("recon");
// switch case
		switch (ruleType.toLowerCase()) {
		case "checksum":
			clickOnChecksum();
			break;

		case "recon":
			clickOnRecon();
			break;

		case "validation":
			clickOnValidation();
			break;

		case "pushdown":
			clickOnPushdown();
			break;

		default:
			throw new IllegalArgumentException("InvaRule Type: " + ruleType);
		}

// 2. Search & Select Template
		clickOnSearchField();
		enterOnSearchField(templateName);
		selectExistingChecksumTemplate();

// 3. Select Workspace
		clickOnWorkspaceField(workspaceName);

// 4. Select Folder
		clickOnFolderField(folderName);

// Next Buttons
		gotoWorkspaceNextbtn();
		gotoRuleMetadataNextbtn();
		gotoCheckMetadataNextbtn();
		gotoNotificationNextbtn();

// 5. Select Source Dataset
		selectionSourceDatasetForImport(connectionType, connectionName);

// 6. Select Target Dataset
		if (isReconOrChecksum) {

			selectionTargetDatasetForImport(connectionType, connectionName);

		} else {
			System.out.println("Skipping Target Dataset for rule type: " + ruleType);
		}

// Next Buttons
		gotoDatasetNextbtn();

		// 7. Import SQL (upload file based on ruleType)
		switch (ruleType.toLowerCase()) {
		case "checksum":
			if (connectionType.equalsIgnoreCase("Database")) {
				uploadingFiles("Checksum.xlsx");
			} else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse")) {
				uploadingFiles("Checksum - Redshift.xlsx");
			} else if (connectionType.equalsIgnoreCase("File")) {
				uploadingFiles("Checksum - Flat File-SQL.xlsx");
			}
			break;

		case "recon":
			if (connectionType.equalsIgnoreCase("Database")) {
				uploadingFiles("Recon.xlsx");
			} else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse")) {
				uploadingFiles("Recon - Redshift.xlsx");
			} else if (connectionType.equalsIgnoreCase("File")) {
				uploadingFiles("Recon - Flat File-SQL.xlsx");
			}
			break;

		case "validation":
			if (connectionType.equalsIgnoreCase("Database")) {
				uploadingFiles("Validation.xlsx");
			} else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse")) {
				uploadingFiles("Validation - Redshift.xlsx");
			} else if (connectionType.equalsIgnoreCase("File")) {
				uploadingFiles("Validation - Flat File-SQL.xlsx");
			}
			break;

		case "pushdown":
			if (connectionType.equalsIgnoreCase("Database")) {
				uploadingFiles("Pushdown.xlsx");
			} else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse")) {
				uploadingFiles("Pushdown - Redshift.xlsx");
			} else if (connectionType.equalsIgnoreCase("File")) {
				uploadingFiles("Pushdown - Flat File-SQL.xlsx");
			}
			break;

		default:
			throw new IllegalArgumentException("Invalid rule type: " + ruleType);
		}

// Next Buttons
		gotoImportSQLNextbtn();

// 8. Generate → Preview → Publish
		clickOnGenerate();
		clickOnGoToPreview();
		selectGeneratedEntity();
		clickOnPublish();
		clickOnGoToPublish();
		clickOnPublishedRule();

// 9. Navigate Data Testing
		navigatHomePage();
	}

	// ============================
	// Page method
	// ============================

	public boolean clickTestGenerator() {
		return executeStep("Click Test Generator.", () -> {
			isInvisibleLoader(driver, loader);
			validateElementIsVisible(driver, testGeneratorModue, "Test Generator");
			clickOnElement(driver, testGeneratorModue, "Test Generator module.", 30);
			ExtentManager.logInfo("Test Generator module is selected...");
		});
	}

	// Click the Checksum rule type from the wizard page.
	public boolean clickOnChecksum() {
		return executeStep("Click Checksum rule type from the wizard page.", () -> {
			isInvisibleLoader(driver, loader);
			validateElementIsVisible(driver, selectChecksumWizard, "Checksum rule type");
			clickOnElement(driver, selectChecksumWizard, "Checksum rule wizard.", 50);
			ExtentManager.logInfo("Defoult template is selected...");
		});
	}

//		// validate URl
//		String expectedUrl = "https://qa.onprem.icedq.com/rulegen-ui/#/wizard/checksum";
//		String actualUrl = driver.getCurrentUrl();
//
//		if (actualUrl.equals(expectedUrl)) {
//			ExtentManager.logPass( "URL matched: " + actualUrl);
//		} else {
//			Base.extentReportFail(driver,"", "URL mismatch. Actual: " + actualUrl);
//		}
//
//		// Validate page title
//
//		WebElement title = driver.findElement(By.xpath("/html/body/app-root/div[2]/div/app-rule/div[2]/span[normalize-space(text())='Checksum Rule Wizard']"));
//
//		if (title.isDisplayed()) {
//			ExtentManager.logPass( "Page title displayed: " + title.getText());
//		} else {
//			Base.extentReportFail(driver,"", "Page title not displayed");
//		}
//
//		// Validate search field
//		WebElement searchInput = driver.findElement(By.xpath("//input[contains(@placeholder,'Search template')]"));
//
//		if (searchInput.isDisplayed()) {
//			ExtentManager.logPass( "Search field is displayed.");
//		} else {
//			Base.extentReportFail(driver,"", "Search field not visible.");
//		}
//
//		// Validate table columns
//		List<String> expectedHeaders = Arrays.asList("Name", "Description", "Type", "Last Updated");
//
//		List<WebElement> headers = driver.findElements(By.xpath("//table//th"));
//
//		for (int i = 0; i < expectedHeaders.size(); i++) {
//			if (headers.get(i).getText().equals(expectedHeaders.get(i))) {
//				ExtentManager.logPass( "Header matched: " + headers.get(i).getText());
//			} else {
//				Base.extentReportFail(driver,"", "Header mismatch!");
//			}
//		}


	// Click the Recon rule type from the wizard page.
	public boolean clickOnRecon() {
		return executeStep("Click Recon rule type from the wizard page.", () -> {
		isInvisibleLoader(driver, loader);
		validateElementIsVisible(driver, selectReconWizard, "Recon rule type");
			clickOnElement(driver, selectReconWizard, "Recon rule wizard.", 50);
			ExtentManager.logInfo("Defoult template is selected...");
			//Base.extentReportFail(driver, "clickOnRecon", "Defoult template is not selected...");
		});

	}

	// Click the Validation rule type from the wizard page.
	public boolean clickOnValidation() {
		return executeStep("Click Recon rule type from the wizard page.", () -> {
		isInvisibleLoader(driver, loader);
		validateElementIsVisible(driver, selectValidationWizard, "Validation rule type");
			clickOnElement(driver, selectValidationWizard, "Validation rule wizard.", 50);
			ExtentManager.logInfo("Defoult template is selected...");
			//Base.extentReportFail(driver, "clickOnValidation", "Defoult template is not selected..." + e.getMessage());
		});

	}

	// Click the Pushdown rule type from the wizard page.
	public boolean clickOnPushdown() {
		return executeStep("Click Recon rule type from the wizard page.", () -> {
		isInvisibleLoader(driver, loader);
		validateElementIsVisible(driver, selectPushdownWizard, "Pushdown rule type");
			clickOnElement(driver, selectPushdownWizard, "Pushdown rule wizard", 50);
			ExtentManager.logInfo("Defoult template is selected...");
			//Base.extentReportFail(driver, "clickOnPushdown", "Defoult template is not selected..." + e.getMessage());
		});

	}

	// Click on the search field box
	public boolean clickOnSearchField() {
		return executeStep("Click on the search field box.", () -> {
		isInvisibleLoader(driver, loader);
		validateElementIsVisible(driver, clickSearchTemplateField, "'Search template' search field ");
			clickOnElement(driver, clickSearchTemplateField, "Search template.", 30);
			ExtentManager.logInfo("'Search template' search box is clickable...");
			//Base.extentReportFail(driver, "clickOnSearchField","'Search template' search box is not clickable..." + e.getMessage());
		});

	}

	// Enter the data on the search field
	public boolean enterOnSearchField(String templateName) {
		return executeStep("Enter the template name.", () -> {
		sendkeysToElement(driver, enterTemplateName, "Search template", templateName);
		validateElementIsVisible(driver, clickSearchButtonIcon, "Search button icon on search template");
			clickOnElement(driver, clickSearchButtonIcon, "search button icon.", 30);
			ExtentManager.logInfo("Initiating action: Press Enter on element '" + templateName + "'");
			ExtentManager.logPass(templateName);
			//ExtentManager.logInfo("Action successful: Enter key pressed on element '" + templateName + "'");
			//Base.extentReportFail(driver, "enterOnSearchField", "Failed to press Enter on element '" + templateName + "'" + e.getMessage());
		});
	}

	// Select the existing template form the list
	public boolean selectExistingChecksumTemplate() {
		return executeStep("Select the existing template form the list.", () -> {
		isInvisibleLoader(driver, loader);
		validateElementIsVisible(driver, selectExistingDynamicChecksumTemplate, "The searched template name");
			clickOnElement(driver, selectExistingDynamicChecksumTemplate, "searched item from the list.", 30);
			ExtentManager.logInfo("Searched item is selected...");
			//Base.extentReportFail(driver, "selectExistingChecksumTemplate", "Searched item is not selected...");
		});
	}

	public boolean clickOnWorkspaceField(String workspaceName) {
		return executeStep("Click the workspace dropdown to select the workspace name.", () -> {
		isInvisibleLoader(driver, loader);
		// Wait for workspace field to be visible and clickable
		clickOnElement(driver, clickOnWorkspaceDropdown, "select workspace dropdown.", 30);
		ExtentManager.logInfo("Workspace dropdown opened.");
		// Click workspace dropdown
		clickOnElement(driver, selectSearchWorkspace, "searched workspace.", 30);
		// Click workspace dropdown // Click workspace dropdown

			sendkeysToElement(driver, selectSearchWorkspace, "Select workspace field", workspaceName + Keys.ENTER);
			ExtentManager.logInfo("Initiating action: Press Enter on element '" + workspaceName + "'");
			ExtentManager.logPass("Action successful: Enter key pressed on element '" + workspaceName + "'");
			//Base.extentReportFail(driver, "clickOnWorkspaceField","Failed to press Enter on element '" + workspaceName + "'" + e.getMessage());
		isInvisibleLoader(driver, loader);
		ExtentManager.logPass("Workspace name selected.");
		});

	}

	// Click on the Folder selector field

	public boolean clickOnFolderField(String folderName) {
		return executeStep("Click the folder dropdown to select the folder name.", () -> {
		isInvisibleLoader(driver, loader);
		clickOnElement(driver, clickOnFolderDropdown, "Folder dropdown", 30);
		ExtentManager.logInfo("Folder dropdown opened.");
			sendkeysToElement(driver, searchFolderName, "searching folder name.", folderName);
			ExtentManager.logInfo("Initiating action: Press Enter on element '" + folderName + "'");
			ExtentManager.logPass("Action successful: Enter key pressed on element '" + folderName + "'");
			//Base.extentReportFail(driver, "clickOnFolderField","Failed to press Enter on element '" + folderName + "'" + e.getMessage());

		clickOnElement(driver, By.xpath("//div[@id='pathtree']//span[@title='Search']"), "Search folder", 30);
		isInvisibleLoader(driver, loader);
		clickOnElement(driver, By.xpath("//div[contains(@class, 'e-text-content')]"), "Search button icon", 10);
		clickOnElement(driver, By.xpath("//span[@class='e-icons e-check']//parent::button"), "Seached folder name", 10);
		ExtentManager.logPass("Folder name selected");
		});

	}

	public boolean gotoWorkspaceNextbtn() {
		return executeStep("Click the Next button of 'Select Container' page.", () -> {
		validateElementIsVisible(driver, clickNextButtonWorkspaceNextbtn, "Next button of 'Select Container' page");
			clickOnElement(driver, clickNextButtonWorkspaceNextbtn, "next button of 'Select Container' page", 20);
			ExtentManager.logInfo("Next button of 'Select Container' page is clickable.");
			//Base.extentReportFail(driver, "gotoWorkspaceNextbtn","Next button of 'Select Container' page is not clickable." + e.getMessage());
		});
	}

	public boolean gotoRuleMetadataNextbtn() {
		return executeStep("Click the Next button of 'Define Rule Metadata' page.", () -> {
		validateElementIsVisible(driver, clickNextButtonmetadataNextbtn, "Next button of 'Define Rule Metadata' page ");
			clickOnElement(driver, clickNextButtonmetadataNextbtn, "next button of 'Define Rule Metadata' page", 20);
			ExtentManager.logInfo("Next button of 'Define Rule Metadata' page is clickable.");
			//Base.extentReportFail(driver, "gotoRuleMetadataNextbtn","Next button of 'Define Rule Metadata' page is not clickable." + e.getMessage());
		});
	}

	public boolean gotoCheckMetadataNextbtn() {
		return executeStep("Click the Next button of 'Define Rule Metadata' page.", () -> {
		validateElementIsVisible(driver, clickNextButtoncheckNextbtn, "Next button of 'Define Rule Metadata' page ");
			clickOnElement(driver, clickNextButtoncheckNextbtn, "next button of 'Define Check Metadata' page", 20);
			ExtentManager.logInfo("Next button of 'Define Check Metadata' page is clickable.");
			//Base.extentReportFail(driver, "gotoCheckMetadataNextbtn","Next button of 'Define Check Metadata' page is not clickable." + e.getMessage());
		});
	}

	public boolean gotoNotificationNextbtn() {
		return executeStep("Click the Next button of 'Configure Notifications' page.", () -> {
		validateElementIsVisible(driver, clickNextButtonnotificationNextbtn,"Next button of 'Configure Notifications' page ");
			clickOnElement(driver, clickNextButtonnotificationNextbtn, "next button of 'Configure Notifications' page",20);
			ExtentManager.logInfo("Next button of 'Configure Notifications' page is clickable.");
			//Base.extentReportFail(driver, "gotoNotificationNextbtn","Next button of 'Configure Notifications' page is not clickable." + e.getMessage());
		});
	}

	/// ********* Select the source dataset ********** ///
///// *******************************/////
/////////////// ********************************* ////////////////
///// *******************************/////

	public boolean selectionSourceDataset(String connectionType, String connectionName, String schemaName)
			throws InterruptedException {

		try {
			// Click on the source connection type dropdown
			clickOnElement(driver, clickSourcedatasetConnectionTypeDropdown, "select connection type.", 30);

			if (connectionType.equalsIgnoreCase("Database")) {
				clickOnElement(driver, selectDatabaseConnectionType, "source database connection.", 20);

				clickOnElement(driver, clickSourcedatasetConnectionDropdown, "select connection dropdown.", 20);

				sendkeysToElement(driver, enterSourceConnectionName, "source connection name", connectionName);
				try {
					sendkeysToEnter(driver, enterSourceConnectionName, "source connection name");
					ExtentManager.logInfo("Connection name is found.");
				} catch (Exception e) {
					//Base.extentReportFail(driver, "enterSourceConnectionName","Connection name is not found: " + e.getMessage());
					return false;
				}

			} else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse")) {
				clickOnElement(driver, selectCloudDataWarehouseConnectionType,
						"source cloud data warehouse connection.", 20);

				clickOnElement(driver, clickSourcedatasetConnectionDropdown, "select connection dropdown.", 20);

				sendkeysToElement(driver, enterSourceConnectionName, "source connection name", connectionName);
				try {
					sendkeysToEnter(driver, enterSourceConnectionName, "source connection name");
					ExtentManager.logInfo("Connection name is found.");
				} catch (Exception e) {
					//Base.extentReportFail(driver, "enterSourceConnectionName","Connection name is not found: " + e.getMessage());
					return false;
				}

				clickOnElement(driver, By.xpath(
						"(//div[contains(text(),'Source Dataset')]/parent::form//following::ejs-dropdownlist[@placeholder='Choose Database']//span[@formcontrolname='database'])[1]"),
						"database dropdown.", 20);

				By databaseInput = By.xpath(
						"//*[@placeholder='Select connection']/following::div[contains(@class,'e-popup')]//input[@type='text']");

				sendkeysToElement(driver, databaseInput, "source database name", "icedqrs");
				try {
					sendkeysToEnter(driver, databaseInput, "source database name");
					ExtentManager.logInfo("Database name is found.");
				} catch (Exception e) {
					//Base.extentReportFail(driver, "enterSourceDatabaseName","Database name is not found: " + e.getMessage());
					return false;
				}

			}

			validateElementIsVisible(driver, clickSourceSchemaDropdown, "schemaName dropdown");// Validate schema
																								// dropdown visibility

			clickOnElement(driver, clickSourceSchemaDropdown, "choose schema dropdown.", 40);// Select schema

			sendkeysToElement(driver, enterSourceSchemaName, "source schema name", schemaName);// Enter schema name
			sendkeysToEnter(driver, enterSourceSchemaName, "source schema name");

			return true;

		} catch (Exception e) {
			ExtentManager.logFail("selectionSourceDataset");
			return false;
		}
	}

	public void selectionSourceDatasetForImport(String connectionType, String connectionName)
			throws InterruptedException {
		// Click on the source connection type.
		clickOnElement(driver, clickSourcedatasetConnectionTypeDropdown, "select connection type.", 30);

		// Select "Database"
		if (connectionType.equalsIgnoreCase("Database")) {
			clickOnElement(driver, selectDatabaseConnectionType, "source database connection.", 20);

			// Click Source Connection
			clickOnElement(driver, clickSourcedatasetConnectionDropdown, "select connection dropdown.", 20);

			// Enter connection name
			sendkeysToElement(driver, enterSourceConnectionName, "source connection name", connectionName);
			sendkeysToEnter(driver, enterSourceConnectionName, "source connection name");

		} else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse")) {
			clickOnElement(driver, selectCloudDataWarehouseConnectionType, "source database connection.", 20);

			// Click Source Connection
			clickOnElement(driver, clickSourcedatasetConnectionDropdown, "select connection dropdown.", 20);

			// Enter connection name
			sendkeysToElement(driver, enterSourceConnectionName, "source connection name", connectionName);
			sendkeysToEnter(driver, enterSourceConnectionName, "source connection name");

		}

		else if (connectionType.equalsIgnoreCase("File")) {
			clickOnElement(driver, selectFileConnectionType, "source database connection.", 20);

			// Click the target connection.
			clickOnElement(driver, clickSourcedatasetConnectionDropdown, "select connection dropdown.", 20);

			// Entering the target connection name
			sendkeysToElement(driver, enterSourceConnectionName, "source connection name", connectionName);
			sendkeysToEnter(driver, enterSourceConnectionName, "source connection name");
			Thread.sleep(500);
		}
	}

	/// ********* Select the target dataset ********** ///
///// *******************************/////
/////////////// ********************************* ////////////////
///// *******************************/////
	public boolean selectionTargetDataset(String connectionType, String connectionName, String schemaName)
			throws InterruptedException {

		try {
			// Click on target connection type dropdown
			clickOnElement(driver, clickTargetdatasetConnectionTypeDropdown, "select connection type.", 30);

			if (connectionType.equalsIgnoreCase("Database")) {
				clickOnElement(driver, selectDatabaseConnectionType, "source database connection.", 20);

				clickOnElement(driver, clickTargetdatasetConnectionDropdown, "select connection dropdown.", 20);

				sendkeysToElement(driver, enterTargetConnectionName, "target connection name", connectionName);

				try {
					sendkeysToEnter(driver, enterTargetConnectionName, "target connection name");
					ExtentManager.logInfo("Connection name is found.");
				} catch (Exception e) {
					//Base.extentReportFail(driver, "enterTargetConnectionName","Connection name is not found: " + e.getMessage());
					return false;
				}

				Thread.sleep(500);

			} else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse")) {
				clickOnElement(driver, selectCloudDataWarehouseConnectionType,
						"source cloud data warehouse connection.", 20);

				clickOnElement(driver, clickTargetdatasetConnectionDropdown, "select connection dropdown.", 20);

				sendkeysToElement(driver, enterTargetConnectionName, "target connection name", connectionName);
				sendkeysToEnter(driver, enterTargetConnectionName, "target connection name");

				clickOnElement(driver, By.xpath(
						"(//div[contains(text(),'Source Dataset')]/parent::form//following::ejs-dropdownlist[@placeholder='Choose Database']//span[@formcontrolname='database'])[2]"),
						"database dropdown.", 20);

				By databaseInput = By.xpath(
						"//*[@placeholder='Select connection']/following::div[contains(@class,'e-popup')]//input[@type='text']");

				sendkeysToElement(driver, databaseInput, "source database name", "icedqrs");

				try {
					sendkeysToEnter(driver, databaseInput, "source database name");
					ExtentManager.logInfo("Connection name is found.");
				} catch (Exception e) {
					//Base.extentReportFail(driver, "enterSourceConnectionName", "Connection name is not found: " + e.getMessage());
					return false;
				}

				Thread.sleep(500);
			}

			validateElementIsVisible(driver, clickTargetSchemaDropdown, "schemaName dropdown");// Validate schema
																								// dropdown visibility

			clickOnElement(driver, clickTargetSchemaDropdown, "choose schema dropdown.", 40); // Select schema

			sendkeysToElement(driver, enterSourceSchemaName, "target schema name", schemaName);
			sendkeysToEnter(driver, enterSourceSchemaName, "target schema name");

			return true;

		} catch (Exception e) {
			ExtentManager.logFail("selectionTargetDataset");
			return false;
		}
	}

	public void selectionTargetDatasetForImport(String connectionType, String connectionName)
			throws InterruptedException {
		/// Click on the target connection type.
		clickOnElement(driver, clickTargetdatasetConnectionTypeDropdown, "select connection type.", 30);

		// Select the target connection type as database.
		if (connectionType.equalsIgnoreCase("Database")) {
			clickOnElement(driver, selectDatabaseConnectionType, "source database connection.", 20);

			// Click the target connection.
			clickOnElement(driver, clickTargetdatasetConnectionDropdown, "select connection dropdown.", 20);

			// Entering the target connection name
			sendkeysToElement(driver, enterTargetConnectionName, "target connection name", connectionName);
			sendkeysToEnter(driver, enterTargetConnectionName, "target connection name");

			Thread.sleep(500);

		} else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse")) {
			clickOnElement(driver, selectCloudDataWarehouseConnectionType, "target database connection.", 20);

			// Click the target connection.
			clickOnElement(driver, clickTargetdatasetConnectionDropdown, "select connection dropdown.", 20);

			// Entering the target connection name
			sendkeysToElement(driver, enterTargetConnectionName, "target connection name", connectionName);
			sendkeysToEnter(driver, enterTargetConnectionName, "target connection name");
			Thread.sleep(500);

		} else if (connectionType.equalsIgnoreCase("File")) {
			clickOnElement(driver, selectFileConnectionType, "target database connection.", 20);

			// Click the target connection.
			clickOnElement(driver, clickTargetdatasetConnectionDropdown, "select connection dropdown.", 20);

			// Entering the target connection name
			sendkeysToElement(driver, enterTargetConnectionName, "target connection name", connectionName);
			sendkeysToEnter(driver, enterTargetConnectionName, "target connection name");
			Thread.sleep(500);
		}

	}

	// Import SQL Files
	public boolean uploadingFiles(String fileName) throws InterruptedException {
		return executeStep("Click the Next button of 'Define Rule Metadata' page.", () -> {
		//Thread.sleep(500);
		// validateElementIsVisible(driver, By.xpath("//*[@name='UploadFiles']"),
		// fileName);
		//Thread.sleep(500);
		uploadFile(driver, By.cssSelector("input[type='file'][name='UploadFiles']"), fileName);
		// Optional: verify the uploaded file appears in list
		//Thread.sleep(500);
		WebElement uploadedFileLabel = driver.findElement(By.cssSelector("li.e-upload-file-list"));

		String uploadedName = uploadedFileLabel.getAttribute("data-file-name");
		System.out.println("✔ File shown in UI: " + uploadedName);
		});
	}

	// Click on the next button of 'select dataset' page.
	public void gotoDatasetNextbtn() {
		validateElementIsVisible(driver, clickNextButtondatasetNextbtn, "Next button of 'Select Dataset' page ");
		try {
			clickOnElement(driver, clickNextButtondatasetNextbtn, "next button of 'Select Dataset' page.", 20);
			ExtentManager.logInfo("Next button of 'Select Dataset' page is clickable.");
		} catch (Exception e) {
			//Base.extentReportFail(driver, "gotoDatasetNextbtn","Next button of 'Select Dataset' page is not clickable.");
		}
	}

	/// ********* Select the Available Table ********** ///.

	public boolean selectionAvailabeTable(String tableName) {
		return executeStep("Select the table name form the avialble table list.", () -> {
		// Click on the available table search field
		clickOnElement(driver, clickAvailableTable, "search available table.", 20);
		// Entering the available table name
		sendkeysToElement(driver, clickAvailableTable, "staff table name", tableName);
		// Select the table checkbox from the list.
		clickOnElement(driver, selectAvailableTable, "seach button icon", 20);
		// Click on the 'Move to' button
		clickOnElement(driver, clickMoveToButton, "Move to button.", 20);
		});
	}

	// Click on the next button of 'select table' page.
	public boolean gotoSelettableNextbtn() {
		return executeStep("Click the Next button of 'Select Dataset' page.", () -> {
		validateElementIsVisible(driver, clickNextButtonSelettableNextbtn, "Next button of 'Select Dataset' page ");
		
			clickOnElement(driver, clickNextButtonSelettableNextbtn, "next button of 'Select Teables' page", 20);
			ExtentManager.logInfo("Next button of 'Select Teables' page is clickable.");
			//Base.extentReportFail(driver, "gotoSelettableNextbtn","Next button of 'Select Teables' page is not clickable." + e.getMessage());

		});
	}

	// Click on the next button of 'select table' page.
	public boolean gotoImportSQLNextbtn() {
		return executeStep("Click the Next button of 'Import SQL' page.", () -> {
		validateElementIsVisible(driver, ClickNextButtonimportSQLNextbtn, "Next button of 'Import SQL' page ");

			clickOnElement(driver, ClickNextButtonimportSQLNextbtn, "next button of 'Import SQL' page.", 20);
			ExtentManager.logInfo("Next button of 'Import SQL' page is clickable.");
			//Base.extentReportFail(driver, "gotoImportSQLNextbtn","Next button of 'Import SQL' page is not clickable." + e.getMessage());
		});
	}

	// Click on the 'Generate' button.
	public boolean clickOnGenerate() {
		return executeStep("Click the Generate button.", () -> {
		validateElementIsVisible(driver, clickGenerateButton, "'Generate' button ");
			clickOnElement(driver, clickGenerateButton, "generate button.", 20);
			ExtentManager.logInfo("Generate button is clickable.");
			//Base.extentReportFail(driver, "clickOnGenerate", "Generate button is not clickable." + e.getMessage());
		});
	}

	// Click on the 'Go To Preview' button.
	public boolean clickOnGoToPreview() {
		return executeStep("Click the Go To Preview button.", () -> {
		validateElementIsVisible(driver, clickGoToPreviewButton, "'Go To Preview' button ");
			clickOnElement(driver, clickGoToPreviewButton, "go to preview button", 20);
			ExtentManager.logInfo("Go To Preview button is clickable.");
			//Base.extentReportFail(driver, "clickOnGoToPreview","Go To Preview button is not clickable." + e.getMessage());
		});
	}

	// Select the generated entity from the 'Preview' page.
	public boolean selectGeneratedEntity() {
		return executeStep("Select the entity form the preview page.", () -> {
		clickOnElement(driver, selectEntity, "checkbox for entity selection.", 20);
		validateElementIsVisible(driver, selectEntity, "The selected entity ");
		});
	}

	// Click on the 'Publish' button.
	public boolean clickOnPublish() {
		return executeStep("Click the Publish button.", () -> {
		validateElementIsVisible(driver, clikPublishButton, "Publish button ");
		clickOnElement(driver, clikPublishButton, "publish button.", 10);
		});
	}

	// Click on the 'Go To Publish' button.
	public boolean clickOnGoToPublish() throws InterruptedException {
		return executeStep("Click the Go To Publish button.", () -> {
		validateElementIsVisible(driver, clickGoToPublishButton, "'Go To Publish' button ");
			clickOnElement(driver, clickGoToPublishButton, "go to publish button.", 50);
			ExtentManager.logInfo( "Go To Publish button is clickable.");
			//Base.extentReportFail(driver,"clickOnGoToPublish", "Go To Publish button is not clickable." + e.getMessage());
		});
	}

	// Click on the hyperlink of the published rule to navigate to the data testing.
	public void clickOnPublishedRule() throws InterruptedException {
		Thread.sleep(500);
		// Capture parent window
		String parent = PageUtil.getParentWindow(driver);

		isInvisibleLoader(driver, loader);
		By ruleNameLocator1 = By.xpath("//tbody[@role='rowgroup']/tr/td/a");

		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(2000));

		WebElement ruleElement = wait1.until(ExpectedConditions.visibilityOfElementLocated(ruleNameLocator1));
		
		// Wait until input value is not empty
		wait1.until(d -> !ruleElement.getText().trim().isEmpty());

		String ruleNamePublishPage = ruleElement.getText();

		ExtentManager.logInfo( "Rule Name from the Published Page.. " + ruleNamePublishPage);

		System.out.println("Rule Name from Published Page: " + ruleNamePublishPage);

		// Click on the hyperlink.
		clickOnElement(driver, clickHyperlinkPublishRule, "published rule", 100);
		ExtentManager.logInfo( "Navigated to new tab");

		// Switch to child
		String child = switchToNewWindow(driver, parent, 30);
		Thread.sleep(500);
		isInvisibleLoader(driver, loader);
		By ruleNameLocator2 = By.xpath("//*[@placeholder='Enter rule name']");

		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement ruleName2 = wait2.until(ExpectedConditions.presenceOfElementLocated(ruleNameLocator2));

		// Wait until input value is not empty
		wait2.until(d -> !ruleName2.getDomProperty("value").isEmpty());

		String ruleNameDataTestingPage = ruleName2.getDomProperty("value");

		ExtentManager.logInfo("Rule Name is from the Data Testing Page.. " + ruleNameDataTestingPage);

		System.out.println("Rule Name from Data Testing Page: " + ruleNameDataTestingPage);

		// === Perform child validations ===
		Thread.sleep(500);
		if (ruleNamePublishPage.trim().equals(ruleNameDataTestingPage.trim())) {
			ExtentManager.logPass(
					"Rule Name is validated successfully. " + ruleNameDataTestingPage);
			System.out.println("Rule Name is matched....");

		} else {
			ExtentManager.logFail("Rule Name is mismatch.   Expectd:   " + ruleNamePublishPage
					+ "but found:  " + ruleNameDataTestingPage);
			System.out.println("Rule Name is not matched....");
		}

		Thread.sleep(500);
		// Close child
		closeChildAndReturn(driver, child, parent);
	}

	// Go to the Data Testing
	public void navigatHomePage() {
		goTo(ConfigReader.getProperty("homePageUrl"));
	}

}
