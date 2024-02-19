package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PurchasePage {
    private WebDriver driver;

    public PurchasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectDepartureAndDestinationCities(String departureCity, String destinationCity) {
        driver.findElement(By.name("fromPort")).sendKeys(departureCity);
        driver.findElement(By.name("toPort")).sendKeys(destinationCity);
    }

    public void findFlights() {
        driver.findElement(By.xpath("/html/body/div[3]/form/div/input")).click();
    }

    public void chooseFlight() {
        driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[3]/td[1]/input")).click();
    }

    public String getTotalCost() {
        return driver.findElement(By.xpath("/html/body/div[2]/p[5]/em")).getText();
    }

    public void purchaseFlight() {
        driver.findElement(By.xpath("/html/body/div[2]/form/div[11]/div/input")).click();
    }
}
