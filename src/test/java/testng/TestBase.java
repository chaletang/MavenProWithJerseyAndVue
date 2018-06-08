package testng;



import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestBase extends RemoteWebDriver {
	WebDriver driver; 
	String browser = "chrome";
	String url = "http://seagate--test.cs61.my.salesforce.com/";

	@BeforeSuite
	public void setUp() throws InterruptedException {
		System.out.println("launching" + browser + "browser");
		driver = Browser.getBrowser(browser);	
		driver.manage().window().maximize();
		
		Thread.sleep(1000);
		
		System.out.println("open URL");
		driver.get(url);
		
		Thread.sleep(3000);
		
		driver.findElement(By.id("username")).sendKeys("521837");	
		driver.findElement(By.id("password")).sendKeys("Sally45689");
		driver.findElement(By.tagName("button")).submit();
		
		Thread.sleep(3000);
	}
	
	@AfterSuite
	public void teardown() throws InterruptedException {
		if(driver!=null) {
			System.out.println("closing" + browser + "browser");
			driver.quit();
		}
	}
}
