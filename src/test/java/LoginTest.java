import org.testng.annotations.*;
import pages.LoginPage;
import pages.ProductPage;
import utils.Driver;

public class LoginTest extends Driver {

    LoginPage loginPage;
    ProductPage productPage;

    @Test(description = "User should see error message for required username", priority = 1)
    public void testLoginInvalidUsername() {
        loginPage = new LoginPage(driver);
        loginPage.tryToLogin("", "");
        loginPage.checkErrorMessageForUsername();
    }


    @Test(description = "User should see error message for required password", priority = 1)
    @Parameters({"valid_username"})
    public void testLoginInvalidPassword(String username) {
        loginPage = new LoginPage(driver);
        loginPage.tryToLogin(username, "");
        loginPage.checkErrorMessageForPassword();
    }


    @Test(description = "User must login successfully", priority = 2)
    @Parameters({"valid_username", "password"})
    public void testValidUserLogin(String username, String password) {
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        loginPage.tryToLogin(username, password);
        productPage.checkTitleAtProductPage();
    }
}
