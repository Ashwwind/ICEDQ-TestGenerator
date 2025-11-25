package com.qa.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.qa.base.Base;
import com.qa.extentreportlistener.ExtentListener;
import com.qa.pages.TestGeneratorPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Listeners(ExtentListener.class)
public class TestGeneratorTest extends Base {

	private static final Logger logger = LogManager.getLogger(TestGeneratorTest.class);

	// Use Data provider
	@DataProvider(name = "ruleData")
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

	// Creating a checksum rule using default dynamic template
	@Test(dataProvider = "ruleData")
	public void createRuleTest(String ruleType, String templateName, String workspaceName, String folderName,
			String connectionName, String schemaName, String tableName) throws InterruptedException {

		TestGeneratorPage page = new TestGeneratorPage(driver);

		page.createRule(ruleType, templateName, workspaceName, folderName, connectionName, schemaName, tableName);

		logger.info("Rule created successfully for rule type: " + ruleType);

	}

}
