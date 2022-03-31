package tests.p04_windows;

import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WindowType;
import tests.BaseTest;
import org.junit.Test;

public class Windows extends BaseTest {

    @Test
    public void newWindow() {
        driver.navigate().to("https://www.google.com/");
        driver.manage().window().setSize(new Dimension(1000,1000));

        String mainHandler = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.navigate().to("https://www.onet.pl/");
        driver.manage().window().setSize(new Dimension(500,1000));

        Assert.assertTrue(driver.getTitle().toLowerCase().contains("onet"));
        driver.close();

        driver.switchTo().window(mainHandler);
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("google"));

        helper.wait(5000);
    }

    @Test
    public void newTab() {
        driver.navigate().to("https://www.google.com/");
        driver.manage().window().setSize(new Dimension(1000,1000));

        String mainHandler = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to("https://www.onet.pl/");
        driver.manage().window().setSize(new Dimension(500,1000));

        Assert.assertTrue(driver.getTitle().toLowerCase().contains("onet"));
        driver.switchTo().window(mainHandler);
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("google"));

        helper.wait(5000);
    }
}
