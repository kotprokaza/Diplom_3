// Диплом 3: UI тесты Stellar Burgers - 27.01.2026
package com.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    
    private final WebDriver driver;
    
    // Локаторы
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private final By logoButton = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    private final By orderFeedButton = By.xpath(".//p[text()='Лента Заказов']");
    
    // Разделы конструктора
    private final By bunsSection = By.xpath(".//span[text()='Булки']/parent::div");
    private final By saucesSection = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By fillingsSection = By.xpath(".//span[text()='Начинки']/parent::div");
    
    // Активный раздел
    private final By activeTab = By.xpath(".//div[contains(@class, 'tab_tab_type_current')]");
    
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    
    @Step("Нажать кнопку 'Войти в аккаунт'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
    
    @Step("Нажать кнопку 'Личный Кабинет'")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }
    
    @Step("Нажать кнопку 'Конструктор'")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }
    
    @Step("Нажать на логотип")
    public void clickLogo() {
        driver.findElement(logoButton).click();
    }
    
    @Step("Перейти в раздел 'Булки'")
    public void clickBunsSection() {
        driver.findElement(bunsSection).click();
        waitForSectionToBeActive("Булки");
    }
    
    @Step("Перейти в раздел 'Соусы'")
    public void clickSaucesSection() {
        driver.findElement(saucesSection).click();
        waitForSectionToBeActive("Соусы");
    }
    
    @Step("Перейти в раздел 'Начинки'")
    public void clickFillingsSection() {
        driver.findElement(fillingsSection).click();
        waitForSectionToBeActive("Начинки");
    }
    
    @Step("Проверить что активен раздел '{sectionName}'")
    public boolean isSectionActive(String sectionName) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(ExpectedConditions.visibilityOfElementLocated(activeTab));
        
        String activeText = driver.findElement(activeTab).getText();
        return activeText.contains(sectionName);
    }
    
    private void waitForSectionToBeActive(String sectionName) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(driver -> isSectionActive(sectionName));
    }
    
    @Step("Открыть главную страницу")
    public void open() {
        driver.get("https://stellarburgers.education-services.ru");
        waitForPageLoad();
    }
    
    private void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }
    
    // Геттеры для локаторов (для использования в тестах)
    public By getBunsLocator() {
        return bunsSection;
    }
    
    public By getSaucesLocator() {
        return saucesSection;
    }
    
    public By getFillingsLocator() {
        return fillingsSection;
    }
}
// Диплом 3: UI тесты Stellar Burgers - 27.01.2026
