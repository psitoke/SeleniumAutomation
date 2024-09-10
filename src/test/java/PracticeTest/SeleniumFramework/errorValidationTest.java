package PracticeTest.SeleniumFramework;

import org.testng.Assert;
import org.testng.annotations.Test;

import PracticeTest.SeleniumFramework.TestComponents.BaseTest;

public class errorValidationTest extends BaseTest{

	@Test(groups= {"errorHandling"})
	public void LoginErrorValidation() {
		
		Lp.loginapplication("anvit@gmail.com","abc@123");
		String errorMessage=Lp.errorMessageValidation();
		Assert.assertEquals("Incorrect email or password.", errorMessage);
	}
	
	@Test(groups= {"errorHandling"})
	public void BlankLogin()
	{
		Lp.errorMessageforblankinput();
		String unameError= Lp.emailError();
		String passError= Lp.passError();
		Assert.assertEquals("*Email is required", unameError);
		Assert.assertEquals("*Password is required", passError);
	}
}
