package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import base.Base;
import utils.PageUtil;

public class TestGeneratorPage extends Base {
	private WebDriver driver;

	// Locator
	By testGeneratorModue = PageUtil.getElementLocator(prop.getProperty("home.testgenerator"));
	By templateSearch = PageUtil.getElementLocator(prop.getProperty("searchfield.tempatename"));
	By selectWorkspace = PageUtil.getElementLocator(prop.getProperty("select.Workspace"));
	By selectSearchWorkspace = PageUtil.getElementLocator(prop.getProperty("select.searchedWorspace"));
	By selectSearchFolder = PageUtil.getElementLocator(prop.getProperty("select.searchedfolder"));
	By loader = PageUtil.getElementLocator(prop.getProperty("loderIsDisplayed"));

//	By testGeneratorModue = By.xpath("//button[.//h2[text()='Test Generator']]");
//	By templateSearch = By.xpath("//input[contains(@placeholder,'Search template')]");
//	By selectWorkspace = By.xpath("//span[./input[contains(@class, 'e-input')]]");
//	By selectSearchWorkspace = By.xpath("//input[@class='e-input-filter e-input e-lib e-keyboard']");
//	By selectSearchFolder = By.xpath("//button[@aria-label=\"Select folder\"]");
//	By loader = By.xpath("//div[@id=\"sp-container\"]//div[@class='e-spinner-pane e-spin-show']//div");

	public TestGeneratorPage(WebDriver driver) {
		this.driver = driver;
	}

	// Page method
	public void clickTestGenerator() {
		PageUtil.isInvisibleLoader(driver, loader);
		PageUtil.clickOnElement(driver, testGeneratorModue, 30);
	}

	// Click the Checksum rule type from the wizard page.
	public void clickOnChecksum() {
		PageUtil.isInvisibleLoader(driver, loader);
		PageUtil.clickOnElement(driver, By.xpath("//h6[text()='Checksum']"), 30);
		PageUtil.isInvisibleLoader(driver, loader);
	}

	// Click on the search field box
	public void clickOnSearchField() {
		PageUtil.isInvisibleLoader(driver, loader);
		PageUtil.clickOnElement(driver, templateSearch, 30);
	}

	// Enter the data on the search field
	public void enterOnSearchField() {
		PageUtil.sendkeysToElement(driver, By.xpath("//input[contains(@placeholder,'Search template')]"),
				"Search template", "Compare Record Counts - Dynamic SQL");
		PageUtil.clickOnElement(driver, By.xpath("//*[@title='Search']"), 30);
	}

	// Select the existing checksum template form the list
	public void selectExistingChecksumTemplate() {
		PageUtil.isInvisibleLoader(driver, loader);
		PageUtil.clickOnElement(driver, By.xpath(
				"//td[@class='e-rowcell e-templatecell e-lastrowcell']/span[@class='dib'][contains(., 'Compare Record Counts - Dynamic SQL')]"),
				30);
	}

	public void clickOnWorkspaceField() {
		PageUtil.isInvisibleLoader(driver, loader);
		// Wait for workspace field to be visible and clickable
		PageUtil.clickOnElement(driver, By.xpath("//span[./input[contains(@class, 'e-input')]]"), 30);
		// Click workspace dropdown
		PageUtil.clickOnElement(driver, selectSearchWorkspace, 30);
		// Click workspace dropdown // Click workspace dropdown
		String workspaceName = "Test_Generator-WIP";
		PageUtil.sendkeysToElement(driver, selectSearchWorkspace, "Select workspace field", workspaceName + Keys.ENTER);
		PageUtil.isInvisibleLoader(driver, loader);
		System.out.println("Workspace '" + workspaceName + "' is selected successfully.");
	}

	// Click on the Folder selector field

	public void clickOnFolderField() throws InterruptedException {
		PageUtil.isInvisibleLoader(driver, loader);
		PageUtil.clickOnElement(driver, By.xpath("//button[@aria-label='Folder']//parent::div"), 30);
		PageUtil.sendkeysToElement(driver,
				By.xpath("//div[@class='dropdown__search-group e-input-group']/input[@id='searchFolder']"),
				"searching folder name.", "MJ_DND");
		PageUtil.clickOnElement(driver, By.xpath("//div[@id='pathtree']//span[@title='Search']"), 30);
		PageUtil.isInvisibleLoader(driver, loader);
		PageUtil.clickOnElement(driver, By.xpath("//div[contains(@class, 'e-text-content')]"), 10);
		PageUtil.clickOnElement(driver, By.xpath("//span[@class='e-icons e-check']//parent::button"), 10);

		System.out.println("The folder is selected...");
	}

