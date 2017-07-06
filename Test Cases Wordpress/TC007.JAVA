package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TC007 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://demosite.center/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testTC007() throws Exception {
    driver.get(baseUrl + "/wordpress/wp-login.php");
    driver.findElement(By.id("user_login")).clear();
    driver.findElement(By.id("user_login")).sendKeys("admin");
    driver.findElement(By.id("user_pass")).clear();
    driver.findElement(By.id("user_pass")).sendKeys("demo123");
    driver.findElement(By.id("wp-submit")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
    driver.findElement(By.linkText("Posts")).click();
    driver.findElement(By.cssSelector("a.add-new-h2")).click();
    driver.findElement(By.id("title")).clear();
    driver.findElement(By.id("title")).sendKeys("New Post");
    driver.findElement(By.id("content")).clear();
    driver.findElement(By.id("content")).sendKeys("New Post");
    driver.findElement(By.id("in-category-4")).click();
    driver.findElement(By.id("new-tag-post_tag")).clear();
    driver.findElement(By.id("new-tag-post_tag")).sendKeys("New Content");
    driver.findElement(By.id("publish")).click();
    driver.findElement(By.id("title")).clear();
    driver.findElement(By.id("title")).sendKeys("New Post Updated");
    driver.findElement(By.id("content")).clear();
    driver.findElement(By.id("content")).sendKeys("New Content Updated");
    driver.findElement(By.id("in-category-1")).click();
    driver.findElement(By.id("publish")).click();
    driver.findElement(By.id("new-tag-post_tag")).clear();
    driver.findElement(By.id("new-tag-post_tag")).sendKeys("New Content Updated");
    driver.findElement(By.id("publish")).click();
    try {
      assertEquals("Post updated. View post", driver.findElement(By.cssSelector("#message > p")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("New Post Updated", driver.findElement(By.id("title")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("New Content Updated", driver.findElement(By.id("content")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Uncategorized", driver.findElement(By.cssSelector("#category-1 > label.selectit")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("X New Content Updated", driver.findElement(By.xpath("//div[@id='post_tag']/div[2]/span[2]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
