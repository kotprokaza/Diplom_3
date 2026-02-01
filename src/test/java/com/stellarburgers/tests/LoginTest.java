package com.stellarburgers.tests;

import com.stellarburgers.BaseTest;
import com.stellarburgers.Constants;
import com.stellarburgers.api.User;
import com.stellarburgers.api.UserClient;
import com.stellarburgers.pages.ForgotPasswordPage;
import com.stellarburgers.pages.LoginPage;
import com.stellarburgers.pages.MainPage;
import com.stellarburgers.pages.RegisterPage;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertTrue;

@DisplayName("Тесты авторизации пользователя (навигация)")
public class LoginTest extends BaseTest {
    
    @Test
    @DisplayName("Переход на страницу логина через кнопку 'Войти в аккаунт'")
    @Description("Тест проверяет навигацию на страницу логина через кнопку 'Войти в аккаунт' на главной странице")
    public void loginViaMainPageButton() {
        driver.get(Constants.BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
        
        LoginPage loginPage = new LoginPage(driver);
        // Проверяем что открылась страница логина
        wait.until(ExpectedConditions.urlContains("/login"));
        assertTrue("Должна открыться страница логина",
                driver.getCurrentUrl().contains("/login"));
    }
    
    @Test
    @DisplayName("Переход на страницу логина через кнопку 'Личный кабинет'")
    @Description("Тест проверяет навигацию на страницу логина через кнопку 'Личный кабинет' на главной странице")
    public void loginViaPersonalAccountButton() {
        driver.get(Constants.BASE_URL);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();
        
        LoginPage loginPage = new LoginPage(driver);
        wait.until(ExpectedConditions.urlContains("/login"));
        assertTrue("Должна открыться страница логина",
                driver.getCurrentUrl().contains("/login"));
    }
    
    @Test
    @DisplayName("Переход на страницу логина со страницы регистрации")
    @Description("Тест проверяет навигацию на страницу логина через ссылку 'Войти' на странице регистрации")
    public void loginViaRegisterPage() {
        driver.get(Constants.REGISTER_URL);
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();
        
        LoginPage loginPage = new LoginPage(driver);
        wait.until(ExpectedConditions.urlContains("/login"));
        assertTrue("Должна открыться страница логина",
                driver.getCurrentUrl().contains("/login"));
    }
    
    @Test
    @DisplayName("Переход на страницу логина со страницы восстановления пароля")
    @Description("Тест проверяет навигацию на страницу логина через ссылку 'Войти' на странице восстановления пароля")
    public void loginViaForgotPasswordPage() {
        driver.get(Constants.FORGOT_PASSWORD_URL);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickLoginLink();
        
        LoginPage loginPage = new LoginPage(driver);
        wait.until(ExpectedConditions.urlContains("/login"));
        assertTrue("Должна открыться страница логина",
                driver.getCurrentUrl().contains("/login"));
    }
}
