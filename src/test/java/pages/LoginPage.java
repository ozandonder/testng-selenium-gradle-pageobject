package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

import static org.testng.Assert.assertEquals;

public class LoginPage extends Driver {
    WebDriver driver;

    public LoginPage(WebDriver web_driver) {
        this.driver = web_driver;
        PageFactory.initElements(web_driver, this);
    }

    @FindBy(id = "login-button")
    WebElement LOGIN_BUTTON;

    @FindBy(id = "user-name")
    WebElement USERNAME_TEXTBOX;

    @FindBy(id = "password")
    WebElement PASSWORD_TEXTBOX;

    @FindBy(xpath = "//*[@class='error-message-container error']/h3")
    WebElement ERROR_TEXT;

    String USERNAME_ERROR_MESSAGE = "Epic sadface: Username is required";
    String PASSWORD_ERROR_MESSAGE = "Epic sadface: Password is required";

    public void tryToLogin(String userName, String password) {
        type(USERNAME_TEXTBOX,userName,true);
        type(PASSWORD_TEXTBOX,password,true);
        clickWebElement(LOGIN_BUTTON);
    }

    public void checkErrorMessageForUsername() {
        assertEquals(ERROR_TEXT.getText(), USERNAME_ERROR_MESSAGE);
    }

    public void checkErrorMessageForPassword() {
        assertEquals(ERROR_TEXT.getText(), PASSWORD_ERROR_MESSAGE);
    }
}
