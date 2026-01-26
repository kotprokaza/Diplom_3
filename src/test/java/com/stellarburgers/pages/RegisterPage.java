package com.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    
    private final WebDriver driver;
    
    // Локаторы
    private final By nameField = By.xpath(".//fieldset[1]//input");
    private final By emailField = By.xpath(".//fieldset[2]//input");
    private final By passwordField = By.xpath(".//fieldset[3]//input");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By loginLink = By.xpath(".//a[text()='Войти']");
    private final By passwordError = By.xpath(".//p[text()='Некорректный пароль']");
    
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }
    
    @Step("Заполнить поле 'Имя' значением: {name}")
    public void fillNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
    }
    
    @Step("Заполнить поле 'Email' значением: {email}")
    public void fillEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }
    
    @Step("Заполнить поле 'Пароль' значением: {password}")
    public void fillPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
    
    @Step("Нажать кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }
    
    @Step("Нажать ссылку 'Войти'")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
    
    @Step("Регистрация пользователя с данными: {name}, {email}, {password}")
    public void register(String name, String email, String password) {
        fillNameField(name);
        fillEmailField(email);
        fillPasswordField(password);
        clickRegisterButton();
    }
    
    @Step("Проверить отображение ошибки пароля")
    public boolean isPasswordErrorDisplayed() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(passwordError));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Step("Проверить что открыта страница регистрации")
    public boolean isRegisterPageDisplayed() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(registerButton));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Step("Открыть страницу регистрации")
    public void open() {
        driver.get("https://stellarburgers.education-services.ru/register");
        waitForPageLoad();
    }
    
    private void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(registerButton));
    }
}
