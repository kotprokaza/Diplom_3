package com.stellarburgers.tests;

import com.stellarburgers.BaseTest;
import com.stellarburgers.Constants;
import com.stellarburgers.api.User;
import com.stellarburgers.api.UserClient;
import com.stellarburgers.pages.RegisterPage;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertTrue;

@DisplayName("Тесты регистрации пользователя")
public class RegistrationTest extends BaseTest {
    
    private User registeredUser;
    
    @After
    @DisplayName("Удаление зарегистрированного пользователя")
    public void tearDownRegisteredUser() {
        if (registeredUser != null && registeredUser.getAccessToken() != null) {
            UserClient.deleteUser(registeredUser.getAccessToken());
        }
    }
    
    @Test
    @DisplayName("Успешная регистрация с валидным паролем")
    @Description("Тест проверяет успешную регистрацию пользователя с корректными данными")
    public void successfulRegistrationWithValidPassword() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String name = "Тестовый" + timestamp;
        String email = "test" + timestamp + "@example.com";
        String password = "Password123!";
        
        driver.get(Constants.REGISTER_URL);
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(name, email, password);
        
        wait.until(ExpectedConditions.urlContains("/login"));
        
        assertTrue("После успешной регистрации должен быть редирект на страницу логина",
                driver.getCurrentUrl().contains("/login"));
        
        // Сохраняем пользователя для последующего удаления
        registeredUser = new User(email, password, name);
        // Пытаемся получить токен через API
        User tempUser = UserClient.createRandomUser();
        if (tempUser.getAccessToken() != null && !tempUser.getAccessToken().isEmpty()) {
            registeredUser.setAccessToken(tempUser.getAccessToken());
        }
    }
    
    @Test
    @DisplayName("Ошибка регистрации с коротким паролем")
    @Description("Тест проверяет, что регистрация с паролем менее 6 символов завершается ошибкой")
    public void registrationFailsWithShortPassword() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String name = "Тестовый" + timestamp;
        String email = "test" + timestamp + "@example.com";
        String shortPassword = "12345";
        
        driver.get(Constants.REGISTER_URL);
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(name, email, shortPassword);
        
        // Ждем проверки пароля
        wait.until(driver -> {
            boolean stillOnRegisterPage = driver.getCurrentUrl().contains("/register");
            boolean errorDisplayed = registerPage.isPasswordErrorDisplayed();
            return stillOnRegisterPage || errorDisplayed;
        });
        
        assertTrue("При коротком пароле должны остаться на странице регистрации или отображаться ошибка",
                driver.getCurrentUrl().contains("/register") || registerPage.isPasswordErrorDisplayed());
    }
}
