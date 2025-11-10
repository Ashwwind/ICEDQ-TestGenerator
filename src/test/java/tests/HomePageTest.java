package tests;

import org.testng.annotations.Test;
import base.Base;
import pages.HomePage;

public class HomePageTest extends Base {

	@Test
	public void homePageValidation()
	{
	HomePage home = new HomePage(driver);
	home.validateHomePage(); 
	}
	

}