package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    /* Locators */
    public By totalPriceRoublesXPath = By.xpath("//div[@class='OrderSummaryPanel__total']//span[@class='moneyprice__content']/span[@class='moneyprice__roubles']");
    public By totalPricePenniesXPath = By.xpath("//div[@class='OrderSummaryPanel__total']//span[@class='moneyprice__content']/span[@class='moneyprice__pennies']");

    public CartPage(WebDriver driver, Duration duration) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, duration);
    }

    public Double getTotalPrice() {
        String totalPriceRoubles = wait.until(ExpectedConditions.presenceOfElementLocated(totalPriceRoublesXPath)).getText();
        String totalPricePennies = wait.until(ExpectedConditions.presenceOfElementLocated(totalPricePenniesXPath)).getText();

        return Double.valueOf(totalPriceRoubles + totalPricePennies);
    }
}
