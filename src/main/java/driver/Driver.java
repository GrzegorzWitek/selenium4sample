package driver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.ClientConfig;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public final class Driver {
    private static Driver instance;
    private final Map<Long, WebDriver> driver;

    private Driver() {
        driver = new HashMap<>();
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
    }

    public static Driver getInstance() {
        if (instance == null) {
            instance = new Driver();
        }
        return instance;
    }

    public void quit() {
        long id = Thread.currentThread().getId();
        if (driver.containsKey(id)) {
            driver.get(id).quit();
            driver.remove(id);
        }
    }

    public WebDriver getDriver() {
//        return getChromeDriver();
//        return getFirefoxDriver();
        return getChromeDriverWithMerge();
    }

    public WebDriver getChromeDriver() {
        long id = Thread.currentThread().getId();
        if (!driver.containsKey(id)) {
            ChromeOptions options = new ChromeOptions();
            options.setPlatformName(Platform.MAC.name());
            options.setBrowserVersion("99");
            options.addArguments("--kiosk");

            printOptions(options);

            driver.put(Thread.currentThread().getId(),new ChromeDriver(options));
        }
        return driver.get(id);
    }

    public WebDriver getChromeDriverWithMerge() {
        long id = Thread.currentThread().getId();
        if (!driver.containsKey(id)) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(Browser.CHROME.browserName());
            capabilities.setPlatform(Platform.MAC);
            capabilities.setVersion("99");

            ChromeOptions options = new ChromeOptions();
            options = options.merge(capabilities);
            options.addArguments("--kiosk");

            printOptions(options);
            printCapabilities(capabilities);

            driver.put(Thread.currentThread().getId(),new ChromeDriver(options));
        }
        return driver.get(id);
    }

    public WebDriver getChromeDriverUsingBuilder(){
        long id = Thread.currentThread().getId();
        if (!driver.containsKey(id)) {
            ClientConfig config = ClientConfig.defaultConfig();

            ChromeOptions options = new ChromeOptions();
            options.setPlatformName(Platform.MAC.name());
            options.setBrowserVersion("99");
            options.addArguments("--kiosk");
            driver.put(Thread.currentThread().getId(), RemoteWebDriver.builder()
                    .oneOf(options)
                    .address("http://localhost:4444/wd/hub")
                    .config(config)
                    .build());
        }
        return driver.get(id);
    }

    public WebDriver getFirefoxDriver() {
        long id = Thread.currentThread().getId();
        if (!driver.containsKey(id)) {
            FirefoxOptions options = new FirefoxOptions();
            options.setPlatformName(Platform.MAC.name());
//            options.setLegacy(false);
            options.addArguments("--width=800", "--height=600");

            printOptions(options);

            driver.put(Thread.currentThread().getId(),new FirefoxDriver(options));
        }
        return driver.get(id);
    }

    private void printOptions(AbstractDriverOptions options){
        System.out.println("Options:");
        for (Object cap : options.getCapabilityNames()) {
            System.out.println(cap + ": " + options.getCapability(cap.toString()));
        }
    }

    private void printCapabilities(DesiredCapabilities capabilities){
        System.out.println("Capabilities:");
        for (String cap : capabilities.getCapabilityNames()) {
            System.out.println(cap + ": " + capabilities.getCapability(cap));
        }
    }

    private void setTimeouts(){
        long id = Thread.currentThread().getId();
        if (!driver.containsKey(id)) {
            driver.get(id).manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
            driver.get(id).manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get(id).manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        }
    }

}
