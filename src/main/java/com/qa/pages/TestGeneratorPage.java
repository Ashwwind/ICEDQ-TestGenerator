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

	        // 🔥 Detect toast AFTER action
	        String uiError = captureUIErrorIfPresent(driver);
	        if (uiError != null) {
	            throw new RuntimeException(uiError);
	        }

	        ExtentManager.logPass(stepName + " ➝ PASSED");
	        return true;

	    } catch (Throwable e) {

	        ExtentManager.logFail(stepName + " ➝ FAILED due to: " + e.getMessage());

	        // Exit flow
	        navigatHomePage();

	        return false;
	    }
	}


	public void stopIfFailed(boolean stepResult) {
	    if (!stepResult) {
	        throw new FlowAbortException("Flow aborted due to UI error or step failure");
	    }
	}

	public static class FlowAbortException extends RuntimeException {
	    public FlowAbortException(String message) {
	        super(message);
	    }
	}


	/// ************* ///
	// Default Dynamic Template
	/// ************* ///

	public void createRuleUsingDefaultDynamicTemplate(String ruleType, String templateName, String workspaceName,
			String folderName, String connectionType, String connectionName, String schemaName, String tableName)
			throws InterruptedException {

		// Step 1: Click Test Generator
		stopIfFailed(executeStep("Click Test Generator", this::clickTestGenerator));

		// Step 2: Click on Rule Type
		stopIfFailed(executeStep("Click On Rule Type: " + ruleType, () -> clickOnRuleType(ruleType)));

		// Step 3: Search and select template
		stopIfFailed(executeStep("Search Template: " + templateName, () -> {
			clickOnSearchField();
			enterOnSearchField(templateName);
			selectExistingChecksumTemplate();
		}));

		// Step 4: Workspace & Folder selection
		stopIfFailed(executeStep("Select Workspace: " + workspaceName, () -> clickOnWorkspaceField(workspaceName)));
		stopIfFailed(executeStep("Select Folder: " + folderName, () -> clickOnFolderField(folderName)));

		// Step 5: Navigate Next buttons
		stopIfFailed(executeStep("Workspace Next", this::gotoWorkspaceNextbtn));
		stopIfFailed(executeStep("Rule Metadata Next", this::gotoRuleMetadataNextbtn));
		stopIfFailed(executeStep("Check Metadata Next", this::gotoCheckMetadataNextbtn));
		stopIfFailed(executeStep("Notification Next", this::gotoNotificationNextbtn));

		// Step 6: Source Dataset selection
		stopIfFailed(executeStep("Select Source Dataset: " + connectionType + "/" + connectionName + "/" + schemaName,
				() -> selectionSourceDataset(connectionType, connectionName, schemaName)));

		// Step 7: Target Dataset selection for non-validation & non-pushdown
		if (!ruleType.equalsIgnoreCase("validation") && !ruleType.equalsIgnoreCase("pushdown")) {
			stopIfFailed(
					executeStep("Select Target Dataset: " + connectionType + "/" + connectionName + "/" + schemaName,
							() -> selectionTargetDataset(connectionType, connectionName, schemaName)));
		}

		// Step 8: Dataset & Table selection
		stopIfFailed(executeStep("Dataset Next", this::gotoDatasetNextbtn));
		stopIfFailed(executeStep("Select Available Table: " + tableName, () -> selectionAvailabeTable(tableName)));
		stopIfFailed(executeStep("Table Next", this::gotoSelettableNextbtn));

		// Step 9: Generate & Preview
		stopIfFailed(executeStep("Click Generate", this::clickOnGenerate));
		stopIfFailed(executeStep("Go To Preview", this::clickOnGoToPreview));
		stopIfFailed(executeStep("Select Generated Entity", this::selectGeneratedEntity));

		// Step 10: Publish
		stopIfFailed(executeStep("Click Publish", this::clickOnPublish));
		stopIfFailed(executeStep("Go To Publish", () -> {
			try {
				clickOnGoToPublish();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}));

		// Step 11: Verify Published Rule
		stopIfFailed(executeStep("Click On Published Rule", this::clickOnPublishedRule));

		// Step 12: Navigate back to Home Page for next flow
		navigatHomePage();
	}


	// ============================
	// Default Import Template
	// ============================

	public void createRuleUsingDefaultImportTemplate(String ruleType, String templateName, String workspaceName,
	        String folderName, String connectionType, String connectionName) throws InterruptedException {

	    boolean isReconOrChecksum = ruleType.equalsIgnoreCase("checksum") || ruleType.equalsIgnoreCase("recon");

	    // Step 1: Click Test Generator
	    stopIfFailed(executeStep("Click Test Generator", this::clickTestGenerator));

	    // Step 2: Click on Rule Type
	    stopIfFailed(executeStep("Click On Rule Type: " + ruleType, () -> clickOnRuleType(ruleType)));

	    // Step 3: Search Template
	    stopIfFailed(executeStep("Search Template: " + templateName, () -> {
	        clickOnSearchField();
	        enterOnSearchField(templateName);
	        selectExistingChecksumTemplate();
	    }));

	    // Step 4: Workspace & Folder selection
	    stopIfFailed(executeStep("Select Workspace: " + workspaceName, () -> clickOnWorkspaceField(workspaceName)));
	    stopIfFailed(executeStep("Select Folder: " + folderName, () -> clickOnFolderField(folderName)));

	    // Step 5: Navigate Next Buttons
	    stopIfFailed(executeStep("Workspace Next", this::gotoWorkspaceNextbtn));
	    stopIfFailed(executeStep("Rule Metadata Next", this::gotoRuleMetadataNextbtn));
	    stopIfFailed(executeStep("Check Metadata Next", this::gotoCheckMetadataNextbtn));
	    stopIfFailed(executeStep("Notification Next", this::gotoNotificationNextbtn));

	    // Step 6: Source Dataset selection
	    stopIfFailed(executeStep("Select Source Dataset for Import: " + connectionType + "/" + connectionName,
	            () -> selectionSourceDatasetForImport(connectionType, connectionName)));

	    // Step 7: Target Dataset selection for checksum/recon
	    if (isReconOrChecksum) {
	        stopIfFailed(executeStep("Select Target Dataset for Import: " + connectionType + "/" + connectionName,
	                () -> selectionTargetDatasetForImport(connectionType, connectionName)));
	    }

	    // Step 8: Dataset Next button
	    stopIfFailed(executeStep("Dataset Next", this::gotoDatasetNextbtn));

	    // Step 9: Upload files based on ruleType and connectionType
	    stopIfFailed(executeStep("Upload Files for Rule Type: " + ruleType, () -> {
	        switch (ruleType.toLowerCase()) {
	            case "checksum":
	                if (connectionType.equalsIgnoreCase("Database"))
						try {
							uploadingFiles("Checksum.xlsx");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse"))
						try {
							uploadingFiles("Checksum - Redshift.xlsx");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					else if (connectionType.equalsIgnoreCase("File"))
						try {
							uploadingFiles("Checksum - Flat File-SQL.xlsx");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                break;

	            case "recon":
	                if (connectionType.equalsIgnoreCase("Database"))
						try {
							uploadingFiles("Recon.xlsx");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse"))
						try {
							uploadingFiles("Recon - Redshift.xlsx");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					else if (connectionType.equalsIgnoreCase("File"))
						try {
							uploadingFiles("Recon - Flat File-SQL.xlsx");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                break;

	            case "validation":
	                if (connectionType.equalsIgnoreCase("Database"))
						try {
							uploadingFiles("Validation.xlsx");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse"))
						try {
							uploadingFiles("Validation - Redshift.xlsx");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					else if (connectionType.equalsIgnoreCase("File"))
						try {
							uploadingFiles("Validation - Flat File-SQL.xlsx");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                break;

	            case "pushdown":
	                if (connectionType.equalsIgnoreCase("Database"))
						try {
							uploadingFiles("Pushdown.xlsx");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse"))
						try {
							uploadingFiles("Pushdown - Redshift.xlsx");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					else if (connectionType.equalsIgnoreCase("File"))
						try {
							uploadingFiles("Pushdown - Flat File-SQL.xlsx");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                break;

	            default:
	                throw new IllegalArgumentException("Invalid rule type: " + ruleType);
	        }
	    }));

	    // Step 10: Import SQL Next button
	    stopIfFailed(executeStep("Import SQL Next", this::gotoImportSQLNextbtn));

	    // Step 11: Generate, Preview, Publish
	    stopIfFailed(executeStep("Click Generate", this::clickOnGenerate));
	    stopIfFailed(executeStep("Go To Preview", this::clickOnGoToPreview));
	    stopIfFailed(executeStep("Select Generated Entity", this::selectGeneratedEntity));
	    stopIfFailed(executeStep("Click Publish", this::clickOnPublish));
	    stopIfFailed(executeStep("Go To Publish", () -> {
			try {
				clickOnGoToPublish();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}));

	    // Step 12: Verify Published Rule
	    stopIfFailed(executeStep("Click On Published Rule", this::clickOnPublishedRule));

	    // Step 13: Navigate to Home Page for next flow
	    navigatHomePage();
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
			//validateElementIsVisible(driver, clickNextButtonmetadataNextbtn,"Next button of 'Define Rule Metadata' page ");
			clickOnElement(driver, clickNextButtonmetadataNextbtn, "next button of 'Define Rule Metadata' page", 20);
			ExtentManager.logInfo("Next button of 'Define Rule Metadata' page is clickable.");
			// Base.extentReportFail(driver, "gotoRuleMetadataNextbtn","Next button of
			// 'Define Rule Metadata' page is not clickable." + e.getMessage());
		});
	}

	public boolean gotoCheckMetadataNextbtn() {
		return executeStep("Click the Next button of 'Define Rule Metadata' page.", () -> {
			//validateElementIsVisible(driver, clickNextButtoncheckNextbtn,"Next button of 'Define Rule Metadata' page ");
			clickOnElement(driver, clickNextButtoncheckNextbtn, "next button of 'Define Check Metadata' page", 20);
			ExtentManager.logInfo("Next button of 'Define Check Metadata' page is clickable.");
			// Base.extentReportFail(driver, "gotoCheckMetadataNextbtn","Next button of
			// 'Define Check Metadata' page is not clickable." + e.getMessage());
		});
	}

	public boolean gotoNotificationNextbtn() {
		return executeStep("Click the Next button of 'Configure Notifications' page.", () -> {
			//validateElementIsVisible(driver, clickNextButtonnotificationNextbtn,"Next button of 'Configure Notifications' page ");
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
			clickOnElement(driver, clickSourcedatasetConnectionTypeDropdown, "select source connection type.", 30);

			if (connectionType.equalsIgnoreCase("Database")) {
				
				//validateElementIsVisible(driver, ClickNextButtonimportSQLNextbtn, schemaName);

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
			//validateElementIsVisible(driver, clickSourceSchemaDropdown, "schemaName dropdown");

			clickOnElement(driver, clickSourceSchemaDropdown, "choose source schema dropdown.", 20);

			sendkeysToElement(driver, enterSourceSchemaName, "source schema name", schemaName);

			sendkeysToEnter(driver, enterSourceSchemaName, "source schema name");

			ExtentManager.logInfo("Schema selected successfully.");
		});
	}
	
	public boolean selectionSourceDataset1(String connectionType,String connectionName,String schemaName) {
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

		

	public boolean selectionSourceDatasetForImport(String connectionType, String connectionName) {

		if (!executeStep("Click Source Dataset Connection Type Dropdown", () -> clickOnElement(driver,
				clickSourcedatasetConnectionTypeDropdown, "select connection type.", 30))) {
			return false;
		}

		if (connectionType.equalsIgnoreCase("Database")) {

			if (!executeStep("Select Source Database Connection Type",
					() -> clickOnElement(driver, selectDatabaseConnectionType, "source database connection.", 20))) {
				return false;
			}

		} else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse")) {

			if (!executeStep("Select Source Cloud Data Warehouse Connection Type", () -> clickOnElement(driver,
					selectCloudDataWarehouseConnectionType, "source cloud data warehouse connection.", 20))) {
				return false;
			}

		} else if (connectionType.equalsIgnoreCase("File")) {

			if (!executeStep("Select Source File Connection Type",
					() -> clickOnElement(driver, selectFileConnectionType, "source file connection.", 20))) {
				return false;
			}

		} else {
			throw new IllegalArgumentException("Invalid connection type: " + connectionType);
		}

		if (!executeStep("Click Source Dataset Connection Dropdown", () -> clickOnElement(driver,
				clickSourcedatasetConnectionDropdown, "select connection dropdown.", 20))) {
			return false;
		}

		if (!executeStep("Enter Source Connection Name",
				() -> sendkeysToElement(driver, enterSourceConnectionName, "source connection name", connectionName))) {
			return false;
		}

		if (!executeStep("Confirm Source Connection Name",
				() -> sendkeysToEnter(driver, enterSourceConnectionName, "source connection name"))) {
			return false;
		}

		return true;
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
				
				//validateElementIsVisible(driver, selectDatabaseConnectionType, "database dropdown");

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
			//validateElementIsVisible(driver, clickTargetSchemaDropdown, "schemaName dropdown");

			clickOnElement(driver, clickTargetSchemaDropdown, "choose schema dropdown.", 20);

			sendkeysToElement(driver, enterSourceSchemaName, "target schema name", schemaName);

			sendkeysToEnter(driver, enterSourceSchemaName, "target schema name");

			ExtentManager.logInfo("Target schema selected successfully.");
		});
	}

	public boolean selectionTargetDatasetForImport(String connectionType, String connectionName) {

		if (!executeStep("Click Target Dataset Connection Type Dropdown", () -> clickOnElement(driver,
				clickTargetdatasetConnectionTypeDropdown, "select connection type.", 30))) {
			return false;
		}

		if (connectionType.equalsIgnoreCase("Database")) {

			if (!executeStep("Select Target Database Connection Type",
					() -> clickOnElement(driver, selectDatabaseConnectionType, "target database connection.", 20))) {
				return false;
			}

		} else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse")) {

			if (!executeStep("Select Target Cloud Data Warehouse Connection Type", () -> clickOnElement(driver,
					selectCloudDataWarehouseConnectionType, "target cloud data warehouse connection.", 20))) {
				return false;
			}

		} else if (connectionType.equalsIgnoreCase("File")) {

			if (!executeStep("Select Target File Connection Type",
					() -> clickOnElement(driver, selectFileConnectionType, "target file connection.", 20))) {
				return false;
			}

		} else {
			throw new IllegalArgumentException("Invalid connection type: " + connectionType);
		}

		if (!executeStep("Click Target Dataset Connection Dropdown", () -> clickOnElement(driver,
				clickTargetdatasetConnectionDropdown, "select connection dropdown.", 20))) {
			return false;
		}

		if (!executeStep("Enter Target Connection Name",
				() -> sendkeysToElement(driver, enterTargetConnectionName, "target connection name", connectionName))) {
			return false;
		}

		if (!executeStep("Confirm Target Connection Name",
				() -> sendkeysToEnter(driver, enterTargetConnectionName, "target connection name"))) {
			return false;
		}

		return true;
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
			//validateElementIsVisible(driver, clickNextButtondatasetNextbtn, "Next button of 'Select Dataset' page ");
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
			//validateElementIsVisible(driver, clickNextButtonSelettableNextbtn, "Next button of 'Select Dataset' page ");

			clickOnElement(driver, clickNextButtonSelettableNextbtn, "next button of 'Select Teables' page", 20);
			ExtentManager.logInfo("Next button of 'Select Teables' page is clickable.");
			// Base.extentReportFail(driver, "gotoSelettableNextbtn","Next button of 'Select
			// Teables' page is not clickable." + e.getMessage());
		});
	}

	// Click on the next button of 'select table' page.
	public boolean gotoImportSQLNextbtn() {
		return executeStep("Click the Next button of 'Import SQL' page.", () -> {
			//validateElementIsVisible(driver, ClickNextButtonimportSQLNextbtn, "Next button of 'Import SQL' page ");

			clickOnElement(driver, ClickNextButtonimportSQLNextbtn, "next button of 'Import SQL' page.", 20);
			ExtentManager.logInfo("Next button of 'Import SQL' page is clickable.");
			// Base.extentReportFail(driver, "gotoImportSQLNextbtn","Next button of 'Import
			// SQL' page is not clickable." + e.getMessage());
		});
	}

	// Click on the 'Generate' button.
	public boolean clickOnGenerate() {
		return executeStep("Click the Generate button.", () -> {
			//validateElementIsVisible(driver, clickGenerateButton, "'Generate' button ");
			clickOnElement(driver, clickGenerateButton, "generate button.", 20);
			ExtentManager.logInfo("Generate button is clickable.");
			// Base.extentReportFail(driver, "clickOnGenerate", "Generate button is not
			// clickable." + e.getMessage());
		});
	}

	// Click on the 'Go To Preview' button.
	public boolean clickOnGoToPreview() {
		return executeStep("Click the Go To Preview button.", () -> {
			//validateElementIsVisible(driver, clickGoToPreviewButton, "'Go To Preview' button ");
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
			//validateElementIsVisible(driver, selectEntity, "The selected entity ");
		});
	}

	// Click on the 'Publish' button.
	public boolean clickOnPublish() {
		return executeStep("Click the Publish button.", () -> {
			//validateElementIsVisible(driver, clikPublishButton, "Publish button ");
			clickOnElement(driver, clikPublishButton, "publish button.", 10);
		});
	}

	// Click on the 'Go To Publish' button.
	public boolean clickOnGoToPublish() throws InterruptedException {
		return executeStep("Click the Go To Publish button.", () -> {
			//validateElementIsVisible(driver, clickGoToPublishButton, "'Go To Publish' button ");
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
