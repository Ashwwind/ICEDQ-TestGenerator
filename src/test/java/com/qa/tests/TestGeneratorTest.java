package com.qa.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.qa.base.Base;
import com.qa.extentreportlistener.ExtentListener;
import com.qa.pages.TestGeneratorPage;

@Listeners(ExtentListener.class)
public class TestGeneratorTest extends Base {


	// Use Data provider
	
	@DataProvider(name = "ruleDataForDynamicTemplate")
	public Object[][] ruleDataProvider() {
		return new Object[][] {
				{ "checksum", "Compare Record Counts - Dynamic SQL", "Test_Generator-WIP", "MJ_DND", "postgreSQL",
						"public", "staff" },
				{ "recon", "Specify Column for Diff - Full Data Compare Dynamic SQL", "Test_Generator-WIP", "MJ_DND",
						"postgreSQL", "public", "staff" },
				{ "validation", "Data Validation - Dynamic SQL", "Test_Generator-WIP", "MJ_DND", "postgreSQL", "public",
						"staff" },
				{ "pushdown", "Data Pushdown Validation - Dynamic SQL", "Test_Generator-WIP", "MJ_DND", "postgreSQL",
						"public", "staff" } };
	}

	// Creating a rule using default dynamic template
	
	@Test(dataProvider = "ruleDataForDynamicTemplate")
	public void createRuleTest(String ruleType, String templateName, String workspaceName, String folderName,
			String connectionName, String schemaName, String tableName) throws InterruptedException {

		// Create a test in Extent Report based on ruleType
		
		ExtentListener.startTest("Create rule using default Dynamic template for - " + ruleType);

		TestGeneratorPage page = new TestGeneratorPage(driver);

		try {
			page.createRuleUsingDefaultDynamicTemplate(ruleType, templateName, workspaceName, folderName,
					connectionName, schemaName, tableName);

			ExtentListener.pass("Rule created successfully for rule type: " + ruleType);

		} catch (Exception e) {
			ExtentListener.fail("Rule creation failed for rule type: " + ruleType + " | " + e.getMessage());
			throw e;
		}
	}

	////// ************************** //////

	// Use Data provider
	
	@DataProvider(name = "ruleDataForImportTemplate")
	public Object[][] ruleDataProvider1() {
		return new Object[][] {
				{ "checksum", "Compare Record Counts - Import SQL", "Test_Generator-WIP", "MJ_DND", "postgreSQL" },
				{ "recon", "Diff and Expression based on Column Position - Import SQL", "Test_Generator-WIP", "MJ_DND",
						"postgreSQL" },
				{ "validation", "Data Validation - Import SQL", "Test_Generator-WIP", "MJ_DND", "postgreSQL" },
				{ "pushdown", "Data Pushdown Validation - Import SQL", "Test_Generator-WIP", "MJ_DND", "postgreSQL" } };
	}

	// Creating a rule using default Import template
	
	@Test(dataProvider = "ruleDataForImportTemplate")
	public void createRuleTest1(String ruleType, String templateName, String workspaceName, String folderName,
			String connectionName) throws InterruptedException {

		// Create a test in Extent Report based on ruleType
		
		ExtentListener.startTest("Create rule using default Import template for - " + ruleType);

		TestGeneratorPage page = new TestGeneratorPage(driver);

		try {
			page.createRuleUsingDefaultImportTemplate(ruleType, templateName, workspaceName, folderName,
					connectionName);

			ExtentListener.pass("Rule created successfully for rule type: " + ruleType);

		} catch (Exception e) {
			ExtentListener.fail("Rule creation failed for rule type: " + ruleType + " | " + e.getMessage());
			throw e;
		}
	}

}
