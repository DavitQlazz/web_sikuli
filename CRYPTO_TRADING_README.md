# Crypto Trading Application

This is a Selenium-based automation framework for crypto trading that can interact with cryptocurrency exchange websites.

## Overview

The crypto trading application follows the Page Object Model (POM) design pattern and provides automated interaction capabilities with cryptocurrency exchanges such as Binance, Coinbase, and Kraken.

## Components

### 1. CryptoHomePage (`src/main/java/pages/CryptoHomePage.java`)

The home page object provides methods to:
- Navigate to different crypto exchanges (Binance, Coinbase, Kraken)
- Search for cryptocurrencies
- View crypto prices (Bitcoin, Ethereum)
- Access market cap and volume information
- Navigate to trading pages
- Browse cryptocurrency lists
- View top gainers and losers

**Key Methods:**
- `openBinance()` - Navigate to Binance exchange
- `openCoinbase()` - Navigate to Coinbase exchange
- `openKraken()` - Navigate to Kraken exchange
- `searchCrypto(String cryptoName)` - Search for a specific cryptocurrency
- `getBitcoinPrice()` - Get current Bitcoin price
- `getEthereumPrice()` - Get current Ethereum price
- `getCryptoList()` - Get list of displayed cryptocurrencies
- `goToMarkets()` - Navigate to markets page

### 2. CryptoTradingPage (`src/main/java/pages/CryptoTradingPage.java`)

The trading page object provides methods to:
- Place buy and sell orders (both market and limit orders)
- Enter trading amounts and prices
- View current prices
- Check account balance
- View order book (bids and asks)
- View order history
- Select crypto trading pairs
- Confirm orders

**Key Methods:**
- `placeLimitBuyOrder(String amount, String price)` - Place a limit buy order
- `placeLimitSellOrder(String amount, String price)` - Place a limit sell order
- `placeMarketBuyOrder(String amount)` - Place a market buy order
- `placeMarketSellOrder(String amount)` - Place a market sell order
- `getCurrentPrice()` - Get current trading price
- `getBalance()` - Get account balance
- `getOrderBookBidsCount()` - Get number of buy orders in order book
- `getOrderBookAsksCount()` - Get number of sell orders in order book
- `getOrderHistory()` - Get list of order history entries

### 3. CryptoTradingTest (`src/test/java/tests/CryptoTradingTest.java`)

The test suite includes comprehensive test scenarios:
- Homepage navigation to different exchanges
- Price retrieval and display
- Cryptocurrency search functionality
- Trading page initialization
- Order book display
- Market cap and volume information
- Crypto list browsing

**Test Cases:**
1. `testCryptoHomePageBinance()` - Test navigation to Binance
2. `testCryptoHomePageCoinbase()` - Test navigation to Coinbase
3. `testCryptoHomePageKraken()` - Test navigation to Kraken
4. `testGetCryptoPrices()` - Test retrieving crypto prices
5. `testSearchCrypto()` - Test searching for cryptocurrencies
6. `testCryptoTradingPageInit()` - Test trading page initialization
7. `testOrderBookDisplay()` - Test order book display
8. `testMarketCapAndVolume()` - Test market cap and volume display
9. `testCryptoListDisplay()` - Test crypto list display
10. `testNavigateToMarkets()` - Test navigation to markets page

## Usage

### Prerequisites
- Java 16 or higher
- Maven 3.6+
- Chrome browser installed
- WebDriver (managed automatically by WebDriverManager)

### Building the Project

```bash
mvn clean compile
```

### Running Tests

To run all crypto trading tests:
```bash
mvn test -Dtest=CryptoTradingTest
```

To run a specific test:
```bash
mvn test -Dtest=CryptoTradingTest#testCryptoHomePageBinance
```

### Example Usage in Code

```java
// Create home page instance
CryptoHomePage homePage = new CryptoHomePage(driver);

// Navigate to Binance
homePage.openBinance();

// Search for Bitcoin
homePage.searchCrypto("BTC");

// Get Bitcoin price
String btcPrice = homePage.getBitcoinPrice();
System.out.println("Bitcoin Price: " + btcPrice);

// Create trading page instance
CryptoTradingPage tradingPage = new CryptoTradingPage(driver);

// Get current price
String currentPrice = tradingPage.getCurrentPrice();

// Place a limit buy order
tradingPage.placeLimitBuyOrder("0.001", "50000");

// Check order history
int orderCount = tradingPage.getOrderHistoryCount();
```

## Architecture

The application follows these design patterns:
- **Page Object Model (POM)**: Separates page structure from test logic
- **Inheritance**: All page objects extend `BasePage` for common functionality
- **Fluent Interface**: Methods return `this` for method chaining
- **Wait Strategies**: Explicit waits for element visibility and clickability

## Supported Exchanges

- **Binance** (https://www.binance.com)
- **Coinbase** (https://www.coinbase.com)
- **Kraken** (https://www.kraken.com)

Additional exchanges can be easily added by extending the `CryptoHomePage` class.

## Features

### Trading Features
- Market order placement
- Limit order placement
- Order book visualization
- Real-time price tracking
- Balance checking
- Order history tracking

### Market Data Features
- Cryptocurrency price monitoring
- Market cap tracking
- Trading volume analysis
- Top gainers/losers identification
- Price chart visualization
- Multiple crypto pair support

## Locator Strategy

The application uses flexible CSS selectors that work across multiple exchange platforms:
- Multiple selector fallbacks for reliability
- Data attributes and class-based selectors
- Responsive to different exchange UI structures

## Error Handling

The application includes robust error handling:
- Try-catch blocks for optional elements
- Graceful fallbacks when elements are not found
- Informative error messages
- Wait strategies to handle dynamic content

## Future Enhancements

Potential improvements:
1. Authentication and login functionality
2. Portfolio management features
3. Real-time WebSocket price updates
4. Advanced order types (stop-loss, OCO, etc.)
5. Trading bot capabilities
6. Performance analytics and reporting
7. Multi-account management
8. API integration for direct trading

## Notes

- This framework is designed for automation testing and educational purposes
- Always ensure compliance with exchange terms of service when using automation
- Be aware of rate limits and API restrictions on exchanges
- Consider using exchange-provided APIs for production trading applications
- Never commit API keys or sensitive credentials to version control

## Troubleshooting

### Common Issues

1. **WebDriver errors**: Ensure Chrome browser is installed and WebDriverManager can access the internet
2. **Element not found**: Website structures change; update locators as needed
3. **Timeout errors**: Increase wait times for slow-loading pages
4. **Session errors**: Ensure proper driver cleanup in tearDown methods

## Contributing

When adding new features:
1. Follow the existing POM structure
2. Add comprehensive test coverage
3. Update documentation
4. Ensure backward compatibility
5. Test with multiple exchanges

## License

This project follows the same license as the parent repository.
