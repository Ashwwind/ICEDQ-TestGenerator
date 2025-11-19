package com.qa.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.qa.base.Base;
import com.qa.extentreportlistener.ExtentListener;
import com.qa.pages.HomePage;

@Listeners(ExtentListener.class)
public class HomePageTest extends Base {

	@Test
	public void homePageValidation()
	{
	HomePage home = new HomePage(driver);
	home.validateHomePage(); 
	}
	

}