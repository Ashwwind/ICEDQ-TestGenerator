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

	/****** Creating a rule using default dynamic template  *************/
	
	@Test(dataProvider = "createTemplate")
	public void createTemplateUsingDefaoultTemplate(String templateType, String ruleType, String templateName,
			String ruleName, String ruleDescription) {

		ExtentManager.startTest("Create template using default Dynamic Template for - " + ruleType);

		TemplatePage templatePage = new TemplatePage(driver);

		try {
			if ("Dynamic".equalsIgnoreCase(templateType)) {
				templatePage.createNewTemplate(ruleType, templateName, ruleName, ruleDescription);

				ExtentManager.logPass("Template created successfully for rule type: " + ruleType);
			}
		} catch (Exception e) {
			ExtentManager.captureScreenshot("Failure_" + ruleType);
			ExtentManager
					.logFail("Template creation failed for rule type: " + ruleType + " | Error: " + e.getMessage());
			throw e;
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@DataProvider(name = "updateTemplate")
	public Object[][] updateTemplateDataProviderExcel() {
		return ExcelReader.readExcel("./src/test/resources/Templates/templateData.xlsx", "Create template");
	}

	/****** Creating a rule using default dynamic template  *************/
	
	@Test(dataProvider = "updateTemplate")
	public void updateTemplateUsingDynamicTemplate(String templateType, String ruleType, String templateName,
			String ruleName, String ruleDescription) {

		ExtentManager.startTest("Create template using default Dynamic Template for - " + ruleType);

		TemplatePage templatePage = new TemplatePage(driver);

		try {
			if ("Dynamic".equalsIgnoreCase(templateType)) {
				templatePage.createNewTemplate(ruleType, templateName, ruleName, ruleDescription);

				ExtentManager.logPass("Template created successfully for rule type: " + ruleType);
			}
		} catch (Exception e) {
			ExtentManager.captureScreenshot("Failure_" + ruleType);
			ExtentManager
					.logFail("Template creation failed for rule type: " + ruleType + " | Error: " + e.getMessage());
			throw e;
		}
	}
	
}
