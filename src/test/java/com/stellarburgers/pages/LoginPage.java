package com.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    
    private final WebDriver driver;
    
    // Локаторы
    private final By emailField = By.xpath(".//input[@name='name']");
    private final By passwordField = By.xpath(".//input[@name='Пароль']");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By registerLink = By.xpath(".//a[text()='Зарегистрироваться']");
    private final By restorePasswordLink = By.xpath(".//a[text()='Восстановить пароль']");
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    
    @Step("Заполнить поле 'Email' значением: {email}")
    public void fillEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }
    
    @Step("Заполнить поле 'Пароль' значением: {password}")
    public void fillPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
    
    @Step("Нажать кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
    
    @Step("Нажать ссылку 'Зарегистрироваться'")
    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }
    
    @Step("Нажать ссылку 'Восстановить пароль'")
    public void clickRestorePasswordLink() {
        driver.findElement(restorePasswordLink).click();
    }
    
    @Step("Авторизация с данными: {email}, {password}")
    public void login(String email, String password) {
        fillEmailField(email);
        fillPasswordField(password);
        clickLoginButton();
    }
    
    @Step("Проверить что открыта страница авторизации")
    public boolean isLoginPageDisplayed() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Step("Открыть страницу авторизации")
    public void open() {
        driver.get("https://stellarburgers.education-services.ru/login");
        waitForPageLoad();
    }
    
    private void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }
}
