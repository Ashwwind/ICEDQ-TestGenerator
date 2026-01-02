package com.qa.pages;

import java.time.Duration;

import org.apache.commons.collections4.functors.CatchAndRethrowClosure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qa.config.ConfigReader;
import com.qa.extentreportlistener.ExtentManager;
import com.qa.utils.PageUtil;

public class Test1 extends PageUtil {
	private WebDriver driver;
	public static final int waitTime = 6; // Global wait time

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

//		// Step 7 (only for recon / checksum)
//		if (!ruleType.equalsIgnoreCase("validation") && !ruleType.equalsIgnoreCase("pushdown")) {
//			selectionTargetDataset(connectionType, targetConnectionName, schemaName);
//		}
//
//		// Step 8
//		gotoDatasetNextbtn();
//		selectionAvailableTable(tableName);
//		gotoSelettableNextbtn();
//
//		// Step 9
//		clickOnGenerate();
//		clickOnGoToPreview();
//		selectGeneratedEntity();
//
//		// Step 10
//		clickOnPublish();
//		clickOnGoToPublish();
//
//		// Step 11
//		clickOnPublishedRule();
//
//		// Step 12
//		navigatHomePage();
	}

	// ============================
	// Page method
	// ============================

	
	public boolean clickTestGenerator() { /// Clicking on Test Generator Module

		try {

			return clickOnField(driver, testGeneratorModue, "Test Generator", "button");

		} catch (Exception e) {
			ExtentManager.logFail("Element is not clicable. " + testGeneratorModue + e.getMessage());
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
	        return clickOnField(driver, clickSearchTemplateField,
	                "'Search template' search field", "search field");
	    } catch (Exception e) {
	        ExtentManager.logFail("Element is not clickable. "
	                + clickSearchTemplateField + e.getMessage());
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
	public boolean selectExistingChecksumTemplate() {

		try {
			boolean flag = clickOnField(driver, selectExistingDynamicChecksumTemplate, "select searched item",
					"search item");

			if (!flag) {
				ExtentManager.logFail("Searched template item is not clickable.");
			}
			return flag;

		} catch (Exception e) {
			ExtentManager.logFail("Exception while selecting searched template item: " + e.getMessage());
			return false;
		}
	}

	public boolean clickOnWorkspaceField(String workspaceName) {

	    boolean flag = true; // controls whether to perform actions
	    try {
	        isInvisibleLoader(driver, loader);

	        // Open workspace dropdown
	        clickOnField(driver, clickOnWorkspaceDropdown, "select workspace dropdown.", "Workspace dropdown");
	        ExtentManager.logInfo("Workspace dropdown opened.");

	        // Click search field
	        clickOnField(driver, selectSearchWorkspace, "searched workspace.", "Search button icon");

	        // Enter workspace name and press ENTER
	        sendkeysToElement1(driver, selectSearchWorkspace, "Select workspace field", workspaceName);
	        sendKeysEnterToElement2(driver, selectSearchWorkspace, "Text Field");
//	        sendkeysToElement(driver, selectSearchWorkspace, "Select workspace field", workspaceName);
//	        sendkeysToEnter(driver, selectSearchWorkspace, "Select workspace field");

	        isInvisibleLoader(driver, loader);

	        ExtentManager.logPass("Workspace name selected: " + workspaceName);

	    } catch (Exception e) {
	        System.out.println("Error selecting workspace: " + e.getMessage());
	        flag = false; // set flag to false if exception occurs
	    }

	    return flag; // return true if all actions succeeded, false otherwise
	}


	// Click on the Folder selector field
	
	public boolean clickOnFolderField(String folderName) {

	    boolean flag = true; // indicates success or failure

	    try {
	        isInvisibleLoader(driver, loader);

	        // Open folder dropdown
	       
	        clickOnField(driver, clickOnFolderDropdown, "Folder dropdown", "Folder dropdown");
	        
	        ExtentManager.logInfo("Folder dropdown opened.");

	        // Search folder
	        sendkeysToElement1(driver, searchFolderName, "searching folder name", folderName);

	        // Click search icon
	        //clickOnField(driver, By.xpath("//div[@id='pathtree']//span[@title='Search']"), "Search folder", waitTime);
	        clickOnField(driver, By.xpath("//div[@id='pathtree']//span[@title='Search']"), "Search folder", "Search button icon");

	        isInvisibleLoader(driver, loader);

	        // Select folder from results
	        clickOnField(driver, By.xpath("//div[contains(@class, 'e-text-content')]"), "Folder result", "Folder result");
	      

	        clickOnField(driver, By.xpath("//span[@class='e-icons e-check']//parent::button"), "Selected folder name", "List");

	        isInvisibleLoader(driver, loader);

	        ExtentManager.logPass("Folder name selected: " + folderName);

	    } catch (Exception e) {
	        System.out.println("Error selecting folder: " + e.getMessage());
	        flag = false; // mark as failed
	    }

	    return flag; // true if all actions succeeded, false if any exception occurred
	}


	public boolean gotoWorkspaceNextbtn() {

	    boolean flag = true;

	    try {
	        flag = clickOnField(driver, clickNextButtonWorkspaceNextbtn, 
	                            "Next button of 'Select Container' page", "button");
	        if (!flag) {
	            ExtentManager.logInfo("Failed to click on Next button of 'Select Container' page.");
	        }
	    } catch (Exception e) {
	        System.out.println("Error clicking Next button: " + e.getMessage());
	        flag = false;
	    }

	    return flag;
	}


	public boolean gotoRuleMetadataNextbtn() {

	    boolean flag = true;

	    try {
	        flag = clickOnField(driver, clickNextButtonmetadataNextbtn, 
	                            "Next button of 'Define Rule Metadata' page", "Button");
	        if (flag) {
	            ExtentManager.logInfo("Next button of 'Define Rule Metadata' page is clickable.");
	        } else {
	            ExtentManager.logInfo("Failed to click Next button of 'Define Rule Metadata' page.");
	        }
	    } catch (Exception e) {
	        System.out.println("Error clicking Next button: " + e.getMessage());
	        flag = false;
	    }

	    return flag;
	}

	public boolean gotoCheckMetadataNextbtn() {

	    boolean flag = true;

	    try {
	        flag = clickOnField(driver, clickNextButtoncheckNextbtn, 
	                            "Next button of 'Define Check Metadata' page", "Button");
	        if (flag) {
	            ExtentManager.logInfo("Next button of 'Define Check Metadata' page is clickable.");
	        } else {
	            ExtentManager.logInfo("Failed to click Next button of 'Define Check Metadata' page.");
	        }
	    } catch (Exception e) {
	        System.out.println("Error clicking Next button: " + e.getMessage());
	        flag = false;
	    }

	    return flag;
	}


	public boolean gotoNotificationNextbtn() {

	    boolean flag = true;

	    try {
	        flag = clickOnField(driver, clickNextButtonnotificationNextbtn, 
	                            "Next button of 'Configure Notifications' page", "Button");
	        if (flag) {
	            ExtentManager.logInfo("Next button of 'Configure Notifications' page is clickable.");
	        } else {
	            ExtentManager.logInfo("Failed to click Next button of 'Configure Notifications' page.");
	        }
	    } catch (Exception e) {
	        System.out.println("Error clicking Next button: " + e.getMessage());
	        flag = false;
	    }

	    return flag;
	}


	/// ********* Select the source dataset ********** ///
	///// *******************************/////
	/////////////// ********************************* ////////////////
	///// *******************************/////

	public boolean selectionSourceDataset(String connectionType, String connectionName, String schemaName) {

	    boolean flag = true;

	    try {
	        // Click on the source connection type dropdown
	        clickOnField(driver, clickSourcedatasetConnectionTypeDropdown, "select source connection type.", "Dropdow field");

	        if (connectionType.equalsIgnoreCase("Database")) {

	            clickOnField(driver, selectDatabaseConnectionType, "source database connection.", "List");
	            isInvisibleLoader(driver, loader);

	            clickOnField(driver, clickSourcedatasetConnectionDropdown, "select connection dropdown.", "List item");

//	            sendkeysToElement(driver, enterSourceConnectionName, "source connection name", connectionName);
//	            sendkeysToEnter(driver, enterSourceConnectionName, "source connection name");
	            sendkeysToElement1(driver, enterSourceConnectionName, "source connection name", connectionName);
	            sendKeysEnterToElement2(driver, enterSourceConnectionName, "Text Field");
	            isInvisibleLoader(driver, loader);

	            ExtentManager.logInfo("Database connection name selected successfully.");

	        } else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse")) {

	            clickOnField(driver, selectCloudDataWarehouseConnectionType, 
	                         "source cloud data warehouse connection.", "Dropdow field");
	            isInvisibleLoader(driver, loader);

	            clickOnField(driver, clickSourcedatasetConnectionDropdown, "select connection dropdown.", "List");

//	            sendkeysToElement(driver, enterSourceConnectionName, "source connection name", connectionName);
//	            sendkeysToEnter(driver, enterSourceConnectionName, "source connection name");
	            sendkeysToElement1(driver, enterSourceConnectionName, "source connection name", connectionName);
	            sendKeysEnterToElement2(driver, enterSourceConnectionName, "Text Field");
	            isInvisibleLoader(driver, loader);

	            ExtentManager.logInfo("Cloud warehouse connection name selected successfully.");

	            By UseMetadataCache = By.xpath(
	                "//*[@id='e-content-element_5']/div/div[2]/div[1]/form/div[2]/div[3]/div/ejs-checkbox/label/span[2]");
	            clickOnField(driver, UseMetadataCache, "Use Metadata Cache check box", "Checkbox");
	            isInvisibleLoader(driver, loader);

	            // Click database dropdown
	            clickOnField(driver, By.xpath(
	                "(//div[contains(text(),'Source Dataset')]/parent::form//following::ejs-dropdownlist[@placeholder='Choose Database']//span[@formcontrolname='database'])[1]"),
	                "database dropdown.", "Dropdown Field");
	            isInvisibleLoader(driver, loader);

	            By databaseInput = By.xpath(
	                "//*[@placeholder='Select connection']/following::div[contains(@class,'e-popup')]//input[@type='text']");
//	            sendkeysToElement(driver, databaseInput, "source database name", "icedqrs");
//	            sendkeysToEnter(driver, databaseInput, "source database name");
	            sendkeysToElement1(driver, databaseInput, "source database name", "icedqrs");
	            sendKeysEnterToElement2(driver, databaseInput, "Text Field");
	            isInvisibleLoader(driver, loader);

	            ExtentManager.logInfo("Database name selected successfully.");
	        }

	        // Schema selection
	        clickOnField(driver, clickSourceSchemaDropdown, "choose source schema dropdown.", "Dropdown Field");
	        isInvisibleLoader(driver, loader);

//	        sendkeysToElement(driver, enterSourceSchemaName, "source schema name", schemaName);
//	        sendkeysToEnter(driver, enterSourceSchemaName, "source schema name");
	        sendkeysToElement1(driver, enterSourceSchemaName, "source schema name", schemaName);
	        sendKeysEnterToElement2(driver, enterSourceSchemaName,"Text Field");
	        isInvisibleLoader(driver, loader);

	        ExtentManager.logInfo("Schema selected successfully.");

	    } catch (Exception e) {
	        System.out.println("Error selecting source dataset: " + e.getMessage());
	        flag = false;
	    }

	    return flag;
	}

}


