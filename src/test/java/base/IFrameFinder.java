package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class IFrameFinder {
  private final WebDriver driver;

  public IFrameFinder(WebDriver driver) {
    this.driver = driver;
  }

  public List<WebElement> findElement(By by, int frameIndex) {
    if (driver.findElements(by).isEmpty()) {
      if (!driver.findElements(By.cssSelector("iframe")).isEmpty()) {
        driver.switchTo().frame(frameIndex);
        findElement(by, frameIndex);
      } else {
        driver.switchTo().defaultContent();
        findElement(by, ++frameIndex);
      }
    }
    return driver.findElements(by);
  }
}
