package com.qa.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.qa.base.Base;
import com.qa.extentreportlistener.ExtentManager;
import com.qa.pages.TestGeneratorPage;
import com.qa.utils.ExcelReader;


import com.qa.extentreportlistener.ExtentReportListener;

@Listeners(ExtentReportListener.class)
public class TestGeneratorTest extends Base {

	// Use Data provider for Dynamic

	@DataProvider(name = "ruleDataForDynamicTemplate")
	public Object[][] ruleDataProviderExcel() {
		return ExcelReader.readExcel("./src/test/resources/Database/ruleData.xlsx", "Dynamic Template");
	}

	// Creating a rule using default dynamic template

	
	@Test(dataProvider = "ruleDataForDynamicTemplate")
	public void createRuleUsingDynamicTemplate(String ruleType, String templateName, String workspaceName,
			String folderName, String connectionType, String SourceConnectionName, String TargetConnectionName,
			String schemaName, String tableName, String xrayTestId) throws InterruptedException {
		

		ExtentManager.startTest("Create rule using default Dynamic Template for - " + ruleType + " | " + connectionType + " connection.");
		ExtentManager.logInfo("Xray Test ID: " + xrayTestId);

		TestGeneratorPage page = new TestGeneratorPage(driver);

		try {
			if (connectionType.equalsIgnoreCase("Database")) {
				page.createRuleUsingDefaultDynamicTemplate(ruleType, templateName, workspaceName, folderName,
						connectionType, SourceConnectionName, TargetConnectionName, schemaName, tableName);
			} else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse")) {
				//page.createRuleUsingDefaultDynamicTemplate(ruleType, templateName, workspaceName, folderName, connectionType, SourceConnectionName, TargetConnectionName, schemaName, tableName);
			}

			ExtentManager.logPass("Rule created successfully for rule type: " + ruleType);

		} catch (Exception e) {
			ExtentManager.captureScreenshot("Failure_" + ruleType);
			ExtentManager.logFail("Rule creation failed for rule type: " + ruleType + " | Error: " + e.getMessage());
			throw e;
		}
	}

	// ****************************************************************************

	@DataProvider(name = "ruleDataForWorkflowCreation")
	public Object[][] ruleDataProviderWorkflow() {
		return ExcelReader.readExcel("./src/test/resources/Database/ruleData.xlsx", "Workflow");
	}

	@Test(dataProvider = "ruleDataForWorkflowCreation")

	public void createWorkflowUsingDefaultDynamicTemplate(String ruleType, String templateName, String workspaceName,
			String folderName, String connectionType, String SourceConnectionName, String TargetConnectionName,
			String schemaName, String tableName) throws Exception {
		ExtentManager.startTest("Create rule using default Dynamic Template for - " + ruleType + " | " + connectionType
				+ " connection.");

		TestGeneratorPage page = new TestGeneratorPage(driver);

		try {
			if (connectionType.equalsIgnoreCase("Database")) {
				page.createWorkflowUsingDefaultDynamicTemplate(ruleType, templateName, workspaceName, folderName,
						connectionType, SourceConnectionName, TargetConnectionName, schemaName, tableName);
			}

			ExtentManager.logPass("Rule created successfully for rule type: " + ruleType);

		} catch (Exception e) {
			ExtentManager.captureScreenshot("Failure_" + ruleType);
			ExtentManager.logFail("Rule creation failed for rule type: " + ruleType + " | Error: " + e.getMessage());
			throw e;
		}
	}

	////// ************************** //////

	// Use Data provider For Import

	@DataProvider(name = "ruleDataForImportTemplate")
	public Object[][] ruleDataProviderImportExcel() {
		return ExcelReader.readExcel("./src/test/resources/Database/ruleData.xlsx", "Import Template");
	}

	@Test(dataProvider = "ruleDataForImportTemplate")
	public void createRuleUsingImportTemplate(String ruleType, String templateName, String workspaceName,
			String folderName, String connectionType, String SourceConnectionName, String TargetConnectionName)
			throws InterruptedException {
		ExtentManager.startTest("Create rule using default Import template for - " + ruleType + " | " + connectionType
				+ " connection.");

		TestGeneratorPage page = new TestGeneratorPage(driver);

		try {
			if (connectionType.equalsIgnoreCase("Database")) {
				page.createRuleUsingDefaultImportTemplate(ruleType, templateName, workspaceName, folderName,
						connectionType, SourceConnectionName, TargetConnectionName);
			} else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse")) {
				page.createRuleUsingDefaultImportTemplate(ruleType, templateName, workspaceName, folderName,
						connectionType, SourceConnectionName, TargetConnectionName);
			} else if (connectionType.equalsIgnoreCase("File")) {
				page.createRuleUsingDefaultImportTemplate(ruleType, templateName, workspaceName, folderName,
						connectionType, SourceConnectionName, TargetConnectionName);
			}

			ExtentManager.logPass("Rule created successfully for rule type: " + ruleType);

		} catch (Exception e) {
			ExtentManager.captureScreenshot("Failure_" + ruleType);
			ExtentManager.logFail("Rule creation failed for rule type: " + ruleType + " | Error: " + e.getMessage());
			throw e;
		}
	}

}
