package com.qa.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.base.Base;
import com.qa.extentreportlistener.ExtentManager;
import com.qa.extentreportlistener.ExtentReportListener;
import com.qa.pages.TemplatePage;
import com.qa.utils.ExcelReader;

@Listeners(ExtentReportListener.class)
public class TemplatePageTest extends Base {

	@DataProvider(name = "createTemplate")
	public Object[][] createTemplateDataProviderExcel() {
		return ExcelReader.readExcel("./src/test/resources/Templates/templateData.xlsx", "Create template");
	}

	/****** Creating a template using default template  *************/
	
	@Test(dataProvider = "createTemplate")
	public void createTemplateUsingDynamicTemplate(String templateType, String ruleType, String templateName, String ruleName, String ruleDescription) {

		ExtentManager.startTest("Create template using default Template for - " + ruleType);

		TemplatePage templatePage = new TemplatePage(driver);

		try {
			if ("Dynamic".equalsIgnoreCase(templateType)) {
				templatePage.createNewTemplate(ruleType, templateName, ruleName, ruleDescription);
			}
			else if("Import".equalsIgnoreCase(templateType))
			{
				templatePage.createNewTemplate(ruleType, templateName, ruleName, ruleDescription);
			}
				ExtentManager.logPass("Template created successfully for rule type: " + ruleType);
		} catch (Exception e) {
			ExtentManager.captureScreenshot("Failure_" + ruleType);
			ExtentManager.logFail("Template creation failed for rule type: " + ruleType + " | Error: " + e.getMessage());
			throw e;
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@DataProvider(name = "updateTemplate")
	public Object[][] updateTemplateDataProviderExcel() {
		return ExcelReader.readExcel("./src/test/resources/Templates/templateData.xlsx", "Update template");
	}

	/****** Updating a template using default template  *************/
	
	@Test(dataProvider = "updateTemplate")
	public void updateTemplateUsingExistingTemplate(String templateType, String ruleType, String templateName, String updatedTemplateName, String updatedTemplateDescription) {

		ExtentManager.startTest("Update the template using existing Template for - " + ruleType);

		TemplatePage templatePage = new TemplatePage(driver);

		try {
			if ("Dynamic".equalsIgnoreCase(templateType)) {
				templatePage.updateExistingTemplate(ruleType, templateName, updatedTemplateName, updatedTemplateDescription);
			}
			else if ("Import".equalsIgnoreCase(templateType))
			{
				templatePage.updateExistingTemplate(ruleType, templateName, updatedTemplateName, updatedTemplateDescription);
			}
				ExtentManager.logPass("Template updated successfully for rule type: " + ruleType);
			
		} catch (Exception e) {
			ExtentManager.captureScreenshot("Failure_" + ruleType);
			ExtentManager
					.logFail("Template updation failed for rule type: " + ruleType + " | Error: " + e.getMessage());
			throw e;
		}
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@DataProvider(name = "deleteTemplate")
	public Object[][] deleteTemplateDataProviderExcel() {
		return ExcelReader.readExcel("./src/test/resources/Templates/templateData.xlsx", "Delete template");
	}

	/****** Deleting the template from the list *************/

	@Test(dataProvider = "deleteTemplate")
	public void deleteTemplateUsingExistingTemplate(String templateType, String ruleType, String templateName) {

		ExtentManager.startTest("Delete the template for - " + ruleType);

		TemplatePage templatePage = new TemplatePage(driver);

		try {
			if ("Dynamic".equalsIgnoreCase(templateType)) {
				templatePage.deleteExistingTemplate(ruleType, templateName);
			} else if ("Import".equalsIgnoreCase(templateType)) {
				templatePage.deleteExistingTemplate(ruleType, templateName);
			}
			ExtentManager.logPass("Template deleted successfully for rule type: " + ruleType);

		} catch (Exception e) {
			ExtentManager.captureScreenshot("Failure_" + ruleType);
			ExtentManager
					.logFail("Template deletion failed for rule type: " + ruleType + " | Error: " + e.getMessage());
			throw e;
		}
	}
	
}
