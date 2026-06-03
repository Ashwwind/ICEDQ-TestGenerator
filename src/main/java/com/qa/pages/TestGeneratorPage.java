package com.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qa.extentreportlistener.ExtentManager;
import com.qa.utils.PageUtil;

public class TestGeneratorPage extends PageUtil {
    private WebDriver driver;
    public static final int waitTime = 10; // Global wait time

    public TestGeneratorPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * ---------------------- By locators ----------------------------------
     **/

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
    By selectSearchWorkspace = getElementLocator(prop.getProperty("select.searchedWorkspace"));
    By clickOnFolderDropdown = getElementLocator(prop.getProperty("wizard.folder.dropdown"));
    By searchFolderName = getElementLocator(prop.getProperty("wizard.search.folderName"));
    By selectSearchFolder = getElementLocator(prop.getProperty("select.searchedfolder"));
    By clickOnSearchIcon = getElementLocator(prop.getProperty("wizard.folderName.search.icon"));
    By selectSearchedFolderName = getElementLocator(prop.getProperty("wizard.folderName.searched"));
    By clickRightTick = getElementLocator(prop.getProperty("wizard.folderName.rightTick.icon"));
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
    By clickNextButtonSelettableNextbtn = getElementLocator(prop.getProperty("availableTable.click.nextButton.SelecttableNextbtn"));

    // Click on Generate
    By clickGenerateButton = getElementLocator(prop.getProperty("generateButton.click"));
    // Click on the Account Dropdown
    By clickOnAccountDropdown = getElementLocator(prop.getProperty("wizard.account.dropdown"));
    // Enter the account Name
    By enterAccountName = getElementLocator(prop.getProperty("wizard.account.searchfield"));
    // Click on the next button of Select account
    By clickNextButtonSelectAccountNextbtn = getElementLocator(prop.getProperty("wizard.account.next.button"));
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
    // Click on source UseMetaDataCache
    By UseMetadataCache = getElementLocator(prop.getProperty("source.metaDataCache"));
    // Click on the Source Database dropdown
    By clickSourceDatabaseDropdown = getElementLocator(prop.getProperty("source.database.dropdown"));
    // Enter source database name
    By enterSourceDatabaseName = getElementLocator(prop.getProperty("source.database.input"));
    // Click on the Target Database dropdown
    By clickTargetDatabaseDropdown = getElementLocator(prop.getProperty("target.database.dropdown"));
    // Enter target database name
    By enterTargetDatabaseName = getElementLocator(prop.getProperty("target.database.input"));

    /// ///// Workflow //////
    // Click on published button
    By clickPublishButton = getElementLocator(prop.getProperty("workflow.publish.button"));
    By selectWorkflowOption = getElementLocator(prop.getProperty("workflow.publish.option"));
    By clickFolderDropdown = getElementLocator(prop.getProperty("workflow.folder.dropdown"));
    By searchFolderField = getElementLocator(prop.getProperty("workflow.folder.search.field"));

    By loader = getElementLocator(prop.getProperty("loderIsDisplayed"));

    public void executeStep(String stepName, Runnable action) {
        try {
            action.run();

            // 🔍 Detect toast AFTER action
            String uiError = captureUIErrorIfPresent(driver);
            if (uiError != null && !uiError.isEmpty()) {
                throw new RuntimeException(uiError);
            }

            ExtentManager.logPass(stepName + " ➝ PASSED");

        } catch (Throwable e) {

            ExtentManager.logFail(stepName + " ➝ FAILED due to: " + e.getMessage());

            // 📸 Screenshot (once per failure)
            ExtentManager.captureScreenshot("Step_Failure");

            // 🚪 Exit current flow
            navigatHomePage();

            // 🔥 STOP FLOW IMMEDIATELY
            throw new FlowAbortException("Flow aborted: " + stepName, e);
        }
    }

    public static class FlowAbortException extends RuntimeException {
        public FlowAbortException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    /// ************* ///
    // Default Dynamic Template

    /// ************* ///

