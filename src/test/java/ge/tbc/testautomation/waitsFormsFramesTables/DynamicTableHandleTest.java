package ge.tbc.testautomation.waitsFormsFramesTables;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class DynamicTableHandler {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/challenging_dom");
    }


    @Test
    public void dynamicTableTest() {
        WebElement table = driver.findElement(By.tagName("table"));

        String desiredColumnText = "Amet";
        String searchByColumnText = "Diceret";
        String searchByValue = "Phaedrum9";

        // FINDING INDEX OF DESIRED COLUMN
        List<WebElement> columns = table.findElements(By.xpath("//th"));
        WebElement desiredColumnElement = columns.stream().filter(tableHead -> tableHead.getText()
                        .equals(desiredColumnText))
                .findFirst().orElse(null);
        int desiredColumIndex = columns.indexOf(desiredColumnElement);

        // FINDING INDEX OF SEARCH BY COLUMN
        WebElement searchByColumnElement = columns.stream().filter(tableHead -> tableHead.getText()
                        .equals(searchByColumnText))
                .findFirst().orElse(null);
        int searchBydColumIndex = columns.indexOf(searchByColumnElement);

        // FINDING VERTICAL INDEX OF SEARCH BY VALUE
        List<WebElement> cellsOfSearchByColum = table.findElements(By.xpath(String.format("//tr/td[%d]", searchBydColumIndex + 1)));
        WebElement searchByValueElement = cellsOfSearchByColum.stream().filter(cell -> cell.getText().equals(searchByValue)).findFirst().orElse(null);
        int desiredRowIndex = cellsOfSearchByColum.indexOf(searchByValueElement);

        WebElement desiredValueElement = table.findElement(By.xpath(String.format("//tr[%d]/td[%d]", desiredRowIndex + 1, desiredColumIndex + 1)));
        System.out.println(desiredValueElement.getText());

//        System.out.println(desiredColumIndex + " " + searchBydColumIndex);
    }
}
