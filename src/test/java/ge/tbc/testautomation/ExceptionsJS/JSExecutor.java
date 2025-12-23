package ge.tbc.testautomation.ExceptionsJS;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class JSExecutor {
    WebDriver driver;
    JavascriptExecutor js;

    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void radioButtonTest(){
        driver.get("https://techcanvass.com/examples/register.html");
        WebElement femaleRadioButton = driver.findElement(By.cssSelector("input[type='radio'][value='female']"));
        if (!femaleRadioButton.isSelected()){
//            femaleRadioButton.click();
            js.executeScript("arguments[0].click();", femaleRadioButton);
        }
    }

    @Test
    public void scrollTest(){
        driver.get("https://www.techlistic.com/2017/02/automate-demo-web-table-with-selenium.html");
        WebElement popTutorials = driver.findElement(By.xpath("//span[text()='Popular Tutorials']"));
        js.executeScript("arguments[0].scrollIntoView();");
        String URL = (String) js.executeScript("return document.location.href");
        System.out.println(URL);
    }
}
