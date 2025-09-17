package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class NavPage extends AbstractComponent {
    WebDriver driver;
    By cartElementPrice = By.xpath("//div[@class='cart-info']/span[@class='price']");
    
    public NavPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // PageFactory Locators
    @FindBy(xpath = "//a[text()='Women']")
    private WebElement womenCategoryButton;
    
    @FindBy(xpath = "//a[@title='View my shopping cart']")
    private WebElement cartButton;
    
    @FindBy(xpath = "//a[@title='remove this product from my cart']")
    private WebElement removeFromCartButton;

    // Actions
    public ProductListPage goToWomenCategoryPage() {
        womenCategoryButton.click(); 
        return new ProductListPage(driver);
    }
    
    public CartSummaryPage goToCart() {
    	cartButton.click();
    	return new CartSummaryPage(driver);
    }
    
    public int removeFirstProduct() {
    	Actions actions = new Actions(driver);
        actions.moveToElement(cartButton) 
               .perform();
        
    	WebElement priceElement = driver.findElement(cartElementPrice);
		String priceText = priceElement.getText();
		String numberOnly = priceText.replaceAll("[^0-9]", "");
		
        clickElement(removeFromCartButton);
		
		return Integer.parseInt(numberOnly);
    }
}
