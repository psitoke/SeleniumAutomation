package PracticeTest.SeleniumFramework.TestComponents;

import java.io.File;
//import java.io.FileInputStream;
import java.io.IOException;
//import java.util.Properties;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import seleniumFramework.pageObjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage Lp;
	public WebDriver initializeDriver() throws IOException
	{
		
//		Properties prop= new Properties();
//		FileInputStream fis= new FileInputStream("C:\\Users\\pranavsitoke\\Downloads\\Pranav_PersonalDocs\\Selenium_Automation\\SeleniumFramework\\src\\main\\java\\seleniumFramework\\resources\\GlobalData.properties");
//		prop.load(fis);
//		String BrowserName= prop.getProperty("browser");
		
//		if (BrowserName.equalsIgnoreCase("chrome"))
//		{
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\pranavsitoke\\Downloads\\Pranav_PersonalDocs\\Selenium_Automation\\chromedriver-win64\\chromedriver.exe");
			driver=new ChromeDriver();
			
		//}
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.manage().window().maximize();
		
		return driver;
	}
	
	
	public List<HashMap<String,String>> getJsonDataToMap(String filepath) throws IOException
	{
		//read json to string
		
		String jsonContent=	FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
	
		//convert string to HashMap Jackson DataBind 
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	
	}
	
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(
				"C:\\Users\\pranavsitoke\\Downloads\\Pranav_PersonalDocs\\Selenium_Automation\\SeleniumFramework\\Screenshots"
						+ "\\reports\\" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return "C:\\Users\\pranavsitoke\\Downloads\\Pranav_PersonalDocs\\Selenium_Automation\\SeleniumFramework\\Screenshots"
				+ "\\reports\\" + testCaseName + ".png";
	}

	
	
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver= initializeDriver();
		Lp = new LandingPage(driver);
		Lp.goTo();
		return Lp;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}
}
