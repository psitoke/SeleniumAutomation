package PracticeTest.SeleniumFramework;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PracticeTest.SeleniumFramework.TestComponents.BaseTest;
import seleniumFramework.pageObjects.CartPage;
import seleniumFramework.pageObjects.CheckoutPage;
import seleniumFramework.pageObjects.ConfirmationPage;
import seleniumFramework.pageObjects.OrderPage;
import seleniumFramework.pageObjects.ProductCatalouge;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class submitOrderDataProvider1_Test extends BaseTest {
	
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(String email, String password, String productName) throws IOException {

		ProductCatalouge Pc = Lp.loginapplication(email, password);

		List<WebElement> products = Pc.getProducList();

		Pc.addProductToCart(productName);
		CartPage Cp = Pc.gotoCart();

		Boolean match = Cp.VerifyProductDisplay(productName);
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
	public Object[][] getData()
	{
		return new Object[][] {{"anvit@gmail.com", "Avishi11","ZARA COAT 3"},{"shetty@gmail.com", "Iamking@000","ADIDAS ORIGINAL"}};
	}

}

