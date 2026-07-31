package com.qa.pages;

import java.util.List;
import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.extentreportlistener.ExtentManager;
import com.qa.utils.PageUtil;


public class TemplatePage extends PageUtil {

    private static final int DEFAULT_WAIT = 1;


    /**
     * JSON copied from a source template; consumed when creating a new one.
     */
    private String copiedPrettyJson;

    // ─── Locators ────────────────────────────────────────────────────────────────

    private final By testGeneratorModule = getElementLocator(prop.getProperty("home.testgenerator"));
    private final By templateSubModule = getElementLocator(prop.getProperty("template.subModule"));

    // Account name selection
    private final By accountFilterList = getElementLocator(prop.getProperty("template.account.list"));
    private final By accountNameText = getElementLocator(prop.getProperty("template.select.account"));

    private final By clickOnAccountSearchField = getElementLocator(prop.getProperty("template.account.searchField"));


    private final By searchField = getElementLocator(prop.getProperty("searchField.searchTemplate"));
    private final By searchIconButton = getElementLocator(prop.getProperty("searchButtonIcon.searchTemplate"));
    private final By searchResultFirstItem = getElementLocator(prop.getProperty("searchItem.fromList"));
    private final By templateNameLink = getElementLocator(prop.getProperty("template.name.hyperlink"));

    private final By copyButton = getElementLocator(prop.getProperty("copyButton"));
    private final By newTemplateButton = getElementLocator(prop.getProperty("newTemplateButton"));
    private final By saveButton = getElementLocator(prop.getProperty("saveButton"));

    private final By accountNameField = getElementLocator(prop.getProperty("accountName"));
    private final By accountNameList = getElementLocator(prop.getProperty("accountNameList"));
    private final By ruleTypeField = getElementLocator(prop.getProperty("ruleType"));
    private final By ruleTypeList = getElementLocator(prop.getProperty("ruleTypeList"));
    private final By nameField = getElementLocator(prop.getProperty("name"));
    private final By descriptionField = getElementLocator(prop.getProperty("description"));

    private final By masterCheckbox = getElementLocator(prop.getProperty("masterCheckbox"));
    private final By moreButton = getElementLocator(prop.getProperty("moreButton"));
    private final By moreDeleteButton = getElementLocator(prop.getProperty("more.deleteButton"));
    private final By deleteConfirmationBox = getElementLocator(prop.getProperty("deleteConfirmationBox"));
    private final By cancelButtonInLogPane = getElementLocator(prop.getProperty("cancelButton"));
    private final By deleteButtonOnDetailsPage = getElementLocator(prop.getProperty("deleteButtonInListingPage"));

    // ✅ Consistent with all other locator fields
    private final By loader = getElementLocator(prop.getProperty("loaderIsDisplayed"));


    // ─── Constructor ─────────────────────────────────────────────────────────────
    
    private final WebDriver driver;

    public TemplatePage(WebDriver driver) {
        this.driver = driver;
    }

    // ─── Flow Abort Infrastructure ───────────────────────────────────────────────

    public void executeStep(String stepName, Runnable action) {
        try {
            action.run();
            //isInvisibleLoader(driver, getElementLocator(prop.getProperty("loaderIsDisplayed")));

            String uiError = captureUIErrorIfPresent(driver);
            if (uiError != null && !uiError.isEmpty()) {
                throw new RuntimeException(STR."UI error detected: \{uiError}");
            }
            ExtentManager.logPass(STR."\{stepName} ➝ PASSED");
        } catch (Throwable e) {
            ExtentManager.logFail(STR."\{stepName} ➝ FAILED due to: \{e.getMessage()}");
            ExtentManager.captureScreenshot("Step_Failure");
            navigateToHomePage();
            throw new FlowAbortException(STR."Flow aborted at: [\{stepName}]", e);
        }
    }

    /**
     * Thrown by {@link #executeStep} to immediately stop the current test flow.
     */
    public static class FlowAbortException extends RuntimeException {
        public FlowAbortException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    // ─── Navigation ──────────────────────────────────────────────────────────────

    public void navigateToHomePage() {
        goTo(homePageUrl);
    }

    // ─── Private Atomic Actions ──────────────────────────────────────────────────
    // These are plain void methods — failures propagate as exceptions
    // and are caught by executeStep().


    public void doClickTestGenerator() {

        clickOnElement(driver, testGeneratorModule, "Test Generator", 1);
    }

    private void doClickTemplateTab() {
        if (!validateField(driver, templateSubModule, "Template Tab", DEFAULT_WAIT)) {
            throw new RuntimeException("Template Tab not visible");
        }
        clickOnElement(driver, templateSubModule, "Template", 1);

        // wait for the loader disappear
        isInvisibleLoader(driver, loader);
    }

