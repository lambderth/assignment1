package resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    public static WebDriver initializeDriver(String browserName) {
        WebDriver driver;

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            
            //stealth mode
            options.addArguments("--disable-save-password-bubble");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.addArguments("--start-maximized");
            options.addArguments("--disable-extensions");
            //options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            //options.setExperimentalOption("useAutomationExtension", false);
            //options.addArguments("--user-data-dir=/tmp/chrome-temp");
            
            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            throw new RuntimeException("Navegador no soportado: " + browserName);
        }
        
        driver.manage().window().maximize();
        return driver;
    }
}
