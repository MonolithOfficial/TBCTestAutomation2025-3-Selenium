package ge.tbc.testautomation.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Util {
    public static void dynamicTableHandler(String desiredColumnText, String searchByColumnText, String searchByValue, WebDriver driver){
        WebElement table = driver.findElement(By.tagName("table"));

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
    }
}
