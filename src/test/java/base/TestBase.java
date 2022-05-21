package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class TestBase {

  protected WebDriver driver;

  @BeforeSuite
  public void beforeSuite() {
    WebDriverManager.chromedriver().setup();
  }

  @BeforeMethod
  public void setUp() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setHeadless(false);
    driver = new ChromeDriver(chromeOptions);
    driver.manage().window().maximize();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    driver.quit();
  }
}
