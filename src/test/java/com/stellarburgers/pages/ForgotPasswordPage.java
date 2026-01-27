// Диплом 3: UI тесты Stellar Burgers - 27.01.2026
package com.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {
    
    private final WebDriver driver;
    
    // Локаторы
    private final By emailField = By.xpath(".//input[@type='text']");
    private final By restoreButton = By.xpath(".//button[text()='Восстановить']");
    private final By loginLink = By.xpath(".//a[text()='Войти']");
    
    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }
    
    @Step("Заполнить поле 'Email' значением: {email}")
    public void fillEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }
    
    @Step("Нажать кнопку 'Восстановить'")
    public void clickRestoreButton() {
        driver.findElement(restoreButton).click();
    }
    
    @Step("Нажать ссылку 'Войти'")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
    
    @Step("Проверить что открыта страница восстановления пароля")
    public boolean isForgotPasswordPageDisplayed() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(restoreButton));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Step("Открыть страницу восстановления пароля")
    public void open() {
        driver.get("https://stellarburgers.education-services.ru/forgot-password");
        waitForPageLoad();
    }
    
    private void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(restoreButton));
    }
}
// Диплом 3: UI тесты Stellar Burgers - 27.01.2026
