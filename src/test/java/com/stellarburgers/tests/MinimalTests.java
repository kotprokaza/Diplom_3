package com.stellarburgers.tests;

import com.stellarburgers.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class MinimalTests extends BaseTest {
    
    @Test
    @DisplayName("Проверка доступности сайта")
    @Description("Тест проверяет что сайт открывается")
    public void testSiteAvailability() {
        driver.get("https://stellarburgers.education-services.ru");
        
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[contains(text(), 'Войти в аккаунт')]")
            ));
        
        assertTrue("Сайт должен быть доступен",
            driver.getTitle().contains("Stellar") || 
            driver.getCurrentUrl().contains("stellarburgers"));
    }
    
    @Test
    @DisplayName("Переход на страницу входа через главную кнопку")
    @Description("Проверяет переход на страницу входа")
    public void testNavigateToLoginFromMainButton() {
        driver.get("https://stellarburgers.education-services.ru");
        
        driver.findElement(By.xpath("//button[contains(text(), 'Войти в аккаунт')]")).click();
        
        new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(), 'Вход')]")
            ));
        
        assertTrue("Должна открыться страница входа",
            driver.findElement(By.xpath("//h2[contains(text(), 'Вход')]")).isDisplayed());
    }
    
    @Test
    @DisplayName("Переход на страницу входа через личный кабинет")
    @Description("Проверяет второй способ перехода на страницу входа")
    public void testNavigateToLoginFromPersonalAccount() {
        driver.get("https://stellarburgers.education-services.ru");
        
        driver.findElement(By.xpath("//p[text()='Личный Кабинет']")).click();
        
        new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(), 'Вход')]")
            ));
        
        assertTrue("Должна открыться страница входа",
            driver.findElement(By.xpath("//h2[contains(text(), 'Вход')]")).isDisplayed());
    }
    
    @Test
    @DisplayName("Переход на страницу регистрации")
    @Description("Проверяет переход на страницу регистрации")
    public void testNavigateToRegistrationPage() {
        driver.get("https://stellarburgers.education-services.ru");
        
        driver.findElement(By.xpath("//button[contains(text(), 'Войти в аккаунт')]")).click();
        
        new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(), 'Вход')]")
            ));
        
        driver.findElement(By.xpath("//a[text()='Зарегистрироваться']")).click();
        
        new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(), 'Регистрация')]")
            ));
        
        assertTrue("Должна открыться страница регистрации",
            driver.findElement(By.xpath("//h2[contains(text(), 'Регистрация')]")).isDisplayed());
    }
    
    @Test
    @DisplayName("Проверка разделов конструктора")
    @Description("Проверяет отображение разделов конструктора")
    public void testConstructorSections() {
        driver.get("https://stellarburgers.education-services.ru");
        
        // Ищем разделы конструктора
        boolean hasBuns = driver.findElements(By.xpath("//*[contains(text(), 'Булки')]")).size() > 0;
        boolean hasSauces = driver.findElements(By.xpath("//*[contains(text(), 'Соусы')]")).size() > 0;
        boolean hasFillings = driver.findElements(By.xpath("//*[contains(text(), 'Начинки')]")).size() > 0;
        
        assertTrue("Должен быть раздел 'Булки'", hasBuns);
        assertTrue("Должен быть раздел 'Соусы'", hasSauces);
        assertTrue("Должен быть раздел 'Начинки'", hasFillings);
    }
}
