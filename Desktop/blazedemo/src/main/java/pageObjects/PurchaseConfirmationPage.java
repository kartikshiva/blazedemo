package pageObjects;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class PurchaseConfirmationPage {
    private WebDriver driver;

    public PurchaseConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public String getID() {
        try {
            WebElement element = driver.findElement(By.xpath("/html/body/div[2]/div/table/tbody/tr[1]/td[2]"));
            String id = element.getText();
            return id;
        } catch (NoSuchElementException e) {
            System.err.println("Element not found: " + e.getMessage());
            return null; 
        }
    }
    
}