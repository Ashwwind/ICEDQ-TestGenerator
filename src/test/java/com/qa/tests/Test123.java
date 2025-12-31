package com.qa.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.qa.base.Base;
import com.qa.extentreportlistener.ExtentManager;
import com.qa.pages.Test1;
import com.qa.pages.TestGeneratorPage;
import com.qa.utils.ExcelReader;
import com.qa.extentreportlistener.ExtentReportListener;

@Listeners(ExtentReportListener.class)
public class Test123 extends Base {

	// Use Data provider for Dynamic

	@DataProvider(name = "ruleDataForDynamicTemplate")
	public Object[][] ruleDataProviderExcel() {
		return ExcelReader.readExcel("./src/test/resources/Database/ruleData.xlsx", "Dynamic Template");
	}

	// Creating a rule using default dynamic template

	@Test(dataProvider = "ruleDataForDynamicTemplate")
	public void createRuleUsingDynamicTemplate(String ruleType, String templateName, String workspaceName,
			String folderName, String connectionType, String SourceConnectionName, String TargetConnectionName,
			String schemaName, String tableName) throws InterruptedException {

		ExtentManager.startTest("Create rule using default Dynamic Template for - " + ruleType + " | " + connectionType
				+ " connection.");

		Test1 page = new Test1(driver);

		try {
			if (connectionType.equalsIgnoreCase("Database")) {
				page.createRuleUsingDefaultDynamicTemplate(ruleType, templateName, workspaceName, folderName,
						connectionType, SourceConnectionName, TargetConnectionName, schemaName, tableName);
			} else if (connectionType.equalsIgnoreCase("Cloud Data Warehouse")) {
				page.createRuleUsingDefaultDynamicTemplate(ruleType, templateName, workspaceName, folderName,
						connectionType, SourceConnectionName, TargetConnectionName, schemaName, tableName);
			}

			ExtentManager.logPass("Rule created successfully for rule type: " + ruleType);

		} catch (Exception e) {
			ExtentManager.captureScreenshot("Failure_" + ruleType);
			ExtentManager.logFail("Rule creation failed for rule type: " + ruleType + " | Error: " + e.getMessage());
			throw e;
		}
	}
	
}