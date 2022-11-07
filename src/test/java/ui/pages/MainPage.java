package ui.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/* Главная страница */
public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public MainPage(WebDriver driver, Duration duration) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, duration);
    }

    public void openMainPage() { // Переход на веб-сайт
        driver.get("https://apteka.ru");
    }
}
