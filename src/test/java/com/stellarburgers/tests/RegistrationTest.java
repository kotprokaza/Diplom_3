package com.stellarburgers.tests;

import com.stellarburgers.BaseTest;
import com.stellarburgers.Constants;
import com.stellarburgers.pages.RegisterPage;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {
    
    @Test
    public void successfulRegistrationWithValidPassword() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String name = "Тестовый" + timestamp;
        String email = "test" + timestamp + "@example.com";
        String password = "Password123!";
        
        driver.get(Constants.REGISTER_URL);
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(name, email, password);
        
        // Ждём редиректа на страницу логина
        wait.until(ExpectedConditions.urlContains("/login"));
        
        assertTrue("После успешной регистрации должен быть редирект на страницу логина", 
                   driver.getCurrentUrl().contains("/login"));
    }
    
    @Test
    public void registrationFailsWithShortPassword() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String name = "Тестовый" + timestamp;
        String email = "test" + timestamp + "@example.com";
        String shortPassword = "12345";
        
        driver.get(Constants.REGISTER_URL);
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(name, email, shortPassword);
        
        // Ждём обработки формы
        wait.until(ExpectedConditions.or(
            ExpectedConditions.urlContains("/register"),
            ExpectedConditions.alertIsPresent()
        ));
        
        assertTrue("При коротком пароле должны остаться на странице регистрации", 
                   driver.getCurrentUrl().contains("/register"));
    }
}
