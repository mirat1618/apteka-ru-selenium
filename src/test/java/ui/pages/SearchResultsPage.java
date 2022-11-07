package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.helper.StashMemory;
import ui.pages.common.Header;

import java.time.Duration;
import java.util.List;

/* Страница результатов поиска */
public class SearchResultsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private Actions actions;

    public By searchResultsTitleXPath = By.xpath("//h1[contains(@class, 'ViewSearch') and descendant::span[contains(@class, 'SearchResultTitle')]]]");
    public By searchResultsCardsXPath = By.xpath("//div[@class='cards-list']/div[@class='catalog-card card-flex']"); // Элементы результатов поиска - карточки товаров
    public By priceTagXPath = By.xpath(".//span[@class='moneyprice__roubles']"); // Цена в карточке товара
    public By addToCartButtonXPath = By.xpath(".//button[descendant::span[contains(text(), 'Купить')]]"); // Кнопка Купить в карточке товара

    public SearchResultsPage(WebDriver driver, Duration duration) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, duration);
        this.actions = new Actions(driver);
    }

    public void userSeesNumberOfSearchResults(Integer expectedNumberOfResults) { // Проверка количества выданного результата поиска
        if(expectedNumberOfResults != 0)
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(searchResultsCardsXPath));

        int actualNumberOfResults = driver.findElements(searchResultsCardsXPath).size();

        /* либо assert, либо выбрасывание RuntimeException, если не совпадает количество */
       //assertTrue(expectedNumberOfResults == actualNumberOfResults);

        if(actualNumberOfResults != expectedNumberOfResults)
            throw new RuntimeException(actualNumberOfResults + " were found instead of " + expectedNumberOfResults);
    }

    /* Добавление первых n-количества товаров в корзину */
    public void userAddsFirstNumberOfElementsToCardAndSumsPrices(Integer firstNumberOfResultsToBeAdded) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(searchResultsCardsXPath));
        List<WebElement> cards = driver.findElements(searchResultsCardsXPath); // Получение карточек товаров

        Double totalPrice = cards.stream() // Подсчет итоговой суммы
                .limit(firstNumberOfResultsToBeAdded)
                .mapToDouble(card -> Double.valueOf(card.findElement(priceTagXPath).getText()))
                .sum();

        /* Прохождение по n-первым товарам в списке и нажатие на кнопку Купить в карточке товара
        Если проходится сразу, получается быстро, javascript-обработчик не успевает добавить все товары в корзину и пропускает их
        Поэтому можно либо (1) просто ждать 1-2 секунды после каждого нажатия на кнопку Купить
        
          TimeUnit.SECONDS.sleep(10);
          
            (или через wait)
            
        (2) Либо более сложный подход -- создаем кастомный ExpectedCondition и проверяем, что счетчик в Корзине увеличился после нажатия на кнопку Купить
        Если условие выполнилось, нажимаем на кнопку Купить в карточке следующего товара
         */
        for(int i = 0; i < firstNumberOfResultsToBeAdded; i++) { // Проходим по n-первым товарам
            cards.get(i).findElement(addToCartButtonXPath).click(); // Нажимаем на добавить
            int finalI = i; // Создаем копию i (в лямбде i не может использоваться -- должна быть final или effectively final
            wait.until(new ExpectedCondition<Boolean>() { // Создаем кастомный Expected Condition
                public Boolean apply(WebDriver driver) {
                    WebElement button = driver.findElement(Header.cartCounterXPath); // Находим счетчик на иконке Корзины
                    String count = button.getAttribute("data-count"); // Получаем значение счетчика на иконке Корзины
                    if(count.equals(String.valueOf(finalI + 1))) // Если значение атрибута data-count равно i
                        return true; // тогда счетчик увеличился, можно продолжать
                    else
                        return false; // иначе продолжим ожидать
                }
            });
        }

        // После прохождения по всем товарам, записываем подсчитанное итоговую сумму
        StashMemory.setCurrentCardTotalPrice(totalPrice);
    }
}
