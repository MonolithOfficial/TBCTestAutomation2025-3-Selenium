package ge.tbc.testautomation.waitsFormsFramesTables;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class FramesAndAlerts {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void framesTest(){
        driver.get("https://jqueryui.com/slider/");
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe.demo-frame")));
        WebElement slider = driver.findElement(By.id("slider"));
        slider.click();

        driver.switchTo().defaultContent(); // go back to main html
    }

    @Test
    public void alertsTest(){
        driver.get("https://demoqa.com/alerts");
        WebElement alertButton = driver.findElement(By.id("alertButton"));
        alertButton.click();

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test
    public void windowTest(){
        driver.get("https://demoqa.com/browser-windows");
        WebElement windowButton = driver.findElement(By.id("windowButton"));
        String currentWindowId = driver.getWindowHandle();
        windowButton.click();

        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows){
            if (!window.equals(currentWindowId)){
                driver.switchTo().window(window);
                WebElement heading = driver.findElement(By.id("sampleHeading"));
                System.out.println(heading.getText());
            }
        }
    }
}
