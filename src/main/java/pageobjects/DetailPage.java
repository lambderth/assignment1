package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import AbstractComponents.AbstractComponent;

public class DetailPage extends AbstractComponent {
    WebDriver driver;
    By stock = By.xpath("//span[@id='availability_value']");
    By addToCart = By.id("add_to_cart");
    By closePopup = By.xpath("//span[@title='Close window']");
    By price = By.cssSelector(".our_price_display");
    
    public DetailPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // PageFactory Locators
    @FindBy(xpath = "//h1")
    private WebElement productTitle;
    
    @FindBy(id = "bigpic")
    private WebElement productImage;
    
    @FindBy(xpath = "//a[contains(@class,'color_pick')]")
    private List<WebElement> colorsList;
    
    @FindBy(id = "group_1")
    private WebElement sizeDropdown;

    // Actions
    public WebElement getProductTitleWebElement() {
        return productTitle;
    }
    
    public WebElement getProductImage() {
    	return productImage;
    }
    
    public boolean hasStock() {
        return driver.findElement(stock).getText().equals("In stock");
    }
    
    public void addToCart() {
    	WebElement cartButton = driver.findElement(addToCart);
    	waitForWebElementToBeClickable(cartButton);
    	clickElement(cartButton);
    	WebElement closeButton = driver.findElement(closePopup);
    	waitForWebElementToAppear(closeButton);
    	clickElement(closeButton);
    	waitForWebElementToDisappear(closeButton);
    }
    
    public int checkAvailability() {
    	for(int i = 0; i < colorsList.size(); i++) {
    		Select select = new Select(sizeDropdown);
			select.selectByIndex(i);
			for(WebElement element : colorsList) {
	    		clickElement(element);
	    		if(hasStock()) {
	    			addToCart();
	    			
	    			WebElement priceElement = driver.findElement(price);
	    			String priceText = priceElement.getText();
	    			String numberOnly = priceText.replaceAll("[^0-9]", "");
	    			
	    			return Integer.parseInt(numberOnly);
	    		} 
	    	}
    	}
    	return 0;
    }
}
