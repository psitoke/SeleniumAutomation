package seleniumFramework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFramework.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;
	
	public LandingPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

@FindBy(id="userEmail")
WebElement userEmail;

@FindBy(id="userPassword")
WebElement password;

@FindBy(id="login")
WebElement submit;	

@FindBy(xpath="//*[@id='toast-container']")
WebElement errorMessage;

@FindBy(xpath="//div[contains(text(),'*Email is required')]")
WebElement emailErrorMessage;

@FindBy(xpath="//div[contains(text(),'*Password is required')]")
WebElement passErrorMessage;
//div[@class='ng-tns-c4-18 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error']
//create action class to perform the actions on landing page

public ProductCatalouge loginapplication(String email, String Password)
{
	userEmail.sendKeys(email);
	password.sendKeys(Password);
	submit.click();
	ProductCatalouge Pc =new ProductCatalouge(driver);
	return Pc;
}

public void goTo() 
{
	
	driver.get("https://rahulshettyacademy.com/client");
	driver.manage().window().maximize();
}

public String errorMessageValidation()
{
	waitForElementToAppear(errorMessage);
	return errorMessage.getText();
	
}

public void errorMessageforblankinput()
{	
userEmail.sendKeys("");
password.sendKeys("");
submit.click();
waitForElementToAppear(emailErrorMessage);


	
}
	public String emailError()
	{
		return emailErrorMessage.getText();
	}
	public String passError()
	{
		return passErrorMessage.getText();
	}
}
