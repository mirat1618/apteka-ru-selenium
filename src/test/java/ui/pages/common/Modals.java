package ui.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Modals {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    /* Locators */
    public By selectCityModalCloseButtonXPath = By.xpath("//button[@class='overlay-close']");
    public By acceptCookiesButtonXPath = By.xpath("//div[@class='CookieNotify']//button[contains(text(), 'Согласен')]");

    public Modals(WebDriver driver, Duration duration) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, duration);
        this.actions = new Actions(driver);
    }

    public void clickSelectCityModalCloseButton() {
        wait.until(ExpectedConditions.elementToBeClickable(selectCityModalCloseButtonXPath)).click();
    }

    public void clickAcceptCookiesButton() {
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButtonXPath)).click();
    }
}
