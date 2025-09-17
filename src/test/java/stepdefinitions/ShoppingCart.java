package stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.*;
import pageobjects.CartSummaryPage;
import pageobjects.NavPage;
import utils.TestContext;

public class ShoppingCart {
	
	TestContext context = Hooks.getContext();
	WebDriver driver = context.getDriver();
	NavPage navPage = context.getNavPage();
	CartSummaryPage cartSummarypage;
	
	int total = 0;
	
	@Given("I add multiple products to the cart")
	public void addProducts() {
		total = navPage.goToWomenCategoryPage().addProducts();
	}
	
	@Given("I remove one product from the cart")
	public void removeProduct() {
		total -= navPage.removeFirstProduct();
	}
	
	@When("I proceed to the checkout page")
	public void checkout() {
		cartSummarypage = navPage.goToCart();
	}
	
	@Then("Price should match expected value")
	public void assertCartTotal() {
		Assert.assertEquals(cartSummarypage.getTotalPrice(), total + 7);
	}

}
