package com.example.demo.blazedemo;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.PurchaseConfirmationPage;
import pageObjects.PurchasePage;

public class AutomationTest {
    private WebDriver driver;
    private HomePage homePage;
    private PurchasePage purchasePage;
    private PurchaseConfirmationPage pcp;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Shreys Laptop\\Desktop\\blazedemo\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        purchasePage = new PurchasePage(driver);
        pcp= new PurchaseConfirmationPage(driver);
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    }

    @DataProvider(name = "cityData")
    public Object[][] cityData() {
        return new Object[][] { { "Mexico City", "London" } };
    }

    @Test(priority = 1)
    public void testHomePageTitle() {
        homePage.navigateToHomePage("https://blazedemo.com/index.php");
        Assert.assertEquals(homePage.getTitle(), "Welcome to the Simple Travel Agency!");
    }

    @Test(priority = 2, dependsOnMethods = "testHomePageTitle")
    public void testDestinationOfTheWeek() {
        homePage.clickDestinationOfTheWeek();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("vacation"));
        driver.navigate().back();
    }

    @Test(dataProvider = "cityData", priority = 3)
    public void testPurchaseTicket(String departureCity, String destinationCity) {
        homePage.navigateToHomePage("https://blazedemo.com/index.php");
        purchasePage.selectDepartureAndDestinationCities(departureCity, destinationCity);
        purchasePage.findFlights();
        purchasePage.chooseFlight();
        Assert.assertTrue(driver.getCurrentUrl().contains("purchase"));
        System.out.println(purchasePage.getTotalCost());
        Assert.assertTrue(purchasePage.getTotalCost().matches("\\d+\\.\\d{2}")); // Validates xxx.xx format
        purchasePage.purchaseFlight();
    }
    
    @Test(priority = 4)
    public void testGetID() {
        System.out.println("ID: "+ pcp.getID());
    }

    
    
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
