package PracticeTest.SeleniumFramework;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import PracticeTest.SeleniumFramework.TestComponents.BaseTest;
import seleniumFramework.pageObjects.CartPage;
import seleniumFramework.pageObjects.CheckoutPage;
import seleniumFramework.pageObjects.ConfirmationPage;
import seleniumFramework.pageObjects.OrderPage;
import seleniumFramework.pageObjects.ProductCatalouge;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class submitLoginTest extends BaseTest {
	
	String productName = "ZARA COAT 3";
	
	@Test
	public void submitOrder() throws IOException {

		ProductCatalouge Pc = Lp.loginapplication("anvit@gmail.com", "Avishi11");

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




}

