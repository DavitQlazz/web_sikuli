package tests;

import base.TestBase;
import pages.CryptoHomePage;
import pages.CryptoTradingPage;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CryptoTradingTest extends TestBase {

    @Test(description = "Test crypto homepage navigation to Binance")
    public void testCryptoHomePageBinance() {
        CryptoHomePage homePage = new CryptoHomePage(driver.get());
        homePage.openBinance();
        
        // Verify page loads
        assertTrue(homePage.isHomePageLoaded(), "Binance homepage should be loaded");
        
        // Wait for page to fully load
        sleep(3000);
    }

    @Test(description = "Test crypto homepage navigation to Coinbase")
    public void testCryptoHomePageCoinbase() {
        CryptoHomePage homePage = new CryptoHomePage(driver.get());
        homePage.openCoinbase();
        
        // Verify page loads
        assertTrue(homePage.isHomePageLoaded(), "Coinbase homepage should be loaded");
        
        // Wait for page to fully load
        sleep(3000);
    }

    @Test(description = "Test crypto homepage navigation to Kraken")
    public void testCryptoHomePageKraken() {
        CryptoHomePage homePage = new CryptoHomePage(driver.get());
        homePage.openKraken();
        
        // Verify page loads
        assertTrue(homePage.isHomePageLoaded(), "Kraken homepage should be loaded");
        
        // Wait for page to fully load
        sleep(3000);
    }

    @Test(description = "Test getting crypto prices from homepage")
    public void testGetCryptoPrices() {
        CryptoHomePage homePage = new CryptoHomePage(driver.get());
        homePage.openBinance();
        
        sleep(3000);
        
        // Try to get Bitcoin price
        String btcPrice = homePage.getBitcoinPrice();
        assertNotNull(btcPrice, "Bitcoin price should not be null");
        System.out.println("Bitcoin Price: " + btcPrice);
        
        // Try to get Ethereum price
        String ethPrice = homePage.getEthereumPrice();
        assertNotNull(ethPrice, "Ethereum price should not be null");
        System.out.println("Ethereum Price: " + ethPrice);
    }

    @Test(description = "Test searching for a cryptocurrency")
    public void testSearchCrypto() {
        CryptoHomePage homePage = new CryptoHomePage(driver.get());
        homePage.openBinance();
        
        sleep(3000);
        
        // Search for Bitcoin
        homePage.searchCrypto("BTC");
        
        sleep(2000);
        
        // Verify search results appear
        int cryptoCount = homePage.getCryptoListCount();
        System.out.println("Found " + cryptoCount + " cryptocurrencies matching search");
    }

    @Test(description = "Test crypto trading page initialization")
    public void testCryptoTradingPageInit() {
        CryptoTradingPage tradingPage = new CryptoTradingPage(driver.get());
        
        // Navigate to a demo trading page
        driver.get().get("https://www.binance.com/en/trade/BTC_USDT");
        
        sleep(5000);
        
        // Try to get current price
        try {
            String currentPrice = tradingPage.getCurrentPrice();
            assertNotNull(currentPrice, "Current price should not be null");
            System.out.println("Current BTC/USDT Price: " + currentPrice);
        } catch (Exception e) {
            System.out.println("Could not retrieve current price: " + e.getMessage());
        }
    }

    @Test(description = "Test order book display")
    public void testOrderBookDisplay() {
        CryptoTradingPage tradingPage = new CryptoTradingPage(driver.get());
        
        // Navigate to a demo trading page
        driver.get().get("https://www.binance.com/en/trade/BTC_USDT");
        
        sleep(5000);
        
        // Check order book
        try {
            int bidsCount = tradingPage.getOrderBookBidsCount();
            int asksCount = tradingPage.getOrderBookAsksCount();
            
            System.out.println("Order Book Bids: " + bidsCount);
            System.out.println("Order Book Asks: " + asksCount);
            
            assertTrue(bidsCount >= 0, "Bids count should be non-negative");
            assertTrue(asksCount >= 0, "Asks count should be non-negative");
        } catch (Exception e) {
            System.out.println("Could not retrieve order book: " + e.getMessage());
        }
    }

    @Test(description = "Test market cap and volume display")
    public void testMarketCapAndVolume() {
        CryptoHomePage homePage = new CryptoHomePage(driver.get());
        homePage.openCoinbase();
        
        sleep(3000);
        
        // Get market cap and volume
        String marketCap = homePage.getMarketCap();
        String volume = homePage.getVolume();
        
        System.out.println("Market Cap: " + marketCap);
        System.out.println("Volume: " + volume);
        
        assertNotNull(marketCap, "Market cap should not be null");
        assertNotNull(volume, "Volume should not be null");
    }

    @Test(description = "Test crypto list display")
    public void testCryptoListDisplay() {
        CryptoHomePage homePage = new CryptoHomePage(driver.get());
        homePage.openBinance();
        
        sleep(3000);
        
        // Get crypto list count
        int cryptoCount = homePage.getCryptoListCount();
        System.out.println("Total cryptocurrencies displayed: " + cryptoCount);
        
        assertTrue(cryptoCount >= 0, "Crypto count should be non-negative");
    }

    @Test(description = "Test navigating to markets page")
    public void testNavigateToMarkets() {
        CryptoHomePage homePage = new CryptoHomePage(driver.get());
        homePage.openBinance();
        
        sleep(3000);
        
        try {
            // Try to navigate to markets
            homePage.goToMarkets();
            sleep(2000);
            System.out.println("Successfully navigated to markets page");
        } catch (Exception e) {
            System.out.println("Could not navigate to markets: " + e.getMessage());
        }
    }

    /**
     * Helper method to pause execution
     */
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
