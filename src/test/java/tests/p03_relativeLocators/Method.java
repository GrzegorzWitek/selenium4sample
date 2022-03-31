package tests.p03_relativeLocators;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import tests.BaseTest;

public class Method extends BaseTest {

    @Test
    public void newMethods() {
        driver.manage().window().setSize(new Dimension(800, 600));
        driver.navigate().to("https://demoqa.com/text-box");
        WebElement element = driver.findElement(By.cssSelector("#userName"));

        System.out.println("Attributes");
        System.out.println(element.getDomAttribute("placeholder"));
        System.out.println("Properties");
        System.out.println(element.getDomProperty("outerHTML"));

        System.out.println("Attributes");
        System.out.println(element.getAttribute("placeholder"));
        System.out.println("Properties");
        System.out.println(element.getAttribute("outerHTML"));
    }
}
