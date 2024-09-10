package seleniumFramework.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFramework.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
		WebDriver driver;
		
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(css=".ta-results button:last-child")
	WebElement selectCountry;
	
	@FindBy(css=".ta-results button:last-child")
	WebElement countryName;
	
	By confirmationMessage= By.cssSelector(".hero-primary");

	By results= By.cssSelector(".ta-results");
	
	public void selectCountry()
	{
		country.sendKeys("india");
		waitforelementtoappear(By.cssSelector(".ta-results"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", countryName);
		
		
	}

	public ConfirmationPage submitOrder()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", submit);
		waitforelementtoappear(confirmationMessage);
		ConfirmationPage Confirm= new ConfirmationPage(driver);
		return Confirm;
	}
}

	
	
	
	
//	
//	public void selectCountry(String countryName)
//	{
//		Actions a = new Actions(driver);
//		a.sendKeys(country, countryName).build().perform();
//		waitforelementtoappear(By.cssSelector(".ta-results"));
//		selectCountry.click();
//	}
//	
//	public ConfirmationPage submitOrder()
//	{
//		submit.click();
//		ConfirmationPage Confirm= new ConfirmationPage(driver);
//		return Confirm;
//	}
	
