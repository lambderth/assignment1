package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class TemplatePage extends AbstractComponent {
    WebDriver driver;
    
    public TemplatePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // PageFactory Locators
    @FindBy(name = "q")
    private WebElement searchBar;

    // Actions
    public void searchSomething(String search) {
        searchBar.sendKeys(search);
        searchBar.submit();
        
        // Just for show, don't use this
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
}
