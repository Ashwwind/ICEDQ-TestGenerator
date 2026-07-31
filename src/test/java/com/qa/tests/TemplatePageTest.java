package com.qa.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.base.Base;
import com.qa.extentreportlistener.ExtentManager;
import com.qa.pages.TemplatePage;
import com.qa.utils.ExcelReader;

/** Listener is declared in testng.xml — no @Listeners annotation needed here. */
public class TemplatePageTest extends Base {

    // ✅ Declare once as a field and initialise in @BeforeMethod
    private TemplatePage templatePage;

    @BeforeMethod
    public void setUpPage() {
        templatePage = new TemplatePage(driver);
    }


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
			String newDescription, String accountName) {

		ExtentManager.startTest(STR."Create Template [\{templateType}] for rule type: \{ruleType}");

        templatePage.createNewTemplate(ruleType, sourceTemplate, newName, newDescription,accountName);
	}


	@Test(dataProvider = "updateTemplateData")
	public void updateTemplate(String templateType, String ruleType, String existingName, String updatedName,
			String updatedDescription, String accountName) {

		ExtentManager.startTest(STR."Update Template [\{templateType}] for rule type: \{ruleType}");

        templatePage.updateExistingTemplate(ruleType, existingName, updatedName, updatedDescription, accountName);
	}


	@Test(dataProvider = "deleteTemplateListData")
	public void deleteTemplateFromListingPage(String templateType, String ruleType, String templateName, String accountName) {

		ExtentManager.startTest(STR."Delete Template from Listing Page [\{templateType}] for rule type: \{ruleType}");

        templatePage.deleteTemplateFromListingPage(ruleType, templateName, accountName);
	}


	@Test(dataProvider = "deleteTemplateDetailsData")
	public void deleteTemplateFromDetailsPage(String templateType, String ruleType, String templateName, String accountName) {

		ExtentManager.startTest(STR."Delete Template from Details Page [\{templateType}] for rule type: \{ruleType}");

        templatePage.deleteTemplateFromDetailsPage(ruleType, templateName, accountName);
	}
}