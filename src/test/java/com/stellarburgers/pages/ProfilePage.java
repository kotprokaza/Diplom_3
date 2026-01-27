// Диплом 3: UI тесты Stellar Burgers - 27.01.2026
package com.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    
    private final WebDriver driver;
    
    // Локаторы
    private final By profileLink = By.xpath(".//a[text()='Профиль']");
    private final By logoutButton = By.xpath(".//button[text()='Выход']");
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private final By logoButton = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }
    
    @Step("Нажать кнопку 'Выход'")
    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }
    
    @Step("Нажать кнопку 'Конструктор'")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }
    
    @Step("Нажать на логотип")
    public void clickLogo() {
        driver.findElement(logoButton).click();
    }
    
    @Step("Проверить что открыт личный кабинет")
    public boolean isProfilePageDisplayed() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(profileLink));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Step("Открыть личный кабинет")
    public void open() {
        driver.get("https://stellarburgers.education-services.ru/account/profile");
        waitForPageLoad();
    }
    
    private void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(profileLink));
    }
}
// Диплом 3: UI тесты Stellar Burgers - 27.01.2026
