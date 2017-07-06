package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TC010 {
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
  public void testTC010() throws Exception {
    driver.get(baseUrl + "/wordpress/wp-login.php");
    driver.findElement(By.id("user_login")).clear();
    driver.findElement(By.id("user_login")).sendKeys("admin");
    driver.findElement(By.id("user_pass")).clear();
    driver.findElement(By.id("user_pass")).sendKeys("demo123");
    driver.findElement(By.id("wp-submit")).click();
    driver.findElement(By.cssSelector("#menu-posts > div.wp-menu-image > a")).click();
    driver.findElement(By.linkText("Categories")).click();
    driver.findElement(By.id("tag-name")).clear();
    driver.findElement(By.id("tag-name")).sendKeys("New Category");
    driver.findElement(By.id("tag-slug")).clear();
    driver.findElement(By.id("tag-slug")).sendKeys("New Category");
    driver.findElement(By.id("tag-description")).clear();
    driver.findElement(By.id("tag-description")).sendKeys("New Category");
    driver.findElement(By.id("submit")).click();
    try {
      assertEquals("", driver.findElement(By.id("tag-name")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("", driver.findElement(By.id("tag-slug")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("", driver.findElement(By.id("tag-description")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("New Category", driver.findElement(By.linkText("New Category")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("New Category", driver.findElement(By.cssSelector("td.description.column-description")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("new-category", driver.findElement(By.cssSelector("td.slug.column-slug")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("0", driver.findElement(By.cssSelector("td.posts.column-posts > a")).getText());
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
