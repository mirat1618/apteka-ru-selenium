package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverInstaller {
    private static ChromeOptions chromeOptions = new ChromeOptions();

    static {
        WebDriverManager wdm = WebDriverManager.chromedriver();
        wdm.setup();

        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("--log-level=3");
        chromeOptions.addArguments("--window-size=1920,1080");

        if(System.getProperty("headless") != null && System.getProperty("headless").equalsIgnoreCase("true"))
           chromeOptions.addArguments("--headless");
    }

    public static ChromeDriver getChromeDriver() {
        ChromeDriver driver = new ChromeDriver(chromeOptions);

        return driver;
    }
}
