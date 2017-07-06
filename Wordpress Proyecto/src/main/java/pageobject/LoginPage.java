package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage {
    WebDriver webDriver;

    @FindBy (id="user_login")
    WebElement txtUser;

    @FindBy (id="user_pass")
    WebElement txtPassword;

    @FindBy (id="wp-submit")
    WebElement btSubmit;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void typeUser (String user) {
        this.txtUser.sendKeys(user);
    }
    public void typePassword (String pass) {
        this.txtPassword.sendKeys(pass);
    }

    public void submitBtn() {
        this.btSubmit.click();
    }

    public void login(String user, String password) {
        typeUser(user);
        typePassword(password);
        submitBtn();
    }
}
