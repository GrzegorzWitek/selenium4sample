package tests.p03_relativeLocators;

import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import tests.BaseTest;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.locators.RelativeLocator;

public class Locators extends BaseTest {

    @Test
    public void useForNormalSize() {
        driver.navigate().to("https://demoqa.com/automation-practice-form");
        By nameLocator = RelativeLocator.with(By.tagName("input"))
                .toRightOf(By.className("left-pannel"))
                .toRightOf(By.id("userName-label"))
                .above(By.id("userEmail-wrapper"));
        driver.findElement(nameLocator).sendKeys("Name");

        helper.wait(5000);
    }

    @Test
    public void useForNormalSizeAndDistance() {
        driver.navigate().to("https://demoqa.com/automation-practice-form");
        By nameLocator = RelativeLocator.with(By.tagName("input"))
                .toRightOf(By.className("left-pannel"))
                .near(By.id("userName-label"),100)
                .above(By.id("userEmail-wrapper"));
        WebElement element = null;
        try {
            element = driver.findElement(nameLocator);
        }
        catch(NoSuchElementException exception){
            Assert.fail("Not found element");
        }
        element.sendKeys("Name");

        helper.wait(5000);
    }

    @Test
    public void useForSmallSize() {
        driver.navigate().to("https://demoqa.com/automation-practice-form");
        driver.manage().window().setSize(new Dimension(400,1000));
        By nameLocator = RelativeLocator.with(By.tagName("input"))
                .toRightOf(By.className("left-pannel"))
                .toRightOf(By.id("userName-label"))
                .above(By.id("userEmail-wrapper"));
        driver.findElement(nameLocator).sendKeys("Name");

        helper.wait(5000);
    }
}
