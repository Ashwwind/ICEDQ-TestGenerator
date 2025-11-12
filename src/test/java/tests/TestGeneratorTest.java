package tests;

import org.testng.annotations.Test;
import base.Base;
import pages.TestGeneratorPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestGeneratorTest extends Base {

	private static final Logger logger = LogManager.getLogger(TestGeneratorTest.class);
	TestGeneratorPage testGeneratorPage;

	// Creating a checksum rule using default dynamic template
	
	@Test
	public void selectTestGenrator() throws InterruptedException {

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
	}

//	@Test(priority = 7)
//	public void clickOnworkspaceNextbtn() throws InterruptedException {
//		// Thread.sleep(5000);
//		TestGeneratorPage testGeneratorPage;
//		testGeneratorPage = new TestGeneratorPage(driver);
//		testGeneratorPage.gotoWorkspaceNextbtn();
//		logger.info("Clicing the next button..");
//	}
//
//	@Test(priority = 8)
//	public void clickOnmetadataNextbtn() throws InterruptedException {
//		// Thread.sleep(5000);
//		TestGeneratorPage testGeneratorPage;
//		testGeneratorPage = new TestGeneratorPage(driver);
//		testGeneratorPage.gotoDAtaMetadataNextbtn();
//		logger.info("Clicing the next button..");
//	}
//
//	@Test(priority = 9)
//	public void clickOnRulemetadataNextbtn() throws InterruptedException {
//		// Thread.sleep(5000);
//		TestGeneratorPage testGeneratorPage;
//		testGeneratorPage = new TestGeneratorPage(driver);
//		testGeneratorPage.gotoRuleMetadataNextbtn();
//		logger.info("Clicing the next button..");
//	}
//	
//	@Test(priority = 9)
//	public void clickOnCheckmetadataNextbtn() throws InterruptedException {
//		// Thread.sleep(5000);
//		TestGeneratorPage testGeneratorPage;
//		testGeneratorPage = new TestGeneratorPage(driver);
//		testGeneratorPage.gotoRuleMetadataNextbtn();
//		logger.info("Clicing the next button..");
//	}
//	
//	@Test(priority = 10)
//	public void clickOnNotificationNextbtn() throws InterruptedException {
//		// Thread.sleep(5000);
//		TestGeneratorPage testGeneratorPage;
//		testGeneratorPage = new TestGeneratorPage(driver);
//		testGeneratorPage.gotoNotificationNextbtn();
//		logger.info("Clicing the next button..");
//	}
//	
//	@Test(priority = 10)
//	public void selectConnecton() throws InterruptedException {
//		// Thread.sleep(5000);
//		TestGeneratorPage testGeneratorPage;
//		testGeneratorPage = new TestGeneratorPage(driver);
//		testGeneratorPage.seectConnection();
//		logger.info("Clicing the next button..");
//	}
}
