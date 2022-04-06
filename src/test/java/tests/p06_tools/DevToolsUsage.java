package tests.p06_tools;

import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v85.log.Log;
import org.openqa.selenium.devtools.v95.network.Network;
import org.openqa.selenium.devtools.v95.security.Security;
import tests.BaseTest;

import java.util.Optional;

public class DevToolsUsage extends BaseTest {

    @Test
    public void logs() {
        DevTools tools = ((HasDevTools) driver).getDevTools();
        tools.createSession();
        tools.send(Log.enable());
        tools.getDomains().events().addConsoleListener(System.out::println);

        driver.manage().window().setSize(new Dimension(800, 600));
        driver.navigate().to("https://demoqa.com/automation-practice-form");

        helper.wait(5000);

        tools.close();
    }

    @Test
    public void jsLogs() {
        DevTools tools = ((HasDevTools) driver).getDevTools();
        tools.createSession();
        tools.send(Log.enable());
        tools.getDomains().events().addJavascriptExceptionListener(System.out::println);
        driver.navigate().to("https://the-internet.herokuapp.com/javascript_error");
        helper.wait(5000);
    }

    @Test
    public void network() {
        DevTools tools = ((HasDevTools) driver).getDevTools();
        tools.createSession();

        tools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        tools.addListener(Network.requestWillBeSent(),
                entry -> {
                    System.out.println("Request URI : " + entry.getRequest().getUrl() + "\n"
                            + " Method : " + entry.getRequest().getMethod() + "\n"
                    );
                    entry.getRequest().getMethod();
                });
        driver.navigate().to("https://www.google.com");
        tools.send(Network.disable());
    }

    @Test
    public void certificate() {
        DevTools tools = ((HasDevTools)driver).getDevTools();
        tools.send(Security.enable());
        tools.send(Security.setIgnoreCertificateErrors(true));

        driver.manage().window().setSize(new Dimension(800, 600));
        driver.navigate().to("https://expired.badssl.com/");

        helper.wait(5000);
    }

    @Test
    public void userAgent() {
        String fakeAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36";
        DevTools tools = ((HasDevTools)driver).getDevTools();
        tools.createSession();
        tools.send(Network.setUserAgentOverride(fakeAgent,Optional.empty(),Optional.empty(), Optional.empty()));

        driver.manage().window().setSize(new Dimension(800, 600));
        driver.navigate().to("https://gs.statcounter.com/detect");

        helper.wait(10000);
    }

}
