package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.Status;
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

	public void createRuleUsingDefaultDynamicTemplate(String ruleType, String templateName, String workspaceName,
			String folderName, String connectionName, String schemaName, String tableName) throws InterruptedException {

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
		selectionSourceDataset(connectionName, schemaName);

// 6. Select Target Dataset
		if (!ruleType.equalsIgnoreCase("validation") && !ruleType.equalsIgnoreCase("pushdown")) {

			selectionTargetDataset(connectionName, schemaName);

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

	public void createRuleUsingDefaultImportTemplate(String ruleType, String templateName, String workspaceName,
			String folderName, String connectionName) throws InterruptedException {

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
		selectionSourceDatasetForImport(connectionName);

// 6. Select Target Dataset
		if (!ruleType.equalsIgnoreCase("validation") && !ruleType.equalsIgnoreCase("pushdown")) {

			selectionTargetDatasetForImport(connectionName);

		} else {
			System.out.println("Skipping Target Dataset for rule type: " + ruleType);
		}

// Next Buttons
		gotoDatasetNextbtn();

// 7. Import SQL
		if (ruleType == "checksum") {
			uploadingFiles("Checksum.xlsx");
		}

		if (ruleType == "recon") {
			uploadingFiles("Recon.xlsx");
		}
		if (ruleType == "validation") {
			uploadingFiles("Validation.xlsx");
		}
		if (ruleType == "pushdown") {
			uploadingFiles("Pushdwon.xlsx");
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
		validateElementIsVisible(driver, testGeneratorModue, "Test Generator", true);
		clickOnElement(driver, testGeneratorModue, "Test Generator", 30);
	}

	// Click the Checksum rule type from the wizard page.
	public void clickOnChecksum() {

		isInvisibleLoader(driver, loader);
		validateElementIsVisible(driver, selectChecksumWizard, "Checksum", true);
		clickOnElement(driver, selectChecksumWizard, "Checksum wizard", 50);

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
		validateElementIsVisible(driver, selectReconWizard, "Recon", true);
		clickOnElement(driver, selectReconWizard, "Recon wizard", 50);

	}

	// Click the Validation rule type from the wizard page.
	public void clickOnValidation() {
		isInvisibleLoader(driver, loader);
		validateElementIsVisible(driver, selectValidationWizard, "Validation", true);
		clickOnElement(driver, selectValidationWizard, "Validation wizard", 50);

	}

	// Click the Pushdown rule type from the wizard page.
	public void clickOnPushdown() {
		isInvisibleLoader(driver, loader);
		validateElementIsVisible(driver, selectPushdownWizard, "Pushdown", true);
		clickOnElement(driver, selectPushdownWizard, "Pushdown wizard", 50);

	}

	// Click on the search field box
	public void clickOnSearchField() {
		isInvisibleLoader(driver, loader);
		validateElementIsVisible(driver, clickSearchTemplateField, "Search template", true);
		clickOnElement(driver, clickSearchTemplateField, "Search template", 30);
	}

	// Enter the data on the search field
	public void enterOnSearchField(String templateName) {
		sendkeysToElement(driver, enterTemplateName, "Search template", templateName);
		validateElementIsVisible(driver, clickSearchButtonIcon, "Search button icon on search template ", true);
		clickOnElement(driver, clickSearchButtonIcon, "Search button icon", 30);
	}

	// Select the existing checksum template form the list
	public void selectExistingChecksumTemplate() {
		isInvisibleLoader(driver, loader);
		validateElementIsVisible(driver, selectExistingDynamicChecksumTemplate, "The searched template", true);
		clickOnElement(driver, selectExistingDynamicChecksumTemplate, "Searched item from the list", 30);
	}

	public void clickOnWorkspaceField(String workspaceName) {
		isInvisibleLoader(driver, loader);
		// Wait for workspace field to be visible and clickable
		clickOnElement(driver, clickOnWorkspaceDropdown, "Select workspace dropdown", 30);
		ExtentListener.test.get().info("Workspace dropdown opened");
		// Click workspace dropdown
		clickOnElement(driver, selectSearchWorkspace, "Searched workspace", 30);

		// Click workspace dropdown // Click workspace dropdown
		sendkeysToElement(driver, selectSearchWorkspace, "Select workspace field", workspaceName + Keys.ENTER);
		isInvisibleLoader(driver, loader);
		ExtentListener.test.get().pass("Workspace name selected");
		System.out.println("Workspace '" + workspaceName + "' is selected successfully.");
	}

	// Click on the Folder selector field

	public void clickOnFolderField(String folderName) {
		isInvisibleLoader(driver, loader);
		clickOnElement(driver, clickOnFolderDropdown, "Folder dropdown", 30);
		ExtentListener.test.get().info("Workspace dropdown opened");
		sendkeysToElement(driver, searchFolderName, "searching folder name.", folderName);
		clickOnElement(driver, By.xpath("//div[@id='pathtree']//span[@title='Search']"), "Search folder", 30);
		isInvisibleLoader(driver, loader);
		clickOnElement(driver, By.xpath("//div[contains(@class, 'e-text-content')]"), "Search button icon", 10);
		clickOnElement(driver, By.xpath("//span[@class='e-icons e-check']//parent::button"), "Seached folder name", 10);
		ExtentListener.test.get().pass("Folder name selected");

		System.out.println("Folder '" + folderName + "' selected.");
	}

	public void gotoWorkspaceNextbtn() {
		clickOnElement(driver, clickNextButtonWorkspaceNextbtn, "next button of 'Select Container' page", 20);
	}

	public void gotoRuleMetadataNextbtn() {
		clickOnElement(driver, clickNextButtonmetadataNextbtn, "next button of 'Define Rule Metadata' page", 20);
	}

	public void gotoCheckMetadataNextbtn() {
		clickOnElement(driver, clickNextButtoncheckNextbtn, "next button of 'Define Check Metadata' page", 20);
	}

	public void gotoNotificationNextbtn() {
		clickOnElement(driver, clickNextButtonnotificationNextbtn, "next button of 'Configure Notifications' page", 20);
	}

	/// ********* Select the source dataset ********** ///
///// *******************************/////
/////////////// ********************************* ////////////////
///// *******************************/////
	public void selectionSourceDataset(String connectionName, String schemaName) throws InterruptedException {
		// JavascriptExecutor js = (JavascriptExecutor) driver;

		// Click on the source connection type.
		clickOnElement(driver, clickSourcedatasetConnectionTypeDropdown, "select connection type", 10);

		// Select "Database"
		clickOnElement(driver, By.xpath("//li[text()='Database']"), "source database connection", 20);

		// Click Source Connection
		clickOnElement(driver, clickSourcedatasetConnectionDropdown, "select connection dropdown", 20);

		// Enter connection name
		sendkeysToElement(driver, enterSourceConnectionName, "source connection name", connectionName);
		sendkeysToEnter(driver, enterSourceConnectionName, "source connection name");

		// Scroll & click schema dropdown
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
//				driver.findElement(clickSourceSchemaDropdown));

		clickOnElement(driver, clickSourceSchemaDropdown, "choose schema dropdown", 20);

//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
//				driver.findElement(clickSourceSchemaDropdown));

		// Enter schema name
		sendkeysToElement(driver, enterSourceSchemaName, "source schema name", schemaName);
		sendkeysToEnter(driver, enterSourceSchemaName, "source schema name");
		
		Thread.sleep(5000);

	}

	public void selectionSourceDatasetForImport(String connectionName) throws InterruptedException {
		// JavascriptExecutor js = (JavascriptExecutor) driver;

		// Click on the source connection type.
		clickOnElement(driver, clickSourcedatasetConnectionTypeDropdown, "select connection type", 30);

		// Select "Database"
		clickOnElement(driver, By.xpath("//li[text()='Database']"), "source database connection", 20);

		// Click Source Connection
		clickOnElement(driver, clickSourcedatasetConnectionDropdown, "select connection dropdown", 20);

		// Enter connection name
		sendkeysToElement(driver, enterSourceConnectionName, "source connection name", connectionName);
		sendkeysToEnter(driver, enterSourceConnectionName, "source connection name");
	}

	/// ********* Select the target dataset ********** ///
///// *******************************/////
/////////////// ********************************* ////////////////
///// *******************************/////
	public void selectionTargetDataset(String connectionName, String schemaName) throws InterruptedException {

		/// Click on the target connection type.
		clickOnElement(driver, clickTargetdatasetConnectionTypeDropdown, "select connection type", 30);

		// Select the target connection type as database.
		clickOnElement(driver, By.xpath("//li[text()='Database']"), "target database connection", 20);

		// Click the target connection.
		clickOnElement(driver, clickTargetdatasetConnectionDropdown, "select connection dropdown", 20);

		// Entering the target connection name
		sendkeysToElement(driver, enterTargetConnectionName, "target connection name", connectionName);
		sendkeysToEnter(driver, enterTargetConnectionName, "target connection name");

		// Scroll & click schema dropdown
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
//				driver.findElement(clickTargetSchemaDropdown));

		clickOnElement(driver, clickTargetSchemaDropdown, "choose schema dropdown", 20);

		// Enter schema name
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
//				driver.findElement(clickTargetSchemaDropdown));

		// Entering the schema name.
		sendkeysToElement(driver, enterSourceSchemaName, "target schema name", schemaName);
		sendkeysToEnter(driver, enterSourceSchemaName, "target schema name");

	}

	public void selectionTargetDatasetForImport(String connectionName) throws InterruptedException {

		/// Click on the target connection type.
		clickOnElement(driver, clickTargetdatasetConnectionTypeDropdown, "select connection type", 10);

		// Select the target connection type as database.
		clickOnElement(driver, By.xpath("//li[text()='Database']"), "target database connection", 20);

		// Click the target connection.
		clickOnElement(driver, clickTargetdatasetConnectionDropdown, "select connection dropdown", 20);

		// Entering the target connection name
		sendkeysToElement(driver, enterTargetConnectionName, "target connection name", connectionName);
		sendkeysToEnter(driver, enterTargetConnectionName, "target connection name");
	}

	// Import SQL Files
	public void uploadingFiles(String fileName) throws InterruptedException {
		
		Thread.sleep(500);
		isDisplayed(driver, By.cssSelector("input[type='file'][name='UploadFiles']"), 30);
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
		clickOnElement(driver, clickNextButtondatasetNextbtn, "next button of 'Select Dataset' page", 20);
	}

	/// ********* Select the Available Table ********** ///.

	public void selectionAvailabeTable(String tableName) {
		// Click on the available table search field
		clickOnElement(driver, clickAvailableTable, "search available table", 20);
		// Entering the available table name
		sendkeysToElement(driver, clickAvailableTable, "staff table name", tableName);
		// Select the table checkbox from the list.
		clickOnElement(driver, selectAvailableTable, "seach button icon", 20);
		// Click on the 'Move to' button
		clickOnElement(driver, clickMoveToButton, "Move to button", 20);
	}

	// Click on the next button of 'select table' page.
	public void gotoSelettableNextbtn() {
		clickOnElement(driver, clickNextButtonSelettableNextbtn, "next button of 'Select Teables' page", 20);
	}

	// Click on the next button of 'select table' page.
	public void gotoImportSQLNextbtn() {
		clickOnElement(driver, By.xpath("//*[@id='e-content-element_6']/div/div[1]/button[2]"),
				"next button of 'Select Teables' page", 20);
	}

	// Click on the 'Generate' button.
	public void clickOnGenerate() {
		clickOnElement(driver, clickGenerateButton, "generate button", 20);
	}

	// Click on the 'Go To Preview' button.
	public void clickOnGoToPreview() {
		clickOnElement(driver, clickGoToPreviewButton, "go to preview button", 20);
	}

	// Select the generated entity from the 'Preview' page.
	public void selectGeneratedEntity() {
		clickOnElement(driver, selectEntity, "check for selection", 20);
		// String str =
		// driver.findElement(By.xpath("//*[@id=\"previewGrid_content_table\"]/tbody/tr[4]/td[2]/a")).getText();
	}

	// Click on the 'Publish' button.
	public void clickOnPublish() {
		clickOnElement(driver, clikPublishButton, "publish button", 10);
	}

	// Click on the 'Go To Publish' button.
	public void clickOnGoToPublish() {
		clickOnElement(driver, clickGoToPublishButton, "go to publish button", 50);
	}

	// Click on the hyperlink of the published rule to navigate to the data testing.
	public void clickOnPublishedRule() throws InterruptedException {
		// Capture parent window
		String parent = PageUtil.getParentWindow(driver);
		
		String ruleNamePublishPage = driver.findElement(By.xpath("//tbody[@role=\"rowgroup\"]/tr/td/a")).getText();
		ExtentListener.test.get().log(Status.INFO, "Rule Name is from the Published Page.. " + ruleNamePublishPage);

		clickOnElement(driver, clickHyperlinkPublishRule, "published rule", 30);
		ExtentListener.test.get().log(Status.INFO, "Navigated to new tab");

		// Switch to child
		String child = switchToNewWindow(driver, parent, 60);
		Thread.sleep(500);
		isDisplayed(driver, By.xpath("//*[@placeholder='Enter rule name']"), 10);
		WebElement ruleName2 = driver.findElement(By.xpath("//*[@placeholder='Enter rule name']"));
		String ruleNameDataTestingPage = ruleName2.getAttribute("value");
		ExtentListener.test.get().log(Status.INFO, "Rule Name is from the Data Testing Page.. " + ruleNameDataTestingPage);
		
		
		// === Perform child validations ===
		Thread.sleep(500);
		if (ruleNamePublishPage.equals(ruleNameDataTestingPage)) {
			ExtentListener.test.get().log(Status.PASS,
					"Rule Name is validated successfully. " + ruleNameDataTestingPage);
		} else {
			Thread.sleep(500);
			ExtentListener.test.get().log(Status.FAIL, "Rule Name is mismatch.   Expectd:   " + ruleNameDataTestingPage + "but found:  " + ruleNameDataTestingPage);
		}

		Thread.sleep(500);
		// Close child
		closeChildAndReturn(driver, child, parent);
		Thread.sleep(500);
	}

	// Go to the Data Testing
	public void navigatHomePage() {
		goTo("https://qa.onprem.icedq.com/#/");
	}

}
