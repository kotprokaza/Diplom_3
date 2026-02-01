package com.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class ExampleTest extends BaseTest {
    
    @Test
    @DisplayName("Проверка открытия главной страницы")
    @Description("Тест проверяет, что главная страница открывается корректно")
    public void testMainPageOpens() {
        // Проверяем, что заголовок страницы содержит нужный текст
        String pageTitle = driver.getTitle();
        System.out.println("Заголовок страницы: " + pageTitle);
        
        // Или проверяем наличие какого-то элемента на странице
        boolean isLogoDisplayed = driver.findElement(By.xpath("//*[contains(@class, 'logo')]")).isDisplayed();
        
        Assert.assertTrue("Логотип должен отображаться на странице", isLogoDisplayed);
        Assert.assertNotNull("Заголовок страницы не должен быть пустым", pageTitle);
    }
}
