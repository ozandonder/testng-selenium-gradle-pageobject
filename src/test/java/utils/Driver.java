package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.*;

public class Driver {

    public static WebDriver driver;

    @Parameters("browser")
    @BeforeTest
    public static WebDriver launchBrowser(String browser) {
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().clearDriverCache().setup();
                ChromeOptions options = new ChromeOptions();
                //options.addArguments("--headless");
                options.addArguments("--incognito");
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().clearDriverCache().setup();
                driver = new FirefoxDriver();
                break;
            case "opera":
                WebDriverManager.operadriver().clearDriverCache().setup();
                driver = new OperaDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser type not supported for " + browser);
        }
        return driver;
    }

    @BeforeMethod
    public void goToUrl() {
        driver.manage().deleteAllCookies();
        driver.get("https://www.saucedemo.com");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    public void clickWebElement(WebElement webElement) {
        webElement.click();
        waitForAjax();
    }

    public void type(WebElement webElement, String text, boolean clear) {
        if (clear) {
            webElement.clear();
        }

        webElement.sendKeys(text);
        waitForAjax();
    }

    public void waitForAjax() {
        JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
        for (int i = 0; i < 50; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Page has not loaded yet ");
            }
            boolean stillRunningAjax = (Boolean) jsDriver
                    .executeScript("return (window.jQuery != undefined && ($(':animated').length != 0 || jQuery.active != 0)) || document.readyState != \"complete\"");
            if (!stillRunningAjax) {
                break;
            }
        }
    }
}
