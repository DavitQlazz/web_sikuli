package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    //There are some elements on the screen that takes long to load, we need to have some wait before acting with them.
    protected void click(By by) {
        wait.until(elementToBeClickable(by)).click();
    }

    //There can be situations where not all items found by a selector are displayed on the screen.
    // This method is used to get all displayed items by a selector.
    protected List<WebElement> findAllDisplayedElements(By by) {
        List<WebElement> els = wait.until(presenceOfAllElementsLocatedBy(by));
        List<WebElement> displayedElement = new ArrayList<>();
        for (WebElement el : els) {
            if (el.isDisplayed()) {
                displayedElement.add(el);
            }
        }
        return displayedElement;
    }
}
