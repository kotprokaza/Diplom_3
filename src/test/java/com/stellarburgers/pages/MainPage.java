package com.stellarburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    
    // Локаторы хранятся в полях класса
    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']/parent::a");
    private final By bunsSection = By.xpath("//span[text()='Булки']/parent::div");
    private final By saucesSection = By.xpath("//span[text()='Соусы']/parent::div");
    private final By fillingsSection = By.xpath("//span[text()='Начинки']/parent::div");
    private final By activeSection = By.cssSelector("div.tab_tab_type_current__2BEPc");
    
    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void clickLoginButton() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        element.click();
    }
    
    public void clickPersonalAccountButton() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton));
        element.click();
    }
    
    public void clickBunsSection() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(bunsSection));
        element.click();
    }
    
    public void clickSaucesSection() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(saucesSection));
        element.click();
    }
    
    public void clickFillingsSection() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(fillingsSection));
        element.click();
    }
    
    public boolean isBunsSectionActive() {
        return isSectionActive("Булки");
    }
    
    public boolean isSaucesSectionActive() {
        return isSectionActive("Соусы");
    }
    
    public boolean isFillingsSectionActive() {
        return isSectionActive("Начинки");
    }
    
    private boolean isSectionActive(String sectionName) {
        try {
            WebElement active = driver.findElement(activeSection);
            return active.getText().contains(sectionName);
        } catch (Exception e) {
            return false;
        }
    }
}
