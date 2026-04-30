package com.qa.pages;

import java.util.List;

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

/**
 * Page Object for the Template module under the Test Generator section. All
 * public flows use executeStep() for uniform logging, screenshots, and
 * flow-abort behaviour on any failure.
 */
public class TemplatePage extends PageUtil {

	private static final int DEFAULT_WAIT = 10;

	private WebDriver driver = null;

	/** JSON copied from a source template; consumed when creating a new one. */
	private String copiedPrettyJson;

	// ─── Locators ────────────────────────────────────────────────────────────────

	private final By testGeneratorModule = getElementLocator(prop.getProperty("home.testgenerator"));
	private final By templateSubModule = getElementLocator(prop.getProperty("template.subModule"));

	private final By searchField = getElementLocator(prop.getProperty("searchField.searchTemplate"));
	private final By searchIconButton = getElementLocator(prop.getProperty("searchButtonIcon.searchTemplate"));
	private final By searchResultFirstItem = getElementLocator(prop.getProperty("searchItem.fromList"));
	private final By templateNameLink = By.xpath("//a[@class='link dib']");

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

	// ─── Constructor ─────────────────────────────────────────────────────────────

	// Right — remove the return type:
	public TemplatePage(WebDriver driver) {
		this.driver = driver;
	}

	// ─── Flow Abort Infrastructure ───────────────────────────────────────────────

	/**
	 * Executes {@code action}, then checks for a UI toast error.
	 * <ul>
	 * <li>On success → logs PASS to the Extent report.</li>
	 * <li>On failure → logs FAIL, captures a screenshot, navigates home, and throws
	 * {@link FlowAbortException} to stop the entire flow.</li>
	 * </ul>
	 *
	 * @param stepName human-readable label shown in the report
	 * @param action   the UI interaction to perform
	 */
	public void executeStep(String stepName, Runnable action) {
		try {
			action.run();
			isInvisibleLoader(driver, getElementLocator(prop.getProperty("loderIsDisplayed")));

			String uiError = captureUIErrorIfPresent(driver);
			if (uiError != null && !uiError.isEmpty()) {
				throw new RuntimeException("UI error detected: " + uiError);
			}
			ExtentManager.logPass(stepName + " ➝ PASSED");
		} catch (Throwable e) {
			ExtentManager.logFail(stepName + " ➝ FAILED due to: " + e.getMessage());
			ExtentManager.captureScreenshot("Step_Failure");
			navigateToHomePage();
			throw new FlowAbortException("Flow aborted at: [" + stepName + "]", e);
		}
	}

	/** Thrown by {@link #executeStep} to immediately stop the current test flow. */
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

	private void doClickTestGenerator() {

		if (!validateField(driver, testGeneratorModule, "Test Generator Button", 5)) {
			throw new RuntimeException("Validation failed: Test Generator button not visible");
		}

		if (!clickOnElement(driver, testGeneratorModule, "Test Generator Button", 5)) {
			throw new RuntimeException("Click failed: Test Generator button");
		}
	}

	private void doClickTemplateTab() {
		if (!validateField(driver, templateSubModule, "Template Tab", 5)) {
			throw new RuntimeException("Template Tab not visible");
		}
		clickOnField(driver, templateSubModule, "Template", "Tab", true);
	}

	private void doClickSearchField() {
		if (!validateField(driver, searchField, "Search Field", 5)) {
			throw new RuntimeException("Search Field not visible");
		}
		clickOnField(driver, searchField, "Search Field", "Input", true);
	}

	private void doEnterSearchText(String templateName) {
		if (!validateField(driver, searchField, "Search Field", 5)) {
			throw new RuntimeException("Search Field not visible");
		}
		sendkeysToElement(driver, searchField, "Search Field", templateName);
	}

	private void doClickSearchIcon() {
		if (!validateField(driver, searchIconButton, "Search Icon", 5)) {
			throw new RuntimeException("Search Icon not visible");
		}
		clickOnField(driver, searchIconButton, "Search Icon", "Button", true);
	}

	private void doSelectFirstSearchResult() {
		if (!validateField(driver, searchResultFirstItem, "First Search Result", 5)) {
			throw new RuntimeException("Search result not visible");
		}
		clickOnField(driver, searchResultFirstItem, "First Search Result", "List item", true);
	}

