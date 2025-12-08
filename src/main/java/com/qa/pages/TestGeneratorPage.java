package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.Status;
import com.qa.config.ConfigReader;
import com.qa.extentreportlistener.ExtentListener;
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
	By clickNextButtonWorkspaceNextbtn = getElementLocator(prop.getProperty("wizard.click.nextButton.workspaceNextbtn"));
	By clickNextButtonmetadataNextbtn = getElementLocator(prop.getProperty("wizard.click.nextButton.metadataNextbtn"));
	By clickNextButtoncheckNextbtn = getElementLocator(prop.getProperty("wizard.click.nextButton.checkNextbtn"));
	By clickNextButtonnotificationNextbtn = getElementLocator(prop.getProperty("wizard.click.nextButton.notificationNextbtn"));

	// Source Dataset
	By selectDatabaseConnectionType = getElementLocator(prop.getProperty("wizard.Database.connectionType"));
	By selectCloudDataWarehouseConnectionType = getElementLocator(prop.getProperty("wizard.CloudDataWarehouse.connectionType"));
	By selectFileConnectionType = getElementLocator(prop.getProperty("wizard.File.connectionType"));
	
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

	By clickNextButtondatasetNextbtn = getElementLocator(prop.getProperty("wizard.click.nextButton.datasetNextbtn"));
	By ClickNextButtonimportSQLNextbtn = getElementLocator(prop.getProperty("wizard.click.nextButton.importSQLNextbtn"));

	// Available Table
	By clickAvailableTable = getElementLocator(prop.getProperty("availableTable.click"));
	By selectAvailableTable = getElementLocator(prop.getProperty("availableTable.select.table"));
	By clickMoveToButton = getElementLocator(prop.getProperty("availableTable.moveto.click"));
	By clickNextButtonSelettableNextbtn = getElementLocator(prop.getProperty("availableTable.click.nextButton.SelettableNextbtn"));

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

	// Page method
	public void clickTestGenerator() {
		isInvisibleLoader(driver, loader);
		validateElementIsVisible(driver, testGeneratorModue, "Test Generator");
		try {
			clickOnElement(driver, testGeneratorModue, "Test Generator module.", 30);
			ExtentListener.test.get().log(Status.INFO, "Test Generator module is selected...");
		} catch (Exception e) {
			ExtentListener.test.get().log(Status.FAIL, "Test Generator module is not selected..." + e.getMessage());
		}

	}

	// Click the Checksum rule type from the wizard page.
	public void clickOnChecksum() {

		isInvisibleLoader(driver, loader);
		validateElementIsVisible(driver, selectChecksumWizard, "Checksum rule type");
		try {
			clickOnElement(driver, selectChecksumWizard, "Checksum rule wizard.", 50);
			ExtentListener.test.get().log(Status.INFO, "Defoult template is selected...");
		} catch (Exception e) {
			ExtentListener.test.get().log(Status.FAIL, "Defoult template is not selected..." + e.getMessage());
		}

//		// validate URl
//		String expectedUrl = "https://qa.onprem.icedq.com/rulegen-ui/#/wizard/checksum";
//		String actualUrl = driver.getCurrentUrl();
//
//		if (actualUrl.equals(expectedUrl)) {
//			ExtentListener.test.get().log(Status.PASS, "URL matched: " + actualUrl);
//		} else {
//			ExtentListener.test.get().log(Status.FAIL, "URL mismatch. Actual: " + actualUrl);
//		}
//
//		// Validate page title
//
//		WebElement title = driver.findElement(By.xpath("/html/body/app-root/div[2]/div/app-rule/div[2]/span[normalize-space(text())='Checksum Rule Wizard']"));
//
//		if (title.isDisplayed()) {
//			ExtentListener.test.get().log(Status.PASS, "Page title displayed: " + title.getText());
//		} else {
//			ExtentListener.test.get().log(Status.FAIL, "Page title not displayed");
//		}
//
//		// Validate search field
//		WebElement searchInput = driver.findElement(By.xpath("//input[contains(@placeholder,'Search template')]"));
//
//		if (searchInput.isDisplayed()) {
//			ExtentListener.test.get().log(Status.PASS, "Search field is displayed.");
//		} else {
//			ExtentListener.test.get().log(Status.FAIL, "Search field not visible.");
//		}
//
//		// Validate table columns
//		List<String> expectedHeaders = Arrays.asList("Name", "Description", "Type", "Last Updated");
//
//		List<WebElement> headers = driver.findElements(By.xpath("//table//th"));
//
//		for (int i = 0; i < expectedHeaders.size(); i++) {
//			if (headers.get(i).getText().equals(expectedHeaders.get(i))) {
//				ExtentListener.test.get().log(Status.PASS, "Header matched: " + headers.get(i).getText());
//			} else {
//				ExtentListener.test.get().log(Status.FAIL, "Header mismatch!");
//			}
//		}
	}

	// Click the Recon rule type from the wizard page.
	public void clickOnRecon() {
		isInvisibleLoader(driver, loader);
		validateElementIsVisible(driver, selectReconWizard, "Recon rule type");
		try {
			clickOnElement(driver, selectReconWizard, "Recon rule wizard.", 50);
			ExtentListener.test.get().log(Status.INFO, "Defoult template is selected...");
		} catch (Exception e) {
			ExtentListener.test.get().log(Status.FAIL, "Defoult template is not selected..." + e.getMessage());
		}

	}

	// Click the Validation rule type from the wizard page.
	public void clickOnValidation() {
		isInvisibleLoader(driver, loader);
		validateElementIsVisible(driver, selectValidationWizard, "Validation rule type");
		try {
			clickOnElement(driver, selectValidationWizard, "Validation rule wizard.", 50);
			ExtentListener.test.get().log(Status.INFO, "Defoult template is selected...");
		} catch (Exception e) {
			ExtentListener.test.get().log(Status.FAIL, "Defoult template is not selected..." + e.getMessage());
		}

	}

	// Click the Pushdown rule type from the wizard page.
	public void clickOnPushdown() {
		isInvisibleLoader(driver, loader);
		validateElementIsVisible(driver, selectPushdownWizard, "Pushdown rule type");
		try {
			clickOnElement(driver, selectPushdownWizard, "Pushdown rule wizard", 50);
			ExtentListener.test.get().log(Status.INFO, "Defoult template is selected...");
		} catch (Exception e) {
			ExtentListener.test.get().log(Status.FAIL, "Defoult template is not selected..." + e.getMessage());
		}

	}

	// Click on the search field box
	public void clickOnSearchField() {
		isInvisibleLoader(driver, loader);
		validateElementIsVisible(driver, clickSearchTemplateField, "'Search template' search field ");
		try {
			clickOnElement(driver, clickSearchTemplateField, "Search template.", 30);
			ExtentListener.test.get().log(Status.INFO, "'Search template' search box is clickable...");
		} catch (Exception e) {
			ExtentListener.test.get().log(Status.FAIL,
					"'Search template' search box is not clickable..." + e.getMessage());
		}

	}

	// Enter the data on the search field
	public void enterOnSearchField(String templateName) {
		sendkeysToElement(driver, enterTemplateName, "Search template", templateName);
		validateElementIsVisible(driver, clickSearchButtonIcon, "Search button icon on search template");
		try {
			clickOnElement(driver, clickSearchButtonIcon, "search button icon.", 30);
			ExtentListener.test.get().log(Status.INFO,
					"Initiating action: Press Enter on element '" + templateName + "'");
			ExtentListener.test.get().log(Status.PASS,
					"Action successful: Enter key pressed on element '" + templateName + "'");
		} catch (Exception e) {
			ExtentListener.test.get().log(Status.FAIL,
					"Failed to press Enter on element '" + templateName + "'" + e.getMessage());
		}
	}

	// Select the existing checksum template form the list
	public void selectExistingChecksumTemplate() {
		isInvisibleLoader(driver, loader);
		validateElementIsVisible(driver, selectExistingDynamicChecksumTemplate, "The searched template name");
		try {
			clickOnElement(driver, selectExistingDynamicChecksumTemplate, "searched item from the list.", 30);
			ExtentListener.test.get().log(Status.INFO, "Searched item is selected...");
		} catch (Exception e) {
			ExtentListener.test.get().log(Status.FAIL, "Searched item is not selected...");
		}
	}

	public void clickOnWorkspaceField(String workspaceName) {
		isInvisibleLoader(driver, loader);
		// Wait for workspace field to be visible and clickable
		clickOnElement(driver, clickOnWorkspaceDropdown, "select workspace dropdown.", 30);
		ExtentListener.test.get().info("Workspace dropdown opened.");
		// Click workspace dropdown
		clickOnElement(driver, selectSearchWorkspace, "searched workspace.", 30);
		// Click workspace dropdown // Click workspace dropdown
		try {
			sendkeysToElement(driver, selectSearchWorkspace, "Select workspace field", workspaceName + Keys.ENTER);
			ExtentListener.test.get().log(Status.INFO,
					"Initiating action: Press Enter on element '" + workspaceName + "'");
			ExtentListener.test.get().log(Status.PASS,
					"Action successful: Enter key pressed on element '" + workspaceName + "'");
		} catch (Exception e) {
			ExtentListener.test.get().log(Status.FAIL,
					"Failed to press Enter on element '" + workspaceName + "'" + e.getMessage());
		}
		isInvisibleLoader(driver, loader);
		ExtentListener.test.get().pass("Workspace name selected.");
		// System.out.println("Workspace '" + workspaceName + "' is selected
		// successfully.");
	}

	// Click on the Folder selector field

	public void clickOnFolderField(String folderName) {
		isInvisibleLoader(driver, loader);
		clickOnElement(driver, clickOnFolderDropdown, "Folder dropdown", 30);
		ExtentListener.test.get().info("Folder dropdown opened.");
		try {
			sendkeysToElement(driver, searchFolderName, "searching folder name.", folderName);
			ExtentListener.test.get().log(Status.INFO,
					"Initiating action: Press Enter on element '" + folderName + "'");
			ExtentListener.test.get().log(Status.PASS,
					"Action successful: Enter key pressed on element '" + folderName + "'");
		} catch (Exception e) {
			ExtentListener.test.get().log(Status.FAIL,
					"Failed to press Enter on element '" + folderName + "'" + e.getMessage());
		}
		clickOnElement(driver, By.xpath("//div[@id='pathtree']//span[@title='Search']"), "Search folder", 30);
		isInvisibleLoader(driver, loader);
		clickOnElement(driver, By.xpath("//div[contains(@class, 'e-text-content')]"), "Search button icon", 10);
		clickOnElement(driver, By.xpath("//span[@class='e-icons e-check']//parent::button"), "Seached folder name", 10);
		ExtentListener.test.get().pass("Folder name selected");
		// System.out.println("Folder '" + folderName + "' selected.");
	}

	public void gotoWorkspaceNextbtn() {
		validateElementIsVisible(driver, clickNextButtonWorkspaceNextbtn, "Next button of 'Select Container' page");
		try {
			clickOnElement(driver, clickNextButtonWorkspaceNextbtn, "next button of 'Select Container' page", 20);
			ExtentListener.test.get().log(Status.INFO, "Next button of 'Select Container' page is clickable.");
		} catch (Exception e) {
			ExtentListener.test.get().log(Status.FAIL,
					"Next button of 'Select Container' page is not clickable." + e.getMessage());
		}
	}

	public void gotoRuleMetadataNextbtn() {
		validateElementIsVisible(driver, clickNextButtonmetadataNextbtn, "Next button of 'Define Rule Metadata' page ");
		try {
			clickOnElement(driver, clickNextButtonmetadataNextbtn, "next button of 'Define Rule Metadata' page", 20);
			ExtentListener.test.get().log(Status.INFO, "Next button of 'Define Rule Metadata' page is clickable.");
		} catch (Exception e) {
			ExtentListener.test.get().log(Status.FAIL,
					"Next button of 'Define Rule Metadata' page is not clickable." + e.getMessage());
		}
	}

	public void gotoCheckMetadataNextbtn() {
		validateElementIsVisible(driver, clickNextButtoncheckNextbtn, "Next button of 'Define Rule Metadata' page ");
		try {
			clickOnElement(driver, clickNextButtoncheckNextbtn, "next button of 'Define Check Metadata' page", 20);
			ExtentListener.test.get().log(Status.INFO, "Next button of 'Define Check Metadata' page is clickable.");
		} catch (Exception e) {
			ExtentListener.test.get().log(Status.FAIL,
					"Next button of 'Define Check Metadata' page is not clickable." + e.getMessage());
		}
	}

	public void gotoNotificationNextbtn() {
		validateElementIsVisible(driver, clickNextButtonnotificationNextbtn,
				"Next button of 'Define Rule Metadata' page ");
		try {
			clickOnElement(driver, clickNextButtonnotificationNextbtn, "next button of 'Configure Notifications' page",
					20);
			ExtentListener.test.get().log(Status.INFO, "Next button of 'Configure Notifications' page is clickable.");
		} catch (Exception e) {
			ExtentListener.test.get().log(Status.FAIL,
					"Next button of 'Configure Notifications' page is not clickable." + e.getMessage());
		}
	}

	/// ********* Select the source dataset ********** ///
