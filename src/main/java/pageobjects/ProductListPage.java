package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class ProductListPage extends AbstractComponent {
    WebDriver driver;
    NavPage navPage;
    By productContainers = By.xpath("//div[@class='product-container']");
    
    public ProductListPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.navPage = new NavPage(driver);
    }

    public List<WebElement> getProducts() {
        return driver.findElements(productContainers);
    }

    public boolean hasStock(WebElement product) {
        return !product.findElements(By.cssSelector(".available-dif")).isEmpty();
    }

    public DetailPage openProduct(WebElement product) {
        WebElement hiddenButton = product.findElement(By.xpath(".//span[text()='More']"));
 
        Actions actions = new Actions(driver);
        actions.moveToElement(product) 
               .moveToElement(hiddenButton)
               .click()  
               .build()
               .perform();

        return new DetailPage(driver);
    }
    
    public int addProducts() {
    	int total = 0;
        for (int i = 0; i < getProducts().size(); i++) {
            WebElement product = getProducts().get(i);
            if (hasStock(product)) {
                DetailPage detailPage = openProduct(product);
                waitForWebElementToAppear(detailPage.getProductImage());
                total += detailPage.checkAvailability();

                navPage.goToWomenCategoryPage();
            }
        }
        return total;
    }

}

