package ui.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/* Модальные окна */
public class Modals {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    /* Locators */
    public By selectCityModalCloseButtonXPath = By.xpath("//button[@class='overlay-close']"); // Кнопка "Выбрать" в окне выбора города
    public By acceptCookiesButtonXPath = By.xpath("//div[@class='CookieNotify']//button[contains(text(), 'Согласен')]"); // Кнопка принятия cookies

    public Modals(WebDriver driver, Duration duration) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, duration);
        this.actions = new Actions(driver);
    }

    public void clickSelectCityModalCloseButton() { // Нажатие на кнопку "Выбрать" в окне выбора города
        wait.until(ExpectedConditions.elementToBeClickable(selectCityModalCloseButtonXPath)).click();
    }

    public void clickAcceptCookiesButton() { // Нажатие на кнопку принятия cookies
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButtonXPath)).click();
    }
}
