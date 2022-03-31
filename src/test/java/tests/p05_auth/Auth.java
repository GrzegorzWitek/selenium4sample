package tests.p05_auth;

import org.junit.Test;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import tests.BaseTest;

public class Auth extends BaseTest {

    @Test
    public void basicAuth(){
        driver.navigate().to("http://foo:bar@httpbin.org/basic-auth/foo/bar");
        helper.wait(5000);
    }

    @Test
    public void basicAuthNew(){
        ((HasAuthentication)driver).register(UsernameAndPassword.of("foo","bar"));
        driver.navigate().to("http://httpbin.org/basic-auth/foo/bar");
        helper.wait(5000);
    }
}
