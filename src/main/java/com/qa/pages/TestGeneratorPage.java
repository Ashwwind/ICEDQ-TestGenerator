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

		} catch (Throwable e) {

			// 👇 Capture UI error message if shown
			captureUIErrorIfPresent();

			ExtentManager.logFail(stepName + " ➝ FAILED due to: " + e.getMessage());

			navigatHomePage(); // recover for next flow
			return false;
		}
	}


	private void stopIfFailed(boolean stepStatus) {
		if (!stepStatus) {
			throw new RuntimeException("Stopping flow due to step failure");
		}
	}

	/// ************* ///
	// Default Dynamic Template
	/// ************* ///

	public void createRuleUsingDefaultDynamicTemplate(String ruleType, String templateName, String workspaceName,
			String folderName, String connectionType, String connectionName, String schemaName, String tableName)
			throws InterruptedException {

		stopIfFailed(clickTestGenerator());
	    stopIfFailed(clickOnRuleType(ruleType));

	    stopIfFailed(clickOnSearchField());
	    stopIfFailed(enterOnSearchField(templateName));
	    stopIfFailed(selectExistingChecksumTemplate());

	    stopIfFailed(clickOnWorkspaceField(workspaceName));
	    stopIfFailed(clickOnFolderField(folderName));

	    stopIfFailed(gotoWorkspaceNextbtn());
	    stopIfFailed(gotoRuleMetadataNextbtn());
	    stopIfFailed(gotoCheckMetadataNextbtn());
	    stopIfFailed(gotoNotificationNextbtn());

	    stopIfFailed(selectionSourceDataset1(connectionType, connectionName, schemaName));

	    if (!ruleType.equalsIgnoreCase("validation")
	            && !ruleType.equalsIgnoreCase("pushdown")) {
	        stopIfFailed(selectionTargetDataset(connectionType, connectionName, schemaName));
	    }

	    stopIfFailed(gotoDatasetNextbtn());
	    stopIfFailed(selectionAvailabeTable(tableName));
	    stopIfFailed(gotoSelettableNextbtn());

	    stopIfFailed(clickOnGenerate());
	    stopIfFailed(clickOnGoToPreview());
	    stopIfFailed(selectGeneratedEntity());
	    stopIfFailed(clickOnPublish());
	    stopIfFailed(clickOnGoToPublish());
	    
	    stopIfFailed(clickOnPublishedRule());

		navigatHomePage();// 9. Navigate Data Testing
	}

	// ============================
	// Default Import Template
	// ============================

	public void createRuleUsingDefaultImportTemplate(String ruleType, String templateName, String workspaceName,
			String folderName, String connectionType, String connectionName) throws InterruptedException {

		clickTestGenerator();

		boolean isReconOrChecksum = ruleType.equalsIgnoreCase("checksum") || ruleType.equalsIgnoreCase("recon");

		clickOnRuleType(ruleType); // switch case

		clickOnSearchField(); // 2. Search & Select Template
		enterOnSearchField(templateName);
		selectExistingChecksumTemplate();

		clickOnWorkspaceField(workspaceName); // 3. Select Workspace

		clickOnFolderField(folderName); // 4. Select Folder

		gotoWorkspaceNextbtn(); // Next Buttons
		gotoRuleMetadataNextbtn();
		gotoCheckMetadataNextbtn();
		gotoNotificationNextbtn();

		selectionSourceDatasetForImport(connectionType, connectionName); // 5. Select Source Dataset

		if (isReconOrChecksum) { // 6. Select Target Dataset

			selectionTargetDatasetForImport(connectionType, connectionName);

		} else {
			System.out.println("Skipping Target Dataset for rule type: " + ruleType);
		}

		gotoDatasetNextbtn(); // Next Buttons

		switch (ruleType.toLowerCase()) { // 7. Import SQL (upload file based on ruleType)
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

		gotoImportSQLNextbtn(); // Next Buttons

		clickOnGenerate(); // 8. Generate → Preview → Publish
		clickOnGoToPreview();
		selectGeneratedEntity();
		clickOnPublish();
		clickOnGoToPublish();
		clickOnPublishedRule();

		navigatHomePage(); // 9. Navigate Data Testing
	}

	// ============================
	// Page method
	// ============================

	public boolean clickTestGenerator() {

		return clickOnField(driver, testGeneratorModue, "Test Generator", "button");
	}

	// Click the Checksum rule type from the wizard page.
//	public boolean clickOnChecksum() {
//
//		return clickOnField(driver, selectChecksumWizard, "Checksum rule type", "tab");
//	}
	
	public boolean clickOnChecksum1() {
		boolean flag=true;
		
		try {
			if (flag)
			{
				clickOnField(driver, selectChecksumWizard, "Checksum rule type", "tab");
			}
		} catch (Exception e) {
			ExtentManager.logFail(e + " clickOnChecksum1 :: " + e.toString());
			flag=false;
		}
		return flag;
	}

	public boolean clickOnRuleType(String ruleType) {
		By locator = null;
		switch (ruleType.toLowerCase()) {
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
			throw new IllegalArgumentException("InvaRule Type: " + ruleType);
		}

		return clickOnField(driver, locator, ruleType + " rule type", "tab");

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

		return clickOnField(driver, selectReconWizard, "Recon rule type", "tab");
	}

	// Click the Validation rule type from the wizard page.
	public boolean clickOnValidation() {

		return clickOnField(driver, selectValidationWizard, "Validation rule type", "tab");
	}

	// Click the Pushdown rule type from the wizard page.
	public boolean clickOnPushdown() {

		return clickOnField(driver, selectPushdownWizard, "Pushdown rule type", "tab");
	}

	// Click on the search field box
	public boolean clickOnSearchField() {

		return clickOnField(driver, clickSearchTemplateField, "'Search template' search field ", "search field");
	}

	// Enter the data on the search field
	public boolean enterOnSearchField(String templateName) {

		boolean isTextEntered = sendkeysToElement1(driver, enterTemplateName, templateName, templateName);

		boolean isSearchClicked = clickOnField(driver, clickSearchButtonIcon, "search button icon",
				"search button icon");

		return isTextEntered && isSearchClicked;
	}

	// Select the existing template form the list
	public boolean selectExistingChecksumTemplate() {

		return clickOnField(driver, selectExistingDynamicChecksumTemplate, "select searched item", "serarch item");
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
			// Base.extentReportFail(driver, "clickOnWorkspaceField","Failed to press Enter
			// on element '" + workspaceName + "'" + e.getMessage());
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
			clickOnElement(driver, By.xpath("//div[@id='pathtree']//span[@title='Search']"), "Search folder", 30);
			isInvisibleLoader(driver, loader);
			clickOnElement(driver, By.xpath("//div[contains(@class, 'e-text-content')]"), "Search button icon", 10);
			clickOnElement(driver, By.xpath("//span[@class='e-icons e-check']//parent::button"), "Seached folder name",
					10);
			ExtentManager.logPass("Folder name selected");
		});

	}

	public boolean gotoWorkspaceNextbtn() {

		return clickOnField(driver, clickNextButtonWorkspaceNextbtn, "Next button of 'Select Container' page",
				"button");
	}

	public boolean gotoRuleMetadataNextbtn() {
		return executeStep("Click the Next button of 'Define Rule Metadata' page.", () -> {
			validateElementIsVisible(driver, clickNextButtonmetadataNextbtn,
					"Next button of 'Define Rule Metadata' page ");
			clickOnElement(driver, clickNextButtonmetadataNextbtn, "next button of 'Define Rule Metadata' page", 20);
			ExtentManager.logInfo("Next button of 'Define Rule Metadata' page is clickable.");
			// Base.extentReportFail(driver, "gotoRuleMetadataNextbtn","Next button of
			// 'Define Rule Metadata' page is not clickable." + e.getMessage());
		});
	}

	public boolean gotoCheckMetadataNextbtn() {
		return executeStep("Click the Next button of 'Define Rule Metadata' page.", () -> {
			validateElementIsVisible(driver, clickNextButtoncheckNextbtn,
					"Next button of 'Define Rule Metadata' page ");
			clickOnElement(driver, clickNextButtoncheckNextbtn, "next button of 'Define Check Metadata' page", 20);
			ExtentManager.logInfo("Next button of 'Define Check Metadata' page is clickable.");
			// Base.extentReportFail(driver, "gotoCheckMetadataNextbtn","Next button of
			// 'Define Check Metadata' page is not clickable." + e.getMessage());
		});
	}

	public boolean gotoNotificationNextbtn() {
		return executeStep("Click the Next button of 'Configure Notifications' page.", () -> {
			validateElementIsVisible(driver, clickNextButtonnotificationNextbtn,
					"Next button of 'Configure Notifications' page ");
			clickOnElement(driver, clickNextButtonnotificationNextbtn, "next button of 'Configure Notifications' page",
					20);
			ExtentManager.logInfo("Next button of 'Configure Notifications' page is clickable.");
			// Base.extentReportFail(driver, "gotoNotificationNextbtn","Next button of
			// 'Configure Notifications' page is not clickable." + e.getMessage());
		});
	}

	/// ********* Select the source dataset ********** ///
