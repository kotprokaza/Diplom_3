package com.stellarburgers.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class UserClient {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api";
    
    public static Map<String, String> createRandomUser() {
        RestAssured.baseURI = BASE_URL;
        
        String timestamp = String.valueOf(System.currentTimeMillis());
        String email = "test" + timestamp + "@example.com";
        String password = "Password123!";
        String name = "TestUser" + timestamp;
        
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("email", email);
        requestBody.put("password", password);
        requestBody.put("name", name);
        
        try {
            Response response = given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .when()
                .post("/auth/register");
            
            Map<String, String> userData = new HashMap<>();
            userData.put("email", email);
            userData.put("password", password);
            userData.put("name", name);
            
            if (response.statusCode() == 200) {
                String accessToken = response.path("accessToken");
                userData.put("accessToken", accessToken != null ? accessToken.replace("Bearer ", "") : "");
            } else {
                userData.put("accessToken", "");
            }
            
            return userData;
        } catch (Exception e) {
            System.out.println("Ошибка создания пользователя: " + e.getMessage());
            Map<String, String> mockData = new HashMap<>();
            mockData.put("email", email);
            mockData.put("password", password);
            mockData.put("name", name);
            mockData.put("accessToken", "");
            return mockData;
        }
    }
    
    public static void deleteUser(String accessToken) {
        if (accessToken == null || accessToken.isEmpty()) {
            return;
        }
        
        try {
            RestAssured.baseURI = BASE_URL;
            given()
                .header("Authorization", accessToken)
                .when()
                .delete("/auth/user")
                .then()
                .statusCode(202);
        } catch (Exception e) {
            System.out.println("Ошибка удаления пользователя: " + e.getMessage());
        }
    }
}
