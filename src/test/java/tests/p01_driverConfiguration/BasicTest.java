package tests.p01_driverConfiguration;

import org.junit.Assert;
import tests.BaseTest;
import org.junit.Test;

public class BasicTest extends BaseTest {

    @Test
    public void checkDriverOptions(){
        driver.navigate().to("https://onet.pl");
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("onet"));

        helper.wait(5000);
    }
}
