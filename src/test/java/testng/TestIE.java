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

public class TestIE {
	
	WebDriver driver;
	
	@BeforeClass
	public void openIE() {
		//-----------------------------Open chrome--------------------------------------------------
		System.out.println("*******************");
		System.out.println("launching ie browser");
		driver = Browser.getBrowser("IE");	
		driver.manage().window().maximize();
	}
	
  
	@Test
	public void testng1() throws InterruptedException {				        		
		//open url	
		driver.get("http://qualitycenter.okla.seagate.com/qcbin/start_a.jsp");
		
		Thread.sleep(1000);
		//locate on inputbox
		//driver.findElement(By.id("kw")).sendKeys("maven in ie");
		        
		Thread.sleep(1000);
		//click search button
		//driver.findElement(By.id("su")).click();
	        
		Thread.sleep(1000);
		//print page title
		String strPageTitle = driver.getTitle();
		System.out.println(strPageTitle);
		//validate result
		Assert.assertEquals(strPageTitle, "maven in ie");
		Reporter.log( strPageTitle, true );
	}
	

	/*@AfterClass
	public void closeIE() {
		if(driver!=null) {
			System.out.println("Closing ie browser");
			driver.quit();
		}
	}*/

}
