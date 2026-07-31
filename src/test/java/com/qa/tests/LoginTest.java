package com.qa.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import com.qa.base.Base;
import com.qa.config.ConfigReader;
import com.qa.pages.LoginPage;

/**
 * Login test scenarios.
 * Listener is declared in testng.xml — no @Listeners annotation needed here.
 */
public class LoginTest extends Base {

    @Test
    public void validateLoginPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginPageValidation();
    }

    @Test
    public void Login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
    }

}
