package ui.steps;

import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ui.WebDriverInstaller;
import ui.helper.Memory;
import ui.pages.CartPage;
import ui.pages.MainPage;
import ui.pages.SearchResultsPage;
import ui.pages.common.Header;
import ui.pages.common.Modals;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class StepsDefinitions {
    public WebDriver driver;

    public MainPage mainPage;
    public CartPage cartPage;
    public SearchResultsPage searchResultsPage;
    public Header header;
    public Modals modals;

    @Before
    public void setUp() {
        this.driver = WebDriverInstaller.getChromeDriver();

        this.mainPage = new MainPage(driver, Duration.ofSeconds(15));
        this.cartPage = new CartPage(driver, Duration.ofSeconds(15));
        this.searchResultsPage = new SearchResultsPage(driver, Duration.ofSeconds(15));
        this.header = new Header(driver, Duration.ofSeconds(15));
        this.modals = new Modals(driver, Duration.ofSeconds(15));
    }

    @After
    public void tearDown(Scenario scenario) {
        if(scenario.isFailed()) {
            byte[] data = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(data, "image/png", scenario.getName());
        }
        driver.quit();
    }

    @Given("Пользователь находится на главной странице")
    public void user_is_on_main_page() {
        mainPage.openMainPage();
        modals.clickSelectCityModalCloseButton();
        modals.clickAcceptCookiesButton();
    }

    @When("Пользователь вводит {string} в поле поиска и нажимает Enter")
    public void user_enters_search_query_and_hits_enter(String string1) {
        header.typeSearchQueryAndHitEnter(string1);
    }

    @Then("Пользователь видит {int} элементов результатов")
    public void user_sees_number_of_search_results(Integer int1) {
        searchResultsPage.userSeesNumberOfSearchResults(int1);
    }

    @And("Пользователь добавляет первые {int} элементов в корзину и суммирует цены добавленных товаров")
    public void user_adds_first_number_of_elements_to_card_and_sums_prices(Integer int1) {
        searchResultsPage.userAddsFirstNumberOfElementsToCardAndSumsPrices(int1);

    }

    @And("Пользователь переходит в корзину")
    public void user_opens_cart() {
        header.clickCartButton();
    }

    @And("Значение поля Итого в корзине равно суммированной цене")
    public void total_price_matches_total_summed_price() {
        assertTrue(Memory.getCurrentCardTotalPrice().equals(cartPage.getTotalPrice()));
    }
}
