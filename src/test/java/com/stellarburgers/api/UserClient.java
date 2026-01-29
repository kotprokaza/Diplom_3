package com.stellarburgers.api;

import java.util.HashMap;
import java.util.Map;

public class UserClient {
    
    public static Map<String, String> createRandomUser() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String email = "test" + timestamp + "@example.com";
        String password = "Password123!";
        String name = "TestUser" + timestamp;
        
        Map<String, String> userData = new HashMap<>();
        userData.put("email", email);
        userData.put("password", password);
        userData.put("name", name);
        
        // В реальном проекте здесь будет вызов API
        // Для примера используем мок
        System.out.println("Создан тестовый пользователь: " + email);
        
        return userData;
    }
    
    public static void deleteUser(String accessToken) {
        // В реальном проекте здесь будет удаление через API
        System.out.println("Пользователь удалён (мок)");
    }
}
