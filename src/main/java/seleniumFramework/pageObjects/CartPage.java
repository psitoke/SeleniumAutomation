package seleniumFramework.pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumFramework.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cart button:first-of-type")
	WebElement checkoutEle;

	@FindBy(css = ".cartSection h3")
	List<WebElement> productTitles;

	public Boolean VerifyProductDisplay(String productName)
	{
		waitForElementToAppear(checkoutEle);
		Boolean match= productTitles.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage gotoCheckout()
	{
		checkoutEle.click();
		CheckoutPage Checkout= new CheckoutPage(driver);
		return Checkout;
	}
	
}