    private void doClickAdvancedSearch(String accountName) {

        if (!validateField(driver, clickOnAccountSearchField, "Search account.", DEFAULT_WAIT)) {
            throw new RuntimeException("Account search field is not visible");
        }

        clickOnElement(driver, clickOnAccountSearchField, "Search account.", 2);

        // ✅ Use variable (not string)
        sendkeysToElement(driver, clickOnAccountSearchField, "Search account field", accountName);
        sendkeysToEnter(driver, clickOnAccountSearchField, "Search account field.");

        // ✅ Get all list items
        List<WebElement> accounts = driver.findElements(accountFilterList);

        for (WebElement account : accounts) {

            // ✅ Use RELATIVE XPath
            String accName = account.findElement(accountNameText).getText().trim();

            // ✅ Compare with variable
            if (accName.equals(accountName)) {
                account.click();
                break;
            }
        }
        // wait for the loader disappear
        isInvisibleLoader(driver, loader);
    }

    private void doClickSearchField() {
        if (!validateField(driver, searchField, "Search Field", DEFAULT_WAIT)) {
            throw new RuntimeException("Search Field not visible");
        }
        clickOnElement(driver, searchField, "Search Field", 1);
    }

    private void doEnterSearchText(String templateName) {
        if (!validateField(driver, searchField, "Search Field", DEFAULT_WAIT)) {
            throw new RuntimeException("Search Field not visible");
        }
        sendkeysToElement(driver, searchField, "Search Field", templateName);
    }

    private void doClickSearchIcon() {
        if (!validateField(driver, searchIconButton, "Search Icon", DEFAULT_WAIT)) {
            throw new RuntimeException("Search Icon not visible");
        }
        clickOnElement(driver, searchIconButton, "Search Icon", 1);
    }

    private void doSelectFirstSearchResult() {
        if (!validateField(driver, searchResultFirstItem, "First Search Result", DEFAULT_WAIT)) {
            throw new RuntimeException("Search result not visible");
        }
        clickOnElement(driver, searchResultFirstItem, "First Search Result", 5);
    }

    private void doClickCopyButton() {
        if (!validateField(driver, copyButton, "Copy Button", DEFAULT_WAIT)) {
            throw new RuntimeException("Copy button not visible");
        }
        clickOnElement(driver, copyButton, "Copy", 5);
    }

    private void doClickNewTemplateButton() {
        if (!validateField(driver, newTemplateButton, "New Template Button", DEFAULT_WAIT)) {
            throw new RuntimeException("New Template button not visible");
        }
        clickOnElement(driver, newTemplateButton, "New Template", 1);
    }

    private void doClickSaveButton() {
        if (!validateField(driver, saveButton, "Save Button", DEFAULT_WAIT)) {
            throw new RuntimeException("Save button not visible");
        }
        clickOnElement(driver, saveButton, "Save", 1);
    }

    private void doSelectAccount(String accountName) {

        if (!validateField(driver, accountNameField, "Account Dropdown", DEFAULT_WAIT)) {
            throw new RuntimeException("Account dropdown not visible");
        }

        clickOnElement(driver, accountNameField, "Account", 5);

        waitForElements(driver, 100).until(ExpectedConditions.visibilityOfElementLocated(accountNameList));

        selectFromDropdown(accountNameList, accountName);
    }

    private void doSelectRuleType(String ruleType) {

        if (!validateField(driver, ruleTypeField, "Rule Type Dropdown", DEFAULT_WAIT)) {
            throw new RuntimeException("Rule Type dropdown not visible");
        }

        clickOnElement(driver, ruleTypeField, "Rule Type", 5);

        waitForElements(driver, 100).until(ExpectedConditions.visibilityOfElementLocated(ruleTypeList));

        selectFromDropdown(ruleTypeList, ruleType);
    }

    private void doEnterName(String name) {
        if (!validateField(driver, nameField, "Name Field", DEFAULT_WAIT)) {
            throw new RuntimeException("Name field not visible");
        }
        clearAndType(nameField, "Name", name);
    }

    private void doEnterDescription(String description) {
        if (!validateField(driver, descriptionField, "Description Field", DEFAULT_WAIT)) {
            throw new RuntimeException("Description field not visible");
        }
        clearAndType(descriptionField, "Description", description);
    }

    // ─── Shared UI Helpers ───────────────────────────────────────────────────────

