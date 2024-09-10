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

public class submitOrderTakeScreenShot_Test extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException {

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

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		ProductCatalouge Pc = Lp.loginapplication("anvit@gmail.com", "Avishi11");
		OrderPage Op = Pc.gotoOrderPage();
		Assert.assertTrue(Op.VerifyOrderDisplay(productName));

	}


	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				"C:\\Users\\pranavsitoke\\Downloads\\Pranav_PersonalDocs\\Selenium_Automation\\SeleniumFramework\\src\\test\\java\\SeleniumFramework\\data\\PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}
