package com.stellarburgers.tests;

import com.stellarburgers.BaseTest;
import com.stellarburgers.Constants;
import com.stellarburgers.pages.MainPage;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {
    
    @Test
    public void switchToBunsSection() {
        driver.get(Constants.BASE_URL);
        MainPage mainPage = new MainPage(driver);
        
        mainPage.clickBunsSection();
        
        // Используем WebDriverWait вместо Thread.sleep()
        wait.until(driver -> mainPage.isBunsSectionActive());
        assertTrue("Раздел 'Булки' должен быть активным", 
                   mainPage.isBunsSectionActive());
    }
    
    @Test
    public void switchToSaucesSection() {
        driver.get(Constants.BASE_URL);
        MainPage mainPage = new MainPage(driver);
        
        mainPage.clickSaucesSection();
        
        wait.until(driver -> mainPage.isSaucesSectionActive());
        assertTrue("Раздел 'Соусы' должен быть активным", 
                   mainPage.isSaucesSectionActive());
    }
    
    @Test
    public void switchToFillingsSection() {
        driver.get(Constants.BASE_URL);
        MainPage mainPage = new MainPage(driver);
        
        mainPage.clickFillingsSection();
        
        wait.until(driver -> mainPage.isFillingsSectionActive());
        assertTrue("Раздел 'Начинки' должен быть активным", 
                   mainPage.isFillingsSectionActive());
    }
}
