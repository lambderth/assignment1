package utils;

import org.openqa.selenium.WebDriver;

import pageobjects.NavPage;
import java.util.Properties;

public class TestContext {
    private WebDriver driver;
    private NavPage navPage;
    private Properties prop;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public NavPage getNavPage() {
        return navPage;
    }

    public void setNavPage(NavPage navPage) {
        this.navPage = navPage;
    }

    public Properties getProp() {
        return prop;
    }

    public void setProp(Properties prop) {
        this.prop = prop;
    }
}
