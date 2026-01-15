package com.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
	
	By testGeneratorModue = getElementLocator(prop.getProperty("home.testgenerator"));
	
	By loader = getElementLocator(prop.getProperty("loderIsDisplayed"));

	// *****************************************Page Method*******************************//
	
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
		return clickOnField(driver, copyButton, "Copy", "Button");
	}

	public boolean clickOnNewTemplateButton() {
		return clickOnField(driver, newTemplateButton, "New Template", "Button");
	}

	public boolean clickOnAccountField() {

		boolean isClicked = clickOnField(driver, accountNameField, "Account", "Dropdown field");
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
			 // 1️⃣ Click the editor container to focus
	        By jsonEditorContainer = By.xpath("//div[contains(@class,'ace_editor')]");
	        WebElement editor = driver.findElement(jsonEditorContainer);
	        editor.click();

	        // 2️⃣ Target the hidden ACE textarea
	        By jsonInput = By.xpath("//textarea[contains(@class,'ace_text-input')]");
	        WebElement input = driver.findElement(jsonInput);

	        // 3️⃣ Select all existing content
	        input.sendKeys(Keys.chord(Keys.CONTROL, "a"));

	        // 4️⃣ Delete existing JSON
	        input.sendKeys(Keys.DELETE);

	        // 5️⃣ Enter new JSON
	        input.sendKeys(Keys.chord(Keys.CONTROL, "v"));

	        return true;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public boolean clickOnNameField(String ruleName) {
		try {
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

			// Clear existing data (Ctrl + A + Delete)
			element.sendKeys(Keys.CONTROL + "a");
			element.sendKeys(Keys.DELETE);

			// Press Enter
			boolean isEnter = sendkeysToElement1(driver, descriptionField, "Text field", ruleDescription);
			
			boolean isEnterPressed = sendKeysEnterToElement2(driver, descriptionField, "Text field");
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

	
	public void navigatHomePage() {
		goTo(ConfigReader.getProperty("homePageUrl"));
		waitForSeconds(1);
	}
	
//******************************************************************************//
	// Create a new Template //
	
	public boolean createNewTemplate(String ruleType, String templateName, String ruleName, String ruleDescription) {
		
		if (!clickTestGenerator()) return false;
	    if (!clickTemplateTab()) return false;
	    if (!clickOnSearchFieldOfSearchTemplate()) return false;
	    if (!searchTemplateName(templateName)) return false;
	    if (!clickOnSearchIcopnOfSearchTemplate()) return false;
	    if (!selectSearchItemSelectFromList()) return false;
	    if (!clickOnCopyButton()) return false;
	    if (!clickTemplateTab()) return false;
	    if (!clickOnNewTemplateButton()) return false;
	    if (!clickOnAccountField()) return false;
	    if (!clickOnRuleTypeField(ruleType)) return false;
	    if (!clickOnJSONField()) return false;
	    if (!clickOnNameField(ruleName)) return false;
	    if (!clickOnDescriptionField(ruleDescription)) return false;
	    if (!clickOnSaveButton()) return false;  
	    clickTemplateTab();
	    validateCreatedTemplateName(templateName);
	    navigatHomePage();
	    waitForSeconds(1);
	    return true;

	}
	
	
	//******************************************************************************//
		// Update the Template //
		
		public boolean updateTemplate(String ruleType, String templateName, String ruleName, String ruleDescription) {
			
			if (!clickTestGenerator()) return false;
		    if (!clickTemplateTab()) return false;
		    if (!clickOnSearchFieldOfSearchTemplate()) return false;
		    if (!searchTemplateName(templateName)) return false;
		    if (!clickOnSearchIcopnOfSearchTemplate()) return false;
		    if (!selectSearchItemSelectFromList()) return false;
		    if (!clickOnCopyButton()) return false;
		    if (!clickTemplateTab()) return false;
		    if (!clickOnNewTemplateButton()) return false;
		    if (!clickOnAccountField()) return false;
		    if (!clickOnRuleTypeField(ruleType)) return false;
		    if (!clickOnJSONField()) return false;
		    if (!clickOnNameField(ruleName)) return false;
		    if (!clickOnDescriptionField(ruleDescription)) return false;
		    if (!clickOnSaveButton()) return false;  
		    clickTemplateTab();
		    validateCreatedTemplateName(templateName);
		    navigatHomePage();
		    waitForSeconds(1);
		    return true;

		}
	
}
