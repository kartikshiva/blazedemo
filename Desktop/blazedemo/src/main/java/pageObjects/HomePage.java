package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHomePage(String url) {
        driver.get(url);
    }

    public String getTitle() {
        return driver.findElement( By.xpath("//div[2]/div/h1")).getText();
    }

    public void clickDestinationOfTheWeek() {
        driver.findElement(By.linkText("destination of the week! The Beach!")).click();
    }
}