	public void gotoWorkspaceNextbtn() {
		PageUtil.clickOnElement(driver, By.xpath("//button[@id='workspaceNextbtn']"), 20);
	}

	public void gotoRuleMetadataNextbtn() {
		PageUtil.clickOnElement(driver, By.xpath("//button[@id='metadataNextbtn']"), 20);
	}

	public void gotoCheckMetadataNextbtn() {
		PageUtil.clickOnElement(driver, By.xpath("//button[@id='checkNextbtn']"), 20);
	}

	public void gotoNotificationNextbtn() {
		PageUtil.clickOnElement(driver, By.xpath("//button[@id='notificationNextbtn']"), 20);
	}

	/// ********* Select the source dataset ********** ///
	public void selectionSourceDataset() {

		// Click on the source connection type.
		PageUtil.clickOnElement(driver, By.xpath(
				"(//div[contains(text(),'Source Dataset')]//parent::form//following::ejs-dropdownlist[@placeholder='Select connection type']//span[@formcontrolname='connectionType'])[1]"),
				10);

		// Select the source connection type as database.
		PageUtil.clickOnElement(driver, By.xpath("//li[text()='Database']"), 20);

		// Select the connection.
		PageUtil.clickOnElement(driver, By.xpath("//*[@id='ej2_dropdownlist_27']/span"), 20);

		// Entering the connection name
		PageUtil.sendkeysToElement(driver, By.xpath("//div[@id='ej2_dropdownlist_161_popup']//input[@type='text']"),
				"source connection name", "postgreSQL" + Keys.ENTER);

		// Click on the schema dropdown
		PageUtil.clickOnElement(driver,
				By.xpath("//span[@aria-label='dropdownlist']/parent::*[@id='ej2_dropdownlist_162']"), 20);

		// Entering the schema name
		PageUtil.sendkeysToElement(driver, By.xpath("//div[@id='ej2_dropdownlist_162_popup']//input[@type='text']"),
				"source shema name", "public" + Keys.ENTER);

	}
	/////////////////////////////////////////

	/// ********* Select the target dataset ********** ///

	public void selectionTargetDataset() {

		/// Click on the target connection type.
		PageUtil.clickOnElement(driver, By.xpath(
				"(//div[contains(text(),'Source Dataset')]//parent::form//following::ejs-dropdownlist[@placeholder='Select connection type']//span[@formcontrolname='connectionType'])[2]"),
				10);

		// Select the target connection type as database.
		PageUtil.clickOnElement(driver, By.xpath("//li[text()='Database']"), 20);

		// Select the connection.
		PageUtil.clickOnElement(driver, By.xpath("//*[@id='ej2_dropdownlist_28']/span"), 20);

		// Entering the connection name
		PageUtil.sendkeysToElement(driver, By.xpath("//div[@id='ej2_dropdownlist_161_popup']//input[@type='text']"),
				"target connection name", "postgreSQL" + Keys.ENTER);

		// Click on the schema dropdown.
		PageUtil.clickOnElement(driver,
				By.xpath("//span[@aria-label='dropdownlist']/parent::*[@id='ej2_dropdownlist_162']"), 20);

		// Entering the schema name.
		PageUtil.sendkeysToElement(driver, By.xpath("//div[@id='ej2_dropdownlist_162_popup']//input[@type='text']"),
				"target schema name", "public" + Keys.ENTER);

	}

	// Click on the next button of 'select dataset' page.

	public void gotoDatasetNextbtn() {
		PageUtil.clickOnElement(driver, By.xpath("//button[@id='datasetNextbtn']"), 20);
	}

	/// ********* Select the Available Table ********** ///.

	public void selectionAvailabeTable() {

		// Click on the available table search field
		PageUtil.clickOnElement(driver, By.xpath("//input[@placeholder='Search available table']"), 20);

		// Entering the available table name
		PageUtil.sendkeysToElement(driver, By.xpath("//input[@placeholder='Search available table']"),
				"staff table name", "staff" + Keys.ENTER);

		// Select the table checkbox from the list.
		PageUtil.clickOnElement(driver, By.xpath("//ul//li[@id='staff']"), 20);

		// Click on the 'Move to' button
		PageUtil.clickOnElement(driver, By.xpath("//button[@aria-label='Move To']"), 20);
	}

	// Click on the next button of 'select table' page.

	public void gotoSelettableNextbtn() {
		PageUtil.clickOnElement(driver, By.xpath("//div[@id='e-content-element_6']//button[normalize-space()='Next']"),
				20);
	}

	// Click on the 'Generate' button.

	public void cickOnGenerate() {
		PageUtil.clickOnElement(driver, By.xpath("//button[@id='reviewNextbtn']"), 20);
	}

}
