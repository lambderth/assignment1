package stepdefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import pageobjects.NavPage;
import resources.DriverFactory;
import utils.TestContext;

public class Hooks {

	private static TestContext context = new TestContext();
	
	public static TestContext getContext() {
        return context;
    }

    @Before
    public void launchApplication() throws IOException {
        Properties prop = loadProperties();
        context.setProp(prop);

        String browserName = System.getProperty("browser") != null ?
                System.getProperty("browser") :
                prop.getProperty("browser");

        WebDriver driver = DriverFactory.initializeDriver(browserName);
        driver.get(prop.getProperty("url"));
        context.setDriver(driver);

        NavPage navPage = new NavPage(driver);
        context.setNavPage(navPage);
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        WebDriver driver = context.getDriver();

        // Capturar screenshot si falla
        if (scenario.isFailed() && driver != null) {
            String screenshotPath = getScreenshot(scenario.getName(), driver);
            scenario.attach(Files.readAllBytes(Paths.get(screenshotPath)), "image/png", scenario.getName());
        }

        if (driver != null) {
            driver.quit();
        }
    }

    private Properties loadProperties() throws IOException {
        Properties prop = new Properties();
        String path = System.getProperty("user.dir") + "/src/main/java/resources/config.properties";
        FileInputStream fis = new FileInputStream(path);
        prop.load(fis);
        return prop;
    }

    private String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
        Files.copy(source.toPath(), Paths.get(destinationFile), StandardCopyOption.REPLACE_EXISTING);
        return destinationFile;
    }
}
