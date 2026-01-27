// Диплом 3: UI тесты Stellar Burgers - 27.01.2026
package com.stellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BaseTest {
    
    protected WebDriver driver;
    protected final String BASE_URL = "https://stellarburgers.education-services.ru";
    
    @Rule
    public TestRule screenshotRule = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            // Можно добавить скриншоты при падении теста
            System.out.println("Тест упал: " + description.getMethodName());
        }
    };
    
    @Before
    @Step("Инициализация драйвера и настройка браузера")
    public void setUp() {
        // Можно переключать между браузерами через системную переменную
        String browser = System.getProperty("browser", "chrome");
        
        if (browser.equalsIgnoreCase("yandex")) {
            setupYandexBrowser();
        } else {
            setupChrome();
        }
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }
    
    @Step("Настройка Chrome браузера")
    private void setupChrome() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }
    
    @Step("Настройка Яндекс.Браузера")
    private void setupYandexBrowser() {
        // Для Яндекс.Браузера используем стандартный ChromeDriver
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        // Пути для Яндекс.Браузера
        // macOS
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        // Windows: "C:\\Users\\username\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe"
        // Linux: "/usr/bin/yandex-browser"
        
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        
        driver = new ChromeDriver(options);
    }
    
    @After
    @Step("Закрытие браузера")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    // Вспомогательные методы
    protected void openMainPage() {
        driver.get(BASE_URL);
    }
    
    protected void waitForPageLoad() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
// Диплом 3: UI тесты Stellar Burgers - 27.01.2026
