package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class CryptoHomePage extends BasePage {

    // Locators for crypto exchange homepage
    By loginButton = By.cssSelector("a[href*='login'], .login-button, button[class*='login']");
    By signupButton = By.cssSelector("a[href*='signup'], .signup-button, button[class*='register']");
    By tradeButton = By.cssSelector("a[href*='trade'], .trade-button, button[class*='trade']");
    By marketsLink = By.cssSelector("a[href*='markets'], .markets-link");
    By cryptoList = By.cssSelector(".crypto-list .crypto-item, [class*='coin-row']");
    By searchBox = By.cssSelector("input[placeholder*='Search'], input[type='search']");
    By priceChart = By.cssSelector(".price-chart, [class*='chart-container']");
    By topGainers = By.cssSelector(".top-gainers .coin, [class*='gainer']");
    By topLosers = By.cssSelector(".top-losers .coin, [class*='loser']");
    By marketCapDisplay = By.cssSelector(".market-cap, [class*='marketcap']");
    By volumeDisplay = By.cssSelector(".volume, [class*='volume']");
    By btcPrice = By.cssSelector("[data-symbol='BTC'] .price, .btc-price");
    By ethPrice = By.cssSelector("[data-symbol='ETH'] .price, .eth-price");

    public CryptoHomePage(WebDriver driver) {
        super(driver);
    }

    public CryptoHomePage open(String url) {
        driver.get(url);
        return this;
    }

    public CryptoHomePage openBinance() {
        driver.get("https://www.binance.com");
        return this;
    }

    public CryptoHomePage openCoinbase() {
        driver.get("https://www.coinbase.com");
        return this;
    }

    public CryptoHomePage openKraken() {
        driver.get("https://www.kraken.com");
        return this;
    }

    public CryptoHomePage clickLogin() {
        click(loginButton);
        return this;
    }

    public CryptoHomePage clickSignup() {
        click(signupButton);
        return this;
    }

    public CryptoHomePage clickTrade() {
        click(tradeButton);
        return this;
    }

    public CryptoHomePage goToMarkets() {
        click(marketsLink);
        return this;
    }

    public CryptoHomePage searchCrypto(String cryptoName) {
        WebElement searchField = wait.until(visibilityOfElementLocated(searchBox));
        searchField.clear();
        searchField.sendKeys(cryptoName);
        return this;
    }

    public List<WebElement> getCryptoList() {
        return findAllDisplayedElements(cryptoList);
    }

    public int getCryptoListCount() {
        return getCryptoList().size();
    }

    public String getBitcoinPrice() {
        try {
            WebElement btcPriceElement = wait.until(visibilityOfElementLocated(btcPrice));
            return btcPriceElement.getText();
        } catch (Exception e) {
            return "N/A";
        }
    }

    public String getEthereumPrice() {
        try {
            WebElement ethPriceElement = wait.until(visibilityOfElementLocated(ethPrice));
            return ethPriceElement.getText();
        } catch (Exception e) {
            return "N/A";
        }
    }

    public List<WebElement> getTopGainers() {
        return findAllDisplayedElements(topGainers);
    }

    public List<WebElement> getTopLosers() {
        return findAllDisplayedElements(topLosers);
    }

    public boolean isPriceChartDisplayed() {
        try {
            WebElement chart = wait.until(visibilityOfElementLocated(priceChart));
            return chart.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getMarketCap() {
        try {
            WebElement marketCap = wait.until(visibilityOfElementLocated(marketCapDisplay));
            return marketCap.getText();
        } catch (Exception e) {
            return "N/A";
        }
    }

    public String getVolume() {
        try {
            WebElement volume = wait.until(visibilityOfElementLocated(volumeDisplay));
            return volume.getText();
        } catch (Exception e) {
            return "N/A";
        }
    }

    public CryptoHomePage selectCryptoFromList(int index) {
        List<WebElement> cryptos = getCryptoList();
        if (index < cryptos.size()) {
            cryptos.get(index).click();
        }
        return this;
    }

    public boolean isHomePageLoaded() {
        try {
            // Check if any of the key elements are visible
            return driver.findElements(loginButton).size() > 0 || 
                   driver.findElements(tradeButton).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
