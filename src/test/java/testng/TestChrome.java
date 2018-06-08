package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;

public class TestChrome {
	
	WebDriver driver;
	
	@BeforeClass
	public void openChrome() {
		//-----------------------------Open chrome--------------------------------------------------
		System.out.println("*******************");
		System.out.println("launching chrome browser");
		driver = Browser.getBrowser("chrome");	
		driver.manage().window().maximize();
	}
	
  
	@Test
	public void testng1() throws InterruptedException {				        		
		//open url	
		driver.get("http://www.baidu.com");
		
		Thread.sleep(1000);
		//locate on inputbox
		driver.findElement(By.id("kw")).sendKeys("maven in chrome");
		        
		Thread.sleep(1000);
		//click search button
		driver.findElement(By.id("su")).click();
	        
		Thread.sleep(1000);
		//print page title
		String strPageTitle = driver.getTitle();
		System.out.println(strPageTitle);
		//validate result
		Assert.assertEquals(strPageTitle, "maven in chrome_");
		Reporter.log( strPageTitle, true );
	}
	
	@Test
	public void testng2() throws InterruptedException {
		//navigate to url		
		driver.navigate().to("http://www.google.com");
		 
		String strPageTitle = driver.getTitle();
		System.out.println("Page title: - "+strPageTitle);
		Assert.assertTrue(strPageTitle.equalsIgnoreCase("Google"), "Page title doesn't match");
		Reporter.log( strPageTitle, true );
	}

	@AfterClass
	public void closeChrome() {
		if(driver!=null) {
			System.out.println("Closing chrome browser");
			driver.quit();
		}
	}

}
