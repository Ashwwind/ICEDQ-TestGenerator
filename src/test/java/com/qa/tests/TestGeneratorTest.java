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

	///

//	ExtentListener.test.get().pass("Step passed successfully");
//	ExtentListener.test.get().fail("Step failed");
//	ExtentListener.test.get().info("Entering data in search field…");

	////

	private static final Logger logger = LogManager.getLogger(TestGeneratorTest.class);

	// Use Data provider
	@DataProvider(name = "ruleData")
	public Object[][] ruleDataProvider() {
		return new Object[][] {
				{ "checksum", "Compare Record Counts - Dynamic SQL", "Test_Generator-WIP", "MJ_DND", "postgreSQL",
						"public", "staff" },
				{ "recon", "Compare Record Counts - Dynamic SQL", "Test_Generator-WIP", "MJ_DND", "postgreSQL",
						"public", "staff" },
				{ "validation", "Compare Record Counts - Dynamic SQL", "Test_Generator-WIP", "MJ_DND", "postgreSQL",
						"public", "staff" },
				{ "pushdown", "Compare Record Counts - Dynamic SQL", "Test_Generator-WIP", "MJ_DND", "postgreSQL",
						"public", "staff" } };
	}

	// Creating a checksum rule using default dynamic template
	@Test(dataProvider = "ruleData")
	public void createRuleTest(String ruleType, String templateName, String workspaceName, String folderName,
			String connectionName, String schemaName, String tableName) throws InterruptedException {

		TestGeneratorPage page = new TestGeneratorPage(driver);

		page.createRule(ruleType, templateName, workspaceName, folderName, connectionName, schemaName, tableName);

		logger.info("Rule created successfully for rule type: " + ruleType);

		// Step1: To Click on the test generator
		try {
			page.clickTestGenerator();
			logger.info("Clicked on Test Generator module.");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		// Step2: To click on the 'Checksum' wizard.

		try {
			page.clickOnChecksum();
			logger.info("The 'Checksum' rule wizard is clickable..");
			// ExtentListener.test.get().pass("The 'Checksum' rule wizard is clickable..");
			// ExtentListener.test.get().addScreenCaptureFromBase64String("Checksum");
		} catch (Exception e) {
			logger.error("The 'Checksum' rule wizard is not clickable.." + e.getMessage());
			// ExtentListener.test.get().fail("The 'Checksum' rule wizard is not
			// clickable..");
			// ExtentListener.test.get().addScreenCaptureFromBase64String("Checksum");
		}

		// Step3: To click the template search field and enter search data.

		try {
			page.clickOnSearchField();
			logger.info("The template search field is clickable..");
		} catch (Exception e) {
			logger.error("The template search field is not clickable.." + e.getMessage());
			// ExtentListener.test.get().fail("template search field is is not
			// clickable..");
			// ExtentListener.test.get().addScreenCaptureFromBase64String("Checksum");
		}
		try {
			page.enterOnSearchField(templateName);
			logger.info("The search data entering..");
		} catch (Exception e) {
			logger.error("Unabe to enter search data.." + e.getMessage());
		}

		// Step4: To click the existing dynamic checksum template.
		try {
			page.selectExistingChecksumTemplate();
			logger.info("The existing dynamic checksum tamplate selected..");
		} catch (Exception e) {
			logger.error("The existing dynamic checksum tamplate is not selected.." + e.getMessage());
		}

		// Step5: To select the workspace from the dropdwon.

		try {
			page.clickOnWorkspaceField(workspaceName);
			logger.info("The workspace is selected..");
		} catch (Exception e) {
			logger.error("The workspace is selected.." + e.getMessage());
		}

		// Step6: To select the folder from the dropdown.

		try {
			page.clickOnFolderField(folderName);
			logger.info("The folder is selected..");
		} catch (Exception e) {
			logger.error("The folder is selected.." + e.getMessage());
		}

		// Step7: To click on the next button of the 'select container' page.

		try {
			page.gotoWorkspaceNextbtn();
			logger.info("The next button clickable from the 'select container' page..");
		} catch (Exception e) {
			logger.error("The next button not clickable from the 'select container' page.." + e.getMessage());
		}

		// Step8: To click on the next button of the 'define rule metadata' page.

		try {
			page.gotoRuleMetadataNextbtn();
			logger.info("The next button clickable from the 'define rule metadata' page..");
		} catch (Exception e) {
			logger.error("The next button clickable from the 'define rule metadata' page.." + e.getMessage());
		}

		// Step9: To click on the next button of the 'define check metadata' page.
		try {
			page.gotoCheckMetadataNextbtn();
			logger.info("The next button clickable from the 'define check metadata' page..");
		} catch (Exception e) {
			logger.error("The next button clickable from the 'define check metadata' page.." + e.getMessage());
		}

		// Step10: To click on the next button of the 'configure notification' page.

		try {
			page.gotoNotificationNextbtn();
			logger.info("The next button clickable from the 'configure notification' page..");
		} catch (Exception e) {
			logger.error("The next button clickable from the 'configure notification' page.." + e.getMessage());
		}

		// Step11: To Select the source connection.
		try {
			page.selectionSourceDataset(tableName, schemaName);
			logger.info("The source connection selected..");
		} catch (Exception e) {
			logger.error("The source connection not selected.." + e.getMessage());
		}

		// Step11: To Select the target connection.
		try {
			page.selectionTargetDataset(tableName, schemaName);
			logger.info("The target connection selected..");
		} catch (Exception e) {
			logger.error("The target connection not selected.." + e.getMessage());
		}

		// Step12: To click on the next button of the 'select dataset' page.
		try {
			page.gotoDatasetNextbtn();
			logger.info("The next button clickable from the 'select dataset' page..");
		} catch (Exception e) {
			logger.error("The next button not clickable from the 'select dataset' page.." + e.getMessage());
		}

		// Step13: To select the available table name.

		try {
			page.selectionAvailabeTable(tableName);
			logger.info("The available table name selected..");
		} catch (Exception e) {
			logger.error("The available table name not selected.." + e.getMessage());
		}

		// Step14: click on the next button of the 'select table' page.

		try {
			page.gotoSelettableNextbtn();
			logger.info("The next button clickable from the 'select table' page.");
		} catch (Exception e) {
			logger.error("The next button not clickable from the 'select table' page." + e.getMessage());
		}

		// Step15: click on the Generate button.

		try {
			page.clickOnGenerate();
			logger.info("The generate button is clickable..");
		} catch (Exception e) {
			logger.error("The generate button is not clickable.." + e.getMessage());
		}

		// Step16: click on the Go To Preview button.

		try {
			page.clickOnGoToPreview();
			logger.info("The 'go to preview' button is clickable..");
		} catch (Exception e) {
			logger.error("The 'go to preview' button is not clickable.." + e.getMessage());
		}

		// Step17: Select the generated entity.
		try {
			page.selectGeneratedEntity();
			logger.info("The generated entity is clickable..");
		} catch (Exception e) {
			logger.error("The generated entity is not clickable.." + e.getMessage());
		}

		// Step18: Click on the 'Publish' button.
		try {
			page.clickOnPublish();
			logger.info("The 'Publish' button is clickable..");
		} catch (Exception e) {
			logger.error("The 'Publish' button is not clickable.." + e.getMessage());
		}

		// Step19: Click on the 'Go To Publish' button.
		try {
			page.clickOnGoToPublish();
			logger.info("The 'Go To Publish' button is clickable..");
		} catch (Exception e) {
			logger.error("The 'Go To Publish' button is not clickable.." + e.getMessage());
		}

		// Step20: Click on the hyperlink text of Publish rule.

		try {
			page.clickOnPublishedRule();
			logger.info("The published rule hyperlink is clickable..");
		} catch (Exception e) {
			logger.error("The published rule hyperlink is not clickable.." + e.getMessage());
		}
	}

}
