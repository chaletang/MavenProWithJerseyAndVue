package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver; 
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TestFirefox {
	
	WebDriver driver;
	
	@BeforeClass
	public void openFirefox() {
		//-----------------------------Open firefox--------------------------------------------------
		System.out.println("*******************");
		System.out.println("launching firefox browser");
		driver = Browser.getBrowser("firefox");
		driver.manage().window().maximize();
	}
	
  
	@Test
	public void testng1() throws InterruptedException {				        		
		//open url	
		driver.get("http://www.baidu.com");
		
		Thread.sleep(1000);
		//locate on inputbox
		driver.findElement(By.id("kw")).sendKeys("maven in ff");
		        
		Thread.sleep(1000);
		//click search button
		driver.findElement(By.id("su")).click();
	        
		Thread.sleep(1000);
		//print page title
		String strPageTitle = driver.getTitle();
		System.out.println(strPageTitle);
		//validate result
		Assert.assertEquals(strPageTitle, "maven in ff_百度搜索");
	}
	
	@Test
	public void testng2() throws InterruptedException {
		//navigate to url		
		driver.navigate().to("http://www.google.com");
		 
		String strPageTitle = driver.getTitle();
		System.out.println("Page title: - "+strPageTitle);
		Assert.assertTrue(strPageTitle.equalsIgnoreCase("Google"), "Page title doesn't match");
	}
	
	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

	@AfterClass
	public void closeFirefox() {
		if(driver!=null) {
			System.out.println("Closing firefox browser");
			driver.quit();
		}
	}

}