    public void createRuleUsingDefaultDynamicTemplate(String ruleType, String templateName, String workspaceName, String folderName, String connectionType, String SourceConnectionName, String TargetConnectionName, String schemaName, String tableName, String accountName) throws InterruptedException {

        // Step 1: Click Test Generator
        executeStep("Click Test Generator", this::clickTestGenerator);

        // Step 2: Select Rule Type
        executeStep("Click On Rule Type: " + ruleType, () -> clickOnRuleType(ruleType));

        // Step 3: Search and Select Account
        executeStep("Search and Select Account: " + accountName, () -> clickOnAccountDropdown(accountName));

        // Step 4: Proceed to Template Selection
        executeStep("Account Next", this::gotoSelectAccountNextbtn);

        // Step 5: Search and Select Template
        executeStep("Search Template: " + templateName, () -> {
            clickOnSearchField();
            enterOnSearchField(templateName);
            selectExistingChecksumTemplate();
        });

        // Step 6: Select Workspace
        executeStep("Select Workspace: " + workspaceName, () -> clickOnWorkspaceField(workspaceName));

        // Step 7: Select Folder
        executeStep("Select Folder: " + folderName, () -> clickOnFolderField(folderName));

        // Step 8: Navigate Through Metadata Screens
        executeStep("Workspace Next", this::gotoWorkspaceNextbtn);
        executeStep("Rule Metadata Next", this::gotoRuleMetadataNextbtn);
        executeStep("Check Metadata Next", this::gotoCheckMetadataNextbtn);
        executeStep("Notification Next", this::gotoNotificationNextbtn);

        // Step 9: Select Source Dataset
        executeStep("Select Source Dataset: " + connectionType + "/" + SourceConnectionName + "/" + schemaName, () -> selectionSourceDataset(connectionType, SourceConnectionName, schemaName));

        // Step 10: Select Target Dataset (Applicable Rule Types Only)
        if (!ruleType.equalsIgnoreCase("validation") && !ruleType.equalsIgnoreCase("pushdown")) {

            executeStep("Select Target Dataset: " + connectionType + "/" + TargetConnectionName + "/" + schemaName, () -> selectionTargetDataset(connectionType, TargetConnectionName, schemaName));
        }

        // Step 11: Proceed to Table Selection
        executeStep("Dataset Next", this::gotoDatasetNextbtn);

        // Step 12: Select Table
        executeStep("Select Available Table: " + tableName, () -> selectionAvailableTable(tableName));

        // Step 13: Proceed After Table Selection
        executeStep("Table Next", this::gotoSelettableNextbtn);

        // Step 14: Generate Rule
        executeStep("Click Generate", this::clickOnGenerate);

        // Step 15: Open Preview
        executeStep("Go To Preview", this::clickOnGoToPreview);
        Thread.sleep(500);

        // Step 16: Select Generated Entity
        executeStep("Select Generated Entity", this::selectGeneratedEntity);
        Thread.sleep(500);

        // Step 17: Publish Rule
        executeStep("Click Publish", this::clickOnPublish);
        Thread.sleep(500);

        // Step 18: Navigate to Published Rule
        executeStep("Go To Publish", () -> {
            try {
                Thread.sleep(500);
                clickOnGoToPublish();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // Step 19: Verify Published Rule
        Thread.sleep(500);
        executeStep("Click On Published Rule", this::clickOnPublishedRule);

        // Step 20: Navigate home after successful flow
        navigatHomePage();
    }

    // ============================
    // Default Import Template
    // ============================

    public void createRuleUsingDefaultImportTemplate(String ruleType, String templateName, String workspaceName, String folderName, String connectionType, String fileType, String SourceConnectionName, String TargetConnectionName, String accountName) throws InterruptedException {

        boolean isReconOrChecksum = ruleType.equalsIgnoreCase("checksum") || ruleType.equalsIgnoreCase("recon");

        // Step 1: Click Test Generator
        executeStep("Click Test Generator", this::clickTestGenerator);

        // Step 2: Select Rule Type
        executeStep("Click On Rule Type: " + ruleType, () -> clickOnRuleType(ruleType));

        // Step 3: Search and Select Account
        executeStep("Search and Select Account: " + accountName, () -> clickOnAccountDropdown(accountName));

        // Step 4: Proceed to Template Selection
        executeStep("Account Next", this::gotoSelectAccountNextbtn);

        // Step 5: Search and Select Template
        executeStep("Search Template: " + templateName, () -> {
            clickOnSearchField();
            enterOnSearchField(templateName);
            selectExistingChecksumTemplate();
        });

        // Step 6: Select Workspace
        executeStep("Select Workspace: " + workspaceName, () -> clickOnWorkspaceField(workspaceName));

        // Step 7: Select Folder
        executeStep("Select Folder: " + folderName, () -> clickOnFolderField(folderName));

        // Step 8: Navigate Through Metadata Screens
        executeStep("Workspace Next", this::gotoWorkspaceNextbtn);
        executeStep("Rule Metadata Next", this::gotoRuleMetadataNextbtn);
        executeStep("Check Metadata Next", this::gotoCheckMetadataNextbtn);
        executeStep("Notification Next", this::gotoNotificationNextbtn);

        // Step 9: Select Source Dataset
        executeStep("Select Source Dataset for Import: " + connectionType + "/" + SourceConnectionName, () -> selectionSourceDatasetForImport(connectionType, SourceConnectionName));

        // Step 10: Select Target Dataset (Checksum/Recon Only)
        if (isReconOrChecksum) {
            executeStep("Select Target Dataset for Import: " + connectionType + "/" + TargetConnectionName, () -> selectionTargetDatasetForImport(connectionType, TargetConnectionName));
        }

        // Step 11: Proceed to Import Screen
        executeStep("Dataset Next", this::gotoDatasetNextbtn);

        // Step 12: Upload Import File(s)
        executeStep("Upload Files for Rule Type: " + ruleType, () -> uploadFileByRuleAndConnection(ruleType, connectionType, fileType));

        // Step 13: Proceed After Import
        executeStep("Import SQL Next", this::gotoImportSQLNextbtn);

        // Step 14: Generate Rule
        executeStep("Click Generate", this::clickOnGenerate);

        // Step 15: Open Preview
        executeStep("Go To Preview", this::clickOnGoToPreview);

        // Step 16: Select Generated Entity
        executeStep("Select Generated Entity", this::selectGeneratedEntity);

        // Step 17: Publish Rule
        executeStep("Click Publish", this::clickOnPublish);

        // Step 18: Navigate to Published Rule
        executeStep("Go To Publish", () -> {
            try {
                clickOnGoToPublish();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // Step 19: Open Published Rule
        executeStep("Click On Published Rule", this::clickOnPublishedRule);

        // Step 20: Navigate to Home Page
        navigatHomePage();
    }

    /// ************* ///
    // Create an workflow

    /// ************* ///

    public void createWorkflowUsingDefaultDynamicTemplate(String ruleType, String templateName, String workspaceName, String folderName, String connectionType, String SourceConnectionName, String TargetConnectionName, String schemaName, String tableName, String workflowName, String accountName) throws InterruptedException {

        // Step 1: Click Test Generator
        executeStep("Click Test Generator", this::clickTestGenerator);

        // Step 2: Select Rule Type
        executeStep("Click On Rule Type: " + ruleType, () -> clickOnRuleType(ruleType));

        // Step 3: Search and Select Account
        executeStep("Search and Select Account: " + accountName, () -> clickOnAccountDropdown(accountName));

        // Step 4: Proceed to Template Selection
        executeStep("Account Next", this::gotoSelectAccountNextbtn);

        // Step 5: Search and Select Template
        executeStep("Search Template: " + templateName, () -> {
            clickOnSearchField();
            enterOnSearchField(templateName);
            selectExistingChecksumTemplate();
        });

        // Step 6: Select Workspace
        executeStep("Select Workspace: " + workspaceName, () -> clickOnWorkspaceField(workspaceName));

        // Step 7: Select Folder
        executeStep("Select Folder: " + folderName, () -> clickOnFolderField(folderName));

        // Step 8: Navigate Through Metadata Screens
        executeStep("Workspace Next", this::gotoWorkspaceNextbtn);
        executeStep("Rule Metadata Next", this::gotoRuleMetadataNextbtn);
        executeStep("Check Metadata Next", this::gotoCheckMetadataNextbtn);
        executeStep("Notification Next", this::gotoNotificationNextbtn);

        // Step 9: Select Source Dataset
        executeStep("Select Source Dataset: " + connectionType + "/" + SourceConnectionName + "/" + schemaName, () -> selectionSourceDataset(connectionType, SourceConnectionName, schemaName));

        // Step 10: Select Target Dataset (Applicable Rule Types Only)
        if (!ruleType.equalsIgnoreCase("validation") && !ruleType.equalsIgnoreCase("pushdown")) {

            executeStep("Select Target Dataset: " + connectionType + "/" + TargetConnectionName + "/" + schemaName, () -> selectionTargetDataset(connectionType, TargetConnectionName, schemaName));
        }

        // Step 11: Proceed to Table Selection
        executeStep("Dataset Next", this::gotoDatasetNextbtn);

        // Step 12: Select Table
        executeStep("Select Available Table: " + tableName, () -> selectionAvailableTable(tableName));

        // Step 13: Proceed After Table Selection
        executeStep("Table Next", this::gotoSelettableNextbtn);

        // Step 14: Generate Workflow
        executeStep("Click Generate", this::clickOnGenerate);

        // Step 15: Open Preview
        executeStep("Go To Preview", this::clickOnGoToPreview);

        // Step 16: Select Generated Entity
        executeStep("Select Generated Entity", this::selectGeneratedEntity);

        // Step 17: Publish Workflow
        executeStep("Publish Workflow: " + workflowName, () -> clickOnPublishTheWorkflow(folderName, workflowName));

        // Step 18: Navigate to Published Workflow
        executeStep("Go To Publish", () -> {
            try {
                clickOnGoToPublish();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // Step 19: Open Published Workflow
        executeStep("Click On Published Workflow", this::clickOnPublishedWorkflow);

        // Step 20: Navigate to Home Page
        navigatHomePage();
    }

    /// ************* ///
    // Existing workflow

    /// ************* ///

    public void existingWorkflowUsingDefaultDynamicTemplate(String ruleType, String templateName, String workspaceName, String folderName, String connectionType, String SourceConnectionName, String TargetConnectionName, String schemaName, String tableName, String workflowName, String accountName) throws InterruptedException {

        // Step 1: Click Test Generator
        executeStep("Click Test Generator", this::clickTestGenerator);

        // Step 2: Select Rule Type
        executeStep("Click On Rule Type: " + ruleType, () -> clickOnRuleType(ruleType));

        // Step 3: Search and Select Account
        executeStep("Search and Select Account: " + accountName, () -> clickOnAccountDropdown(accountName));

        // Step 4: Proceed to Template Selection
        executeStep("Account Next", this::gotoSelectAccountNextbtn);

        // Step 5: Search and Select Template
        executeStep("Search Template: " + templateName, () -> {
            clickOnSearchField();
            enterOnSearchField(templateName);
            selectExistingChecksumTemplate();
        });

        // Step 6: Select Workspace
        executeStep("Select Workspace: " + workspaceName, () -> clickOnWorkspaceField(workspaceName));

        // Step 7: Select Folder
        executeStep("Select Folder: " + folderName, () -> clickOnFolderField(folderName));

        // Step 8: Navigate Through Metadata Screens
        executeStep("Workspace Next", this::gotoWorkspaceNextbtn);
        executeStep("Rule Metadata Next", this::gotoRuleMetadataNextbtn);
        executeStep("Check Metadata Next", this::gotoCheckMetadataNextbtn);
        executeStep("Notification Next", this::gotoNotificationNextbtn);

        // Step 9: Select Source Dataset
        executeStep("Select Source Dataset: " + connectionType + "/" + SourceConnectionName + "/" + schemaName, () -> selectionSourceDataset(connectionType, SourceConnectionName, schemaName));

        // Step 10: Select Target Dataset (Applicable Rule Types Only)
        if (!ruleType.equalsIgnoreCase("validation") && !ruleType.equalsIgnoreCase("pushdown")) {

            executeStep("Select Target Dataset: " + connectionType + "/" + TargetConnectionName + "/" + schemaName, () -> selectionTargetDataset(connectionType, TargetConnectionName, schemaName));
        }

        // Step 11: Proceed to Table Selection
        executeStep("Dataset Next", this::gotoDatasetNextbtn);

        // Step 12: Select Table
        executeStep("Select Available Table: " + tableName, () -> selectionAvailableTable(tableName));

        // Step 13: Proceed After Table Selection
        executeStep("Table Next", this::gotoSelettableNextbtn);

        // Step 14: Generate Workflow
        executeStep("Click Generate", this::clickOnGenerate);

        // Step 15: Open Preview
        executeStep("Go To Preview", this::clickOnGoToPreview);

        // Step 16: Select Generated Entity
        executeStep("Select Generated Entity", this::selectGeneratedEntity);

        // Step 17: Publish Existing Workflow
        executeStep("Publish Existing Workflow: " + workflowName, () -> clickOnPublishTheExistingWorkflow(folderName, workflowName));

        // Step 18: Navigate to Published Workflow
        executeStep("Go To Publish", () -> {
            try {
                clickOnGoToPublish();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // Step 19: Open Published Workflow
        executeStep("Click On Published Workflow", this::clickOnPublishedWorkflow);

        // Step 20: Navigate to Home Page
        navigatHomePage();
    }

    // ============================
    // Page method
    // ============================

    private void uploadFileByRuleAndConnection(String ruleType, String connectionType, String fileType) {

        String fileName = resolveImportFileName(ruleType, connectionType, fileType);

        if (fileName == null) {
            throw new IllegalArgumentException("No import file found for RuleType: " + ruleType + ", ConnectionType: " + connectionType);
        }

        uploadingFiles(fileName);
    }

    private String resolveImportFileName(String ruleType, String connectionType, String fileType) {

        switch (ruleType.toLowerCase()) {

            case "checksum":
                return resolveByConnection(connectionType, fileType, "Checksum.xlsx", "Checksum - Redshift.xlsx", "Checksum - Flat File-SQL.xlsx", "Checksum - Excel.xlsx");

            case "recon":
                return resolveByConnection(connectionType, fileType, "Recon.xlsx", "Recon - Redshift.xlsx", "Recon - Flat File-SQL.xlsx", "Recon - Excel.xlsx");

            case "validation":
                return resolveByConnection(connectionType, fileType, "Validation.xlsx", "Validation - Redshift.xlsx", "Validation - Flat File-SQL.xlsx", "Validation - Excel.xlsx");

            case "pushdown":
                return resolveByConnection(connectionType, fileType, "Pushdown.xlsx", "Pushdown - Redshift.xlsx", "Pushdown - Flat File-SQL.xlsx", "Pushdown - Excel.xlsx");

            default:
                throw new IllegalArgumentException("Invalid rule type: " + ruleType);
        }
    }

    private String resolveByConnection(String connectionType, String fileType, String databaseFile, String cloudFile, String flatFile, String excelFile) {

        switch (connectionType.toLowerCase()) {

            case "database":
                return databaseFile;

            case "cloud data warehouse":
                return cloudFile;

            case "file":

                if (fileType.equalsIgnoreCase("FlatFile-SQL")) {
                    return flatFile;

                } else if (fileType.equalsIgnoreCase("Excel")) {
                    return excelFile;

                } else {
                    throw new IllegalArgumentException("Invalid file type: " + fileType);
                }

            default:
                throw new IllegalArgumentException("Invalid connection type: " + connectionType);
        }
    }

    public boolean clickTestGenerator() {

        return clickOnField(driver, testGeneratorModue, "Test Generator", "button", true);
    }

    // Click the Checksum rule type from the wizard page.
    // public boolean clickOnChecksum() {
    //
    // return clickOnField(driver, selectChecksumWizard, "Checksum rule type",
    // "tab");
    // }

    public boolean clickOnChecksum1() {
        boolean flag = true;

        try {
            if (flag) {
                clickOnField(driver, selectChecksumWizard, "Checksum rule type", "tab", true);
            }
        } catch (Exception e) {
            ExtentManager.logFail(e + " clickOnChecksum1 :: " + e.toString());
            flag = false;
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

        return clickOnField(driver, locator, ruleType + " rule type", "tab", true);

    }

    // Click the Recon rule type from the wizard page.
    public boolean clickOnRecon() {

        return clickOnField(driver, selectReconWizard, "Recon rule type", "tab", true);
    }

    // Click the Validation rule type from the wizard page.
    public boolean clickOnValidation() {

        return clickOnField(driver, selectValidationWizard, "Validation rule type", "tab", true);
    }

    // Click the Pushdown rule type from the wizard page.
    public boolean clickOnPushdown() {

        return clickOnField(driver, selectPushdownWizard, "Pushdown rule type", "tab", true);
    }

    // Click on the Account dropdown
    public boolean clickOnAccountDropdown(String accountName) {

        if (!clickOnField(driver, clickOnAccountDropdown, "Account", "Drop-down field", true)) {
            return false;
        }

        clickOnElement(driver, enterAccountName, "Enter account name", waitTime);
        sendkeysToElement(driver, enterAccountName, "Enter account name", accountName);
        sendkeysToEnter(driver, enterAccountName, "Select workspace field");

        return true;
    }

    public boolean gotoSelectAccountNextbtn() {
        return clickOnField(driver, clickNextButtonSelectAccountNextbtn, "Next button of 'Select Account' page", "button", true);
    }

    // Click on the search field box
    public boolean clickOnSearchField() {

        return clickOnField(driver, clickSearchTemplateField, "'Search template' search field ", "search field", true);
    }

    // Enter the data on the search field
    public boolean enterOnSearchField(String templateName) {

        boolean isTextEntered = sendkeysToElement1(driver, enterTemplateName, templateName, templateName);

        boolean isSearchClicked = clickOnField(driver, clickSearchButtonIcon, "search button icon", "search button icon", true);

        return isTextEntered && isSearchClicked;
    }

    // Select the existing template form the list
    public boolean selectExistingChecksumTemplate() {

        return clickOnField(driver, selectExistingDynamicChecksumTemplate, "select searched item", "serarch item", true);
    }

    public void clickOnWorkspaceField(String workspaceName) {

        isInvisibleLoader(driver, loader);

        // Open workspace dropdown
        clickOnElement(driver, clickOnWorkspaceDropdown, "select workspace dropdown.", waitTime);
        ExtentManager.logInfo("Workspace dropdown opened.");

        // Click search field
        clickOnElement(driver, selectWorkspace, "searched workspace.", waitTime);

        // Enter workspace name and press ENTER
        sendkeysToElement(driver, selectSearchWorkspace, "Select workspace field", workspaceName);
        sendkeysToEnter(driver, selectSearchWorkspace, "Select workspace field");

        isInvisibleLoader(driver, loader);

        ExtentManager.logPass("Workspace name selected: " + workspaceName);
    }

    // Click on the Folder selector field

    public void clickOnFolderField(String folderName) {

        isInvisibleLoader(driver, loader);

        // Open folder dropdown
        clickOnElement(driver, clickOnFolderDropdown, "Folder dropdown", waitTime);
        ExtentManager.logInfo("Folder dropdown opened.");

        // Search folder
        sendkeysToElement(driver, searchFolderName, "searching folder name", folderName);

        // Click search icon
        clickOnElement(driver, clickOnSearchIcon, "Search folder", waitTime);

        isInvisibleLoader(driver, loader);

        // Select folder from results
        clickOnElement(driver, selectSearchedFolderName, "Folder result", waitTime);

        clickOnElement(driver, clickRightTick, "Selected folder name", 10);

        isInvisibleLoader(driver, loader);

        ExtentManager.logPass("Folder name selected: " + folderName);
    }

    public boolean gotoWorkspaceNextbtn() {
        return clickOnField(driver, clickNextButtonWorkspaceNextbtn, "Next button of 'Select Container' page", "button", true);
    }

    public void gotoRuleMetadataNextbtn() {// Click Next button on Rule Metadata page
        clickOnElement(driver, clickNextButtonmetadataNextbtn, "next button of 'Define Rule Metadata' page", waitTime);
        ExtentManager.logInfo("Next button of 'Define Rule Metadata' page is clickable.");
    }

    public void gotoCheckMetadataNextbtn() {// Click Next button on Check Metadata page
        clickOnElement(driver, clickNextButtoncheckNextbtn, "next button of 'Define Check Metadata' page", waitTime);
        ExtentManager.logInfo("Next button of 'Define Check Metadata' page is clickable.");
    }

    public void gotoNotificationNextbtn() {// Click Next button on Configure Notifications page
        clickOnElement(driver, clickNextButtonnotificationNextbtn, "next button of 'Configure Notifications' page", waitTime);
        ExtentManager.logInfo("Next button of 'Configure Notifications' page is clickable.");
    }

    /// ********* Select the source dataset ********** ///
    /// // *******************************/////
    /// //////////// ********************************* ////////////////
    /// // *******************************/////

    public void selectionSourceDataset(String connectionType, String connectionName, String schemaName) {

        // Click on the source connection type dropdown
        clickOnElement(driver, clickSourcedatasetConnectionTypeDropdown, "select source connection type.", waitTime);

        if (connectionType.equalsIgnoreCase("Database")) {

            clickOnElement(driver, selectDatabaseConnectionType, "source database connection.", waitTime);
            isInvisibleLoader(driver, loader);

            clickOnElement(driver, clickSourcedatasetConnectionDropdown, "select connection dropdown.", 40);

            sendkeysToElement(driver, enterSourceConnectionName, "source connection name", connectionName);

            sendkeysToEnter(driver, enterSourceConnectionName, "source connection name");
            isInvisibleLoader(driver, loader);

            ExtentManager.logInfo("Database connection name selected successfully.");

        } else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse")) {

            clickOnElement(driver, selectCloudDataWarehouseConnectionType, "source cloud data warehouse connection.", waitTime);
            isInvisibleLoader(driver, loader);

            clickOnElement(driver, clickSourcedatasetConnectionDropdown, "select connection dropdown.", 60);

            sendkeysToElement(driver, enterSourceConnectionName, "source connection name", connectionName);

            sendkeysToEnter(driver, enterSourceConnectionName, "source connection name");
            isInvisibleLoader(driver, loader);

            ExtentManager.logInfo("Cloud warehouse connection name selected successfully.");

//			By UseMetadataCache = By.xpath(
//					"//*[@id='e-content-element_5']/div/div[2]/div[1]/form/div[2]/div[3]/div/ejs-checkbox/label/span[2]");

            clickOnElement(driver, UseMetadataCache, "Use Metadata Cache check box", waitTime);
            isInvisibleLoader(driver, loader);

            // Click database dropdown
            clickOnElement(driver, clickSourceDatabaseDropdown, "database dropdown.", waitTime);
            isInvisibleLoader(driver, loader);

            sendkeysToElement(driver, enterSourceDatabaseName, "source database name", "icedqrs");
            isInvisibleLoader(driver, loader);

            sendkeysToEnter(driver, enterSourceDatabaseName, "source database name");
            isInvisibleLoader(driver, loader);

            ExtentManager.logInfo("Database name selected successfully.");
        }

        // Schema selection
        clickOnElement(driver, clickSourceSchemaDropdown, "choose source schema dropdown.", 40);
        isInvisibleLoader(driver, loader);

        sendkeysToElement(driver, enterSourceSchemaName, "source schema name", schemaName);
        isInvisibleLoader(driver, loader);

        sendkeysToEnter(driver, enterSourceSchemaName, "source schema name");
        isInvisibleLoader(driver, loader);

        ExtentManager.logInfo("Schema selected successfully.");
    }

    public void selectionSourceDatasetForImport(String connectionType, String connectionName) {

        // Click Source Dataset Connection Type Dropdown
        clickOnElement(driver, clickSourcedatasetConnectionTypeDropdown, "select connection type.", waitTime);

        // Select connection type
        switch (connectionType.toLowerCase()) {
            case "database":
                clickOnElement(driver, selectDatabaseConnectionType, "source database connection.", waitTime);
                break;

            case "cloud data warehouse":
                clickOnElement(driver, selectCloudDataWarehouseConnectionType, "source cloud data warehouse connection.", waitTime);
                break;

            case "file":
                validateField(driver, selectFileConnectionType, "Connection Type", 5);
                clickOnElement(driver, selectFileConnectionType, "source file connection.", waitTime);
                break;

            default:
                throw new IllegalArgumentException("Invalid connection type: " + connectionType);
        }

        // Click Source Dataset Connection Dropdown
        validateField(driver, clickSourcedatasetConnectionDropdown, connectionName, 5);
        clickOnElement(driver, clickSourcedatasetConnectionDropdown, "select connection dropdown.", waitTime);
        isInvisibleLoader(driver, loader);

        // Enter and confirm connection name
        validateField(driver, enterSourceConnectionName, "Enter source connection name", 2);
        sendkeysToElement(driver, enterSourceConnectionName, "source connection name", connectionName);
        sendkeysToEnter(driver, enterSourceConnectionName, "source connection name");
        isInvisibleLoader(driver, loader);

        ExtentManager.logInfo("Source dataset selected successfully: " + connectionType + " / " + connectionName);
    }

    /// ********* Select the target dataset ********** ///
    /// // *******************************/////
    /// //////////// ********************************* ////////////////
    /// // *******************************/////
    public void selectionTargetDataset(String connectionType, String connectionName, String schemaName) {

        // Click target connection type dropdown
        clickOnElement(driver, clickTargetdatasetConnectionTypeDropdown, "select target connection type.", waitTime);

        if (connectionType.equalsIgnoreCase("Database")) {

            clickOnElement(driver, selectDatabaseConnectionType, "target database connection.", waitTime);
            isInvisibleLoader(driver, loader);

            clickOnElement(driver, clickTargetdatasetConnectionDropdown, "select target connection dropdown.", 40);

            sendkeysToElement(driver, enterTargetConnectionName, "target connection name", connectionName);

            sendkeysToEnter(driver, enterTargetConnectionName, "target connection name");
            isInvisibleLoader(driver, loader);

            ExtentManager.logInfo("Target database connection selected successfully.");

        } else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse")) {

            clickOnElement(driver, selectCloudDataWarehouseConnectionType, "target cloud data warehouse connection.", waitTime);
            isInvisibleLoader(driver, loader);

            clickOnElement(driver, clickTargetdatasetConnectionDropdown, "select target connection dropdown.", 60);

            sendkeysToElement(driver, enterTargetConnectionName, "target connection name", connectionName);

            sendkeysToEnter(driver, enterTargetConnectionName, "target connection name");
            isInvisibleLoader(driver, loader);

            ExtentManager.logInfo("Target cloud connection selected successfully.");

            // Click database dropdown
            clickOnElement(driver, clickTargetDatabaseDropdown, "database dropdown.", waitTime);
            isInvisibleLoader(driver, loader);

            sendkeysToElement(driver, enterTargetDatabaseName, "target database name", "icedqrs");

            sendkeysToEnter(driver, enterTargetDatabaseName, "target database name");
            isInvisibleLoader(driver, loader);

            ExtentManager.logInfo("Target database selected successfully.");
        }

        // Schema selection
        clickOnElement(driver, clickTargetSchemaDropdown, "choose target schema dropdown.", 40);
        isInvisibleLoader(driver, loader);

        sendkeysToElement(driver, enterSourceSchemaName, "target schema name", schemaName);

        sendkeysToEnter(driver, enterSourceSchemaName, "target schema name");
        isInvisibleLoader(driver, loader);

        ExtentManager.logInfo("Target schema selected successfully.");
    }

    public void selectionTargetDatasetForImport(String connectionType, String connectionName) {
        // Click Target Dataset Connection Type Dropdown
        clickOnElement(driver, clickTargetdatasetConnectionTypeDropdown, "select target connection type.", waitTime);

        // Select connection type
        switch (connectionType.toLowerCase()) {
            case "database":
                clickOnElement(driver, selectDatabaseConnectionType, "target database connection.", waitTime);
                break;

            case "cloud data warehouse":
                clickOnElement(driver, selectCloudDataWarehouseConnectionType, "target cloud data warehouse connection.", waitTime);
                break;

            case "file":
                validateField(driver, selectFileConnectionType, "Connection Type", 5);
                clickOnElement(driver, selectFileConnectionType, "target file connection.", waitTime);
                break;

            default:
                throw new IllegalArgumentException("Invalid connection type: " + connectionType);
        }

        // Click Target Dataset Connection Dropdown
        validateField(driver, clickTargetdatasetConnectionDropdown, connectionName, 5);
        clickOnElement(driver, clickTargetdatasetConnectionDropdown, "select target connection dropdown.", 40);
        isInvisibleLoader(driver, loader);

        // Enter and confirm connection name
        validateField(driver, enterTargetConnectionName, "Enter target connection name", 10);
        sendkeysToElement(driver, enterTargetConnectionName, "target connection name", connectionName);
        sendkeysToEnter(driver, enterTargetConnectionName, "target connection name");
        isInvisibleLoader(driver, loader);

        ExtentManager.logInfo("Target dataset selected successfully: " + connectionType + " / " + connectionName);
    }

    // Import SQL Files
    public void uploadingFiles(String fileName) {
        // Upload file
        uploadFile(driver, By.cssSelector("input[type='file'][name='UploadFiles']"), fileName);

        // Optional: verify the uploaded file appears in the list
        WebElement uploadedFileLabel = driver.findElement(By.cssSelector("li.e-upload-file-list"));
        String uploadedName = uploadedFileLabel.getAttribute("data-file-name");

        ExtentManager.logInfo("✔ File shown in UI: " + uploadedName);
    }

    // Click on the Next button of 'Select Dataset' page
    public void gotoDatasetNextbtn() {
        clickOnElement(driver, clickNextButtondatasetNextbtn, "next button of 'Select Dataset' page.", waitTime);
        ExtentManager.logInfo("Next button of 'Select Dataset' page is clickable.");
    }

    /// ********* Select the Available Table ********** ///.

    public void selectionAvailableTable(String tableName) {
        // Click on the available table search field
        clickOnElement(driver, clickAvailableTable, "search available table.", waitTime);

        // Enter the table name
        sendkeysToElement(driver, clickAvailableTable, "available table name", tableName);

        // Select the table checkbox from the list
        clickOnElement(driver, selectAvailableTable, "table checkbox", waitTime);

        // Click on the 'Move to' button
        clickOnElement(driver, clickMoveToButton, "'Move to' button", waitTime);

        ExtentManager.logInfo("Table '" + tableName + "' selected successfully.");
    }

    // Click on the Next button of 'Select Table' page
    public void gotoSelettableNextbtn() {
        clickOnElement(driver, clickNextButtonSelettableNextbtn, "Next button of 'Select Tables' page", waitTime);
        ExtentManager.logInfo("Next button of 'Select Tables' page is clickable.");
    }

    // Click on the Next button of 'Import SQL' page
    public void gotoImportSQLNextbtn() {
        clickOnElement(driver, ClickNextButtonimportSQLNextbtn, "Next button of 'Import SQL' page", waitTime);
        ExtentManager.logInfo("Next button of 'Import SQL' page is clickable.");
    }

    // Click on the 'Generate' button
    public void clickOnGenerate() {
        clickOnElement(driver, clickGenerateButton, "Generate button", waitTime);
        ExtentManager.logInfo("Generate button is clickable.");
    }

    // Click on the 'Go To Preview' button
    public void clickOnGoToPreview() {
        clickOnElement(driver, clickGoToPreviewButton, "Go To Preview button", waitTime);
        ExtentManager.logInfo("Go To Preview button is clickable.");
    }

    // Select the generated entity from the 'Preview' page
    public void selectGeneratedEntity() {
        clickOnElement(driver, selectEntity, "Checkbox for entity selection", waitTime);
        ExtentManager.logInfo("Generated entity selected successfully.");
    }

    // Click on the 'Publish' button
    public void clickOnPublish() {
        clickOnElement(driver, clikPublishButton, "Publish button", waitTime);
        ExtentManager.logInfo("Publish button is clickable.");
    }

    // Click on the Workflow 'Publish' button
    public void clickOnPublishTheWorkflow(String folderName, String workflowName) {

//		By clickWorkflowPublishButton = By.xpath("//*[@id='publish_item']");
//		By selectWorkflow = By.xpath("//*[@id='publish_item-popup']/ul/li[contains(text(), 'Workflow')]");
//		By clickFolderDropdown = By.xpath("//*[@id='publishRW']/div[2]/div[1]/div[2]/div[2]/div/div[1]");
//		By searchFolderField = By.xpath("//*[@placeholder='Search folder']");
        By clickOnFolderName = By.xpath("//*[@id='ruleListRighttreeView_active']/div[2]/span/div");
        By selectFolder = By.xpath("//*[@id='ruleListRighttreeView_active']/div[2]/span/div/div/button[1]/span");
        By clickOnNameField = By.xpath("//input[@placeholder='Enter Name']");
        By clickOnCreate = By.xpath("//*[@id='publishRW']/div[3]/button[2]");

        clickOnElement(driver, clickPublishButton, "publish item button", waitTime);

        clickOnElement(driver, selectWorkflowOption, "workflow button", waitTime);
        isInvisibleLoader(driver, loader);

        clickOnElement(driver, clickFolderDropdown, "folder dropdown", waitTime);
        clickOnElement(driver, searchFolderField, "search folder field", waitTime);
        sendkeysToElement(driver, searchFolderField, "search folder field", folderName);
        sendkeysToEnter(driver, searchFolderField, folderName);
        isInvisibleLoader(driver, loader);

        clickOnElement(driver, clickOnFolderName, "searched folder", waitTime);
        clickOnElement(driver, selectFolder, "to select the folder", waitTime);
        isInvisibleLoader(driver, loader);

        clickOnElement(driver, clickOnNameField, "Name field - workflow name", waitTime);

        WebElement element = driver.findElement(clickOnNameField);
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);

        sendkeysToElement(driver, clickOnNameField, "Name field - workflow name", workflowName);
        sendkeysToEnter(driver, clickOnNameField, workflowName);

        clickOnElement(driver, clickOnCreate, "create button", waitTime);
        isInvisibleLoader(driver, loader);

        ExtentManager.logInfo("Workflow is created successfully...");
    }

    // Click on the Existing Workflow 'Publish' button
    public void clickOnPublishTheExistingWorkflow(String folderName, String workflowName) {

        By clickWorkflowPublishButton = By.xpath("//*[@id='publish_item']");
        By selectWorkflow = By.xpath("//*[@id='publish_item-popup']/ul/li[contains(text(), 'Workflow')]");
        By clickAddToExistingWorkflow = By.xpath("//*[contains(text(),'Add to Existing Workflow')]");
        By clickFolderDropdown = By.xpath("//*[@id='publishRW']/div[2]/div[1]/div[2]/div[2]/div/div[1]");
        By searchFolderField = By.xpath("//*[@placeholder='Search folder']");
        By clickOnFolderName = By.xpath("//*[@id='ruleListRighttreeView_active']/div[2]/span/div");
        By selectFolder = By.xpath("//*[@id='ruleListRighttreeView_active']/div[2]/span/div/div/button[1]/span");
        By clickWorkflowDropdown = By.xpath("//*[@placeholder='Workflow Name']/span");
        By searchOnWorkfloName = By.xpath("//*[@aria-label='workflowDropdown']/span/span/input");

        By clickOnAdd = By.xpath("//button[contains(normalize-space(text()), 'Add')]");

        clickOnElement(driver, clickWorkflowPublishButton, "publish item button", waitTime);

        clickOnElement(driver, selectWorkflow, "workflow button", waitTime);
        isInvisibleLoader(driver, loader);

        clickOnElement(driver, clickAddToExistingWorkflow, "Add to Existing Workflow", waitTime);
        isInvisibleLoader(driver, loader);

        clickOnElement(driver, clickFolderDropdown, "folder dropdown", waitTime);
        clickOnElement(driver, searchFolderField, "search folder field", waitTime);

        sendkeysToElement(driver, searchFolderField, "search folder field", folderName);
        sendkeysToEnter(driver, searchFolderField, folderName);
        isInvisibleLoader(driver, loader);

        clickOnElement(driver, clickOnFolderName, "searched folder", waitTime);
        clickOnElement(driver, selectFolder, "to select the folder", waitTime);
        isInvisibleLoader(driver, loader);

        // add Workflow logic.

        clickOnElement(driver, clickWorkflowDropdown, "Workflow dropdown field", waitTime);
        clickOnElement(driver, searchOnWorkfloName, "Search field - workflow ", waitTime);
        sendkeysToElement(driver, searchOnWorkfloName, "Search field - workflow ", workflowName);
        sendkeysToEnter(driver, searchOnWorkfloName, workflowName);

        clickOnElement(driver, clickOnAdd, "Add button", waitTime);

        ExtentManager.logInfo("Rules are add successfully in the existing Workflow...");
    }

    // Click on the 'Go To Publish' button
    public void clickOnGoToPublish() throws InterruptedException {
        clickOnElement(driver, clickGoToPublishButton, "Go To Publish button", waitTime);
        ExtentManager.logInfo("Go To Publish button is clickable.");
    }

    public void clickOnPublishedRule() {
        executeStep("Validate published rule and navigate to Data Testing page.", () -> {

            // Capture parent window
            String parent = PageUtil.getParentWindow(driver);

            // Wait for loader to disappear
            waitForLoaderToDisappear(driver, loader, 30);

            // Wait for the published rule hyperlink to appear and have text
            By ruleNameLocator1 = By.xpath("//tbody[@role='rowgroup']/tr/td/a");
            WebElement ruleElement = waitForElementVisible(driver, ruleNameLocator1, 60);
            new WebDriverWait(driver, Duration.ofSeconds(60)).until(d -> !ruleElement.getText().trim().isEmpty());

            String ruleNamePublishPage = ruleElement.getText();
            ExtentManager.logInfo("Rule Name from Published Page: " + ruleNamePublishPage);

            // Click hyperlink to navigate to Data Testing
            clickOnElement(driver, clickHyperlinkPublishRule, "Published Rule", waitTime);

            // Switch to child window
            String child = switchToNewWindow(driver, parent, 30);
            waitForLoaderToDisappear(driver, loader, 30);

            // Wait for the rule name input to be present and populated
            By ruleNameLocator2 = By.xpath("//*[@placeholder='Enter rule name']");
            WebElement ruleName2 = waitForElementVisible(driver, ruleNameLocator2, 30);
            new WebDriverWait(driver, Duration.ofSeconds(30)).until(d -> !ruleName2.getDomProperty("value").isEmpty());

            String ruleNameDataTestingPage = ruleName2.getDomProperty("value");
            ExtentManager.logInfo("Rule Name from Data Testing Page: " + ruleNameDataTestingPage);

            // Validation
            if (!ruleNamePublishPage.trim().equals(ruleNameDataTestingPage.trim())) {
                throw new AssertionError("Rule name mismatch. Expected: " + ruleNamePublishPage + " but found: " + ruleNameDataTestingPage);
            }

            ExtentManager.logPass("Rule Name validated successfully.");

            // Run the published rule
            runPublishRule();

            // Validate Instance ID
            clickOnRuleRecentrun();
            validateRuleInstanceID(driver); // No InterruptedException needed anymore

            // Close child window and return to parent
            closeChildAndReturn(driver, child, parent);
        });
    }

    // Click on the hyperlink of the published workflow to navigate to the Data
    // Testing - workflow details page.
    public void clickOnPublishedWorkflow() {

        executeStep("Validate published workflow and navigate to Workflow details page.", () -> {

            // 1️⃣ Capture parent window
            String parent = PageUtil.getParentWindow(driver);

            // 2️⃣ Wait for loader
            waitForLoaderToDisappear(driver, loader, 30);

            // 3️⃣ Get workflow name from Published page
            By workflowNameLocator1 = By.xpath("//tbody[@role='rowgroup']/tr/td/a");

            WebElement ruleElement = waitForElementVisible(driver, workflowNameLocator1, 60);

            new WebDriverWait(driver, Duration.ofSeconds(30)).until(d -> !ruleElement.getText().trim().isEmpty());

            String workflowNamePublishPage = ruleElement.getText().trim();
            ExtentManager.logInfo("Workflow Name from Published Page: " + workflowNamePublishPage);

            // 4️⃣ Click hyperlink
            clickOnElement(driver, clickHyperlinkPublishRule, "Published Workflow", waitTime);

            // 5️⃣ Switch to child window
            String child = switchToNewWindow(driver, parent, 30);
            waitForLoaderToDisappear(driver, loader, 30);

            // 6️⃣ Click Overview tab
            By clickOnOverview = By.xpath("//*[@id='e-item-ej2Tab_0']/div/div/div/div");
            clickOnElement(driver, clickOnOverview, "Overview tab", waitTime);

            // 7️⃣ Get workflow name from details page
            By workflowNameLocator2 = By.id("inputWorkflowName");

            WebElement ruleName2 = waitForElementVisible(driver, workflowNameLocator2, 30);

            new WebDriverWait(driver, Duration.ofSeconds(20)).until(d -> !ruleName2.getDomProperty("value").trim().isEmpty());

            String workflowNameDataTestingPage = ruleName2.getDomProperty("value").trim();
            ExtentManager.logInfo("Workflow Name from Workflow Details Page: " + workflowNameDataTestingPage);

            if (!workflowNamePublishPage.equals(workflowNameDataTestingPage)) {
                throw new AssertionError("Workflow name mismatch. Expected: " + workflowNamePublishPage + " but found: " + workflowNameDataTestingPage);
            }

            ExtentManager.logPass("Workflow Name validated successfully.");

            // 9️⃣ Run workflow
            runPublishWorkflow();

            // 🔟 Validate Instance ID
            clickOnWorkflowRecentrun();
            validateWorkflowInstanceID(driver);

            // 1️⃣1️⃣ Close child window
            closeChildAndReturn(driver, child, parent);
        });
    }

    // Go to the Data Testing
//	public void navigatHomePage() {
//		goTo(ConfigReader.getProperty("homePageUrl"));
//	}

    public void navigatHomePage() {
        // Jenkins runtime
        goTo(homePageUrl);
        // goTo(ConfigReader.getProperty("homePageUrl"));
        ExtentManager.logInfo("Home page URL " + homePageUrl);
    }

    // Define Rule Metadata
    public void validateDefineRuleMetadataPage() {
        // validateElement(driver, ClickNextButtonimportSQLNextbtn, null, null); // Name
        // field
        WebElement RuleProperty = driver.findElement(By.xpath("//*[@id=\\\"acrdn_header_11\\\"]/div[1]/div"));
        scrollToElement(driver, RuleProperty);
    }

    // Define Check Metadata
    public void validateDefineCheckMetadataPage() {
        // validateElement(driver, ClickNextButtonimportSQLNextbtn, null, null); // Name
        // field
        WebElement RuleProperty = driver.findElement(By.xpath("//*[@id=\\\"acrdn_header_11\\\"]/div[1]/div"));
        scrollToElement(driver, RuleProperty);
    }

    // Run the publish rule on the Data Testing module.
    public void runPublishRule() {
        // Click on the Run button.
        clickOnElement(driver, By.xpath("//*[@id='scrollPane']/app-flow-diagram/div[1]/button[1]"), "run button", waitTime);
        isInvisibleLoader(driver, loader);

    }

    // Run the publish workflow on the Data Testing module.
    public void runPublishWorkflow() {
        // Click on the Run button.
        clickOnElement(driver, By.xpath("//*[@id='imaster']/app-workflow-orchestrator/div[2]/div[1]/app-workflows/app-custom-workflow/div[1]/div/div[2]/ejs-tooltip/button[1]"), "run button", waitTime);
        isInvisibleLoader(driver, loader);

    }

    // Click on the Rule Recent runs button.
    public void clickOnRuleRecentrun() {
        // Click on the Recent runs button.
        By recentRunsButton = By.xpath("//*[@id='scrollPane']/app-flow-diagram/div[1]/button[3]");
        clickOnElement(driver, recentRunsButton, "recent runs button", 10);

    }

    // Click on the Workflow Recent runs button.
    public void clickOnWorkflowRecentrun() {
        // Click on the Recent runs button.
        By recentRunsButton = By.xpath("//*[@id='imaster']/app-workflow-orchestrator/div[2]/div[1]/app-workflows/app-custom-workflow/div[1]/div/div[2]/ejs-tooltip/button[3]/span");
        clickOnElement(driver, recentRunsButton, "recent runs button", waitTime);

        // waitForSeconds(1);

    }

    // Validate Rule Instance ID...
    public void validateRuleInstanceID(WebDriver driver) {

        By refreshBtn = By.xpath("//*[@id='rrElement']/app-recent-run/div[1]/div[1]/div[2]/button");
        By instanceIdLocator = By.xpath("//*[@id='parentGrid_content_table']/tbody/tr/td[3]");
        By statusLocator = By.xpath("//*[@id='parentGrid_content_table']/tbody/tr/td[4]");

        int maxRetries = 10;
        int waitBetweenRetries = 30; // seconds

        for (int retryCount = 0; retryCount < maxRetries; retryCount++) {

            try {
                // 1️⃣ Wait for Refresh button to be clickable
                WebElement refreshButton = waitForElementClickable(driver, refreshBtn, 20);

                // Scroll into view
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", refreshButton);

                // Click refresh
                clickOnElement(driver, refreshBtn, "Refresh Button", 20);

                // 2️⃣ Wait for loader to disappear
                waitForLoaderToDisappear(driver, loader, 30);

                // 3️⃣ Check if Instance ID row is displayed
                if (isElementDisplayed(driver, instanceIdLocator, 10)) {

                    String instanceId = driver.findElement(instanceIdLocator).getText();
                    String status = driver.findElement(statusLocator).getText().trim();

                    ExtentManager.logInfo("Instance ID: " + instanceId);
                    ExtentManager.logInfo("Current Status: " + status);

                    // Exit loop if status is NOT Submitted or Running
                    if (!status.equalsIgnoreCase("Submitted") && !status.equalsIgnoreCase("Running")) {

                        ExtentManager.logInfo("Final execution status: " + status);
                        return;
                    }
                }

                // 4️⃣ Wait between retries (non-blocking)
                WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(waitBetweenRetries));
                shortWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*"))); // dummy wait

            } catch (Exception e) {
                ExtentManager.logInfo("Retry " + (retryCount + 1) + " failed: " + e.getMessage());
            }
        }

        throw new RuntimeException("Status did not change from Submitted/Running after " + maxRetries + " retries");
    }

    // Validate Workflow Instance ID...
    public void validateWorkflowInstanceID(WebDriver driver) {

        By refreshBtn = By.xpath("//*[@id='rrElement']/div/div/button");
        By instanceIdLocator = By.xpath("//*[@id='parentGrid_content_table']/tbody/tr/td[3]");
        By statusLocator = By.xpath("//*[@id='parentGrid_content_table']/tbody/tr/td[5]");

        int maxRetries = 10;
        int waitBetweenRetries = 30; // seconds

        for (int retryCount = 0; retryCount < maxRetries; retryCount++) {

            try {
                // 1️⃣ Wait for Refresh button to be clickable
                WebElement refreshButton = waitForElementClickable(driver, refreshBtn, 20);

                // Scroll into view
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", refreshButton);

                // Click refresh
                clickOnElement(driver, refreshBtn, "Refresh Button", 20);

                // 2️⃣ Wait for loader to disappear
                waitForLoaderToDisappear(driver, loader, 30);

                // 3️⃣ Check if Instance ID row is displayed
                if (isElementDisplayed(driver, instanceIdLocator, 10)) {

                    String instanceId = driver.findElement(instanceIdLocator).getText();
                    String status = driver.findElement(statusLocator).getText().trim();

                    ExtentManager.logInfo("Instance ID: " + instanceId);
                    ExtentManager.logInfo("Current Status: " + status);

                    // Exit loop if status is NOT Submitted or Running
                    if (!status.equalsIgnoreCase("Submitted") && !status.equalsIgnoreCase("Running")) {

                        ExtentManager.logInfo("Final execution status: " + status);
                        return;
                    }
                }

                // 4️⃣ Wait between retries (non-blocking)
                WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(waitBetweenRetries));
                shortWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*"))); // dummy wait

            } catch (Exception e) {
                ExtentManager.logInfo("Retry " + (retryCount + 1) + " failed: " + e.getMessage());
            }
        }

        throw new RuntimeException("Status did not change from Submitted/Running after " + maxRetries + " retries");
    }
}
