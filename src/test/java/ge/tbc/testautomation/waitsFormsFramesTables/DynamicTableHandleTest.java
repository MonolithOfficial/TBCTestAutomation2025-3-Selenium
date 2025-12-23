package ge.tbc.testautomation.waitsFormsFramesTables;

import ge.tbc.testautomation.util.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DynamicTableHandleTest {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/challenging_dom");
    }


    // this test shows dynamic handling of a web table structure, you can use this implementation to derive your own
    @Test
    public void dynamicTableTest() {
        Util.dynamicTableHandler("Amet", "Diceret", "Phaedrum9", driver);
    }
}