	private void doClickCopyButton() {
		if (!validateField(driver, copyButton, "Copy Button", 5)) {
			throw new RuntimeException("Copy button not visible");
		}
		clickOnField(driver, copyButton, "Copy", "Button", true);
	}

	private void doClickNewTemplateButton() {
		if (!validateField(driver, newTemplateButton, "New Template Button", 5)) {
			throw new RuntimeException("New Template button not visible");
		}
		clickOnField(driver, newTemplateButton, "New Template", "Button", true);
	}

	private void doClickSaveButton() {
		if (!validateField(driver, saveButton, "Save Button", 5)) {
			throw new RuntimeException("Save button not visible");
		}
		clickOnField(driver, saveButton, "Save", "Button", true);
	}

	private void doSelectAccount() {

		if (!validateField(driver, accountNameField, "Account Dropdown", 5)) {
			throw new RuntimeException("Account dropdown not visible");
		}

		clickOnField(driver, accountNameField, "Account", "Dropdown", true);

		waitForElements(driver, 100).until(ExpectedConditions.visibilityOfElementLocated(accountNameList));

		selectFromDropdown(accountNameList, "Quality_Assurance");
	}

	private void doSelectRuleType(String ruleType) {

		if (!validateField(driver, ruleTypeField, "Rule Type Dropdown", 5)) {
			throw new RuntimeException("Rule Type dropdown not visible");
		}

		clickOnField(driver, ruleTypeField, "Rule Type", "Dropdown", true);

		waitForElements(driver, 100).until(ExpectedConditions.visibilityOfElementLocated(ruleTypeList));

		selectFromDropdown(ruleTypeList, ruleType);
	}

	private void doEnterName(String name) {
		if (!validateField(driver, nameField, "Name Field", 5)) {
			throw new RuntimeException("Name field not visible");
		}
		clearAndType(nameField, "Name", name);
	}

	private void doEnterDescription(String description) {
		if (!validateField(driver, descriptionField, "Description Field", 5)) {
			throw new RuntimeException("Description field not visible");
		}
		clearAndType(descriptionField, "Description", description);
	}

	// ─── Shared UI Helpers ───────────────────────────────────────────────────────

