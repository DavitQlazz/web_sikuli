package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class CryptoTradingPage extends BasePage {

    // Locators for crypto trading elements
    By buyButton = By.cssSelector("[data-action='buy'], .buy-button, button[class*='buy']");
    By sellButton = By.cssSelector("[data-action='sell'], .sell-button, button[class*='sell']");
    By amountInput = By.cssSelector("input[name='amount'], input[placeholder*='amount'], input[id*='amount']");
    By priceInput = By.cssSelector("input[name='price'], input[placeholder*='price'], input[id*='price']");
    By orderBookBids = By.cssSelector(".order-book .bids tr, [class*='bid'] tr");
    By orderBookAsks = By.cssSelector(".order-book .asks tr, [class*='ask'] tr");
    By cryptoPairSelector = By.cssSelector("select[name='pair'], .pair-selector, [class*='trading-pair']");
    By currentPrice = By.cssSelector(".current-price, [class*='price-ticker'], .ticker-price");
    By balanceDisplay = By.cssSelector(".balance, [class*='wallet-balance'], .account-balance");
    By orderHistoryRows = By.cssSelector(".order-history tbody tr, [class*='order-row']");
    By confirmOrderButton = By.cssSelector("button[type='submit'], .confirm-order, [class*='confirm']");
    By marketTab = By.cssSelector("[data-tab='market'], .market-tab");
    By limitTab = By.cssSelector("[data-tab='limit'], .limit-tab");

    public CryptoTradingPage(WebDriver driver) {
        super(driver);
    }

    public CryptoTradingPage selectCryptoPair(String pair) {
        WebElement pairElement = wait.until(visibilityOfElementLocated(cryptoPairSelector));
        Select select = new Select(pairElement);
        select.selectByVisibleText(pair);
        return this;
    }

    public CryptoTradingPage enterAmount(String amount) {
        WebElement amountField = wait.until(visibilityOfElementLocated(amountInput));
        amountField.clear();
        amountField.sendKeys(amount);
        return this;
    }

    public CryptoTradingPage enterPrice(String price) {
        WebElement priceField = wait.until(visibilityOfElementLocated(priceInput));
        priceField.clear();
        priceField.sendKeys(price);
        return this;
    }

    public CryptoTradingPage clickBuyButton() {
        click(buyButton);
        return this;
    }

    public CryptoTradingPage clickSellButton() {
        click(sellButton);
        return this;
    }

    public CryptoTradingPage confirmOrder() {
        click(confirmOrderButton);
        return this;
    }

    public CryptoTradingPage selectMarketOrder() {
        click(marketTab);
        return this;
    }

    public CryptoTradingPage selectLimitOrder() {
        click(limitTab);
        return this;
    }

    public String getCurrentPrice() {
        WebElement priceElement = wait.until(visibilityOfElementLocated(currentPrice));
        return priceElement.getText();
    }

    public String getBalance() {
        WebElement balanceElement = wait.until(visibilityOfElementLocated(balanceDisplay));
        return balanceElement.getText();
    }

    public int getOrderBookBidsCount() {
        List<WebElement> bids = findAllDisplayedElements(orderBookBids);
        return bids.size();
    }

    public int getOrderBookAsksCount() {
        List<WebElement> asks = findAllDisplayedElements(orderBookAsks);
        return asks.size();
    }

    public List<WebElement> getOrderHistory() {
        return findAllDisplayedElements(orderHistoryRows);
    }

    public int getOrderHistoryCount() {
        return getOrderHistory().size();
    }

    public boolean isOrderPlaced() {
        try {
            // Check if order history has new entries
            return getOrderHistoryCount() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public CryptoTradingPage placeLimitBuyOrder(String amount, String price) {
        selectLimitOrder();
        enterAmount(amount);
        enterPrice(price);
        clickBuyButton();
        return this;
    }

    public CryptoTradingPage placeLimitSellOrder(String amount, String price) {
        selectLimitOrder();
        enterAmount(amount);
        enterPrice(price);
        clickSellButton();
        return this;
    }

    public CryptoTradingPage placeMarketBuyOrder(String amount) {
        selectMarketOrder();
        enterAmount(amount);
        clickBuyButton();
        return this;
    }

    public CryptoTradingPage placeMarketSellOrder(String amount) {
        selectMarketOrder();
        enterAmount(amount);
        clickSellButton();
        return this;
    }
}