///// *******************************/////
/////////////// ********************************* ////////////////
///// *******************************/////
	public void selectionSourceDataset(String connectionType, String connectionName, String schemaName)
			throws InterruptedException {
		// JavascriptExecutor js = (JavascriptExecutor) driver;

		// Click on the source connection type.
		clickOnElement(driver, clickSourcedatasetConnectionTypeDropdown, "select connection type.", 30);

		// Select "Database"
		// clickOnElement(driver, selectConnectionType, "source database connection.",
		// 20);
		if (connectionType.equalsIgnoreCase("Database")) {
			clickOnElement(driver, selectDatabaseConnectionType, "source database connection.", 20);

			// Click Source Connection
			clickOnElement(driver, clickSourcedatasetConnectionDropdown, "select connection dropdown.", 20);

			// Enter connection name
			sendkeysToElement(driver, enterSourceConnectionName, "source connection name", connectionName);
			sendkeysToEnter(driver, enterSourceConnectionName, "source connection name");

			Thread.sleep(500);

		} else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse")) {
			clickOnElement(driver, selectCloudDataWarehouseConnectionType, "source cloud data warehouse connection.", 20);

			clickOnElement(driver, clickSourcedatasetConnectionDropdown, "select connection dropdown.", 20);

			sendkeysToElement(driver, enterSourceConnectionName, "source connection name", connectionName);
			sendkeysToEnter(driver, enterSourceConnectionName, "source connection name");

			clickOnElement(driver, By.xpath(
					"(//div[contains(text(),'Source Dataset')]/parent::form//following::ejs-dropdownlist[@placeholder='Choose Database']//span[@formcontrolname='database'])[1]"), "database dropdown.", 20);

			sendkeysToElement(driver, By.xpath(
					"(//div[contains(text(),'Source Dataset')]/parent::form//following::ejs-dropdownlist[@placeholder='Choose Database']//span[@formcontrolname='database'])[1]"), "source databse name", "icedrs");
			sendkeysToEnter(driver, By.xpath(
					"(//div[contains(text(),'Source Dataset')]/parent::form//following::ejs-dropdownlist[@placeholder='Choose Database']//span[@formcontrolname='database'])[1]"), "source database name");

			Thread.sleep(5000);
		}

		// Scroll & click schema dropdown
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
//				driver.findElement(clickSourceSchemaDropdown));


		clickOnElement(driver, clickSourceSchemaDropdown, "choose schema dropdown.", 40);

