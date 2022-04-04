import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import utils.Driver;

public class ProductTest extends Driver {
    LoginPage loginPage;
    ProductPage productPage;

    @Test(description = "User must add product to cart")
    @Parameters({"valid_username", "password"})
    void testAddProductToCart(String username, String password) {
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        loginPage.tryToLogin(username, password);
        productPage.addToCart("1").checkShoppingCartBadge("1");
    }

    @Test(description = "User should be able to return from product detail page")
    @Parameters({"valid_username", "password"})
    void testReturnFromPDP(String username, String password) {
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        loginPage.tryToLogin(username, password);
        productPage.clickProductTitleLink("1").clickBackToProductButton().checkTitleAtProductPage();
    }
}