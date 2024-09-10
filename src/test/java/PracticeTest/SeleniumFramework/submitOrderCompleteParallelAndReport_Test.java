package PracticeTest.SeleniumFramework;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PracticeTest.SeleniumFramework.TestComponents.BaseTest;
import PracticeTest.SeleniumFramework.TestComponents.Retry;
import seleniumFramework.pageObjects.CartPage;
import seleniumFramework.pageObjects.CheckoutPage;
import seleniumFramework.pageObjects.ConfirmationPage;
import seleniumFramework.pageObjects.OrderPage;
import seleniumFramework.pageObjects.ProductCatalouge;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class submitOrderCompleteParallelAndReport_Test extends BaseTest {
	
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData")
	public void submitOrder(HashMap<String,String> input ) throws IOException {

		ProductCatalouge Pc = Lp.loginapplication(input.get("email"), input.get("pass"));

		List<WebElement> products = Pc.getProducList();

		Pc.addProductToCart(input.get("product"));
		CartPage Cp = Pc.gotoCart();

		Boolean match = Cp.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);

		CheckoutPage Checkout = Cp.gotoCheckout();

		Checkout.selectCountry();
		ConfirmationPage Confirm = Checkout.submitOrder();

		String confirmMessage = Confirm.getConfirmationMessage();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

	}

	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalouge Pc = Lp.loginapplication("anvit@gmail.com", "Avishi11");
		OrderPage Op= Pc.gotoOrderPage();
		Assert.assertTrue(Op.VerifyOrderDisplay(productName));
		
	}

	@DataProvider
	public Object[][] getData() throws IOException
	{
	 List<HashMap<String,String>> data=	getJsonDataToMap("C:\\Users\\pranavsitoke\\Downloads\\Pranav_PersonalDocs\\Selenium_Automation\\SeleniumFramework\\src\\test\\java\\SeleniumFramework\\data\\PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

	
	@Test
	public void LoginErrorValidation() {
		
		Lp.loginapplication("anvit@gmail.com","abc@123");
		String errorMessage=Lp.errorMessageValidation();
		Assert.assertEquals("Incorrect email or password.", errorMessage);
	}
	
	@Test
	public void BlankLogin()
	{
		Lp.errorMessageforblankinput();
		String unameError= Lp.emailError();
		String passError= Lp.passError();
		Assert.assertEquals("*Email is required", unameError);
		Assert.assertEquals("*Password is required", passError);
	}
	
	@Test (retryAnalyzer=Retry.class)
	public void InvalidLoginErrorValidation() {
		
		Lp.loginapplication("anvit@gmail.com","abc@123");
		String errorMessage=Lp.errorMessageValidation();
		Assert.assertEquals("Logged IN", errorMessage);
	}
	
	@Test(retryAnalyzer=Retry.class)
	public void FailBlankLogin()
	{
		Lp.errorMessageforblankinput();
		String unameError= Lp.emailError();
		String passError= Lp.passError();
		Assert.assertEquals("*Email required", unameError);
		Assert.assertEquals("*Password required", passError);
	}
}

