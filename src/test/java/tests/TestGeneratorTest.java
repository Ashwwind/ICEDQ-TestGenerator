package tests;

import org.testng.annotations.Test;
import base.Base;
import pages.TestGeneratorPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestGeneratorTest extends Base {

	private static final Logger logger = LogManager.getLogger(TestGeneratorTest.class);
	private TestGeneratorPage testGeneratorPage;

	// Creating a checksum rule using default dynamic template
	@Test(priority=1)
	public void selectTestGenrator() throws InterruptedException {
		try {
			TestGeneratorPage testGeneratorPage = new TestGeneratorPage(driver);
			testGeneratorPage.clickTestGenerator();
			logger.info("The test generator module is clickable..");
		} catch (Exception e) {
			logger.error("The test generator module is not clickable.." + e.getMessage());
		}
	}

	@Test(priority=2)
	public void selectChecksum() {
		try {
			TestGeneratorPage testGeneratorPage = new TestGeneratorPage(driver);
			Thread.sleep(5000);
			testGeneratorPage.clickOnChecksum();
			logger.info("The 'Checksum' rule wizard is clickable..");
		} catch (Exception e) {
			logger.error("The 'Checksum' rule wizard is not clickable.." + e.getMessage());
		}
	}

	@Test(priority=3)
	public void clickSearchField() throws InterruptedException {
		Thread.sleep(5000);
		TestGeneratorPage testGeneratorPage;
		testGeneratorPage = new TestGeneratorPage(driver);
		testGeneratorPage.clickOnSearchField();
		logger.info("The search field is clickable..");
		testGeneratorPage.enterOnSearchField();
		logger.info("The serach button icon is clickable..");
	}

	@Test(priority=4)
	public void selectTemplate() throws InterruptedException {
		Thread.sleep(5000);
		TestGeneratorPage testGeneratorPage;
		testGeneratorPage = new TestGeneratorPage(driver);
		testGeneratorPage.selectExistingChecksumTemplate();
		logger.info("The exiting templated selected");
	}

	@Test(priority=5)
	public void clickOnWorkspace() throws InterruptedException {
		Thread.sleep(5000);
		TestGeneratorPage testGeneratorPage;
		testGeneratorPage = new TestGeneratorPage(driver);
		testGeneratorPage.clickOnWorkspaceField123();
		logger.info("The workspace is selected..");

	}

	@Test(priority=6)
	public void clickOnFolderFiled() throws InterruptedException {
		TestGeneratorPage testGeneratorPage;
		testGeneratorPage = new TestGeneratorPage(driver);
		testGeneratorPage.clickOnFolderFiled();
		logger.info("The folder is selected..");
	}
}
