package tests;

import base.SikuliFinder;
import base.TestBase;
import org.testng.annotations.Test;

public class TestCasino extends TestBase {

  SikuliFinder sikuli;

  @Test
  public void testName() {
    sikuli = new SikuliFinder(driver.get());
    driver.get().get("https://google.com/ncr");
    slp();
    sikuli.findElement("more").click();
    slp();
    slp();
    sikuli.clickOnImage("gmail");
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
