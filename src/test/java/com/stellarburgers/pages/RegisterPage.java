package com.stellarburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    
    private final By nameField = By.xpath("//input[@type='text' and @name='name']");
    private final By emailField = By.xpath("//input[@type='text' and @name='name']");
    private final By passwordField = By.xpath("//input[@type='password']");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By loginLink = By.xpath("//a[text()='Войти']");
    
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void enterName(String name) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        element.clear();
        element.sendKeys(name);
    }
    
    public void enterEmail(String email) {
        // На странице регистрации email поле тоже имеет name='name'
        java.util.List<WebElement> fields = driver.findElements(emailField);
        if (fields.size() > 1) {
            fields.get(1).clear();
            fields.get(1).sendKeys(email);
        }
    }
    
    public void enterPassword(String password) {
        WebElement element = driver.findElement(passwordField);
        element.clear();
        element.sendKeys(password);
    }
    
    public void clickRegisterButton() {
        WebElement element = driver.findElement(registerButton);
        element.click();
    }
    
    public void clickLoginLink() {
        WebElement element = driver.findElement(loginLink);
        element.click();
    }
    
    public void register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }
}
