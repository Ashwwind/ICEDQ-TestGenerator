package com.qa.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qa.base.Base;
import com.qa.extentreportlistener.ExtentListener;
import com.qa.pages.TestGeneratorPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Listeners(ExtentListener.class)
public class TestGeneratorTest extends Base {

	private static final Logger logger = LogManager.getLogger(TestGeneratorTest.class);

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
		ExtentListener.startTest("Create Rule Test - " + ruleType);
		
		TestGeneratorPage page = new TestGeneratorPage(driver);

		try {
	        page.createRuleUsingDefaultDynamicTemplate(ruleType, templateName, workspaceName,
	                folderName, connectionName, schemaName, tableName);

	        ExtentListener.pass("Rule created successfully for rule type: " + ruleType);

	    } catch (Exception e) {
	        ExtentListener.fail("Rule creation failed for rule type: " + ruleType + " | " + e.getMessage());
	        throw e;
	    }
	}
	
	
	
	//////    **************************   //////
	
	
	
//	// Use Data provider
//		@DataProvider(name = "ruleDataForImportTemplate")
//		public Object[][] ruleDataProvider1() {
//			return new Object[][] {
//					{ "checksum", "Compare Record Counts - Dynamic SQL", "Test_Generator-WIP", "MJ_DND", "postgreSQL",
//							"public", "staff" },
//					{ "recon", "Specify Column for Diff - Full Data Compare Dynamic SQL", "Test_Generator-WIP", "MJ_DND",
//							"postgreSQL", "public", "staff" },
//					{ "validation", "Data Validation - Dynamic SQL", "Test_Generator-WIP", "MJ_DND", "postgreSQL", "public",
//							"staff" },
//					{ "pushdown", "Data Pushdown Validation - Dynamic SQL", "Test_Generator-WIP", "MJ_DND", "postgreSQL",
//							"public", "staff" } };
//		}
//
//		// Creating a rule using default dynamic template
//		@Test(dataProvider = "ruleDataForImportTemplate")
//		public void createRuleTest1(String ruleType, String templateName, String workspaceName, String folderName,
//				String connectionName, String schemaName, String tableName) throws InterruptedException {
//
//	// Create a test in Extent Report based on ruleType
//			ExtentListener.startTest("Create Rule Test - " + ruleType);
//			
//			TestGeneratorPage page = new TestGeneratorPage(driver);
//
//			try {
//		        page.createRuleUsingDefaultDynamicTemplate(ruleType, templateName, workspaceName,
//		                folderName, connectionName, schemaName, tableName);
//
//		        ExtentListener.pass("Rule created successfully for rule type: " + ruleType);
//
//		    } catch (Exception e) {
//		        ExtentListener.fail("Rule creation failed for rule type: " + ruleType + " | " + e.getMessage());
//		        throw e;
//		    }
//		}

}