//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
//				driver.findElement(clickSourceSchemaDropdown));

		Thread.sleep(500);
		// Enter schema name
		sendkeysToElement(driver, enterSourceSchemaName, "source schema name", schemaName);
		sendkeysToEnter(driver, enterSourceSchemaName, "source schema name");

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
			Thread.sleep(500);

		} else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse")) {
			clickOnElement(driver, selectCloudDataWarehouseConnectionType, "source database connection.", 20);

			// Click Source Connection
			clickOnElement(driver, clickSourcedatasetConnectionDropdown, "select connection dropdown.", 20);

			// Enter connection name
			sendkeysToElement(driver, enterSourceConnectionName, "source connection name", connectionName);
			sendkeysToEnter(driver, enterSourceConnectionName, "source connection name");
			Thread.sleep(500);
		}

		else if (connectionType.equalsIgnoreCase("File")) {
			clickOnElement(driver, selectFileConnectionType, "source database connection.", 20);

			// Click the target connection.
			clickOnElement(driver, clickTargetdatasetConnectionDropdown, "select connection dropdown.", 20);

			// Entering the target connection name
			sendkeysToElement(driver, enterTargetConnectionName, "source connection name", connectionName);
			sendkeysToEnter(driver, enterTargetConnectionName, "source connection name");
			Thread.sleep(500);
		}
	}

	/// ********* Select the target dataset ********** ///
