package com.qa.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.qa.base.Base;
import com.qa.extentreportlistener.ExtentListener;
import com.qa.pages.TestGeneratorPage;
import com.qa.utils.ExcelReader;

@Listeners(ExtentListener.class)
public class TestGeneratorTest extends Base {
	// Use Data provider for Dynamic

//	@DataProvider(name = "ruleDataForDynamicTemplate")
//	public Object[][] ruleDataProvider1() {
//		return new Object[][] {
//				{ "checksum", "Compare Record Counts - Dynamic SQL", "Test_Generator-WIP", "MJ_DND", "postgreSQL",
//						"public", "staff" },
//				{ "recon", "Specify Column for Diff - Full Data Compare Dynamic SQL", "Test_Generator-WIP", "MJ_DND",
//						"postgreSQL", "public", "staff" },
//				{ "validation", "Data Validation - Dynamic SQL", "Test_Generator-WIP", "MJ_DND", "postgreSQL", "public",
//						"staff" },
//				{ "pushdown", "Data Pushdown Validation - Dynamic SQL", "Test_Generator-WIP", "MJ_DND", "postgreSQL",
//						"public", "staff" } };
//	}
	
	@DataProvider(name = "ruleDataForDynamicTemplate")
	public Object[][] ruleDataProviderExcel() {
		return ExcelReader.readExcel(
			"./src/test/resources/Database/ruleData.xlsx",
			"Dynamic Template"
		);
	}


	// Creating a rule using default dynamic template

	@Test(dataProvider = "ruleDataForDynamicTemplate")
	public void createRuleUsingDynamicTemplate(String ruleType, String templateName, String workspaceName,
			String folderName, String connectionType,String connectionName, String schemaName, String tableName) throws InterruptedException {

		ExtentListener.startTest("Create rule using default Dynamic template for - " + ruleType);

		TestGeneratorPage page = new TestGeneratorPage(driver);

		try {
			if(connectionType.equalsIgnoreCase("Database")) {
			page.createRuleUsingDefaultDynamicTemplate(ruleType, templateName, workspaceName, folderName, connectionType,
					connectionName, schemaName, tableName);
			}
			else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse"))
			{
				page.createRuleUsingDefaultDynamicTemplate(ruleType, templateName, workspaceName, folderName, connectionType,
						connectionName, schemaName, tableName);
			}

			ExtentListener.pass("Rule created successfully for rule type: " + ruleType);

		} catch (Exception e) {
			ExtentListener.fail("Rule creation failed for rule type: " + ruleType + " | " + e.getMessage());
			throw e;
		}
	}

	////// ************************** //////

	// Use Data provider For Import

//	@DataProvider(name = "ruleDataForImportTemplate")
//	public Object[][] ruleDataProvider2() {
//		return new Object[][] {
//			{ "checksum", "Compare Record Counts - Import SQL", "Test_Generator-WIP", "MJ_DND", "postgreSQL" },
//			{ "recon", "Diff and Expression based on Column Position - Import SQL", "Test_Generator-WIP", "MJ_DND", "postgreSQL" },
//			{ "validation", "Data Validation - Import SQL", "Test_Generator-WIP", "MJ_DND", "postgreSQL" },
//			{ "pushdown", "Data Pushdown Validation - Import SQL", "Test_Generator-WIP", "MJ_DND", "postgreSQL" }
//		};
//	}
	
	@DataProvider(name = "ruleDataForImportTemplate")
	public Object[][] ruleDataProviderImportExcel() {
		return ExcelReader.readExcel(
			"./src/test/resources/Database/ruleData.xlsx",
			"Import Template"
		);
	}

	

	@Test(dataProvider = "ruleDataForImportTemplate")
	public void createRuleUsingImportTemplate(String ruleType, String templateName,
	        String workspaceName, String folderName, String connectionType, String connectionName) throws InterruptedException {
		ExtentListener.startTest("Create rule using default Import template for - " + ruleType);

		TestGeneratorPage page = new TestGeneratorPage(driver);

		try {
			if(connectionType.equalsIgnoreCase("Database")) {
			page.createRuleUsingDefaultImportTemplate(ruleType, templateName, workspaceName, folderName, connectionType,
					connectionName);
			}
			else if(connectionType.equalsIgnoreCase("Cloud Data Warehouse")) {
				page.createRuleUsingDefaultImportTemplate(ruleType, templateName, workspaceName, folderName, connectionType,
						connectionName);
			}

			ExtentListener.pass("Rule created successfully for rule type: " + ruleType);

		} catch (Exception e) {
			ExtentListener.fail("Rule creation failed for rule type: " + ruleType + " | " + e.getMessage());
			throw e;
		}
	}

}
