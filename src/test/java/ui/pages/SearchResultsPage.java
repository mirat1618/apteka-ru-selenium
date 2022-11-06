package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.helper.Memory;
import ui.pages.common.Header;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchResultsPage {
    private WebDriver driver;
    private WebDriverWait wait;


    private Actions actions;

    public By searchResultsTitleXPath = By.xpath("//h1[contains(@class, 'ViewSearch') and descendant::span[contains(@class, 'SearchResultTitle')]]]");
    public By searchResultsCardsXPath = By.xpath("//div[@class='cards-list']/div[@class='catalog-card card-flex']");
    public By priceTagXPath = By.xpath(".//span[@class='moneyprice__roubles']");
    public By addToCartButtonXPath = By.xpath(".//button[descendant::span[contains(text(), 'Купить')]]");


    public SearchResultsPage(WebDriver driver, Duration duration) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, duration);
        this.actions = new Actions(driver);
    }

    public void userSeesNumberOfSearchResults(Integer expectedNumberOfResults) {

        if(expectedNumberOfResults != 0)
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(searchResultsCardsXPath));

        int actualNumberOfResults = driver.findElements(searchResultsCardsXPath).size();

//        assertTrue(expectedNumberOfResults == actualNumberOfResults);

        if(actualNumberOfResults != expectedNumberOfResults)
            throw new RuntimeException(actualNumberOfResults + " were found instead of " + expectedNumberOfResults);
    }

    public void userAddsFirstNumberOfElementsToCardAndSumsPrices(Integer firstNumberOfResultsToBeAdded) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(searchResultsCardsXPath));
        List<WebElement> cards = driver.findElements(searchResultsCardsXPath);

        Double totalPrice = cards.stream()
                .limit(firstNumberOfResultsToBeAdded)
                .mapToDouble(card -> Double.valueOf(card.findElement(priceTagXPath).getText()))
                .sum();

        for(int i = 0; i < firstNumberOfResultsToBeAdded; i++) {
            cards.get(i).findElement(addToCartButtonXPath).click();
            int finalI = i;
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    WebElement button = driver.findElement(Header.cartCounterXPath);
                    String count = button.getAttribute("data-count");
                    if(count.equals(String.valueOf(finalI + 1)))
                        return true;
                    else
                        return false;
                }
            });
        }

        /* Можно просто фризить после каждого добавления
        TimeUnit.SECONDS.sleep(10);

         */
        Memory.setCurrentCardTotalPrice(totalPrice);
    }
}
