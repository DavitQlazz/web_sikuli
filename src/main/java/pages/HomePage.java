package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class HomePage extends BasePage {

    By sale = By.cssSelector(".product-container .right-block .price-percent-reduction");
    By addToCard = By.cssSelector(".hovered .button");
    By continueBtn = By.cssSelector(".continue.btn");
    By checkoutBtn = By.cssSelector("[title='Proceed to checkout']");
    By cartItem = By.cssSelector(".cart_item");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        driver.get("https://automationpractice.com");
        return this;
    }

    public HomePage addAllSaleItemsToCard() {
        List<WebElement> elements = findAllDisplayedElements(sale);

        for (int i = 0; i < elements.size(); i++) {
            addToCard(elements.get(i));
            if (i == elements.size() - 1) {
                proceedCheckout();
                break;
            }
            continueShopping();
        }
        return this;
    }

    public HomePage addToCard(WebElement item) {
        Actions actions = new Actions(driver);
        actions
                .moveToElement(item)
                .perform();
        click(addToCard);
        return this;
    }

    public void continueShopping() {
        click(continueBtn);
    }

    public void proceedCheckout() {
        click(checkoutBtn);
    }

    public void checkItemsCount(int count) {
        assertEquals(count, findAllDisplayedElements(cartItem).size());
    }

    public Integer getSaleItemsCount() {
        return findAllDisplayedElements(sale).size();
    }

}
