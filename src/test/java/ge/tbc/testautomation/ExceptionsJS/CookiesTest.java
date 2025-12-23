package ge.tbc.testautomation.ExceptionsJS;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CookiesTest {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
    }

    @Test
    public void cookiesTest(){
        Cookie namedCookie = driver.manage().getCookieNamed("optimizelyBuckets");
        System.out.println(namedCookie.getValue());

        Cookie newCookie = new Cookie("Test Automation", "Bootcamp 12");
        driver.manage().addCookie(newCookie);

//        driver.manage().deleteAllCookies();
        driver.manage().deleteCookieNamed("rack.session");
    }

}
