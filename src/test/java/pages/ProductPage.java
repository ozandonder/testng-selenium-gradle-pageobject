package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

import static org.testng.Assert.assertEquals;

public class ProductPage extends Driver {
    WebDriver driver;

    public ProductPage(WebDriver web_driver) {
        this.driver = web_driver;
        PageFactory.initElements(web_driver, this);
    }

    @FindBy(className = "title")
    WebElement PRODUCT_PAGE_TITLE;


    @FindBy(className = "shopping_cart_badge")
    WebElement SHOPPING_CART_BADGE;

    @FindBy(id = "back-to-products")
    WebElement BACK_TO_PRODUCT_BUTTON;

    String PRODUCT_PAGE_TITLE_TEXT = "PRODUCTS";

    public void checkTitleAtProductPage() {
        assertEquals(PRODUCT_PAGE_TITLE.getText(), PRODUCT_PAGE_TITLE_TEXT);
    }

    public ProductPage addToCart(String productIndex) {
        WebElement webElement = driver.findElement(By.xpath("//*[@class='inventory_item'][" + productIndex + "]//button"));
        clickWebElement(webElement);
        return this;
    }

    public ProductPage clickProductTitleLink(String productIndex) {
        WebElement webElement = driver.findElement(By.xpath("//*[@class='inventory_item'][" + productIndex + "]//*[@class='inventory_item_name']"));
        clickWebElement(webElement);
        return this;
    }

    public ProductPage clickBackToProductButton() {
        clickWebElement(BACK_TO_PRODUCT_BUTTON);
        return this;
    }

    public void checkShoppingCartBadge(String count) {
        assertEquals(SHOPPING_CART_BADGE.getText(), count);
    }
}
