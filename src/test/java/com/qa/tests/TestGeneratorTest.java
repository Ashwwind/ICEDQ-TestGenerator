package com.qa.tests;

import org.testng.annotations.Test;

import com.qa.base.Base;
import com.qa.pages.TestGeneratorPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestGeneratorTest extends Base {

	private static final Logger logger = LogManager.getLogger(TestGeneratorTest.class);

	// Creating a checksum rule using default dynamic template

	@Test
	public void selectTestGenrator() throws InterruptedException {
		TestGeneratorPage testGeneratorPage = new TestGeneratorPage(driver);
		// Step1: To Click on the test generator
		try {
			testGeneratorPage.clickTestGenerator();
			logger.info("The test generator module is clickable..");
		} catch (Exception e) {
			logger.error("The test generator module is not clickable.." + e.getMessage());
		}

		// Step2: To click on the 'Checksum' wizard.

		try {
			testGeneratorPage.clickOnChecksum();
			logger.info("The 'Checksum' rule wizard is clickable..");
		} catch (Exception e) {
			logger.error("The 'Checksum' rule wizard is not clickable.." + e.getMessage());
		}

		// Step3: To click the template search field and enter search data.

		try {
			testGeneratorPage.clickOnSearchField();
			logger.info("The template search field is clickable..");
		} catch (Exception e) {
			logger.error("The template search field is not clickable.." + e.getMessage());
		}
		try {
			testGeneratorPage.enterOnSearchField();
			logger.info("The search data entering..");
		} catch (Exception e) {
			logger.error("Unabe to enter search data.." + e.getMessage());
		}

		// Step4: To click the existing dynamic checksum template.
		try {
			testGeneratorPage.selectExistingChecksumTemplate();
			logger.info("The existing dynamic checksum tamplate selected..");
		} catch (Exception e) {
			logger.error("The existing dynamic checksum tamplate is not selected.." + e.getMessage());
		}

		// Step5: To select the workspace from the dropdwon.

		try {
			testGeneratorPage.clickOnWorkspaceField();
			logger.info("The workspace is selected..");
		} catch (Exception e) {
			logger.error("The workspace is selected.." + e.getMessage());
		}

		// Step6: To select the folder from the dropdown.

		try {
			testGeneratorPage.clickOnFolderField();
			logger.info("The folder is selected..");
		} catch (Exception e) {
			logger.error("The folder is selected.." + e.getMessage());
		}

		// Step7: To click on the next button of the 'select container' page.

		try {
			testGeneratorPage.gotoWorkspaceNextbtn();
			logger.info("The next button clickable from the 'select container' page..");
		} catch (Exception e) {
			logger.error("The next button not clickable from the 'select container' page.." + e.getMessage());
		}

		// Step8: To click on the next button of the 'define rule metadata' page.

		try {
			testGeneratorPage.gotoRuleMetadataNextbtn();
			logger.info("The next button clickable from the 'define rule metadata' page..");
		} catch (Exception e) {
			logger.error("The next button clickable from the 'define rule metadata' page.." + e.getMessage());
		}

		// Step9: To click on the next button of the 'define check metadata' page.
		try {
			testGeneratorPage.gotoCheckMetadataNextbtn();
			logger.info("The next button clickable from the 'define check metadata' page..");
		} catch (Exception e) {
			logger.error("The next button clickable from the 'define check metadata' page.." + e.getMessage());
		}

		// Step10: To click on the next button of the 'configure notification' page.

		try {
			testGeneratorPage.gotoNotificationNextbtn();
			logger.info("The next button clickable from the 'configure notification' page..");
		} catch (Exception e) {
			logger.error("The next button clickable from the 'configure notification' page.." + e.getMessage());
		}

		// Step11: To Select the source connection.
		try {
			testGeneratorPage.selectionSourceDataset();
			logger.info("The source connection selected..");
		} catch (Exception e) {
			logger.error("The source connection not selected.." + e.getMessage());
		}

		// Step11: To Select the target connection.
		try {
			testGeneratorPage.selectionTargetDataset();
			logger.info("The target connection selected..");
		} catch (Exception e) {
			logger.error("The target connection not selected.." + e.getMessage());
		}

		// Step12: To click on the next button of the 'select dataset' page.
		try {
			testGeneratorPage.gotoDatasetNextbtn();
			logger.info("The next button clickable from the 'select dataset' page..");
		} catch (Exception e) {
			logger.error("The next button not clickable from the 'select dataset' page.." + e.getMessage());
		}

		// Step13: To select the available table name.

		try {
			testGeneratorPage.selectionAvailabeTable();
			logger.info("The available table name selected..");
		} catch (Exception e) {
			logger.error("The available table name not selected.." + e.getMessage());
		}

		// Step14: click on the next button of the 'select table' page.

		try {
			testGeneratorPage.gotoSelettableNextbtn();
			logger.info("The next button clickable from the 'select table' page.");
		} catch (Exception e) {
			logger.error("The next button not clickable from the 'select table' page." + e.getMessage());
		}

		// Step15: click on the Generate button.

		try {
			testGeneratorPage.cickOnGenerate();
			logger.info("The generate button is clickable..");
		} catch (Exception e) {
			logger.error("The generate button is not clickable.." + e.getMessage());
		}

		// Step16: click on the Go To Preview button.

		try {
			testGeneratorPage.clickOnGoToPreview();
			logger.info("The 'go to preview' button is clickable..");
		} catch (Exception e) {
			logger.error("The 'go to preview' button is not clickable.." + e.getMessage());
		}

	}

}
