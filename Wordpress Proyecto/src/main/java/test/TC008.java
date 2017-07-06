package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TC008 {
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
    public void testTC008() throws Exception {
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
        driver.findElement(By.id("title")).sendKeys("New Test");
        driver.findElement(By.id("content")).clear();
        driver.findElement(By.id("content")).sendKeys("New Test");
        driver.findElement(By.id("in-category-4")).click();
        driver.findElement(By.id("new-tag-post_tag")).clear();
        driver.findElement(By.id("new-tag-post_tag")).sendKeys("New Test");
        driver.findElement(By.id("publish")).click();
        driver.findElement(By.linkText("Move to Trash")).click();
        try {
            assertEquals("Item moved to the Trash. Undo", driver.findElement(By.cssSelector("#message > p")).getText());
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
