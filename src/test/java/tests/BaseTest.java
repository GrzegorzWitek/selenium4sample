package tests;

import driver.Driver;
import driver.Helper;
import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    protected WebDriver driver = Driver.getInstance().getDriver();
    protected Helper helper = new Helper();

    @AfterClass
    public static void clean(){
        Driver.getInstance().quit();
    }
}
