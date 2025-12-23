package ge.tbc.testautomation.ExceptionsJS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Exceptions {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");

    }

    @Test
    public void testStaleElementReferenceException() {
        WebElement enableButton = driver.findElement(By.xpath("//button[text()='Enable']"));
        enableButton.click();

        driver.navigate().refresh();
        enableButton = driver.findElement(By.xpath("//button[text()='Enable']"));
        enableButton.click();
        System.out.println(enableButton.getText());
    }
}
