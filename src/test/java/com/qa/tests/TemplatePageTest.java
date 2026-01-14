package com.qa.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.base.Base;
import com.qa.extentreportlistener.ExtentReportListener;
import com.qa.pages.TemplatePage;

@Listeners(ExtentReportListener.class)
public class TemplatePageTest extends Base {

	@Test
	public void createTemplate() {
		TemplatePage templatepage = new TemplatePage(driver);
		templatepage.createNewTemplate();
	}
}
