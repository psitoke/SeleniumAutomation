package PracticeTest.SeleniumFramework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

//import seleniumFramework.pageObjects.LandingPage;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class standAloneTest {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", 
				"C:\\Users\\pranavsitoke\\Downloads\\Pranav_PersonalDocs\\Selenium_Automation\\chromedriver-win64\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		//LandingPage Lp =new LandingPage(driver);
		String productName="ZARA COAT 3";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("anvit@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Avishi11");
		driver.findElement(By.id("login")).click();
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement ReqProduct = products.stream()
				.filter(prod -> prod.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		ReqProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartitems= driver.findElements(By.cssSelector(".cartSection h3"));
	
	Boolean match=cartitems.stream().anyMatch(cartItem-> cartItem.getText().equalsIgnoreCase(productName));
	Assert.assertTrue(match);
	
		driver.findElement(By.cssSelector(".cart button:first-of-type")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[placeholder='Select Country']")));
		
//		Actions a = new Actions(driver);
//		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
		
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("India");


		//driver.findElement(By.cssSelector(".ta-results button:last-child")).click();

		WebElement country = driver.findElement(By.cssSelector(".ta-results button:last-child"));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", country);
		

	WebElement submit = driver.findElement(By.cssSelector(".action__submit"));

		js.executeScript("arguments[0].click();", submit);
		
		String ConfirmMessage= driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
		driver.close();
		
		
	}
}