	/**
	 * Waits for the dropdown list to populate, finds the option matching
	 * {@code value}, and clicks it.
	 *
	 * @param listLocator locator for the dropdown list items
	 * @param value       the option text to select (case-insensitive)
	 * @throws RuntimeException if the option is not found after waiting
	 */
	private void selectFromDropdown(By listLocator, String value) {
		WebDriverWait wait = waitForElements(driver, DEFAULT_WAIT);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Step 1 — Wait for the listbox container to be visible
		By listboxContainer = By.xpath("//*[@aria-label='listbox']");
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(listboxContainer));
		} catch (TimeoutException e) {
			throw new RuntimeException("Listbox container did not appear within " + DEFAULT_WAIT + "s");
		}

		// Step 2 — Wait for overlay/interceptor on TOP of the listbox to disappear
		// (Even if no spinner — the dropdown animation itself can briefly block clicks)
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(
					By.xpath("//*[@aria-label='listbox']/preceding-sibling::*[contains(@class,'overlay') "
							+ "or contains(@class,'backdrop') or contains(@class,'loading')]")));
		} catch (TimeoutException ignored) {
			// No overlay found — that's fine, continue
		}

		// Step 3 — Find and click the matching <li> by re-fetching on every retry
		boolean selected = wait.until(driver -> {
			try {
				List<WebElement> items = driver.findElements(listLocator);

				// Guard: wait until items are non-empty and fully rendered
				if (items.isEmpty())
					return false;

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

							// Small yield — lets scroll settle and any CSS transition finish
							Thread.sleep(100);

							// Try normal click first
							item.click();
							ExtentManager.logInfo("Selected: '" + value + "'");
							return true;

						} catch (ElementClickInterceptedException e) {
							// Something still on top — JS click bypasses the interceptor
							try {
								js.executeScript("arguments[0].click();", item);
								ExtentManager.logInfo("Selected via JS click: '" + value + "'");
								return true;
							} catch (Exception jsEx) {
								return false; // retry
							}
						} catch (StaleElementReferenceException s) {
							return false; // retry
						} catch (InterruptedException ie) {
							Thread.currentThread().interrupt();
							return false;
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
				}).filter(t -> !t.isEmpty()).collect(java.util.stream.Collectors.toList());

				ExtentManager.logInfo("Option '" + value + "' not yet found. Available: " + available);
				return false; // retry

			} catch (StaleElementReferenceException e) {
				return false; // full list went stale, retry
			}
		});

		if (!selected) {
			throw new RuntimeException("Failed to select '" + value + "' from listbox after " + DEFAULT_WAIT + "s");
		}
	}

	/**
	 * Clears a text field, types {@code value}, and confirms with ENTER. Throws on
	 * any Selenium exception (caught by executeStep).
	 */
	private void clearAndType(By locator, String fieldLabel, String value) {
		WebDriverWait wait = waitForElements(driver, DEFAULT_WAIT);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
		element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		element.sendKeys(Keys.DELETE);
		element.sendKeys(value);

		// Wait until the field actually reflects the typed value before proceeding
		wait.until(ExpectedConditions.attributeContains(locator, "value", value));
		ExtentManager.logInfo("Typed '" + value + "' into " + fieldLabel);
	}

	// ─── ACE Editor Helpers ──────────────────────────────────────────────────────

	/**
	 * Reads JSON from the ACE editor, pretty-prints it, and stores it in
	 * {@link #copiedPrettyJson}. Throws on parse failure (caught by executeStep).
	 */
	private void doReadJsonFromEditor() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String rawJson = (String) js
				.executeScript("return ace.edit(document.querySelector('.ace_editor')).getValue();");

		try {
			ObjectMapper mapper = new ObjectMapper();
			Object parsed = mapper.readValue(rawJson, Object.class);
			copiedPrettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(parsed).replace("\r\n", "\n")
					.trim();
		} catch (Exception e) {
			throw new RuntimeException("Failed to parse JSON from ACE editor: " + e.getMessage(), e);
		}
	}

	/**
	 * Writes {@link #copiedPrettyJson} into the ACE editor via JavaScript. Throws
	 * if no JSON is available or the script fails (caught by executeStep).
	 */
	private void doWriteJsonToEditor() {
		if (copiedPrettyJson == null || copiedPrettyJson.isEmpty()) {
			throw new RuntimeException("No JSON available to write — call read step first");
		}

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript(
				"var editor = ace.edit(document.querySelector('.ace_editor'));" + "editor.session.setUseWorker(false);"
						+ "editor.setValue(arguments[0], -1);" + "editor.clearSelection();" + "editor.blur();",
				copiedPrettyJson);
	}
	// ─── Validation ──────────────────────────────────────────────────────────────

	/**
	 * Searches for {@code expectedName} and asserts the first result matches. Run
	 * outside executeStep so its own inner steps each get individual logging.
	 */
	private void validateTemplateName(String expectedName, String action) {
		executeStep("Search for " + action + " template: " + expectedName, () -> {
			doClickSearchField();
			doEnterSearchText(expectedName);
			doClickSearchIcon();
		});

		executeStep("Validate " + action + " template name: " + expectedName, () -> {
			String actualName = driver.findElement(templateNameLink).getText();
			ExtentManager.logInfo("Expected : " + expectedName);
			ExtentManager.logInfo("Actual   : " + actualName);
			if (!expectedName.equals(actualName)) {
				throw new RuntimeException(
						"Name mismatch — expected: '" + expectedName + "', got: '" + actualName + "'");
			}
		});
	}

	// ─── Delete Step Groups ──────────────────────────────────────────────────────

	private void doDeleteFromListingPage() {
		clickOnField(driver, masterCheckbox, "Master Checkbox", "Checkbox", true);
		clickOnField(driver, moreButton, "More", "Button", true);
		clickOnField(driver, moreDeleteButton, "Delete (More menu)", "Button", true);
		clickOnField(driver, deleteConfirmationBox, "Confirm Delete", "Button", true);
		clickOnField(driver, cancelButtonInLogPane, "Cancel Log Pane", "Button", true);
	}

	private void doDeleteFromDetailsPage() {
		clickOnField(driver, deleteButtonOnDetailsPage, "Delete (Details Page)", "Button", true);
		clickOnField(driver, deleteConfirmationBox, "Confirm Delete", "Button", true);
	}

	// ─── Public Flows ────────────────────────────────────────────────────────────

	/**
	 * Copies JSON from {@code sourceTemplate}, then creates a new template with the
	 * supplied rule type, name and description.
	 * 
	 * @throws InterruptedException
	 */
	public void createNewTemplate(String ruleType, String sourceTemplate, String newName, String newDescription)
			throws InterruptedException {

		// Step 1 — Navigate to module
		executeStep("Click Test Generator", this::doClickTestGenerator);
		executeStep("Click Template Tab", this::doClickTemplateTab);

		// Step 2 — Find and copy source template
		executeStep("Search Template: " + sourceTemplate, () -> {
			doClickSearchField();
			doEnterSearchText(sourceTemplate);
			doClickSearchIcon();
		});
		executeStep("Select Source Template", this::doSelectFirstSearchResult);
		executeStep("Click Copy Button", this::doClickCopyButton);

		// Step 3 — Capture editor JSON
		executeStep("Read JSON from ACE Editor", this::doReadJsonFromEditor);

		// Step 4 — Open new template form
		executeStep("Click Template Tab", this::doClickTemplateTab);
		executeStep("Click New Template Button", this::doClickNewTemplateButton);

		// Step 5 — Fill in form fields
		executeStep("Select Account", this::doSelectAccount);
		executeStep("Select Rule Type: " + ruleType, () -> doSelectRuleType(ruleType));
		Thread.sleep(DEFAULT_WAIT);
		executeStep("Write JSON to ACE Editor", this::doWriteJsonToEditor);
		executeStep("Enter Template Name: " + newName, () -> doEnterName(newName));
		executeStep("Enter Description: " + newDescription, () -> doEnterDescription(newDescription));

		// Step 6 — Save and validate
		executeStep("Click Save Button", this::doClickSaveButton);
		executeStep("Click Template Tab", this::doClickTemplateTab);
		validateTemplateName(newName, "created");

		navigateToHomePage();
	}

	/**
	 * Searches for {@code existingName}, then updates its name and description.
	 */
	public void updateExistingTemplate(String ruleType, String existingName, String updatedName,
			String updatedDescription) {

		// Step 1 — Navigate to module
		executeStep("Click Test Generator", this::doClickTestGenerator);
		executeStep("Click Template Tab", this::doClickTemplateTab);

		// Step 2 — Find the template
		executeStep("Search Template: " + existingName, () -> {
			doClickSearchField();
			doEnterSearchText(existingName);
			doClickSearchIcon();
		});
		executeStep("Open Template: " + existingName, this::doSelectFirstSearchResult);

		// Step 3 — Update fields
		executeStep("Update Name: " + updatedName, () -> doEnterName(updatedName));
		executeStep("Update Description: " + updatedDescription, () -> doEnterDescription(updatedDescription));

		// Step 4 — Save and validate
		executeStep("Click Save Button", this::doClickSaveButton);
		executeStep("Click Template Tab", this::doClickTemplateTab);
		validateTemplateName(updatedName, "updated");

		navigateToHomePage();
	}

	/**
	 * Searches for {@code templateName} and deletes it from the listing page using
	 * the More > Delete flow.
	 */
	public void deleteTemplateFromListingPage(String ruleType, String templateName) {

		// Step 1 — Navigate to module
		executeStep("Click Test Generator", this::doClickTestGenerator);
		executeStep("Click Template Tab", this::doClickTemplateTab);

		// Step 2 — Find the template
		executeStep("Search Template: " + templateName, () -> {
			doClickSearchField();
			doEnterSearchText(templateName);
			doClickSearchIcon();
		});

		// Step 3 — Delete via listing-page More menu
		executeStep("Delete Template from Listing Page: " + templateName, this::doDeleteFromListingPage);

		navigateToHomePage();
	}

	/**
	 * Searches for {@code templateName}, opens it, and deletes it from its details
	 * page.
	 */
	public void deleteTemplateFromDetailsPage(String ruleType, String templateName) {

		// Step 1 — Navigate to module
		executeStep("Click Test Generator", this::doClickTestGenerator);
		executeStep("Click Template Tab", this::doClickTemplateTab);

		// Step 2 — Find and open the template
		executeStep("Search Template: " + templateName, () -> {
			doClickSearchField();
			doEnterSearchText(templateName);
			doClickSearchIcon();
		});
		executeStep("Open Template: " + templateName, this::doSelectFirstSearchResult);

		// Step 3 — Delete from details page
		executeStep("Delete Template from Details Page: " + templateName, this::doDeleteFromDetailsPage);

		navigateToHomePage();
	}
}