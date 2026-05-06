package com.qa.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.base.Base;
import com.qa.extentreportlistener.ExtentManager;
import com.qa.extentreportlistener.ExtentReportListener;
import com.qa.pages.Test1;
import com.qa.utils.ExcelReader;


@Listeners(ExtentReportListener.class)
public class TemplatePageTest extends Base {

	// ─── Data Providers ──────────────────────────────────────────────────────────

	@DataProvider(name = "createTemplateData")
	public Object[][] createTemplateData() {
		return ExcelReader.readExcel("./src/test/resources/Templates/templateData.xlsx", "Create template");
	}

	@DataProvider(name = "updateTemplateData")
	public Object[][] updateTemplateData() {
		return ExcelReader.readExcel("./src/test/resources/Templates/templateData.xlsx", "Update template");
	}

	@DataProvider(name = "deleteTemplateListData")
	public Object[][] deleteTemplateListData() {
		return ExcelReader.readExcel("./src/test/resources/Templates/templateData.xlsx", "Delete template - List");
	}

	@DataProvider(name = "deleteTemplateDetailsData")
	public Object[][] deleteTemplateDetailsData() {
		return ExcelReader.readExcel("./src/test/resources/Templates/templateData.xlsx", "Delete template - Details");
	}

	// ─── Test Methods ────────────────────────────────────────────────────────────


	@Test(dataProvider = "createTemplateData")
	public void createTemplate(String templateType, String ruleType, String sourceTemplate, String newName,
			String newDescription) throws InterruptedException {

		ExtentManager.startTest("Create Template [" + templateType + "] for rule type: " + ruleType);

		new Test1(driver).createNewTemplate(ruleType, sourceTemplate, newName, newDescription);
	}


	@Test(dataProvider = "updateTemplateData")
	public void updateTemplate(String templateType, String ruleType, String existingName, String updatedName,
			String updatedDescription) {

		ExtentManager.startTest("Update Template [" + templateType + "] for rule type: " + ruleType);

		new Test1(driver).updateExistingTemplate(ruleType, existingName, updatedName, updatedDescription);
	}


	@Test(dataProvider = "deleteTemplateListData")
	public void deleteTemplateFromListingPage(String templateType, String ruleType, String templateName) {

		ExtentManager.startTest("Delete Template from Listing Page [" + templateType + "] for rule type: " + ruleType);

		new Test1(driver).deleteTemplateFromListingPage(ruleType, templateName);
	}


	@Test(dataProvider = "deleteTemplateDetailsData")
	public void deleteTemplateFromDetailsPage(String templateType, String ruleType, String templateName) {

		ExtentManager.startTest("Delete Template from Details Page [" + templateType + "] for rule type: " + ruleType);

		new Test1(driver).deleteTemplateFromDetailsPage(ruleType, templateName);
	}
}