    private void selectFromDropdown(By listLocator, String value) {
        WebDriverWait wait = waitForElements(driver, DEFAULT_WAIT);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Step 1 — Wait for the listbox container to be visible
        By listboxContainer = By.xpath("//*[@aria-label='listbox']");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(listboxContainer));
        } catch (TimeoutException e) {
            throw new RuntimeException(STR."Listbox container did not appear within \{DEFAULT_WAIT}s");
        }

        // Step 2 — Wait for overlay/interceptor on TOP of the listbox to disappear
        // (Even if no spinner — the dropdown animation itself can briefly block clicks)
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@aria-label='listbox']/preceding-sibling::*[contains(@class,'overlay') " + "or contains(@class,'backdrop') or contains(@class,'loading')]")));
        } catch (TimeoutException ignored) {
            // No overlay found — that's fine, continue
        }

        // Step 3 — Find and click the matching <li> by re-fetching on every retry
        boolean selected = wait.until(driver -> {
            try {
                List<WebElement> items = driver.findElements(listLocator);

                // Guard: wait until items are non-empty and fully rendered
                if (items.isEmpty()) return false;

                for (WebElement item : items) {
                    String text;
                    try {
                        text = item.getText().trim();
                    } catch (StaleElementReferenceException s) {
                        return false; // list re-rendered, retry outer lambda
                    }

                    if (text.equalsIgnoreCase(value)) {
                        try {
                            // Scroll into view first
                            js.executeScript("arguments[0].scrollIntoView({block:'center',inline:'nearest'});", item);

                            Objects.requireNonNull(wait.until(ExpectedConditions.elementToBeClickable(item))).click();

                            ExtentManager.logInfo(STR."Selected: '\{value}'");
                            return true;

                        } catch (ElementClickInterceptedException e) {
                            // Something still on top — JS click bypasses the interceptor
                            try {
                                js.executeScript("arguments[0].click();", item);
                                ExtentManager.logInfo(STR."Selected via JS click: '\{value}'");
                                return true;
                            } catch (Exception jsEx) {
                                return false; // retry
                            }
                        } catch (StaleElementReferenceException s) {
                            return false; // retry
                        }
                    }
                }

                // Option not found yet in current render — log available for debug
                List<String> available = items.stream().map(el -> {
                    try {
                        return el.getText().trim();
                    } catch (Exception e) {
                        return "";
                    }
                }).filter(t -> !t.isEmpty()).toList();

                ExtentManager.logInfo(STR."Option '\{value}' not yet found. Available: \{available}");
                return false; // retry

            } catch (StaleElementReferenceException e) {
                return false; // full list went stale, retry
            }
        });

        if (!selected) {
            throw new RuntimeException(STR."Failed to select '\{value}' from listbox after \{DEFAULT_WAIT}s");
        }
    }


    private void clearAndType(By locator, String fieldLabel, String value) {
        WebDriverWait wait = waitForElements(driver, DEFAULT_WAIT);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        if (element == null) {
            throw new RuntimeException(STR."Element not found for locator: \{locator}");
        }
        element.click();
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.DELETE);
        element.sendKeys(value);

        // Wait until the field actually reflects the typed value before proceeding
        wait.until(ExpectedConditions.attributeContains(locator, "value", value));
        ExtentManager.logInfo(STR."Typed '\{value}' into \{fieldLabel}");
    }

    // ─── ACE Editor Helpers ──────────────────────────────────────────────────────


    private void doReadJsonFromEditor() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String rawJson = (String) js.executeScript("return ace.edit(document.querySelector('.ace_editor')).getValue();");

        try {
            ObjectMapper mapper = new ObjectMapper();
            Object parsed = mapper.readValue(rawJson, Object.class);
            copiedPrettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(parsed).replace("\r\n", "\n").trim();
        } catch (Exception e) {
            throw new RuntimeException(STR."Failed to parse JSON from ACE editor: \{e.getMessage()}", e);
        }
    }

    // ✅ Use a text block (Java 15+) or a constant for readability
    private static final String SET_ACE_EDITOR_SCRIPT =
            "var editor = ace.edit(document.querySelector('.ace_editor'));" +
                    "editor.session.setUseWorker(false);" +
                    "editor.setValue(arguments[0], -1);" +
                    "editor.clearSelection();" +
                    "editor.blur();";

    private void doWriteJsonToEditor() {
        if (copiedPrettyJson == null || copiedPrettyJson.isEmpty()) {
            throw new RuntimeException("No JSON available to write — call read step first");
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(SET_ACE_EDITOR_SCRIPT, copiedPrettyJson);
    }
    // ─── Validation ──────────────────────────────────────────────────────────────


    private void validateTemplateName(String expectedName, String action) {
        executeStep(STR."Search for \{action} template: \{expectedName}", () -> {
            doClickSearchField();
            doEnterSearchText(expectedName);
            doClickSearchIcon();
        });

        executeStep(STR."Validate \{action} template name: \{expectedName}", () -> {
            String actualName = driver.findElement(templateNameLink).getText();
            ExtentManager.logInfo(STR."Expected : \{expectedName}");
            ExtentManager.logInfo(STR."Actual   : \{actualName}");
            if (!expectedName.equals(actualName)) {
                throw new RuntimeException(STR."Name mismatch — expected: '\{expectedName}', got: '\{actualName}'");
            }
        });
    }

    // ─── Delete Step Groups ──────────────────────────────────────────────────────

    private void doDeleteFromListingPage() {
        clickOnElement(driver, masterCheckbox, "Master Checkbox", 1);
        clickOnElement(driver, moreButton, "More", 2);
        clickOnElement(driver, moreDeleteButton, "Delete (More menu)", 2);
        clickOnElement(driver, deleteConfirmationBox, "Confirm Delete", 5);
        clickOnElement(driver, cancelButtonInLogPane, "Cancel Log Pane", 2);
    }

    private void doDeleteFromDetailsPage() {
        clickOnElement(driver, deleteButtonOnDetailsPage, "Delete (Details Page)", 5);
        clickOnElement(driver, deleteConfirmationBox, "Confirm Delete", 5);
    }

    // ─── Public Flows ────────────────────────────────────────────────────────────


    public void createNewTemplate(String ruleType,
                                  String sourceTemplate,
                                  String newName,
                                  String newDescription,
                                  String accountName) {

        executeStep("Create New Template", () -> {

            // Navigate to the Test Generator Template module
            doClickTestGenerator();
            doClickTemplateTab();

            // Filter templates by Account
            doClickAdvancedSearch(accountName);

            // Search for the existing source template
            doClickSearchField();
            doEnterSearchText(sourceTemplate);
            doClickSearchIcon();

            // Open the source template and copy its JSON configuration
            doSelectFirstSearchResult();
            doClickCopyButton();
            doReadJsonFromEditor();

            // Return to the Template listing page
            doClickTemplateTab();

            // Create a new template
            doClickNewTemplateButton();

            // Configure the new template
            doSelectAccount(accountName);
            doSelectRuleType(ruleType);
            doWriteJsonToEditor();
            doEnterName(newName);
            doEnterDescription(newDescription);

            // Save the new template
            doClickSaveButton();

            // Verify that the template was created successfully
            doClickTemplateTab();
            validateTemplateName(newName, "created");

            // Return to the application's home page
            navigateToHomePage();
        });
    }


    public void updateExistingTemplate(String ruleType,
                                       String existingName,
                                       String updatedName,
                                       String updatedDescription,
                                       String accountName) {

        executeStep("Update Existing Template", () -> {

            // Navigate to Template module
            doClickTestGenerator();
            doClickTemplateTab();

            // Open Advanced Search and filter by Account
            doClickAdvancedSearch(accountName);

            // Search and open template
            doClickSearchField();
            doEnterSearchText(existingName);
            doClickSearchIcon();
            doSelectFirstSearchResult();

            // Update template details
            doEnterName(updatedName);
            doEnterDescription(updatedDescription);

            // Save and validate
            doClickSaveButton();
            doClickTemplateTab();

            validateTemplateName(updatedName, "updated");

            navigateToHomePage();
        });
    }


    public void deleteTemplateFromListingPage(String ruleType,
                                              String templateName,
                                              String accountName) {

        executeStep("Delete Template From Listing Page", () -> {

            // Navigate to Template module
            doClickTestGenerator();
            doClickTemplateTab();

            // Open Advanced Search and filter by Account
            doClickAdvancedSearch(accountName);

            // Search template
            doClickSearchField();
            doEnterSearchText(templateName);
            doClickSearchIcon();

            // Delete template
            doDeleteFromListingPage();

            navigateToHomePage();
        });
    }

    public void deleteTemplateFromDetailsPage(String ruleType,
                                              String templateName,
                                              String accountName) {

        executeStep("Delete Template From Details Page", () -> {

            // Navigate to Template module
            doClickTestGenerator();
            doClickTemplateTab();

            // Open Advanced Search and filter by Account
            doClickAdvancedSearch(accountName);

            // Search and open template
            doClickSearchField();
            doEnterSearchText(templateName);
            doClickSearchIcon();
            doSelectFirstSearchResult();

            // Delete template
            doDeleteFromDetailsPage();

            navigateToHomePage();
        });
    }
}