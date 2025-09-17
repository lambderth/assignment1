package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class CartSummaryPage extends AbstractComponent {
    WebDriver driver;
    
    public CartSummaryPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // PageFactory Locators
    @FindBy(id = "total_price")
    private WebElement totalPrice;

    // Actions
    public int getTotalPrice() {
		String priceText = totalPrice.getText();
		String numberOnly = priceText.replaceAll("[^0-9]", "");
		
		return Integer.parseInt(numberOnly);
    }
}
