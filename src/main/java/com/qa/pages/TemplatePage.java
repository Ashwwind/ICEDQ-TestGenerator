package com.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.config.ConfigReader;
import com.qa.extentreportlistener.ExtentManager;
import com.qa.utils.PageUtil;

public class TemplatePage extends PageUtil {

	private WebDriver driver;

	public TemplatePage(WebDriver driver) {
		this.driver = driver;
	}

	// ******************************************Locator**********************************//

	By templateSubModule = getElementLocator(prop.getProperty("template.subModule"));
	By searchFieldOfSearchTemplate = getElementLocator(prop.getProperty("searchField.searchTemplate"));
	By searchIcopnOfSearchTemplate = getElementLocator(prop.getProperty("searchButtonIcon.searchTemplate"));
	By searchItemSelectFromList = getElementLocator(prop.getProperty("searchItem.fromList"));
	By copyButton = getElementLocator(prop.getProperty("copyButton"));
	By newTemplateButton = getElementLocator(prop.getProperty("newTemplateButton"));
	By accountNameField = getElementLocator(prop.getProperty("accountName"));
	By accountNameList = getElementLocator(prop.getProperty("accountNameList"));
	By ruleTypeField = getElementLocator(prop.getProperty("ruleType"));
	By ruleTypeList = getElementLocator(prop.getProperty("ruleTypeList"));
	By nameField = getElementLocator(prop.getProperty("name"));
	By descriptionField = getElementLocator(prop.getProperty("description"));
	By jsonField = getElementLocator(prop.getProperty("json"));
	By saveButton = getElementLocator(prop.getProperty("saveButton"));
	By checkboxSelection = getElementLocator(prop.getProperty("masterCheckbox"));
	By moreButtonSelection = getElementLocator(prop.getProperty("moreButton"));
	By moreDeleteButttonSelection = getElementLocator(prop.getProperty("more.deleteButton"));
	By deleteconfirmationBoxSelection = getElementLocator(prop.getProperty("deleteConfirmationBox"));
	By deleteButtonOfconfirmationBox = getElementLocator(prop.getProperty("deleteButtonInConfirmationBox"));
	By cancelButtonOfLogMessagePane = getElementLocator(prop.getProperty("cancelButton"));
	By deleteButtonOfListingPage = getElementLocator(prop.getProperty("deleteButtonInListingPage"));

	By testGeneratorModue = getElementLocator(prop.getProperty("home.testgenerator"));

	By loader = getElementLocator(prop.getProperty("loderIsDisplayed"));

	// *****************************************Page
	// Method*******************************//

	public boolean clickTestGenerator() {

		return clickOnField(driver, testGeneratorModue, "Test Generator", "button");

	}

	public boolean clickTemplateTab() {
		return clickOnField(driver, templateSubModule, "template - submodule", "Tab");
	}

	public boolean clickOnSearchFieldOfSearchTemplate() {
		return clickOnField(driver, searchFieldOfSearchTemplate, "Search template", "Search field");
	}

	public boolean searchTemplateName(String templateName) {
		return sendkeysToElement1(driver, searchFieldOfSearchTemplate, "Template Name", templateName);
	}

	public boolean clickOnSearchIcopnOfSearchTemplate() {
		return clickOnField(driver, searchIcopnOfSearchTemplate, "Search icon button", "Search field icon");
	}

	public boolean selectSearchItemSelectFromList() {
		return clickOnField(driver, searchItemSelectFromList, "Searched item", "Searched item");
	}

	public boolean clickOnCopyButton() {
		boolean result = clickOnField(driver, copyButton, "Copy", "Button");
		waitForSeconds(2);
		return result;
	}

	// Read the JSON and stored in the varible
	private String prettyJson;

	public void jsonReadAndStore() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;

			// 1️⃣ Read raw JSON from the ACE editor
			String rawJson = (String) js
					.executeScript("return ace.edit(document.querySelector('.ace_editor')).getValue();");

			// 2️⃣ Use Jackson to pretty‑print the JSON
			ObjectMapper objectMapper = new ObjectMapper();
			Object jsonObject = objectMapper.readValue(rawJson, Object.class);

			prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject).replace("\r\n", "\n").trim();

			//System.out.println(prettyJson);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean setJsonAndLockEditor() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;

			js.executeScript("var editor = ace.edit(document.querySelector('.ace_editor'));"
					+ "editor.session.setUseWorker(false);" + "editor.setValue(arguments[0], -1);"
					+ "editor.clearSelection();" + "editor.blur();", prettyJson);

			waitForSeconds(1);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean clickOnNewTemplateButton() {
		return clickOnField(driver, newTemplateButton, "New Template", "Button");
	}

	public boolean clickOnAccountField() {

		boolean isClicked = clickOnField(driver, accountNameField, "Account", "Dropdown field");
		waitForSeconds(2);
		if (!isClicked) {
			return false;
		}

		List<WebElement> list = driver.findElements(accountNameList);

		for (WebElement element : list) {
			if (element.getText().equalsIgnoreCase("Quality_Assurance")) {
				element.click();
				return true;
			}
		}

		return false;
	}

	public boolean clickOnRuleTypeField(String ruleType) {

		boolean isClicked = clickOnField(driver, ruleTypeField, "Rule type", "Dropdown field");
		if (!isClicked) {
			return false;
		}
		List<WebElement> list = driver.findElements(ruleTypeList);

		for (WebElement element : list) {
			if (element.getText().equalsIgnoreCase(ruleType)) {
				element.click();
				return true;
			}
		}

		return false;
	}

	public boolean clickOnJSONField() {
		try {
			// 1️⃣ Focus the ACE editor
			By jsonEditorContainer = By.xpath("//div[contains(@class,'ace_editor')]");
			WebElement editor = driver.findElement(jsonEditorContainer);
			editor.click();

			// 2️⃣ Locate the hidden input area
			By jsonInputLocator = By.xpath("//textarea[contains(@class,'ace_text-input')]");
			WebElement input = driver.findElement(jsonInputLocator);

			// 3️⃣ Clear existing content
			input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			input.sendKeys(Keys.DELETE);

			// 4️⃣ Enter the pretty JSON
			input.sendKeys(prettyJson);

			waitForSeconds(1);

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean clickOnNameField(String ruleName) {
		try {
			waitForSeconds(1);

			// Click on Name field
			boolean isClicked = clickOnField(driver, nameField, "Name", "Text field");
			if (!isClicked) {
				return false;
			}

			WebElement element = driver.findElement(nameField);

			element.sendKeys(Keys.CONTROL + "a");
			element.sendKeys(Keys.DELETE);

			boolean isEnter = sendkeysToElement1(driver, nameField, "Text field", ruleName);

			boolean isEnterPressed = sendKeysEnterToElement2(driver, nameField, "Text field");
			waitForSeconds(1);
			return isEnterPressed;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean clickOnDescriptionField(String ruleDescription) {
		try {
			// Click on Description field
			boolean isClicked = clickOnField(driver, descriptionField, "Description", "Text field");
			if (!isClicked) {
				return false;
			}

			WebElement element = driver.findElement(descriptionField);

			element.sendKeys(Keys.CONTROL + "a");
			element.sendKeys(Keys.DELETE);

			boolean isEnter = sendkeysToElement1(driver, descriptionField, "Text field", ruleDescription);

			boolean isEnterPressed = sendKeysEnterToElement2(driver, descriptionField, "Text field");
			waitForSeconds(1);
			return isEnterPressed;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean clickOnSaveButton() {
		return clickOnField(driver, saveButton, "Save", "Button");

	}

	public boolean validateCreatedTemplateName(String templateName) {

		clickOnSearchFieldOfSearchTemplate();
		searchTemplateName(templateName);
		clickOnSearchIcopnOfSearchTemplate();

		// Locate the template name element
		WebElement templateElement = driver.findElement(By.xpath("//a[@class='link dib']"));
		waitForSeconds(1);

		// Get text from UI
		String actualTemplateName = templateElement.getText();
		waitForSeconds(1);
		
		ExtentManager.logInfo("Given template name: "+ templateName);
		ExtentManager.logInfo("Created template name: "+ actualTemplateName);

		// Compare expected vs actual
		if (templateName.equals(actualTemplateName)) {
			System.out.println("Template created successfully.");
			ExtentManager.logInfo("Template created successfully.");
			return true;
		} else {
			System.out.println("Template creation failed.");
			ExtentManager.logFail("Template creation failed.");
			return false;
		}
	}

	public boolean validateUpdatedTemplateName(String updatedTemplateName) {

		clickOnSearchFieldOfSearchTemplate();
		searchTemplateName(updatedTemplateName);
		clickOnSearchIcopnOfSearchTemplate();

		// Locate the template name element
		WebElement templateElement = driver.findElement(By.xpath("//a[@class='link dib']"));
		waitForSeconds(1);

		// Get text from UI
		String actualTemplateName = templateElement.getText();
		waitForSeconds(1);
		
		ExtentManager.logInfo("Given template name: "+ updatedTemplateName);
		ExtentManager.logInfo("Updated template name: "+ actualTemplateName);

		// Compare expected vs actual
		if (updatedTemplateName.equals(actualTemplateName)) {
			System.out.println("Template updated successfully.");
			ExtentManager.logInfo("Template updated successfully.");
			return true;
		} else {
			System.out.println("Template updation failed.");
			ExtentManager.logFail("Template updation failed.");
			return false;
		}
	}

	public void selectIntity() {
		
		clickOnField(driver, checkboxSelection, "Entity selection", "Checkbox"); // Select the master checkbox

		waitForSeconds(1);

		clickOnField(driver, moreButtonSelection, "More button", "Button"); // Select the more button option


		clickOnField(driver, moreDeleteButttonSelection, "Delete button", "Button"); // Select the delete button from more button option

		waitForSeconds(1);

		// Switch diver to handle popup message

		waitForSeconds(1);

		clickOnField(driver, deleteconfirmationBoxSelection, "Delete button", "Button");

		waitForSeconds(1);

		clickOnField(driver, cancelButtonOfLogMessagePane, "Cancel button", "Button");

		waitForSeconds(1);
		
		System.out.println("Template deleted successfully from listing page.");
		ExtentManager.logInfo("Template deleted successfully from listing page.");


	}

	// Entity deletion from the details page.

	public void deleteTemplateFromDetailsPage() {

		clickOnField(driver, deleteButtonOfListingPage, "Delete button", "Button");
		waitForSeconds(1);

		clickOnField(driver, deleteconfirmationBoxSelection, "Delete button", "Button");

		waitForSeconds(1);
		
		System.out.println("Template deleted successfully from details page.");
		ExtentManager.logInfo("Template deleted successfully from details page.");

	}

	// Validate toast message
	public void validateToastMessage() {
		captureUIErrorIfPresent(driver);
	}

//	public void navigatHomePage() {
//		goTo(ConfigReader.getProperty("homePageUrl"));
//		waitForSeconds(1);
//	}

	public void navigatHomePage() {
		goTo(homePageUrl);
	}
	
	// ******************************************************************************//
	// Create a new Template //

	public boolean createNewTemplate(String ruleType, String templateName, String ruleName, String ruleDescription) {

		if (!clickTestGenerator())
			return false;
		if (!clickTemplateTab())
			return false;
		if (!clickOnSearchFieldOfSearchTemplate())
			return false;
		if (!searchTemplateName(templateName))
			return false;
		if (!clickOnSearchIcopnOfSearchTemplate())
			return false;
		if (!selectSearchItemSelectFromList())
			return false;
		if (!clickOnCopyButton())
			return false;

		jsonReadAndStore();
		waitForSeconds(1);

		if (!clickTemplateTab())
			return false;
		if (!clickOnNewTemplateButton())
			return false;
		if (!clickOnAccountField())
			return false;
		if (!clickOnRuleTypeField(ruleType))
			return false;
		if (!clickOnJSONField())
			return false;

		setJsonAndLockEditor();

		if (!clickOnNameField(ruleName))
			return false;
		if (!clickOnDescriptionField(ruleDescription))
			return false;
		if (!clickOnSaveButton())
			return false;
		clickTemplateTab();
		validateCreatedTemplateName(templateName);
		navigatHomePage();
		waitForSeconds(1);
		return true;

	}

	// ******************************************************************************//
	// Update the Template //

	public boolean updateExistingTemplate(String ruleType, String templateName, String updatedTemplateName,
			String updatedTemplateDescription) {

		if (!clickTestGenerator())
			return false;
		if (!clickTemplateTab())
			return false;
		if (!clickOnSearchFieldOfSearchTemplate())
			return false;
		if (!searchTemplateName(templateName))
			return false;
		if (!clickOnSearchIcopnOfSearchTemplate())
			return false;
		if (!selectSearchItemSelectFromList())
			return false;
		if (!clickOnNameField(updatedTemplateName))
			return false;
		if (!clickOnDescriptionField(updatedTemplateDescription))
			return false;
		if (!clickOnSaveButton())
			return false;
		clickTemplateTab();
		validateUpdatedTemplateName(updatedTemplateName);
		waitForSeconds(1);
		navigatHomePage();
		waitForSeconds(1);
		return true;

	}

	// ******************************************************************************//
	// Delete the Template form the listing page //

	public boolean deleteExistingTemplateFromList(String ruleType, String templateName) {

		if (!clickTestGenerator())
			return false;
		if (!clickTemplateTab())
			return false;
		if (!clickOnSearchFieldOfSearchTemplate())
			return false;
		if (!searchTemplateName(templateName))
			return false;
		if (!clickOnSearchIcopnOfSearchTemplate())
			return false;
		selectIntity();
		waitForSeconds(1);
		navigatHomePage();
		waitForSeconds(1);
		return true;

	}

	// ******************************************************************************//
	// Delete the Template form the details page //

	public boolean deleteTemplateDetailsPage(String ruleType, String templateName) {

		if (!clickTestGenerator())
			return false;
		if (!clickTemplateTab())
			return false;
		if (!clickOnSearchFieldOfSearchTemplate())
			return false;
		if (!searchTemplateName(templateName))
			return false;
		if (!clickOnSearchIcopnOfSearchTemplate())
			return false;
		if (!selectSearchItemSelectFromList())
			return false;

		deleteTemplateFromDetailsPage();
		validateToastMessage();
		waitForSeconds(1);
		navigatHomePage();
		waitForSeconds(1);

		return true;

	}

}
