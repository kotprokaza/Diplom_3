package com.stellarburgers.tests;

import com.stellarburgers.BaseTest;
import com.stellarburgers.Constants;
import com.stellarburgers.api.UserClient;
import com.stellarburgers.pages.LoginPage;
import com.stellarburgers.pages.MainPage;
import com.stellarburgers.pages.RegisterPage;
import com.stellarburgers.pages.ForgotPasswordPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Map;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {
    private Map<String, String> testUser;
    
    @Before
    public void setUpTestUser() {
        testUser = UserClient.createRandomUser();
    }
    
    @After
    public void tearDownTestUser() {
        UserClient.deleteUser(testUser.get("accessToken"));
    }
    
    @Test
    public void loginViaMainPageButton() {
        driver.get(Constants.BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testUser.get("email"), testUser.get("password"));
        
        wait.until(ExpectedConditions.urlContains("/account/profile"));
        assertTrue("После логина должен быть на странице профиля", 
                   driver.getCurrentUrl().contains("/account/profile"));
    }
    
    @Test
    public void loginViaPersonalAccountButton() {
        driver.get(Constants.BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testUser.get("email"), testUser.get("password"));
        
        wait.until(ExpectedConditions.urlContains("/account/profile"));
        assertTrue("После логина должен быть на странице профиля", 
                   driver.getCurrentUrl().contains("/account/profile"));
    }
    
    @Test 
    public void loginViaRegisterPage() {
        driver.get(Constants.REGISTER_URL);
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testUser.get("email"), testUser.get("password"));
        
        wait.until(ExpectedConditions.urlContains("/account/profile"));
        assertTrue("После логина должен быть на странице профиля", 
                   driver.getCurrentUrl().contains("/account/profile"));
    }
    
    @Test
    public void loginViaForgotPasswordPage() {
        driver.get(Constants.FORGOT_PASSWORD_URL);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickLoginLink();
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testUser.get("email"), testUser.get("password"));
        
        wait.until(ExpectedConditions.urlContains("/account/profile"));
        assertTrue("После логина должен быть на странице профиля", 
                   driver.getCurrentUrl().contains("/account/profile"));
    }
}
