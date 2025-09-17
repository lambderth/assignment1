package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import pageobjects.TemplatePage;
import resources.DriverFactory;

public class BaseTest {
    public WebDriver driver;
    public TemplatePage templatePage;
    public Properties prop;

    public Properties loadProperties() throws IOException {
        prop = new Properties();
        String path = System.getProperty("user.dir") + "/src/main/java/resources/config.properties";
        FileInputStream fis = new FileInputStream(path);
        prop.load(fis);
        return prop;
    }
    
    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
        Files.copy(source.toPath(), Paths.get(destinationFile), StandardCopyOption.REPLACE_EXISTING);
        return destinationFile;
    }

    @BeforeMethod
    public TemplatePage launchApplication() throws IOException {
        prop = loadProperties();

        // Prioridad: -Dbrowser=chrome > config.properties
        String browserName = System.getProperty("browser") != null ?
                System.getProperty("browser") :
                prop.getProperty("browser");

        driver = DriverFactory.initializeDriver(browserName);
        driver.get(prop.getProperty("url"));
        templatePage = new TemplatePage(driver);
        return templatePage;
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
