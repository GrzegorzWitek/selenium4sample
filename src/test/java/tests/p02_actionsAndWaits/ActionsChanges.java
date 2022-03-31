package tests.p02_actionsAndWaits;

import tests.BaseTest;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionsChanges extends BaseTest {

    @Test
    public void moveAndClick(){
        driver.navigate().to("https://demoqa.com/tool-tips");

        Actions action = new Actions(driver);
        WebElement hoverElement = driver.findElement(By.cssSelector("#toolTipTextField"));

        action.click(hoverElement).build().perform();

        helper.wait(5000);
    }

    @Test
    public void moveAndClickWithWait(){
        driver.navigate().to("https://demoqa.com/tool-tips");

        Actions action = new Actions(driver);
        WebElement hoverElement = driver.findElement(By.cssSelector("#toolTipTextField"));
        WebElement hoverButton = driver.findElement(By.cssSelector("#toolTipButton"));

        action.moveToElement(hoverElement).click().build().perform();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".tooltip-inner")));
        action.moveToElement(hoverButton).click().build().perform();

        helper.wait(5000);
    }
}
