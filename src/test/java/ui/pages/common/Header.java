package ui.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    /* Locators */
    public static By searchFieldXPath = By.xpath("//input[@type='search' and @name='q' and @id='apteka-search']");
    public static By cartButtonXPath = By.xpath("//div[@class='ButtonIcon' and descendant::a[@class='ButtonIcon__link']]");
    public static By cartCounterXPath = By.xpath("//div[@class='SiteHeaderMain__cart']//span[contains(@class, 'ButtonIcon__counter')]");

    public Header(WebDriver driver, Duration duration) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, duration);
        this.actions = new Actions(driver);
    }

    public void typeSearchQueryAndHitEnter(String searchQuery) {
        wait.until(ExpectedConditions.elementToBeClickable(searchFieldXPath));
        WebElement searchField = driver.findElement(searchFieldXPath);
        searchField.sendKeys(searchQuery);
        searchField.sendKeys(Keys.ENTER);
    }

    public void clickCartButton() {
        wait.until(ExpectedConditions.elementToBeClickable(cartButtonXPath)).click();
    }

}
