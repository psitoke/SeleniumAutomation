package seleniumFramework.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumFramework.AbstractComponents.AbstractComponent;

public class ProductCatalouge extends AbstractComponent {

	WebDriver driver;

	public ProductCatalouge(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

// using PageFactory
	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By producstBy= By.cssSelector(".mb-3");
	By addToCart= By.cssSelector(".card-body button:last-of-type");
	By toastMessage= By.cssSelector("#toast-container");
	
	public List<WebElement> getProducList()
	{
		waitforelementtoappear(producstBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement ReqProduct = getProducList().stream()
				.filter(prod -> prod.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return ReqProduct;
	}
	
	public void addProductToCart(String productName)
	{
		WebElement ReqProduct= getProductByName(productName);
		ReqProduct.findElement(addToCart).click();
		waitforelementtoappear(toastMessage);
		waitForElementToDisappear(spinner);
	}
}
