package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class TestBase {
  protected static final ThreadLocal<WebDriver> driver =
      ThreadLocal.withInitial(TestBase::createDriver);

  private static WebDriver createDriver() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setHeadless(false);
    return new ChromeDriver(chromeOptions);
  }

  @BeforeMethod
  public void setUp() {
    driver.get().manage().window().maximize();
  }

  @BeforeSuite
  public void beforeSuite() {
    WebDriverManager.chromedriver().setup();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    driver.get().quit();
    driver.remove();
  }
}
