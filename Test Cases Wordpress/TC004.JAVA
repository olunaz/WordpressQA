package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TC004 {
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
  public void testTC004() throws Exception {
    driver.get(baseUrl + "/wordpress/wp-login.php");
    driver.findElement(By.id("user_login")).clear();
    driver.findElement(By.id("user_login")).sendKeys("admin");
    driver.findElement(By.id("user_pass")).clear();
    driver.findElement(By.id("user_pass")).sendKeys("demo123");
    driver.findElement(By.id("wp-submit")).click();
    driver.findElement(By.id("title")).clear();
    driver.findElement(By.id("title")).sendKeys("Nuevo Post");
    driver.findElement(By.id("content")).clear();
    driver.findElement(By.id("content")).sendKeys("Nuevo Contenido");
    driver.findElement(By.id("tags-input")).clear();
    driver.findElement(By.id("tags-input")).sendKeys("Nuevo Contenido");
    driver.findElement(By.id("publish")).click();
    driver.findElement(By.linkText("Edit post")).click();
    driver.findElement(By.id("title")).clear();
    driver.findElement(By.id("title")).sendKeys("Nuevo Nuevo Post");
    driver.findElement(By.id("content")).clear();
    driver.findElement(By.id("content")).sendKeys("Nuevo Nuevo Contenido");
    driver.findElement(By.id("publish")).click();
    driver.findElement(By.linkText("View post")).click();
    try {
      assertEquals("Not Found", driver.findElement(By.cssSelector("h1")).getText());
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
