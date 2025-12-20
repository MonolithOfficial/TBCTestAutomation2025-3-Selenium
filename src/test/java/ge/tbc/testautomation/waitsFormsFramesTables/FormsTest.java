package ge.tbc.testautomation.waitsFormsFrames;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class FormsTest {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void radioButtonTest(){
        WebElement femaleRadioButton = driver.findElement(By.cssSelector("input[type='radio'][value='female']"));
        if (!femaleRadioButton.isSelected()){
            femaleRadioButton.click();
        }
    }

    @Test
    public void checkButtonTest(){
        WebElement deliveryCB = driver.findElement(By.cssSelector("input[type='checkbox'][name='DeliveryLoc']"));
        deliveryCB.click();
    }

    @Test
    public void nativeDropDownTest(){
        Select modelDropDown = new Select(driver.findElement(By.cssSelector("select[name='model']")));
//        modelDropDown.isMultiple(); // can or not select more than one option
        modelDropDown.selectByVisibleText("Serene Pad 32G");
    }

    @Test
    public void customDropDownTest(){
        driver.get("https://ng-bootstrap.github.io/#/components/dropdown/examples");
        WebElement dropDownButton = driver.findElement(By.id("dropdownBasic1"));
        dropDownButton.click();

        List<WebElement> options = driver.findElements(By.xpath("//button[@id='dropdownBasic1']//following-sibling::div//button"));

        WebElement actionOption = options.stream()
                .filter(webElement -> webElement.getText().contains("Action"))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("NO ACTION ELEMENT FOUND"));

        actionOption.click();
    }
}
