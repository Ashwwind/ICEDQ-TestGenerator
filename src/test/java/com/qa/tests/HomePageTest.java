package com.qa.tests;

import org.testng.annotations.Test;
import com.qa.base.Base;
import com.qa.pages.HomePage;

/** Listener is declared in testng.xml — no @Listeners annotation needed here. */
public class HomePageTest extends Base {

    @Test
    public void validateHomePage() {
        HomePage home = new HomePage(driver);
        home.homepageValidation();
    }

}