///// *******************************/////
/////////////// ********************************* ////////////////
///// *******************************/////
	public void selectionTargetDataset(String connectionType, String connectionName, String schemaName)
			throws InterruptedException {

		/// Click on the target connection type.
		clickOnElement(driver, clickTargetdatasetConnectionTypeDropdown, "select connection type.", 30);

		// Select the target connection type as database.
		// clickOnElement(driver, By.xpath("//li[text()='Database']"), "target database
		// connection.", 20);
		if (connectionType.equalsIgnoreCase("Database")) {
			clickOnElement(driver, selectDatabaseConnectionType, "source database connection.", 20);

			// Click the target connection.
			clickOnElement(driver, clickTargetdatasetConnectionDropdown, "select connection dropdown.", 20);

			// Entering the target connection name
			sendkeysToElement(driver, enterTargetConnectionName, "target connection name", connectionName);
			sendkeysToEnter(driver, enterTargetConnectionName, "target connection name");

			Thread.sleep(500);

		} else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse")) {
			clickOnElement(driver, selectCloudDataWarehouseConnectionType, "source cloud data warehouse connection.",
					20);

			clickOnElement(driver, clickTargetdatasetConnectionDropdown, "select connection dropdown.", 20);

			sendkeysToElement(driver, enterTargetConnectionName, "target connection name", connectionName);
			sendkeysToEnter(driver, enterTargetConnectionName, "target connection name");

			clickOnElement(driver, By.xpath(
					"(//div[contains(text(),'Source Dataset')]/parent::form//following::ejs-dropdownlist[@placeholder='Choose Database']//span[@formcontrolname='database'])[2]"),
					"database dropdown.", 20);

			sendkeysToElement(driver, By.xpath(
					"(//div[contains(text(),'Source Dataset')]/parent::form//following::ejs-dropdownlist[@placeholder='Choose Database']//span[@formcontrolname='database'])[2]"),
					"source databse name", "icedrs");
			sendkeysToEnter(driver, By.xpath(
					"(//div[contains(text(),'Source Dataset')]/parent::form//following::ejs-dropdownlist[@placeholder='Choose Database']//span[@formcontrolname='database'])[2]"),
					"source databse name");

			Thread.sleep(5000);

		}

		// Scroll & click schema dropdown
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
//				driver.findElement(clickTargetSchemaDropdown));

		clickOnElement(driver, clickTargetSchemaDropdown, "choose schema dropdown.", 40);

		// Enter schema name
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
//				driver.findElement(clickTargetSchemaDropdown));

		Thread.sleep(500);
		// Entering the schema name.
		sendkeysToElement(driver, enterSourceSchemaName, "target schema name", schemaName);
		sendkeysToEnter(driver, enterSourceSchemaName, "target schema name");

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
	public void uploadingFiles(String fileName) throws InterruptedException {

		Thread.sleep(500);
		// validateElementIsVisible(driver, By.xpath("//*[@name='UploadFiles']"),
		// fileName);
		Thread.sleep(500);
		uploadFile(driver, By.cssSelector("input[type='file'][name='UploadFiles']"), fileName);
		// Optional: verify the uploaded file appears in list
		Thread.sleep(500);
		WebElement uploadedFileLabel = driver.findElement(By.cssSelector("li.e-upload-file-list"));

		String uploadedName = uploadedFileLabel.getAttribute("data-file-name");
		System.out.println("✔ File shown in UI: " + uploadedName);
	}

	// Click on the next button of 'select dataset' page.
	public void gotoDatasetNextbtn() {
		validateElementIsVisible(driver, clickNextButtondatasetNextbtn, "Next button of 'Select Dataset' page ");
		try {
			clickOnElement(driver, clickNextButtondatasetNextbtn, "next button of 'Select Dataset' page.", 20);
			ExtentListener.test.get().log(Status.INFO, "Next button of 'Select Dataset' page is clickable.");
		} catch (Exception e) {
			ExtentListener.test.get().log(Status.FAIL, "Next button of 'Select Dataset' page is not clickable.");
		}
	}

	/// ********* Select the Available Table ********** ///.

	public void selectionAvailabeTable(String tableName) {
		// Click on the available table search field
		clickOnElement(driver, clickAvailableTable, "search available table.", 20);
		// Entering the available table name
		sendkeysToElement(driver, clickAvailableTable, "staff table name", tableName);
		// Select the table checkbox from the list.
		clickOnElement(driver, selectAvailableTable, "seach button icon", 20);
		// Click on the 'Move to' button
		clickOnElement(driver, clickMoveToButton, "Move to button.", 20);
	}

	// Click on the next button of 'select table' page.
	public void gotoSelettableNextbtn() {
		validateElementIsVisible(driver, clickNextButtonSelettableNextbtn, "Next button of 'Select Dataset' page ");
		try {
			clickOnElement(driver, clickNextButtonSelettableNextbtn, "next button of 'Select Teables' page", 20);
			ExtentListener.test.get().log(Status.INFO, "Next button of 'Select Teables' page is clickable.");
		} catch (Exception e) {
			ExtentListener.test.get().log(Status.FAIL,
					"Next button of 'Select Teables' page is not clickable." + e.getMessage());
		}
	}

	// Click on the next button of 'select table' page.
	public void gotoImportSQLNextbtn() {
		validateElementIsVisible(driver, ClickNextButtonimportSQLNextbtn, "Next button of 'Import SQL' page ");
		try {
			clickOnElement(driver, ClickNextButtonimportSQLNextbtn, "next button of 'Import SQL' page.", 20);
			ExtentListener.test.get().log(Status.INFO, "Next button of 'Import SQL' page is clickable.");
		} catch (Exception e) {
			ExtentListener.test.get().log(Status.FAIL,
					"Next button of 'Import SQL' page is not clickable." + e.getMessage());
		}
	}

	// Click on the 'Generate' button.
	public void clickOnGenerate() {
		validateElementIsVisible(driver, clickGenerateButton, "'Generate' button ");
		try {
			clickOnElement(driver, clickGenerateButton, "generate button.", 20);
			ExtentListener.test.get().log(Status.INFO, "Generate button is clickable.");
		} catch (Exception e) {
			ExtentListener.test.get().log(Status.FAIL, "Generate button is not clickable." + e.getMessage());
		}
	}

	// Click on the 'Go To Preview' button.
	public void clickOnGoToPreview() {
		validateElementIsVisible(driver, clickGoToPreviewButton, "'Go To Preview' button ");
		try {
			clickOnElement(driver, clickGoToPreviewButton, "go to preview button", 20);
			ExtentListener.test.get().log(Status.INFO, "Go To Preview button is clickable.");
		} catch (Exception e) {
			ExtentListener.test.get().log(Status.FAIL, "Go To Preview button is not clickable." + e.getMessage());
		}
	}

	// Select the generated entity from the 'Preview' page.
	public void selectGeneratedEntity() {
		clickOnElement(driver, selectEntity, "checkbox for entity selection.", 20);
		validateElementIsVisible(driver, selectEntity, "The selected entity ");
	}

	// Click on the 'Publish' button.
	public void clickOnPublish() {
		validateElementIsVisible(driver, clikPublishButton, "Publish button ");
		clickOnElement(driver, clikPublishButton, "publish button.", 10);
	}

	// Click on the 'Go To Publish' button.
	public void clickOnGoToPublish() {
		validateElementIsVisible(driver, clickGoToPublishButton, "'Go To Publish' button ");
		try {
			clickOnElement(driver, clickGoToPublishButton, "go to publish button.", 50);
			ExtentListener.test.get().log(Status.INFO, "Go To Publish button is clickable.");
		} catch (Exception e) {
			ExtentListener.test.get().log(Status.FAIL, "Go To Publish button is not clickable." + e.getMessage());
		}
	}

	// Click on the hyperlink of the published rule to navigate to the data testing.
	public void clickOnPublishedRule() throws InterruptedException {
		// Capture parent window
		String parent = PageUtil.getParentWindow(driver);

		String ruleNamePublishPage = driver.findElement(By.xpath("//tbody[@role='rowgroup']/tr/td/a")).getText();
		ExtentListener.test.get().log(Status.INFO, "Rule Name is from the Published Page.. " + ruleNamePublishPage);

		clickOnElement(driver, clickHyperlinkPublishRule, "published rule", 30);
		ExtentListener.test.get().log(Status.INFO, "Navigated to new tab");

		// Switch to child
		String child = switchToNewWindow(driver, parent, 60);
		Thread.sleep(500);
		isInvisibleLoader(driver, loader);
		isDisplayed(driver, By.xpath("//*[@placeholder='Enter rule name']"), 20);
		WebElement ruleName2 = driver.findElement(By.xpath("//*[@placeholder='Enter rule name']"));
		String ruleNameDataTestingPage = ruleName2.getAttribute("value");
		ExtentListener.test.get().log(Status.INFO,
				"Rule Name is from the Data Testing Page.. " + ruleNameDataTestingPage);

		// === Perform child validations ===
		Thread.sleep(500);
		if (ruleNamePublishPage.trim().equals(ruleNameDataTestingPage.trim())) {
			ExtentListener.test.get().log(Status.PASS,
					"Rule Name is validated successfully. " + ruleNameDataTestingPage);
		} else {
			Thread.sleep(500);
			ExtentListener.test.get().log(Status.FAIL, "Rule Name is mismatch.   Expectd:   " + ruleNameDataTestingPage
					+ "but found:  " + ruleNameDataTestingPage);
		}

		Thread.sleep(500);
		// Close child
		closeChildAndReturn(driver, child, parent);
		Thread.sleep(500);
	}

	// Go to the Data Testing
	public void navigatHomePage() {
		goTo(ConfigReader.getProperty("homePageUrl"));
	}

}
