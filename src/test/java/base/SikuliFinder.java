package base;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.Image;
import org.sikuli.script.*;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.*;

public class SikuliFinder {

  private final WebDriver driver;

  public SikuliFinder(WebDriver driver) {
    this.driver = driver;
  }

  private void createImage(File file) {
    try {
      BufferedImage read = ImageIO.read(file);
      File outputFile =
          new File("src/test/resources/images/" + Thread.currentThread().getId() + ".png");
      ImageIO.write(read, "png", outputFile);
    } catch (IOException ignored) {
    }
  }

  private Image getImage(String fileName) {
    ClassLoader classLoader = getClass().getClassLoader();
    URL resource = classLoader.getResource("images/" + fileName + ".png");
    if (resource == null) {
      throw new IllegalArgumentException("file not found! " + fileName + ".png");
    } else {
      try {
        return Image.create(new File(resource.toURI()));
      } catch (URISyntaxException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private List<WebElement> getAllElements() {
    List<WebElement> elements =
        driver.findElements(By.xpath(".//*[not(ancestor::*[contains(@style,'display:none')])]"));
    elements = elements.stream().filter(el -> el.getRect().getHeight() > 0).toList();
    return elements;
  }

  /**
   * Find the nearest WebElement to the image
   *
   * @return WebElement
   */
  private WebElement nearestPoint(Point p) {
    Map<Point, WebElement> all = new HashMap<>();
    List<WebElement> elements = getAllElements();
    elements.forEach(
        el -> all.put(new Point(el.getLocation().getX(), el.getLocation().getY()), el));

    Set<Point> allPoints = all.keySet();
    Map<Double, Point> filteredPoints = new HashMap<>();

    // Calculate points distances
    allPoints.forEach(point -> filteredPoints.put(p.distanceSq(point), point));

    Double min = Collections.min(filteredPoints.keySet());
    Point point = filteredPoints.get(min);
    return all.get(point);
  }

  /**
   * Find and return WebElement by image if it's exist in DOM
   *
   * @param imagePath path of the image
   * @return WebElement
   */
  public WebElement findElement(String imagePath) {
    Finder finder = getFinder();
    finder.find(new Pattern(getImage(imagePath)));

    Match match = finder.getList().get(0);
    System.out.println(match.getScore());
    WebElement element = nearestPoint(match.getRect().getLocation());
    createImage(element.getScreenshotAs(OutputType.FILE));
    return element;
  }

  /**
   * Click on element using coordinates
   *
   * @param imagePath path of the image
   */
  public void clickOnImage(String imagePath) {
    Finder finder = getFinder();
    finder.find(new Pattern(getImage(imagePath)));
    List<Match> matchList = finder.getList();
    if (matchList.isEmpty()) {
      throw new org.openqa.selenium.NoSuchElementException(imagePath + ": not found");
    }

    Match match = matchList.get(0);
    System.out.println(match.getScore());
    Actions action = new Actions(driver);
    action.moveByOffset(match.getX(), match.getY()).click().perform();
  }

  /**
   * Create find area
   *
   * @return new Finder()
   */
  private Finder getFinder() {
    Screenshot ashot = new AShot().takeScreenshot(driver);
    Rectangle rectangle = new Rectangle(ashot.getImage().getWidth(), ashot.getImage().getHeight());
    ScreenImage screenImage = new ScreenImage(rectangle, ashot.getImage());
    return new Finder(screenImage, new Region(rectangle));
  }
}
