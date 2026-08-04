package com.qa.pages;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Objects;

import com.qa.base.Base;
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
    // private WebDriver driver;
    public static final int waitTime = 4; // Global wait time

    public TestGeneratorPage(WebDriver driver) {
        Base.driver = driver;
    }

    /**
     * ---------------------- By locators ----------------------------------
     **/

    By testGeneratorModule = getElementLocator(prop.getProperty("home.testgenerator"));
    // Checksum Rule
    By selectChecksumWizard = getElementLocator(prop.getProperty("wizard.checksumRule"));
    // Recon Rule
    By selectReconWizard = getElementLocator(prop.getProperty("wizard.reconRule"));
    // Validation Rule
    By selectValidationWizard = getElementLocator(prop.getProperty("wizard.validationRule"));
    // Pushdown Rule
    By selectPushdownWizard = getElementLocator(prop.getProperty("wizard.pushdownRule"));
    // Duplicate Rule
    By selectDuplicateWizard = getElementLocator(prop.getProperty("wizard.duplicateRule"));

    By clickSearchTemplateField = getElementLocator(prop.getProperty("wizard.searchField.templateName"));
    By enterTemplateName = getElementLocator(prop.getProperty("wizard.searchField.enter.templateName"));
    By clickSearchButtonIcon = getElementLocator(prop.getProperty("wizard.searchField.searchButtonIcon"));
    By selectExistingDynamicChecksumTemplate = getElementLocator(prop.getProperty("wizard.existing.dynamic.template"));
    By clickOnWorkspaceDropdown = getElementLocator(prop.getProperty("wizard.workspace.dropdown"));
    By selectWorkspace = getElementLocator(prop.getProperty("select.Workspace"));
    By selectSearchWorkspace = getElementLocator(prop.getProperty("select.searchedWorkspace"));
    By clickOnFolderDropdown = getElementLocator(prop.getProperty("wizard.folder.dropdown"));
    By searchFolderName = getElementLocator(prop.getProperty("wizard.search.folderName"));
    By clickOnSearchIcon = getElementLocator(prop.getProperty("wizard.folderName.search.icon"));
    By selectSearchedFolderName = getElementLocator(prop.getProperty("wizard.folderName.searched"));
    By clickRightTick = getElementLocator(prop.getProperty("wizard.folderName.rightTick.icon"));
    By clickWorkspaceNextButton = getElementLocator(prop.getProperty("wizard.click.nextButton.workspaceNextButton"));
    By clickNextButtonMetadataNextButton = getElementLocator(prop.getProperty("wizard.click.nextButton.metadataNextButton"));
    By clickNextButtonCheckNextButton = getElementLocator(prop.getProperty("wizard.click.nextButton.checkNextButton"));
    By clickNextButtonNotificationNextButton = getElementLocator(prop.getProperty("wizard.click.nextButton.notificationNextButton"));

    // Source Dataset
    By selectDatabaseConnectionType = getElementLocator(prop.getProperty("wizard.Database.connectionType"));
    By selectCloudDataWarehouseConnectionType = getElementLocator(prop.getProperty("wizard.CloudDataWarehouse.connectionType"));
    By selectFileConnectionType = getElementLocator(prop.getProperty("wizard.File.connectionType"));

    By clickSourceDataSetConnectionTypeDropdown = getElementLocator(prop.getProperty("wizard.click.sourceConnectionTypeDropdown"));
    By clickSourceDataSetConnectionDropdown = getElementLocator(prop.getProperty("wizard.click.sourceConnectionDropdown"));
    By enterSourceConnectionName = getElementLocator(prop.getProperty("wizard.source.connection.input"));
    By clickSourceSchemaDropdown = getElementLocator(prop.getProperty("wizard.source.schema.dropdown"));
    By enterSourceSchemaName = getElementLocator(prop.getProperty("wizard.source.schema.input"));

    // Target Dataset
    By clickTargetdatasetConnectionTypeDropdown = getElementLocator(prop.getProperty("wizard.click.targetConnectionTypeDropdown"));
    By clickTargetdatasetConnectionDropdown = getElementLocator(prop.getProperty("wizard.click.targetConnectionDropdown"));
    By enterTargetConnectionName = getElementLocator(prop.getProperty("wizard.target.connection.input"));
    By clickTargetSchemaDropdown = getElementLocator(prop.getProperty("wizard.target.schema.dropdown"));

    By clickNextButtondatasetNextbtn = getElementLocator(prop.getProperty("wizard.click.nextButton.datasetNextButton"));
    By ClickNextButtonimportSQLNextbtn = getElementLocator(prop.getProperty("wizard.click.nextButton.importSQLNextButton"));

    // Available Table
    By clickAvailableTable = getElementLocator(prop.getProperty("availableTable.click"));
    By selectAvailableTable = getElementLocator(prop.getProperty("availableTable.select.table"));
    By clickMoveToButton = getElementLocator(prop.getProperty("availableTable.moveto.click"));
    By clickNextButtonSelettableNextbtn = getElementLocator(prop.getProperty("availableTable.click.nextButton.selectTableNextButton"));

    // Click on Generate
    By clickGenerateButton = getElementLocator(prop.getProperty("generateButton.click"));
    // Click on the Account Dropdown
    By clickOnAccountDropdown = getElementLocator(prop.getProperty("wizard.account.dropdown"));
    // Enter the account Name
    By enterAccountName = getElementLocator(prop.getProperty("wizard.account.searchfield"));
    // Click on the next button of Select account
    By clickNextButtonSelectAccountNextbtn = getElementLocator(prop.getProperty("wizard.account.next.button"));
    // Select the generated entity for publish
    By selectEntity = getElementLocator(prop.getProperty("publishEntity.select"));
    // Click on publish button to publish
    By clikPublishButton = getElementLocator(prop.getProperty("published.click"));
    // Click on Go To Publish
    By clickGoToPublishButton = getElementLocator(prop.getProperty("goToPublishButton.click"));
    // Click on hyperlink of publish entity
    By clickHyperlinkPublishRule = getElementLocator(prop.getProperty("publishRuleHyperlink.click"));
    // Click on source UseMetaDataCache
    By sourceUseMetadataCache = getElementLocator(prop.getProperty("source.metaDataCache"));
    // Click on target UseMetaDataCache
    By targetUseMetadataCache = getElementLocator(prop.getProperty("target.metaDataCache"));
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

    /// Preview Page ////
    // Click on the search button icon
    By clickOnSearchIconPreview = getElementLocator(prop.getProperty("preview.search.icon"));
    // Select the searched entity from the list
    By entityIsDisplayedPreview = getElementLocator(prop.getProperty("preview.searchEntity.display"));

    /// published Page ////
    // Click on the search button icon
    By clickOnSearchIconPublished = getElementLocator(prop.getProperty("published.search.icon"));
    // Select the searched entity from the list
    By entityIsDisplayedPublished = getElementLocator(prop.getProperty("published.searchEntity.display"));

    /// Data Testing Rule page ////
    // Click on the run button
    By clickOnRunButton = getElementLocator(prop.getProperty("run.button"));
    // Click on the Recent runs button
    By clickOnRecentruns = getElementLocator(prop.getProperty("recentruns.button"));
    // Click on the refresh button of recent run
    By clcikOnRefreshButton = getElementLocator(prop.getProperty("recentruns.refresh.button"));
    // Get the Instance ID
    By getInstanceID = getElementLocator(prop.getProperty("recentruns.instanceID"));
    // Get the Status
    By geStatus = getElementLocator(prop.getProperty("recentruns.status"));

    /// Data Testing Workflow page ////
    // Click on the refresh button of recent run in workflow page
    By clcikOnRefreshButtonInWorkflow = getElementLocator(prop.getProperty("workflow.recentruns.refresh.button"));
    // Get the Instance ID
    By getInstanceIDInWorkflow = getElementLocator(prop.getProperty("workflow.recentruns.instanceID"));

    /// Request page ///
    // Click on the 'Go To Request' button
    By clickOnGoToRequestButton = getElementLocator(prop.getProperty("goToRequest.button"));
    // Search field box to search request
    By searchFieldOfRequest = getElementLocator(prop.getProperty("requests.searchFieldBox"));
    // Click on the view hyperlink
    By clickOnViewHyperlink = getElementLocator(prop.getProperty("view.hyperlink"));
    // Click on the refresh button of logs pane
    By clickOnRefreshButton = getElementLocator(prop.getProperty("logs.refresh.button"));
    // Click on the close button
    By clickOnCloseButton = getElementLocator(prop.getProperty("close.button"));
    // Click on the previe tab from request page
    By clickOnPreviewTab = getElementLocator(prop.getProperty("preview.tab"));
    // Search field box of search preview
    By SearchFieldOfPreview = getElementLocator(prop.getProperty("preview.searchFieldBox"));

    By loader = getElementLocator(prop.getProperty("loaderIsDisplayed"));

    public void executeStep(String stepName, Runnable action) {
        try {
            action.run();

            // 🔍 Detect toast AFTER action
            String uiError = captureUIErrorIfPresent(driver);
            if (uiError != null && !uiError.isEmpty()) {
                throw new RuntimeException(uiError);
            }

            ExtentManager.logPass(STR."\{stepName} ➝ PASSED");

        } catch (Throwable e) {

            ExtentManager.logFail(STR."\{stepName} ➝ FAILED due to: \{e.getMessage()}");

            // 📸 Screenshot (once per failure)
            ExtentManager.captureScreenshot("Step_Failure");

            // 🚪 Exit current flow
            navigatHomePage();

            // 🔥 STOP FLOW IMMEDIATELY
            throw new FlowAbortException(STR."Flow aborted: \{stepName}", e);
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

    public void createRuleUsingDefaultDynamicTemplate(String ruleType,
                                                      String templateName,
                                                      String workspaceName,
                                                      String folderName,
                                                      String connectionType,
                                                      String sourceConnectionName,
                                                      String targetConnectionName,
                                                      String schemaName,
                                                      String tableName,
                                                      String accountName,
                                                      String databaseName) {

        executeStep("Create Rule Using Default Dynamic Template", () -> {

            // Open Test Generator
            clickTestGenerator();

            // Select Rule Type
            clickOnRuleType(ruleType);

            // Select Account
            clickOnAccountDropdown(accountName);
            gotoSelectAccountNextbtn();

            // Select Template
            clickOnSearchField();
            enterOnSearchField(templateName);
            selectExistingChecksumTemplate();

            // Select Workspace & Folder
            clickOnWorkspaceField(workspaceName);
            clickOnFolderField(folderName);

            // Navigate Metadata Screens
            gotoWorkspaceNextbtn();
            gotoRuleMetadataNextbtn();
            gotoCheckMetadataNextbtn();
            gotoNotificationNextbtn();

            // Select Source Dataset
            selectionSourceDataset(connectionType, sourceConnectionName, schemaName, databaseName);

            // Select Target Dataset (Validation & Pushdown don't require target)
            if (!ruleType.equalsIgnoreCase("validation")
                    && !ruleType.equalsIgnoreCase("pushdown")) {

                selectionTargetDataset(connectionType, targetConnectionName, schemaName, databaseName);
            }

            // Select Table
            gotoDatasetNextbtn();
            selectionAvailableTable(tableName);
            gotoSelettableNextbtn();

            // Generate Rule
            clickOnGenerate();

            // Go to Request
            ClickGoToRequestButton();

            // Publish Rule
            selectGeneratedEntity();
            clickOnPublish();

            try {
                clickOnGoToPublish();
            } catch (InterruptedException e) {
                throw new RuntimeException("Failed to navigate to published rule.", e);
            }

            // Verify Published Rule
            clickOnPublishedRule();

            // Navigate Home
            navigatHomePage();
        });
    }

    // ============================
    // Default Import Template
    // ============================

    public void createRuleUsingDefaultImportTemplate(String ruleType,
                                                     String templateName,
                                                     String workspaceName,
                                                     String folderName,
                                                     String connectionType,
                                                     String fileType,
                                                     String sourceConnectionName,
                                                     String targetConnectionName,
                                                     String accountName) {

        executeStep("Create Rule Using Default Import Template", () -> {

            boolean isReconOrChecksum =
                    ruleType.equalsIgnoreCase("checksum")
                            || ruleType.equalsIgnoreCase("recon");

            // Open Test Generator
            clickTestGenerator();

            // Select Rule Type
            clickOnRuleType(ruleType);

            // Select Account
            clickOnAccountDropdown(accountName);
            gotoSelectAccountNextbtn();

            // Select Template
            clickOnSearchField();
            enterOnSearchField(templateName);
            selectExistingChecksumTemplate();

            // Workspace & Folder
            clickOnWorkspaceField(workspaceName);
            clickOnFolderField(folderName);

            // Navigate Wizard
            gotoWorkspaceNextbtn();
            gotoRuleMetadataNextbtn();
            gotoCheckMetadataNextbtn();
            gotoNotificationNextbtn();

            // Source Dataset
            selectionSourceDatasetForImport(connectionType, sourceConnectionName);

            // Target Dataset (Recon / Checksum only)
            if (isReconOrChecksum) {
                selectionTargetDatasetForImport(connectionType, targetConnectionName);
            }

            gotoDatasetNextbtn();

            // Upload Import File
            uploadFileByRuleAndConnection(ruleType, connectionType, fileType);

            // Generate Rule
            gotoImportSQLNextbtn();
            clickOnGenerate();

            // Request Page
            ClickGoToRequestButton();

            // Publish Rule
            selectGeneratedEntity();
            clickOnPublish();

            try {
                clickOnGoToPublish();
            } catch (InterruptedException e) {
                throw new RuntimeException("Failed to navigate to published rule.", e);
            }

            clickOnPublishedRule();

            // Return Home
            navigatHomePage();
        });
    }

    /// ************* ///
    // Create a workflow
    /// ************* ///

    public void createWorkflowUsingDefaultDynamicTemplate(String ruleType,
                                                          String templateName,
                                                          String workspaceName,
                                                          String folderName,
                                                          String connectionType,
                                                          String sourceConnectionName,
                                                          String targetConnectionName,
                                                          String schemaName,
                                                          String tableName,
                                                          String workflowName,
                                                          String accountName,
                                                          String databaseName) {

        executeStep("Create Workflow Using Default Dynamic Template", () -> {

            // Open Test Generator
            clickTestGenerator();

            // Select Rule Type
            clickOnRuleType(ruleType);

            // Select Account
            clickOnAccountDropdown(accountName);
            gotoSelectAccountNextbtn();

            // Select Template
            clickOnSearchField();
            enterOnSearchField(templateName);
            selectExistingChecksumTemplate();

            // Select Workspace & Folder
            clickOnWorkspaceField(workspaceName);
            clickOnFolderField(folderName);

            // Navigate Metadata Screens
            gotoWorkspaceNextbtn();
            gotoRuleMetadataNextbtn();
            gotoCheckMetadataNextbtn();
            gotoNotificationNextbtn();

            // Select Source Dataset
            selectionSourceDataset(
                    connectionType,
                    sourceConnectionName,
                    schemaName,
                    databaseName);

            // Select Target Dataset (Validation & Pushdown don't require target)
            if (!ruleType.equalsIgnoreCase("validation")
                    && !ruleType.equalsIgnoreCase("pushdown")) {

                selectionTargetDataset(
                        connectionType,
                        targetConnectionName,
                        schemaName,
                        databaseName);
            }

            // Select Table
            gotoDatasetNextbtn();
            selectionAvailableTable(tableName);
            gotoSelettableNextbtn();

            // Generate Workflow
            clickOnGenerate();

            // Open Request
            ClickGoToRequestButton();

            // Select Generated Entity
            selectGeneratedEntity();

            // Publish Workflow
            clickOnPublishTheWorkflow(folderName, workflowName);

            try {
                clickOnGoToPublish();
            } catch (InterruptedException e) {
                throw new RuntimeException("Failed to navigate to published workflow.", e);
            }

            // Open Published Workflow
            clickOnPublishedWorkflow();

            // Navigate Home
            navigatHomePage();
        });
    }

    /// ************* ///
    // Existing workflow
    /// ************* ///

    public void existingWorkflowUsingDefaultDynamicTemplate(String ruleType,
                                                            String templateName,
                                                            String workspaceName,
                                                            String folderName,
                                                            String connectionType,
                                                            String sourceConnectionName,
                                                            String targetConnectionName,
                                                            String schemaName,
                                                            String tableName,
                                                            String workflowName,
                                                            String accountName,
                                                            String databaseName) {

        executeStep("Publish Existing Workflow Using Default Dynamic Template", () -> {

            // Open Test Generator
            clickTestGenerator();

            // Select Rule Type
            clickOnRuleType(ruleType);

            // Select Account
            clickOnAccountDropdown(accountName);
            gotoSelectAccountNextbtn();

            // Select Template
            clickOnSearchField();
            enterOnSearchField(templateName);
            selectExistingChecksumTemplate();

            // Select Workspace & Folder
            clickOnWorkspaceField(workspaceName);
            clickOnFolderField(folderName);

            // Navigate Metadata Screens
            gotoWorkspaceNextbtn();
            gotoRuleMetadataNextbtn();
            gotoCheckMetadataNextbtn();
            gotoNotificationNextbtn();

            // Select Source Dataset
            selectionSourceDataset(
                    connectionType,
                    sourceConnectionName,
                    schemaName,
                    databaseName);

            // Select Target Dataset (Validation & Pushdown don't require target)
            if (!ruleType.equalsIgnoreCase("validation")
                    && !ruleType.equalsIgnoreCase("pushdown")) {

                selectionTargetDataset(
                        connectionType,
                        targetConnectionName,
                        schemaName,
                        databaseName);
            }

            // Select Table
            gotoDatasetNextbtn();
            selectionAvailableTable(tableName);
            gotoSelettableNextbtn();

            // Generate Workflow
            clickOnGenerate();

            // Go To Request
            ClickGoToRequestButton();

            // Select Generated Entity
            selectGeneratedEntity();

            // Publish Existing Workflow
            clickOnPublishTheExistingWorkflow(folderName, workflowName);

            try {
                clickOnGoToPublish();
            } catch (InterruptedException e) {
                throw new RuntimeException("Failed to navigate to published workflow.", e);
            }

            // Verify Published Workflow
            clickOnPublishedWorkflow();

            // Navigate Home
            navigatHomePage();
        });
    }

    // ============================
    // Page method
    // ============================

    private void uploadFileByRuleAndConnection(String ruleType, String connectionType, String fileType) {

        String fileName = resolveImportFileName(ruleType, connectionType, fileType);

        if (fileName == null) {
            throw new IllegalArgumentException(STR."No import file found for RuleType: \{ruleType}, ConnectionType: \{connectionType}");
        }

        uploadingFiles(fileName);
    }

    private String resolveImportFileName(String ruleType, String connectionType, String fileType) {

        return switch (ruleType.toLowerCase()) {
            case "checksum" ->
                    resolveByConnection(connectionType, fileType, "Checksum.xlsx", "Checksum - Redshift.xlsx", "Checksum - Flat File-SQL.xlsx", "Checksum - Excel.xlsx");
            case "recon" ->
                    resolveByConnection(connectionType, fileType, "Recon.xlsx", "Recon - Redshift.xlsx", "Recon - Flat File-SQL.xlsx", "Recon - Excel.xlsx");
            case "validation" ->
                    resolveByConnection(connectionType, fileType, "Validation.xlsx", "Validation - Redshift.xlsx", "Validation - Flat File-SQL.xlsx", "Validation - Excel.xlsx");
            case "pushdown" ->
                    resolveByConnection(connectionType, fileType, "Pushdown.xlsx", "Pushdown - Redshift.xlsx", "Pushdown - Flat File-SQL.xlsx", "Pushdown - Excel.xlsx");
            case "duplicate" ->
                    resolveByConnection(connectionType, fileType, "Duplicate.xlsx", "Pushdown - Redshift.xlsx", "Pushdown - Flat File-SQL.xlsx", "Pushdown - Excel.xlsx");
            default -> throw new IllegalArgumentException(STR."Invalid rule type: \{ruleType}");
        };
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
                    throw new IllegalArgumentException(STR."Invalid file type: \{fileType}");
                }

            default:
                throw new IllegalArgumentException(STR."Invalid connection type: \{connectionType}");
        }
    }

    public void clickTestGenerator() {

        clickOnElement(driver, testGeneratorModule, "Test Generator", 1);
    }

    public void clickOnRuleType(String ruleType) {
        By locator = switch (ruleType.toLowerCase()) {
            case "checksum" -> selectChecksumWizard;
            case "recon" -> selectReconWizard;
            case "validation" -> selectValidationWizard;
            case "pushdown" -> selectPushdownWizard;
            case "duplicate" -> selectDuplicateWizard;
            default -> throw new IllegalArgumentException(STR."Invalid Rule Type: \{ruleType}");
        };

        clickOnElement(driver, locator, STR."\{ruleType} rule type", 2);

    }

    // Click on the Account dropdown
    public void clickOnAccountDropdown(String accountName) {

        try {
            if (!clickOnElement(driver, clickOnAccountDropdown, "Account", 10)) {
                return;
            }

            clickOnElement(driver, enterAccountName, "Enter account name", 15);
            sendkeysToElement(driver, enterAccountName, "Enter account name", accountName);
            sendkeysToEnter(driver, enterAccountName, "Select workspace field");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void gotoSelectAccountNextbtn() {
        // clickOnElement(driver, clickNextButtonSelectAccountNextbtn, "Next button of
        // 'Select Account' page", "button");
        clickOnElement(driver, clickNextButtonSelectAccountNextbtn, "Next button of 'Select Account' page", 1);
    }

    // Click on the search field box
    public void clickOnSearchField() {
        clickOnElement(driver, clickSearchTemplateField, "'Search template' search field ", waitTime);
    }

    // Enter the data on the search field
    public void enterOnSearchField(String templateName) {

        sendkeysToElement(driver, enterTemplateName, templateName, templateName);

        clickOnElement(driver, clickSearchButtonIcon, "search button icon", 2);

    }

    // Select the existing template form the list
    public void selectExistingChecksumTemplate() {
        // Also ensure no loader is covering the grid
        isInvisibleLoader(driver, loader);
        // clickOnElement(driver, selectExistingDynamicChecksumTemplate, "select searched
        // item", "search item");
        clickOnElement(driver, selectExistingDynamicChecksumTemplate, "select searched item", waitTime);
    }

    public void clickOnWorkspaceField(String workspaceName) {

        isInvisibleLoader(driver, loader);

        // Open workspace dropdown
        clickOnElement(driver, clickOnWorkspaceDropdown, "select workspace dropdown.", 10);
        ExtentManager.logInfo("Workspace dropdown opened.");

        // Click search field
        clickOnElement(driver, selectWorkspace, "searched workspace.", waitTime);

        // Enter workspace name and press ENTER
        sendkeysToElement(driver, selectSearchWorkspace, "Select workspace field", workspaceName);
        sendkeysToEnter(driver, selectSearchWorkspace, "Select workspace field");

        isInvisibleLoader(driver, loader);

        ExtentManager.logPass(STR."Workspace name selected: \{workspaceName}");
    }

    // Click on the Folder selector field

    public void clickOnFolderField(String folderName) {

        isInvisibleLoader(driver, loader);

        // Open folder dropdown
        clickOnElement(driver, clickOnFolderDropdown, "Folder dropdown", 10);
        ExtentManager.logInfo("Folder dropdown opened.");

        // Search folder
        sendkeysToElement(driver, searchFolderName, "searching folder name", folderName);

        // Click search icon
        clickOnElement(driver, clickOnSearchIcon, "Search folder", waitTime);

        isInvisibleLoader(driver, loader);

        // Select folder from results
        clickOnElement(driver, selectSearchedFolderName, "Folder result", 10);

        clickOnElement(driver, clickRightTick, "Selected folder name", 10);

        isInvisibleLoader(driver, loader);

        ExtentManager.logPass(STR."Folder name selected: \{folderName}");
    }

    public void gotoWorkspaceNextbtn() {
        clickOnElement(driver, clickWorkspaceNextButton, "Next button of 'Select Container' page", 1);
    }

    public void gotoRuleMetadataNextbtn() {// Click Next button on Rule Metadata page
        clickOnElement(driver, clickNextButtonMetadataNextButton, "next button of 'Define Rule Metadata' page", 1);
        ExtentManager.logInfo("Next button of 'Define Rule Metadata' page is clickable.");
    }

    public void gotoCheckMetadataNextbtn() {// Click Next button on Check Metadata page
        clickOnElement(driver, clickNextButtonCheckNextButton, "next button of 'Define Check Metadata' page", 1);
        ExtentManager.logInfo("Next button of 'Define Check Metadata' page is clickable.");
    }

    public void gotoNotificationNextbtn() {// Click Next button on Configure Notifications page
        clickOnElement(driver, clickNextButtonNotificationNextButton, "next button of 'Configure Notifications' page", 1);
        ExtentManager.logInfo("Next button of 'Configure Notifications' page is clickable.");
    }

    /// ********* Select the source dataset ********** ///
    /// // *******************************/////
    /// //////////// ********************************* ////////////////
    /// // *******************************/////

    public void selectionSourceDataset(String connectionType, String connectionName, String schemaName, String databaseName) {

        // Click on the source connection type dropdown
        clickOnElement(driver, clickSourceDataSetConnectionTypeDropdown, "select source connection type.", 10);

        if (connectionType.equalsIgnoreCase("Database")) {

            clickOnElement(driver, selectDatabaseConnectionType, "source database connection.", 10);
            isInvisibleLoader(driver, loader);

            clickOnElement(driver, clickSourceDataSetConnectionDropdown, "select connection dropdown.", 20);

            sendkeysToElement(driver, enterSourceConnectionName, "source connection name", connectionName);

            sendkeysToEnter(driver, enterSourceConnectionName, "source connection name");
            isInvisibleLoader(driver, loader);

            // Click Source UseMetadataCache
            try {
                if (isDisplayed(driver, sourceUseMetadataCache, 1)) {
                    clickOnElement(driver, sourceUseMetadataCache, "Use Metadata Cache check box", 5);
                }
            } catch (Exception e) {
                System.out.println("Use Metadata Cache checkbox is not displayed.");
            }

            ExtentManager.logInfo("Database connection name selected successfully.");

        } else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse")) {

            clickOnElement(driver, selectCloudDataWarehouseConnectionType, "source cloud data warehouse connection.", 10);
            isInvisibleLoader(driver, loader);

            clickOnElement(driver, clickSourceDataSetConnectionDropdown, "select connection dropdown.", 20);

            sendkeysToElement(driver, enterSourceConnectionName, "source connection name", connectionName);

            sendkeysToEnter(driver, enterSourceConnectionName, "source connection name");
            isInvisibleLoader(driver, loader);

            ExtentManager.logInfo("Cloud warehouse connection name selected successfully.");

            // Click source UseMetadataCache
            try {
                if (isDisplayed(driver, sourceUseMetadataCache, 1)) {
                    clickOnElement(driver, sourceUseMetadataCache, "Use Metadata Cache check box", 5);
                }
            } catch (Exception e) {
                System.out.println("Use Metadata Cache checkbox is not displayed.");
            }

            // Click database dropdown
            clickOnElement(driver, clickSourceDatabaseDropdown, "database dropdown.", 10);
            isInvisibleLoader(driver, loader);

            sendkeysToElement(driver, enterSourceDatabaseName, "source database name", databaseName);
            isInvisibleLoader(driver, loader);

            sendkeysToEnter(driver, enterSourceDatabaseName, "source database name");
            isInvisibleLoader(driver, loader);

            ExtentManager.logInfo("Database name selected successfully.");
        }

        // Schema selection
        clickOnElement(driver, clickSourceSchemaDropdown, "choose source schema dropdown.", 10);
        isInvisibleLoader(driver, loader);

        sendkeysToElement(driver, enterSourceSchemaName, "source schema name", schemaName);
        isInvisibleLoader(driver, loader);

        sendkeysToEnter(driver, enterSourceSchemaName, "source schema name");
        isInvisibleLoader(driver, loader);

        ExtentManager.logInfo("Schema selected successfully.");
    }


    public void selectionSourceDatasetForImport(String connectionType, String connectionName) {

        // Click Source Dataset Connection Type Dropdown
        clickOnElement(driver, clickSourceDataSetConnectionTypeDropdown, "select connection type.", 10);

        // Select connection type
        switch (connectionType.toLowerCase()) {
            case "database":
                clickOnElement(driver, selectDatabaseConnectionType, "source database connection.", 10);
                break;

            case "cloud data warehouse":
                clickOnElement(driver, selectCloudDataWarehouseConnectionType, "source cloud data warehouse connection.", 10);
                break;

            case "file":
                validateField(driver, selectFileConnectionType, "Connection Type", 10);
                clickOnElement(driver, selectFileConnectionType, "source file connection.", 10);
                break;

            default:
                throw new IllegalArgumentException(STR."Invalid connection type: \{connectionType}");
        }

        // Click Source Dataset Connection Dropdown
        validateField(driver, clickSourceDataSetConnectionDropdown, connectionName, 10);
        clickOnElement(driver, clickSourceDataSetConnectionDropdown, "select connection dropdown.", 10);
        isInvisibleLoader(driver, loader);

        // Enter and confirm connection name
        validateField(driver, enterSourceConnectionName, "Enter source connection name", 5);
        sendkeysToElement(driver, enterSourceConnectionName, "source connection name", connectionName);
        sendkeysToEnter(driver, enterSourceConnectionName, "source connection name");
        isInvisibleLoader(driver, loader);

        // Click source UseMetadataCache
        clickOnElement(driver, sourceUseMetadataCache, "Use Metadata Cache check box", 2);

        ExtentManager.logInfo(STR."Source dataset selected successfully: \{connectionType} / \{connectionName}");
    }

    /// ********* Select the target dataset ********** ///
    /// // *******************************/////
    /// //////////// ********************************* ////////////////
    /// // *******************************/////
    public void selectionTargetDataset(String connectionType, String connectionName, String schemaName, String databaseName) {

        // Click target connection type dropdown
        clickOnElement(driver, clickTargetdatasetConnectionTypeDropdown, "select target connection type.", 10);

        if (connectionType.equalsIgnoreCase("Database")) {

            clickOnElement(driver, selectDatabaseConnectionType, "target database connection.", 20);
            isInvisibleLoader(driver, loader);

            clickOnElement(driver, clickTargetdatasetConnectionDropdown, "select target connection dropdown.", 20);

            sendkeysToElement(driver, enterTargetConnectionName, "target connection name", connectionName);

            sendkeysToEnter(driver, enterTargetConnectionName, "target connection name");
            isInvisibleLoader(driver, loader);

            // Click target UseMetadataCache
            try {
                if (isDisplayed(driver, targetUseMetadataCache, 1)) {
                    clickOnElement(driver, targetUseMetadataCache, "Use Metadata Cache check box", 5);
                }
            } catch (Exception e) {
                System.out.println("Use Metadata Cache checkbox is not displayed.");
            }

            ExtentManager.logInfo("Target database connection selected successfully.");

        } else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse")) {

            clickOnElement(driver, selectCloudDataWarehouseConnectionType, "target cloud data warehouse connection.", 10);
            isInvisibleLoader(driver, loader);

            clickOnElement(driver, clickTargetdatasetConnectionDropdown, "select target connection dropdown.", 20);

            sendkeysToElement(driver, enterTargetConnectionName, "target connection name", connectionName);

            sendkeysToEnter(driver, enterTargetConnectionName, "target connection name");
            isInvisibleLoader(driver, loader);

            ExtentManager.logInfo("Target cloud connection selected successfully.");

            // Click target UseMetadataCache
            try {
                if (isDisplayed(driver, targetUseMetadataCache, 1)) {
                    clickOnElement(driver, targetUseMetadataCache, "Use Metadata Cache check box", 5);
                }
            } catch (Exception e) {
                System.out.println("Use Metadata Cache checkbox is not displayed.");
            }

            // Click database dropdown
            clickOnElement(driver, clickTargetDatabaseDropdown, "database dropdown.", 10);
            isInvisibleLoader(driver, loader);

            sendkeysToElement(driver, enterTargetDatabaseName, "target database name", databaseName);

            sendkeysToEnter(driver, enterTargetDatabaseName, "target database name");
            isInvisibleLoader(driver, loader);

            ExtentManager.logInfo("Target database selected successfully.");
        }

        // Schema selection
        clickOnElement(driver, clickTargetSchemaDropdown, "choose target schema dropdown.", 10);
        isInvisibleLoader(driver, loader);

        sendkeysToElement(driver, enterSourceSchemaName, "target schema name", schemaName);

        sendkeysToEnter(driver, enterSourceSchemaName, "target schema name");
        isInvisibleLoader(driver, loader);

        ExtentManager.logInfo("Target schema selected successfully.");
    }

    public void selectionTargetDatasetForImport(String connectionType, String connectionName) {
        // Click Target Dataset Connection Type Dropdown
        clickOnElement(driver, clickTargetdatasetConnectionTypeDropdown, "select target connection type.", 10);

        // Select connection type
        switch (connectionType.toLowerCase()) {
            case "database":
                clickOnElement(driver, selectDatabaseConnectionType, "target database connection.", 10);
                break;

            case "cloud data warehouse":
                clickOnElement(driver, selectCloudDataWarehouseConnectionType, "target cloud data warehouse connection.", 10);
                break;

            case "file":
                validateField(driver, selectFileConnectionType, "Connection Type", 5);
                clickOnElement(driver, selectFileConnectionType, "target file connection.", 10);
                break;

            default:
                throw new IllegalArgumentException(STR."Invalid connection type: \{connectionType}");
        }

        // Click Target Dataset Connection Dropdown
        validateField(driver, clickTargetdatasetConnectionDropdown, connectionName, 5);
        clickOnElement(driver, clickTargetdatasetConnectionDropdown, "select target connection dropdown.", 10);
        isInvisibleLoader(driver, loader);

        // Enter and confirm connection name
        validateField(driver, enterTargetConnectionName, "Enter target connection name", 5);
        sendkeysToElement(driver, enterTargetConnectionName, "target connection name", connectionName);
        sendkeysToEnter(driver, enterTargetConnectionName, "target connection name");
        isInvisibleLoader(driver, loader);

        // Click target UseMetadataCache
        clickOnElement(driver, targetUseMetadataCache, "Use Metadata Cache check box", 2);

        ExtentManager.logInfo(STR."Target dataset selected successfully: \{connectionType} / \{connectionName}");
    }

    // Import SQL Files
    public void uploadingFiles(String fileName) {
        // Upload file
        uploadFile(driver, By.cssSelector("input[type='file'][name='UploadFiles']"), fileName);

        // Optional: verify the uploaded file appears in the list
        WebElement uploadedFileLabel = driver.findElement(By.cssSelector("li.e-upload-file-list"));
        String uploadedName = uploadedFileLabel.getAttribute("data-file-name");

        ExtentManager.logInfo(STR."✔ File shown in UI: \{uploadedName}");
    }

    // Click on the Next button of 'Select Dataset' page
    public void gotoDatasetNextbtn() {
        clickOnElement(driver, clickNextButtondatasetNextbtn, "next button of 'Select Dataset' page.", 1);
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

        ExtentManager.logInfo(STR."Table '\{tableName}' selected successfully.");
    }

    // Click on the Next button of 'Select Table' page
    public void gotoSelettableNextbtn() {
        clickOnElement(driver, clickNextButtonSelettableNextbtn, "Next button of 'Select Tables' page", 1);
        ExtentManager.logInfo("Next button of 'Select Tables' page is clickable.");
    }

    // Click on the Next button of 'Import SQL' page
    public void gotoImportSQLNextbtn() {
        clickOnElement(driver, ClickNextButtonimportSQLNextbtn, "Next button of 'Import SQL' page", 1);
        ExtentManager.logInfo("Next button of 'Import SQL' page is clickable.");
    }

    // Click on the 'Generate' button
    public void clickOnGenerate() {
        clickOnElement(driver, clickGenerateButton, "Generate button", waitTime);
        ExtentManager.logInfo("Generate button is clickable.");
    }

    // Select the generated entity from the 'Preview' page
    public void selectGeneratedEntity() {

        // Validate the preview rule is displayed or not in the list
        searchEntityUntilIsDisplayedOnPreview();

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
        element.sendKeys(STR."\{Keys.CONTROL}a");
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

        // Validate the published rule is displayed or not in the list.
        searchEntityUntilIsDisplayedOnPublished();

        executeStep("Validate published rule and navigate to Data Testing page.", () -> {

            // Capture parent window
            String parent = PageUtil.getParentWindow(driver);

            // Wait for loader to disappear
            waitForLoaderToDisappear(driver, loader, 30);

            // Wait for the published rule hyperlink to appear and have text
            By ruleNameLocator1 = By.xpath("//tbody[@role='rowgroup']/tr/td/a");
            WebElement ruleElement = waitForElementVisible(driver, ruleNameLocator1, 60);
            new WebDriverWait(driver, Duration.ofSeconds(60)).until(_ -> !ruleElement.getText().trim().isEmpty());

            String ruleNamePublishPage = ruleElement.getText();
            ExtentManager.logInfo(STR."Rule Name from Published Page: \{ruleNamePublishPage}");

            // Click hyperlink to navigate to Data Testing
            clickOnElement(driver, clickHyperlinkPublishRule, "Published Rule", waitTime);

            // Switch to child window
            String child = switchToNewWindow(driver, parent, 30);
            waitForLoaderToDisappear(driver, loader, 30);

            // Wait for the rule name input to be present and populated
            By ruleNameLocator2 = By.xpath("//*[@placeholder='Enter rule name']");
            WebElement ruleName2 = waitForElementVisible(driver, ruleNameLocator2, 30);
            new WebDriverWait(driver, Duration.ofSeconds(30)).until(_ -> !Objects.requireNonNull(ruleName2.getDomProperty("value")).isEmpty());

            String ruleNameDataTestingPage = ruleName2.getDomProperty("value");
            ExtentManager.logInfo(STR."Rule Name from Data Testing Page: \{ruleNameDataTestingPage}");

            // Validation
            assert ruleNameDataTestingPage != null;
            if (!ruleNamePublishPage.trim().equals(ruleNameDataTestingPage.trim())) {
                throw new AssertionError(STR."Rule name mismatch. Expected: \{ruleNamePublishPage} but found: \{ruleNameDataTestingPage}");
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

            //Validate the published workflow is displayed or not in the list.
            searchEntityUntilIsDisplayedOnPublished();

            // 1️⃣ Capture parent window
            String parent = PageUtil.getParentWindow(driver);

            // 2️⃣ Wait for loader
            waitForLoaderToDisappear(driver, loader, 30);

            // 3️⃣ Get workflow name from Published page
            By workflowNameLocator1 = By.xpath("//tbody[@role='rowgroup']/tr/td/a");

            // 🔄 Refresh button click
            By refreshButton = By.xpath("//*[@title='Refresh']");
            WebElement ref = driver.findElement(refreshButton);
            ref.click();

            // 2️⃣ Wait for loader
            waitForLoaderToDisappear(driver, loader, 30);

            ref.click();

            waitForLoaderToDisappear(driver, loader, 30);


            WebElement ruleElement = waitForElementVisible(driver, workflowNameLocator1, 60);

            new WebDriverWait(driver, Duration.ofSeconds(30)).until(_ -> !ruleElement.getText().trim().isEmpty());

            String workflowNamePublishPage = ruleElement.getText().trim();
            ExtentManager.logInfo(STR."Workflow Name from Published Page: \{workflowNamePublishPage}");

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

            new WebDriverWait(driver, Duration.ofSeconds(10)).until(_ -> !Objects.requireNonNull(ruleName2.getDomProperty("value")).trim().isEmpty());

            String workflowNameDataTestingPage = Objects.requireNonNull(ruleName2.getDomProperty("value")).trim();
            ExtentManager.logInfo(STR."Workflow Name from Workflow Details Page: \{workflowNameDataTestingPage}");

            if (!workflowNamePublishPage.equals(workflowNameDataTestingPage)) {
                throw new AssertionError(STR."Workflow name mismatch. Expected: \{workflowNamePublishPage} but found: \{workflowNameDataTestingPage}");
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

    public void navigatHomePage() {
        // Jenkins runtime
        goTo(homePageUrl);
        // goTo(ConfigReader.getProperty("homePageUrl"));
        ExtentManager.logInfo(STR."Home page URL \{homePageUrl}");
    }

    // Run the published rule on the Data Testing module.
    public void runPublishRule() {
        // Click on the Run button.
        clickOnElement(driver, clickOnRunButton, "run button", waitTime);
        isInvisibleLoader(driver, loader);

    }

    // Run the published workflow on the Data Testing module.
    public void runPublishWorkflow() {
        // Click on the Run button.
        clickOnElement(driver, clickOnRunButton, "run button", waitTime);
        isInvisibleLoader(driver, loader);

    }

    // Click on the Rule Recent runs button.
    public void clickOnRuleRecentrun() {
        // Click on the Recent runs button.
        clickOnElement(driver, clickOnRecentruns, "recent runs button", waitTime);

    }

    // Click on the Workflow Recent runs button.
    public void clickOnWorkflowRecentrun() {
        // Click on the Recent runs button.
        clickOnElement(driver, clickOnRecentruns, "recent runs button", waitTime);

        // waitForSeconds(1);

    }

    // Validate Rule Instance ID...
    public void validateRuleInstanceID(WebDriver driver) {

        int maxRetries = 13;
        int waitBetweenRetries = 20; // seconds

        for (int retryCount = 0; retryCount < maxRetries; retryCount++) {

            try {
                clickOnElement(driver, clcikOnRefreshButton, "Refresh Button", waitTime);

                // 2️⃣ Wait for loader to disappear
                waitForLoaderToDisappear(driver, loader, waitTime);

                // 3️⃣ Check if Instance ID row is displayed
                if (isElementDisplayed(driver, getInstanceID, waitTime)) {

                    String instanceId = driver.findElement(getInstanceID).getText();
                    String status = driver.findElement(geStatus).getText().trim();

                    ExtentManager.logInfo(STR."Instance ID: \{instanceId}");
                    ExtentManager.logInfo(STR."Current Status: \{status}");

                    // Exit loop if status is NOT Submitted or Running
                    if (!status.equalsIgnoreCase("Submitted") && !status.equalsIgnoreCase("Running")) {

                        ExtentManager.logInfo(STR."Final execution status: \{status}");
                        return;
                    }
                }

                // 4️⃣ Wait between retries (non-blocking)
                WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(waitBetweenRetries));
                shortWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*"))); // dummy wait

            } catch (Exception e) {
                ExtentManager.logInfo(STR."Retry \{retryCount + 1} failed: \{e.getMessage()}");
            }
        }

        throw new RuntimeException(STR."Status did not change from Submitted/Running after \{maxRetries} retries");
    }

    // Validate Workflow Instance ID...
    public void validateWorkflowInstanceID(WebDriver driver) {

        int maxRetries = 13;
        int waitBetweenRetries = 25; // seconds

        for (int retryCount = 0; retryCount < maxRetries; retryCount++) {

            try {
                clickOnElement(driver, clcikOnRefreshButtonInWorkflow, "Refresh Button", waitTime);

                // 2️⃣ Wait for loader to disappear
                waitForLoaderToDisappear(driver, loader, 30);

                // 3️⃣ Check if Instance ID row is displayed
                if (isElementDisplayed(driver, getInstanceIDInWorkflow, waitTime)) {

                    String instanceId = driver.findElement(getInstanceIDInWorkflow).getText();
                    String status = driver.findElement(geStatus).getText().trim();

                    ExtentManager.logInfo(STR."Instance ID: \{instanceId}");
                    ExtentManager.logInfo(STR."Current Status: \{status}");

                    // Exit loop if status is NOT Submitted or Running
                    if (!status.equalsIgnoreCase("Submitted") && !status.equalsIgnoreCase("Running")) {

                        ExtentManager.logInfo(STR."Final execution status: \{status}");
                        return;
                    }
                }

                // 4️⃣ Wait between retries (non-blocking)
                WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(waitBetweenRetries));
                shortWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*"))); // dummy wait

            } catch (Exception e) {
                ExtentManager.logInfo(STR."Retry \{retryCount + 1} failed: \{e.getMessage()}");
            }
        }

        throw new RuntimeException(STR."Status did not change from Submitted/Running after \{maxRetries} retries");
    }

    // Click on the 'Go To Request' button
    String currentRequestID = null;

    public void ClickGoToRequestButton() {

        clickOnElement(driver, clickOnGoToRequestButton, "Go To Request button.", waitTime);

        clickOnElement(driver, searchFieldOfRequest, "Request Search Field", waitTime);
        WebElement element = driver.findElement(searchFieldOfRequest);


        String requestID = element.getAttribute("value");
        System.out.println(requestID);

        currentRequestID = requestID;

        // Verify the logs
        verifyLogs();

        // navigate
        navigateFromRequestToPreview();

    }

    // Verifies the generated or published rule and workflow logs for the given
    // request ID.
    public void verifyLogs() {

        clickOnElement(driver, clickOnViewHyperlink, "View hyperlink.", waitTime);

        By logArea = By.xpath("//textarea[@readonly]");

        int maxAttempts = 10; // 6 minutes total if waiting 20 second each

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {

            clickOnElement(driver, clickOnRefreshButton, "Refresh button.", waitTime);

            try {

                WebElement textArea = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(logArea));

                assert textArea != null;
                String logs = textArea.getAttribute("value");

                // Scroll to latest logs
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", textArea);

                System.out.println(STR."Attempt \{attempt} checking logs...");

                // SUCCESS
                assert logs != null;
                if (logs.contains("Test Generator Rule Creation Process Completed.")) {

                    System.out.println("Process completed successfully.");

                    clickOnElement(driver, clickOnCloseButton, "Close button.", waitTime);
                    return;
                }

                // FAILURE (optional)
                if (logs.contains("Failed") || logs.contains("Exception") || logs.contains("Error")) {

                    clickOnElement(driver, clickOnCloseButton, "Close button.", waitTime);

                    throw new AssertionError("Process failed. Logs contain failure indicators.");
                }

                // Still running
                System.out.println("Process still running. Refreshing again...");

                if (attempt < maxAttempts) {
                    Thread.sleep(Duration.ofSeconds(15).toMillis());
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted while waiting for logs.", e);

            } catch (Exception e) {

                System.out.println(STR."Unable to read logs on attempt \{attempt}. Retrying...");

                if (attempt == maxAttempts) {
                    throw e;
                }
            }
        }

        clickOnElement(driver, clickOnCloseButton, "Close button.", waitTime);

        throw new AssertionError(STR."Final completion log not found after \{maxAttempts} attempts.");
    }

    // Navigate to Request tab to the Preview page.
    public void navigateFromRequestToPreview() {
        clickOnElement(driver, clickOnPreviewTab, "Preview tab button", waitTime);

        clickOnElement(driver, SearchFieldOfPreview, "Preview tab button", waitTime);

        // element.click();
        sendkeysToElement(driver, SearchFieldOfPreview, "request ID", currentRequestID);
        sendkeysToEnter(driver, SearchFieldOfPreview, "request ID");
    }

    // Search the entity until is displayed for the Preview page.
    public void searchEntityUntilIsDisplayedOnPreview() {

        int maxAttempts = 10;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {

            ExtentManager.logInfo(STR."Search Attempt: \{attempt}");

            clickOnElement(driver, clickOnSearchIconPreview, "Search Icon", waitTime);

            // Wait for loader to disappear
            waitForLoaderToDisappear(driver, loader, waitTime);

            try {
                WebElement row = driver.findElement(entityIsDisplayedPreview);
                String rowText = row.getText().trim();

                ExtentManager.logInfo(STR."Grid Row Text: \{rowText}");

                // Valid data found
                if (!rowText.equalsIgnoreCase("No records to display")) {
                    ExtentManager.logInfo("Entity found.");
                    return;
                }

                ExtentManager.logInfo("No records found.");

            } catch (NoSuchElementException e) {
                ExtentManager.logInfo("Grid row not found.");
            }

            if (attempt < maxAttempts) {

                ExtentManager.logInfo("Entity not found. Waiting 15 seconds before retry.");

                try {
                    Thread.sleep(Duration.ofSeconds(15).toMillis());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
        ExtentManager.logInfo(STR."Entity not found after \{maxAttempts} attempts.");
    }

    // Search the entity until is displayed for the published page.
    public void searchEntityUntilIsDisplayedOnPublished() {

        int maxAttempts = 10;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {

            ExtentManager.logInfo(STR."Search Attempt: \{attempt}");

            clickOnElement(driver, clickOnSearchIconPublished, "Search Icon", waitTime);

            // Wait for loader to disappear
            waitForLoaderToDisappear(driver, loader, waitTime);

            try {

                WebElement row = driver.findElement(entityIsDisplayedPublished);
                String rowText = row.getText().trim();

                ExtentManager.logInfo(STR."Grid Row Text: \{rowText}");

                // Valid data found
                if (!rowText.equalsIgnoreCase("No records to display")) {
                    ExtentManager.logInfo("Entity found.");
                    return;
                }

                ExtentManager.logInfo("No records found.");

            } catch (NoSuchElementException e) {
                ExtentManager.logInfo("Grid row not found.");
            }

            if (attempt < maxAttempts) {

                ExtentManager.logInfo("Entity not found. Waiting 15 seconds before retry.");

                try {
                    Thread.sleep(Duration.ofSeconds(15).toMillis());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }

        ExtentManager.logInfo(STR."Entity not found after \{maxAttempts} attempts.");
    }
}
