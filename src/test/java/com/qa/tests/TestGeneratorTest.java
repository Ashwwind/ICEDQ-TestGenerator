package com.qa.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.qa.base.Base;
import com.qa.extentreportlistener.ExtentManager;
import com.qa.pages.TestGeneratorPage;
import com.qa.utils.ExcelReader;
import com.qa.utils.PageUtil;
import com.qa.extentreportlistener.ExtentReportListener;


@Listeners(ExtentReportListener.class)
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

		ExtentManager.startTest("Create rule using default Dynamic Template for - " + ruleType + " | " + connectionType + " connection.");

		TestGeneratorPage page = new TestGeneratorPage(driver);

		try {
			if(connectionType.equalsIgnoreCase("Database")) {
				page.createRuleUsingDefaultDynamicTemplate(ruleType, templateName, workspaceName, folderName, connectionType,connectionName, schemaName, tableName);
			}
			else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse"))
			{
				//page.createRuleUsingDefaultDynamicTemplate(ruleType, templateName, workspaceName, folderName, connectionType,connectionName, schemaName, tableName);
			}

			ExtentManager.logPass("Rule created successfully for rule type: " + ruleType);;

		} catch (Exception e) {
			ExtentManager.captureScreenshot("Failure_" + ruleType );
			ExtentManager.logFail("Rule creation failed for rule type: " + ruleType + " | Error: " + e.getMessage());
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
		ExtentManager.startTest("Create rule using default Import template for - " + ruleType + " | " + connectionType + " connection.");

		TestGeneratorPage page = new TestGeneratorPage(driver);

		try {
			if(connectionType.equalsIgnoreCase("Database")) {
				page.createRuleUsingDefaultImportTemplate(ruleType, templateName, workspaceName, folderName, connectionType,connectionName);
			}
			else if(connectionType.equalsIgnoreCase("Cloud Data Warehouse")) {
				page.createRuleUsingDefaultImportTemplate(ruleType, templateName, workspaceName, folderName, connectionType,connectionName);
			}
			else if(connectionType.equalsIgnoreCase("File")) {
				page.createRuleUsingDefaultImportTemplate(ruleType, templateName, workspaceName, folderName, connectionType,
						connectionName);
			}

			ExtentManager.logPass("Rule created successfully for rule type: " + ruleType);

		} catch (Exception e) {
			ExtentManager.logFail("Rule creation failed for rule type: " + ruleType + " | " + e.getMessage());
		}
		finally {
			page.navigatHomePage();
		}
	}

}
