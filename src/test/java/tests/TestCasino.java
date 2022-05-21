package tests;

import base.SikuliFinder;
import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class TestCasino extends TestBase {

  SikuliFinder sikuli;

  @Test
  public void testName() {
    sikuli = new SikuliFinder(driver);
    driver.get("https://google.com/ncr");
    sikuli.findElement("more").click();
    slp();
    sikuli.clickOnImage("gmail");
    slp();
    slp();
    slp();
    slp();
  }

  private void slp() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