//	public void selectionSourceDatasetForImport(String connectionType, String connectionName) {
//
//		// Click Source Dataset Connection Type Dropdown
//		clickOnField(driver, clickSourcedatasetConnectionTypeDropdown, "select connection type.", waitTime);
//
//		// Select connection type
//		switch (connectionType.toLowerCase()) {
//		case "database":
//			clickOnField(driver, selectDatabaseConnectionType, "source database connection.", waitTime);
//			break;
//
//		case "cloud data warehouse":
//			clickOnField(driver, selectCloudDataWarehouseConnectionType, "source cloud data warehouse connection.",
//					waitTime);
//			break;
//
//		case "file":
//			clickOnField(driver, selectFileConnectionType, "source file connection.", waitTime);
//			break;
//
//		default:
//			throw new IllegalArgumentException("Invalid connection type: " + connectionType);
//		}
//
//		// Click Source Dataset Connection Dropdown
//		clickOnField(driver, clickSourcedatasetConnectionDropdown, "select connection dropdown.", waitTime);
//		isInvisibleLoader(driver, loader);
//
//		// Enter and confirm connection name
//		sendkeysToElement(driver, enterSourceConnectionName, "source connection name", connectionName);
//		sendkeysToEnter(driver, enterSourceConnectionName, "source connection name");
//		isInvisibleLoader(driver, loader);
//
//		ExtentManager.logInfo("Source dataset selected successfully: " + connectionType + " / " + connectionName);
//	}
//
//	/// ********* Select the target dataset ********** ///
//	///// *******************************/////
//	/////////////// ********************************* ////////////////
//	///// *******************************/////
//	public void selectionTargetDataset(String connectionType, String connectionName, String schemaName) {
//
//		// Click target connection type dropdown
//		clickOnField(driver, clickTargetdatasetConnectionTypeDropdown, "select target connection type.", waitTime);
//
//		if (connectionType.equalsIgnoreCase("Database")) {
//
//			clickOnField(driver, selectDatabaseConnectionType, "target database connection.", waitTime);
//			isInvisibleLoader(driver, loader);
//
//			clickOnField(driver, clickTargetdatasetConnectionDropdown, "select target connection dropdown.",
//					waitTime);
//
//			sendkeysToElement(driver, enterTargetConnectionName, "target connection name", connectionName);
//
//			sendkeysToEnter(driver, enterTargetConnectionName, "target connection name");
//			isInvisibleLoader(driver, loader);
//
//			ExtentManager.logInfo("Target database connection selected successfully.");
//
//		} else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse")) {
//
//			clickOnField(driver, selectCloudDataWarehouseConnectionType, "target cloud data warehouse connection.",
//					waitTime);
//			isInvisibleLoader(driver, loader);
//
//			clickOnField(driver, clickTargetdatasetConnectionDropdown, "select target connection dropdown.",
//					waitTime);
//
//			sendkeysToElement(driver, enterTargetConnectionName, "target connection name", connectionName);
//
//			sendkeysToEnter(driver, enterTargetConnectionName, "target connection name");
//			isInvisibleLoader(driver, loader);
//
//			ExtentManager.logInfo("Target cloud connection selected successfully.");
//
//			// Click database dropdown
//			clickOnField(driver, By.xpath(
//					"(//div[contains(text(),'Source Dataset')]/parent::form//following::ejs-dropdownlist[@placeholder='Choose Database']//span[@formcontrolname='database'])[2]"),
//					"database dropdown.", waitTime);
//			isInvisibleLoader(driver, loader);
//
//			By databaseInput = By.xpath(
//					"//*[@placeholder='Select connection']/following::div[contains(@class,'e-popup')]//input[@type='text']");
//
//			sendkeysToElement(driver, databaseInput, "target database name", "icedqrs");
//
//			sendkeysToEnter(driver, databaseInput, "target database name");
//			isInvisibleLoader(driver, loader);
//
//			ExtentManager.logInfo("Target database selected successfully.");
//		}
//
//		// Schema selection
//		clickOnField(driver, clickTargetSchemaDropdown, "choose target schema dropdown.", waitTime);
//		isInvisibleLoader(driver, loader);
//
//		sendkeysToElement(driver, enterSourceSchemaName, "target schema name", schemaName);
//
//		sendkeysToEnter(driver, enterSourceSchemaName, "target schema name");
//		isInvisibleLoader(driver, loader);
//
//		ExtentManager.logInfo("Target schema selected successfully.");
//	}
//
//	public void selectionTargetDatasetForImport(String connectionType, String connectionName) {
//		// Click Target Dataset Connection Type Dropdown
//		clickOnField(driver, clickTargetdatasetConnectionTypeDropdown, "select target connection type.", waitTime);
//
//		// Select connection type
//		switch (connectionType.toLowerCase()) {
//		case "database":
//			clickOnField(driver, selectDatabaseConnectionType, "target database connection.", waitTime);
//			break;
//
//		case "cloud data warehouse":
//			clickOnField(driver, selectCloudDataWarehouseConnectionType, "target cloud data warehouse connection.",
//					waitTime);
//			break;
//
//		case "file":
//			clickOnField(driver, selectFileConnectionType, "target file connection.", waitTime);
//			break;
//
//		default:
//			throw new IllegalArgumentException("Invalid connection type: " + connectionType);
//		}
//
//		// Click Target Dataset Connection Dropdown
//		clickOnField(driver, clickTargetdatasetConnectionDropdown, "select target connection dropdown.", waitTime);
//		isInvisibleLoader(driver, loader);
//
//		// Enter and confirm connection name
//		sendkeysToElement(driver, enterTargetConnectionName, "target connection name", connectionName);
//
//		sendkeysToEnter(driver, enterTargetConnectionName, "target connection name");
//		isInvisibleLoader(driver, loader);
//
//		ExtentManager.logInfo("Target dataset selected successfully: " + connectionType + " / " + connectionName);
//	}
//
//	// Import SQL Files
//	public void uploadingFiles(String fileName) {
//		// Upload file
//		uploadFile(driver, By.cssSelector("input[type='file'][name='UploadFiles']"), fileName);
//
//		// Optional: verify the uploaded file appears in the list
//		WebElement uploadedFileLabel = driver.findElement(By.cssSelector("li.e-upload-file-list"));
//		String uploadedName = uploadedFileLabel.getAttribute("data-file-name");
//
//		ExtentManager.logInfo("✔ File shown in UI: " + uploadedName);
//	}
//
//	// Click on the Next button of 'Select Dataset' page
//	public void gotoDatasetNextbtn() {
//		clickOnField(driver, clickNextButtondatasetNextbtn, "next button of 'Select Dataset' page.", waitTime);
//		ExtentManager.logInfo("Next button of 'Select Dataset' page is clickable.");
//	}
//
//	/// ********* Select the Available Table ********** ///.
//
//	public void selectionAvailableTable(String tableName) {
//		// Click on the available table search field
//		clickOnField(driver, clickAvailableTable, "search available table.", waitTime);
//
//		// Enter the table name
//		sendkeysToElement(driver, clickAvailableTable, "available table name", tableName);
//
//		// Select the table checkbox from the list
//		clickOnField(driver, selectAvailableTable, "table checkbox", waitTime);
//
//		// Click on the 'Move to' button
//		clickOnField(driver, clickMoveToButton, "'Move to' button", waitTime);
//
//		ExtentManager.logInfo("Table '" + tableName + "' selected successfully.");
//	}
//
//	// Click on the Next button of 'Select Table' page
//	public void gotoSelettableNextbtn() {
//		clickOnField(driver, clickNextButtonSelettableNextbtn, "Next button of 'Select Tables' page", waitTime);
//		ExtentManager.logInfo("Next button of 'Select Tables' page is clickable.");
//	}
//
//	// Click on the Next button of 'Import SQL' page
//	public void gotoImportSQLNextbtn() {
//		clickOnField(driver, ClickNextButtonimportSQLNextbtn, "Next button of 'Import SQL' page", waitTime);
//		ExtentManager.logInfo("Next button of 'Import SQL' page is clickable.");
//	}
//
//	// Click on the 'Generate' button
//	public void clickOnGenerate() {
//		clickOnField(driver, clickGenerateButton, "Generate button", waitTime);
//		ExtentManager.logInfo("Generate button is clickable.");
//	}
//
//	// Click on the 'Go To Preview' button
//	public void clickOnGoToPreview() {
//		clickOnField(driver, clickGoToPreviewButton, "Go To Preview button", waitTime);
//		ExtentManager.logInfo("Go To Preview button is clickable.");
//	}
//
//	// Select the generated entity from the 'Preview' page
//	public void selectGeneratedEntity() {
//		clickOnField(driver, selectEntity, "Checkbox for entity selection", waitTime);
//		ExtentManager.logInfo("Generated entity selected successfully.");
//	}
//
//	// Click on the 'Publish' button
//	public void clickOnPublish() {
//		clickOnField(driver, clikPublishButton, "Publish button", waitTime);
//		ExtentManager.logInfo("Publish button is clickable.");
//	}
//
//	// Click on the 'Publish' button
//	public void clickOnPublishTheWorkflow(String folderName) {
//
//		By clickWorkflowPublishButton = By.xpath("//*[@id='publish_item']");
//		By selectWorkflow = By.xpath("//*[@id='publish_item-popup']/ul/li[contains(text(), 'Workflow')]");
//		By clickFolderDropdown = By.xpath("//*[@id='publishRW']/div[2]/div[1]/div[2]/div[2]/div/div[1]");
//		By searchFolderField = By.xpath("//*[@placeholder='Search folder']");
//		By clickOnFolderName = By.xpath("//*[@id='ruleListRighttreeView_active']/div[2]/span/div");
//		By selectFolder = By.xpath("//*[@id='ruleListRighttreeView_active']/div[2]/span/div/div/button[1]/span");
//		By clickOnCreate = By.xpath("//*[@id='publishRW']/div[3]/button[2]");
//
//		clickOnField(driver, clickWorkflowPublishButton, "publish item button", waitTime);
//
//		clickOnField(driver, selectWorkflow, "workflow button", waitTime);
//		isInvisibleLoader(driver, loader);
//
//		clickOnField(driver, clickFolderDropdown, "folder dropdown", waitTime);
//		clickOnField(driver, searchFolderField, "search folder field", waitTime);
//		sendkeysToElement(driver, searchFolderField, "search folder field", folderName);
//		sendkeysToEnter(driver, searchFolderField, folderName);
//		isInvisibleLoader(driver, loader);
//
//		clickOnField(driver, clickOnFolderName, "searched folder", waitTime);
//		clickOnField(driver, selectFolder, "to select the folder", waitTime);
//		isInvisibleLoader(driver, loader);
//
//		clickOnField(driver, clickOnCreate, "create button", waitTime);
//		isInvisibleLoader(driver, loader);
//
//		ExtentManager.logInfo("Workflow is created successfully...");
//	}
//
//	// Click on the 'Go To Publish' button
//	public void clickOnGoToPublish() throws InterruptedException {
//		clickOnField(driver, clickGoToPublishButton, "Go To Publish button", waitTime);
//		ExtentManager.logInfo("Go To Publish button is clickable.");
//	}
//
//	// Click on the hyperlink of the published rule to navigate to the Data Testing
//	public void clickOnPublishedRule() {
//
//		try {
//			Thread.sleep(500);
//		} catch (InterruptedException e) {
//			Thread.currentThread().interrupt();
//		}
//
//		// Capture parent window
//		String parent = PageUtil.getParentWindow(driver);
//
//		isInvisibleLoader(driver, loader);
//
//		// Get rule name from Published page
//		By ruleNameLocator1 = By.xpath("//tbody[@role='rowgroup']/tr/td/a");
//		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(60));
//
//		WebElement ruleElement = wait1.until(ExpectedConditions.visibilityOfElementLocated(ruleNameLocator1));
//		wait1.until(d -> !ruleElement.getText().trim().isEmpty());
//
//		String ruleNamePublishPage = ruleElement.getText().trim();
//		ExtentManager.logInfo("Rule Name from Published Page: " + ruleNamePublishPage);
//
//		// Click hyperlink
//		clickOnField(driver, clickHyperlinkPublishRule, "Published Rule", waitTime);
//
//		// Switch to child window
//		String child = switchToNewWindow(driver, parent, 30);
//		isInvisibleLoader(driver, loader);
//
//		// Get rule name from Data Testing page
//		By ruleNameLocator2 = By.xpath("//*[@placeholder='Enter rule name']");
//		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(30));
//
//		WebElement ruleName2 = wait2.until(ExpectedConditions.presenceOfElementLocated(ruleNameLocator2));
//		wait2.until(d -> !ruleName2.getDomProperty("value").isEmpty());
//
//		String ruleNameDataTestingPage = ruleName2.getDomProperty("value").trim();
//		ExtentManager.logInfo("Rule Name from Data Testing Page: " + ruleNameDataTestingPage);
//
//		// Validation
//		if (!ruleNamePublishPage.equals(ruleNameDataTestingPage)) {
//			throw new AssertionError(
//					"Rule name mismatch. Expected: " + ruleNamePublishPage + " but found: " + ruleNameDataTestingPage);
//		}
//
//		ExtentManager.logPass("Rule Name validated successfully.");
//
//		// Run rule
//		runPublishRule();
//
//		// Validate instance ID
//		clickOnRuleRecentrun();
//		try {
//			validateRuleInstanceID(driver);
//		} catch (InterruptedException e) {
//			Thread.currentThread().interrupt();
//			ExtentManager.logInfo("Instance ID validation interrupted: " + e.getMessage());
//		}
//
//		// Close child and return to parent
//		closeChildAndReturn(driver, child, parent);
//	}
//
//	// Click on the hyperlink of the published workflow to navigate to the Data
//	// Testing - workflow details page.
//	public void clickOnPublishedWorkflow() {
//
//		// Capture parent window
//		String parent = PageUtil.getParentWindow(driver);
//
//		isInvisibleLoader(driver, loader);
//
//		// Get workflow name from Published page
//		By workflowNameLocator1 = By.xpath("//tbody[@role='rowgroup']/tr/td/a");
//		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(60));
//
//		WebElement workflowElement = wait1.until(ExpectedConditions.visibilityOfElementLocated(workflowNameLocator1));
//		wait1.until(d -> !workflowElement.getText().trim().isEmpty());
//
//		String workflowNamePublishPage = workflowElement.getText().trim();
//		ExtentManager.logInfo("Workflow Name from Published Page: " + workflowNamePublishPage);
//
//		// Click hyperlink
//		clickOnField(driver, clickHyperlinkPublishRule, "Published Workflow", waitTime);
//
//		// Switch to child window
//		String child = switchToNewWindow(driver, parent, 30);
//		isInvisibleLoader(driver, loader);
//
//		// Click Overview tab
//		By clickOnOverview = By.xpath("//*[@id='e-item-ej2Tab_0']/div/div/div/div");
//		clickOnField(driver, clickOnOverview, "Overview tab section", waitTime);
//
//		// Get workflow name from Data Testing page
//		By workflowNameLocator2 = By.id("inputWorkflowName");
//		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(30));
//
//		WebElement workflowNameField = wait2.until(ExpectedConditions.presenceOfElementLocated(workflowNameLocator2));
//		wait2.until(d -> !workflowNameField.getDomProperty("value").trim().isEmpty());
//
//		String workflowNameDataTestingPage = workflowNameField.getDomProperty("value").trim();
//
//		ExtentManager.logInfo("Workflow Name from Workflow details Page: " + workflowNameDataTestingPage);
//
//		// Validation
//		if (!workflowNamePublishPage.equals(workflowNameDataTestingPage)) {
//			throw new AssertionError("Workflow name mismatch. Expected: " + workflowNamePublishPage + " but found: "
//					+ workflowNameDataTestingPage);
//		}
//
//		ExtentManager.logPass("Workflow Name validated successfully.");
//
//		// Run workflow
//		runPublishWorkflow();
//
//		// Validate instance ID
//		clickOnWorkflowRecentrun();
//		try {
//			validateWorkflowInstanceID(driver);
//		} catch (InterruptedException e) {
//			Thread.currentThread().interrupt();
//			ExtentManager.logInfo("Instance ID validation interrupted: " + e.getMessage());
//		}
//
//		// Close child and return to parent
//		closeChildAndReturn(driver, child, parent);
//	}
//
//	// Go to the Data Testing
//	public void navigatHomePage() {
//		goTo(ConfigReader.getProperty("homePageUrl"));
//	}
//
//	// Define Rule Metadata
//	public void validateDefineRuleMetadataPage() {
//		// validateElement(driver, ClickNextButtonimportSQLNextbtn, null, null); // Name
//		// field
//		WebElement RuleProperty = driver.findElement(By.xpath("//*[@id=\\\"acrdn_header_11\\\"]/div[1]/div"));
//		scrollToElement(driver, RuleProperty);
//	}
//
//	// Define Check Metadata
//	public void validateDefineCheckMetadataPage() {
//		// validateElement(driver, ClickNextButtonimportSQLNextbtn, null, null); // Name
//		// field
//		WebElement RuleProperty = driver.findElement(By.xpath("//*[@id=\\\"acrdn_header_11\\\"]/div[1]/div"));
//		scrollToElement(driver, RuleProperty);
//	}
//
//	// Run the publish rule on the Data Testing module.
//	public void runPublishRule() {
//		// Click on the Run button.
//		clickOnField(driver, By.xpath("//*[@id='scrollPane']/app-flow-diagram/div[1]/button[1]"), "run button",
//				waitTime);
//		isInvisibleLoader(driver, loader);
//
//	}
//
//	// Run the publish workflow on the Data Testing module.
//	public void runPublishWorkflow() {
//		// Click on the Run button.
//		clickOnField(driver, By.xpath(
//				"//*[@id='imaster']/app-workflow-orchestrator/div[2]/div[1]/app-workflows/app-custom-workflow/div[1]/div/div[2]/ejs-tooltip/button[1]"),
//				"run button", waitTime);
//		isInvisibleLoader(driver, loader);
//
//	}
//
//	// Click on the Rule Recent runs button.
//	public void clickOnRuleRecentrun() {
//		// Click on the Recent runs button.
//		clickOnField(driver, By.xpath("//*[@id='scrollPane']/app-flow-diagram/div[1]/button[3]"),
//				"recent runs button", 10);
//		waitForSeconds(1);
//
//	}
//
//	// Click on the Workflow Recent runs button.
//	public void clickOnWorkflowRecentrun() {
//		// Click on the Recent runs button.
//		clickOnField(driver, By.xpath(
//				"//*[@id='imaster']/app-workflow-orchestrator/div[2]/div[1]/app-workflows/app-custom-workflow/div[1]/div/div[2]/ejs-tooltip/button[3]/span"),
//				"recent runs button", 10);
//		waitForSeconds(1);
//
//	}
//
//	// Validate Rule Instance ID...
//	public void validateRuleInstanceID(WebDriver driver) throws InterruptedException {
//
//		By refreshBtn = By.xpath("//*[@id='rrElement']/app-recent-run/div[1]/div[1]/div[2]/button");
//		By instanceIdLocator = By.xpath("//*[@id='parentGrid_content_table']/tbody/tr/td[3]");
//		By statusLocator = By.xpath("//*[@id='parentGrid_content_table']/tbody/tr/td[4]");
//
//		int maxRetries = 10;
//		int retryCount = 0;
//
//		while (retryCount < maxRetries) {
//
//			clickOnField(driver, refreshBtn, "Refresh button", waitTime);
//
//			waitForSeconds(1);
//
//			if (isElementDisplayed(driver, instanceIdLocator, 5)) {
//
//				String instanceId = driver.findElement(instanceIdLocator).getText();
//				String status = driver.findElement(statusLocator).getText().trim();
//
//				ExtentManager.logInfo("Instance ID: " + instanceId);
//				ExtentManager.logInfo("Current Status: " + status);
//
//				// Keep waiting while status is Submitted or Running
//				if (!status.equalsIgnoreCase("Submitted") && !status.equalsIgnoreCase("Running")) {
//
//					ExtentManager.logInfo("Final execution status: " + status);
//					return; // exit once proper status is reached
//				}
//			}
//
//			retryCount++;
//		}
//
//		throw new RuntimeException("Status did not change from Submitted/Running after retries");
//	}
//
//	// Validate Workflow Instance ID
//	public void validateWorkflowInstanceID(WebDriver driver) throws InterruptedException {
//
//		By refreshBtn = By.xpath("//*[@id='rrElement']/div/div/button");
//		By instanceIdLocator = By.xpath("//*[@id='parentGrid_content_table']/tbody/tr/td[3]");
//		By statusLocator = By.xpath("//*[@id='parentGrid_content_table']/tbody/tr/td[5]");
//
//		int maxRetries = 10;
//		int retryCount = 0;
//
//		while (retryCount < maxRetries) {
//
//			clickOnField(driver, refreshBtn, "Refresh button", waitTime);
//
//			waitForSeconds(1);
//
//			if (isElementDisplayed(driver, instanceIdLocator, 5)) {
//
//				String instanceId = driver.findElement(instanceIdLocator).getText();
//				String status = driver.findElement(statusLocator).getText().trim();
//
//				ExtentManager.logInfo("Instance ID: " + instanceId);
//				ExtentManager.logInfo("Current Status: " + status);
//
//				// Keep waiting while status is Submitted or Running
//				if (!status.equalsIgnoreCase("Submitted") && !status.equalsIgnoreCase("Running")) {
//
//					ExtentManager.logInfo("Final execution status: " + status);
//					return; // exit once proper status is reached
//				}
//			}
//
//			retryCount++;
//		}
//
//		throw new RuntimeException("Status did not change from Submitted/Running after retries");
//	}
//}
