package ge.tbc.testautomation.ExceptionsJS;

import ge.tbc.testautomation.data.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static ge.tbc.testautomation.data.Constants.*;

@Test(groups = {"E2E - successful product purchase - SCRUM-T18"})
public class SuccessfulPurchaseScenarioCrossBrowserTest {
    WebDriver driver;

    @BeforeClass
    @Parameters("browserType")
    public void setUp(String browserType) {
        if (browserType.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions()
                    .setExperimentalOption(
                            "prefs",
                            Map.of(
                                    "credentials_enable_service", false,
                                    "profile.password_manager_enabled", false
                            )
                    );
            driver = new ChromeDriver(options);
        } else if (browserType.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions()
                    .addPreference("credentials_enable_service", false)
                    .addPreference("profile.password_manager_enabled", false);
            driver = new FirefoxDriver(options);
        }
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @Test(description = "Login as standard user", priority = 1)
    public void loginAsStandardUser() {
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.sendKeys(Constants.STANDARD_USER);

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(Constants.PASSWORD);

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
    }

    @Test(description = "Add backpack to cart", priority = 2)
    public void addToCart() {
        WebElement addToCartButton = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']//following::button[1]"));
        addToCartButton.click();

        WebElement removeButton = driver.findElement(By.id("remove-sauce-labs-backpack"));
        boolean isVisible = removeButton.isDisplayed();
        Assert.assertTrue(isVisible);
    }

    @Test(description = "Review the cart", priority = 3)
    public void reviewCart() {
        WebElement cartIcon = driver.findElement(By.cssSelector("a.shopping_cart_link"));
        cartIcon.click();

        List<WebElement> cartItems = driver.findElements(By.cssSelector("div.cart_item"));
        Assert.assertEquals(cartItems.size(), 1);
    }

    @Test(description = "Go to checkout page", priority = 4)
    public void goToCheckout() {
        WebElement checkoutButton = driver.findElement(By.id("checkout"));
        checkoutButton.click();

        WebElement checkoutPageLabel = driver.findElement(By.cssSelector("span.title"));
        Assert.assertEquals(checkoutPageLabel.getText(), CHECKOUT_LABEL);
    }

    @Test(description = "Enter checkout information", priority = 5)
    public void enterInformation() {
        WebElement firstNameInput = driver.findElement(By.id("first-name"));
        WebElement lastNameInput = driver.findElement(By.id("last-name"));
        WebElement zipCodeInput = driver.findElement(By.id("postal-code"));

        firstNameInput.sendKeys("Joee");
        lastNameInput.sendKeys("Doe");
        zipCodeInput.sendKeys("0200");
    }

    @Test(description = "Proceed to final page", priority = 6)
    public void proceedToFinalPage() {
        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();

        WebElement checkoutPageLabel = driver.findElement(By.cssSelector("span.title"));
        Assert.assertEquals(checkoutPageLabel.getText(), CHECKOUT_OVERVIEW_LABEL);
    }

    @Test(description = "Finish order", priority = 7)
    public void finishOrder() {
        WebElement finish = driver.findElement(By.id("finish"));
        finish.click();

        WebElement successMessage = driver.findElement(By.cssSelector("h2.complete-header"));
        Assert.assertEquals(successMessage.getText(), SUCCESS_MESSAGE);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