///// *******************************/////
/////////////// ********************************* ////////////////
///// *******************************/////

	public boolean selectionSourceDataset(String connectionType, String connectionName, String schemaName) {

		return executeStep("Select source dataset details.", () -> {

			// Click on the source connection type dropdown
			clickOnElement(driver, clickSourcedatasetConnectionTypeDropdown, "select connection type.", 30);

			if (connectionType.equalsIgnoreCase("Database")) {

				clickOnElement(driver, selectDatabaseConnectionType, "source database connection.", 20);

				clickOnElement(driver, clickSourcedatasetConnectionDropdown, "select connection dropdown.", 20);

				sendkeysToElement(driver, enterSourceConnectionName, "source connection name", connectionName);

				sendkeysToEnter(driver, enterSourceConnectionName, "source connection name");

				ExtentManager.logInfo("Database connection name selected successfully.");

			} else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse")) {

				clickOnElement(driver, selectCloudDataWarehouseConnectionType,
						"source cloud data warehouse connection.", 20);

				clickOnElement(driver, clickSourcedatasetConnectionDropdown, "select connection dropdown.", 20);

				sendkeysToElement(driver, enterSourceConnectionName, "source connection name", connectionName);

				sendkeysToEnter(driver, enterSourceConnectionName, "source connection name");

				ExtentManager.logInfo("Cloud warehouse connection name selected successfully.");

				clickOnElement(driver, By.xpath(
						"(//div[contains(text(),'Source Dataset')]/parent::form//following::ejs-dropdownlist[@placeholder='Choose Database']//span[@formcontrolname='database'])[1]"),
						"database dropdown.", 20);

				By databaseInput = By.xpath(
						"//*[@placeholder='Select connection']/following::div[contains(@class,'e-popup')]//input[@type='text']");

				sendkeysToElement(driver, databaseInput, "source database name", "icedqrs");

				sendkeysToEnter(driver, databaseInput, "source database name");

				ExtentManager.logInfo("Database name selected successfully.");
			}

			// Schema selection
			validateElementIsVisible(driver, clickSourceSchemaDropdown, "schemaName dropdown");

			clickOnElement(driver, clickSourceSchemaDropdown, "choose schema dropdown.", 40);

			sendkeysToElement(driver, enterSourceSchemaName, "source schema name", schemaName);

			sendkeysToEnter(driver, enterSourceSchemaName, "source schema name");

			ExtentManager.logInfo("Schema selected successfully.");
		});
	}
	
	public boolean selectionSourceDataset1(String connectionType,
			String connectionName,
			String schemaName) {
		boolean flag = true;

		try {
			clickOnElement(driver, selectDatabaseConnectionType,"source database connection.", 20);

			clickOnElement(driver, clickSourcedatasetConnectionDropdown,"select connection dropdown.", 20);

			sendkeysToElement(driver, enterSourceConnectionName,"source connection name", connectionName);

			sendkeysToEnter(driver, enterSourceConnectionName,"source connection name");

		} catch (Exception e) {
			ExtentManager.logFail("selectionSourceDataset1 failed: " + e.toString());
			flag = false;
		}

		return flag;
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
	public boolean selectionTargetDataset(String connectionType, String connectionName, String schemaName) {

		return executeStep("Select target dataset details.", () -> {

			// Click on target connection type dropdown
			clickOnElement(driver, clickTargetdatasetConnectionTypeDropdown, "select connection type.", 30);

			if (connectionType.equalsIgnoreCase("Database")) {

				clickOnElement(driver, selectDatabaseConnectionType, "target database connection.", 20);

				clickOnElement(driver, clickTargetdatasetConnectionDropdown, "select connection dropdown.", 20);

				sendkeysToElement(driver, enterTargetConnectionName, "target connection name", connectionName);

				sendkeysToEnter(driver, enterTargetConnectionName, "target connection name");

				ExtentManager.logInfo("Target database connection selected successfully.");

			} else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse")) {

				clickOnElement(driver, selectCloudDataWarehouseConnectionType,
						"target cloud data warehouse connection.", 20);

				clickOnElement(driver, clickTargetdatasetConnectionDropdown, "select connection dropdown.", 20);

				sendkeysToElement(driver, enterTargetConnectionName, "target connection name", connectionName);

				sendkeysToEnter(driver, enterTargetConnectionName, "target connection name");

				ExtentManager.logInfo("Target cloud connection selected successfully.");

				clickOnElement(driver, By.xpath(
						"(//div[contains(text(),'Source Dataset')]/parent::form//following::ejs-dropdownlist[@placeholder='Choose Database']//span[@formcontrolname='database'])[2]"),
						"database dropdown.", 20);

				By databaseInput = By.xpath(
						"//*[@placeholder='Select connection']/following::div[contains(@class,'e-popup')]//input[@type='text']");

				sendkeysToElement(driver, databaseInput, "target database name", "icedqrs");

				sendkeysToEnter(driver, databaseInput, "target database name");

				ExtentManager.logInfo("Target database selected successfully.");
			}

			// Schema selection
			validateElementIsVisible(driver, clickTargetSchemaDropdown, "schemaName dropdown");

			clickOnElement(driver, clickTargetSchemaDropdown, "choose schema dropdown.", 40);

			sendkeysToElement(driver, enterSourceSchemaName, "target schema name", schemaName);

			sendkeysToEnter(driver, enterSourceSchemaName, "target schema name");

			ExtentManager.logInfo("Target schema selected successfully.");
		});
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
			// Thread.sleep(500);
			// validateElementIsVisible(driver, By.xpath("//*[@name='UploadFiles']"),
			// fileName);
			// Thread.sleep(500);
			uploadFile(driver, By.cssSelector("input[type='file'][name='UploadFiles']"), fileName);
			// Optional: verify the uploaded file appears in list
			// Thread.sleep(500);
			WebElement uploadedFileLabel = driver.findElement(By.cssSelector("li.e-upload-file-list"));

			String uploadedName = uploadedFileLabel.getAttribute("data-file-name");
			System.out.println("✔ File shown in UI: " + uploadedName);
		});
	}

	// Click on the next button of 'select dataset' page.
	public boolean gotoDatasetNextbtn() {
		return executeStep("Click the Next button of 'Select Dataset' page", () -> {
			validateElementIsVisible(driver, clickNextButtondatasetNextbtn, "Next button of 'Select Dataset' page ");
			clickOnElement(driver, clickNextButtondatasetNextbtn, "next button of 'Select Dataset' page.", 20);
			ExtentManager.logInfo("Next button of 'Select Dataset' page is clickable.");
		});	
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
			// Base.extentReportFail(driver, "gotoSelettableNextbtn","Next button of 'Select
			// Teables' page is not clickable." + e.getMessage());
		});
	}

	// Click on the next button of 'select table' page.
	public boolean gotoImportSQLNextbtn() {
		return executeStep("Click the Next button of 'Import SQL' page.", () -> {
			validateElementIsVisible(driver, ClickNextButtonimportSQLNextbtn, "Next button of 'Import SQL' page ");

			clickOnElement(driver, ClickNextButtonimportSQLNextbtn, "next button of 'Import SQL' page.", 20);
			ExtentManager.logInfo("Next button of 'Import SQL' page is clickable.");
			// Base.extentReportFail(driver, "gotoImportSQLNextbtn","Next button of 'Import
			// SQL' page is not clickable." + e.getMessage());
		});
	}

	// Click on the 'Generate' button.
	public boolean clickOnGenerate() {
		return executeStep("Click the Generate button.", () -> {
			validateElementIsVisible(driver, clickGenerateButton, "'Generate' button ");
			clickOnElement(driver, clickGenerateButton, "generate button.", 20);
			ExtentManager.logInfo("Generate button is clickable.");
			// Base.extentReportFail(driver, "clickOnGenerate", "Generate button is not
			// clickable." + e.getMessage());
		});
	}

	// Click on the 'Go To Preview' button.
	public boolean clickOnGoToPreview() {
		return executeStep("Click the Go To Preview button.", () -> {
			validateElementIsVisible(driver, clickGoToPreviewButton, "'Go To Preview' button ");
			clickOnElement(driver, clickGoToPreviewButton, "go to preview button", 20);
			ExtentManager.logInfo("Go To Preview button is clickable.");
			// Base.extentReportFail(driver, "clickOnGoToPreview","Go To Preview button is
			// not clickable." + e.getMessage());
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
			ExtentManager.logInfo("Go To Publish button is clickable.");
			// Base.extentReportFail(driver,"clickOnGoToPublish", "Go To Publish button is
			// not clickable." + e.getMessage());
		});
	}

	// Click on the hyperlink of the published rule to navigate to the data testing.
	public boolean clickOnPublishedRule() {

		return executeStep("Validate published rule and navigate to Data Testing page.", () -> {

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

			// Capture parent window
			String parent = PageUtil.getParentWindow(driver);

			isInvisibleLoader(driver, loader);

			By ruleNameLocator1 = By.xpath("//tbody[@role='rowgroup']/tr/td/a");

			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(60));
			WebElement ruleElement = wait1.until(ExpectedConditions.visibilityOfElementLocated(ruleNameLocator1));

			wait1.until(d -> !ruleElement.getText().trim().isEmpty());

			String ruleNamePublishPage = ruleElement.getText();
			ExtentManager.logInfo("Rule Name from Published Page: " + ruleNamePublishPage);

			// Click hyperlink
			clickOnElement(driver, clickHyperlinkPublishRule, "published rule", 60);

			// Switch to child window
			String child = switchToNewWindow(driver, parent, 30);
			isInvisibleLoader(driver, loader);

			By ruleNameLocator2 = By.xpath("//*[@placeholder='Enter rule name']");
			WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(30));

			WebElement ruleName2 = wait2.until(ExpectedConditions.presenceOfElementLocated(ruleNameLocator2));

			wait2.until(d -> !ruleName2.getDomProperty("value").isEmpty());

			String ruleNameDataTestingPage = ruleName2.getDomProperty("value");
			ExtentManager.logInfo("Rule Name from Data Testing Page: " + ruleNameDataTestingPage);

			// Validation
			if (!ruleNamePublishPage.trim().equals(ruleNameDataTestingPage.trim())) {
				throw new AssertionError("Rule name mismatch. Expected: " + ruleNamePublishPage + " but found: "
						+ ruleNameDataTestingPage);
			}

			ExtentManager.logPass("Rule Name validated successfully.");

			// Close child window
			closeChildAndReturn(driver, child, parent);
		});
	}

	// Go to the Data Testing
	public void navigatHomePage() {
		goTo(ConfigReader.getProperty("homePageUrl"));
	}

}
