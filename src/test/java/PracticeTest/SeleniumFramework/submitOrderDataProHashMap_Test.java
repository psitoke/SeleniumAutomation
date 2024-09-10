package PracticeTest.SeleniumFramework;

import java.io.IOException;
import java.util.HashMap;
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

public class submitOrderDataProHashMap_Test extends BaseTest {
	
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData", groups= {"Purchase"})
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
	public Object[][] getData()
	{
		HashMap<String,String> map= new HashMap<String,String>();
		map.put("email", "anvit@gmail.com");
		map.put("pass", "Avishi11");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String,String> map1= new HashMap<String,String>();
		map1.put("email", "shetty@gmail.com");
		map1.put("pass", "Iamking@000");
		map1.put("product", "ADIDAS ORIGINAL");
		
		return new Object[][] {{map},{map1}};
	}

}

