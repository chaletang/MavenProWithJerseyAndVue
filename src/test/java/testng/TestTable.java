package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TestTable {
	
	private WebDriver driver;  
	private Table table;
	
	@BeforeClass
	//------------------------------Open chrome------------------------------//
	public void openChrome() {
		System.out.println("*******************");
		System.out.println("launching chrome browser");
		driver = Browser.getBrowser("chrome");	
		driver.manage().window().maximize();
	}
	
	@Test
	//------------------------------Open URL------------------------------//
	public void openUrl() throws InterruptedException {
		System.out.println("*******************");
		driver.get("http://localhost:8080/MyMavenWebTest/");
		System.out.println("open url");
		Thread.sleep(5000);
	}
	
	@Test
	//------------------------------Find Table------------------------------//
	public void findTable() throws InterruptedException {
		System.out.println("*******************");
		table = new Table(driver.findElement(By.id("table")));
		System.out.println("find table");
		Thread.sleep(2000);
	}
	
	@Test
	//------------------------------Print Table Row Count------------------------------//
	public void printTableRowCount() throws InterruptedException {
		System.out.println("*******************");
		int expectedData = 4;
		int actualData = table.getRowCount();
		System.out.println("expected row count is 4, actual row count is:" + actualData);
		Assert.assertEquals(expectedData,actualData);
		Thread.sleep(2000);
	}
	
	@Test
	//------------------------------Print Table Cell Data------------------------------//
	public void printTableCellData() throws InterruptedException {
		System.out.println("*******************");
		int rowNum = 2;
		int cellNum = 2;	
		WebElement cell = table.getCell(rowNum, cellNum);
		String cellData = table.getCellData(cell);
		System.out.println("table[" + rowNum + "," + cellNum + "]——————>" + cellData);
		Thread.sleep(2000);
	}
	
	@Test
	//------------------------------Print Table Cell Sum------------------------------//
	public void printTableCellSum() throws InterruptedException {
		System.out.println("*******************");
		int rows = table.getRowCount();
		int cellNum = 1;
		int sum = 0;
		for(int rowNum = 0; rowNum < rows; rowNum = rowNum + 1){  
			WebElement cell = table.getCell(rowNum, cellNum);
			int cellData = Integer.parseInt(table.getCellData(cell));
			System.out.println("[" + rowNum + "," + cellNum + "]——————>" + cellData);	
			sum = sum + cellData;
		}
		System.out.println("Cell(" + cellNum + ")Sum——————>" + sum);
		Thread.sleep(2000);
	}
	
	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

	@AfterClass
	//------------------------------Close chrome------------------------------//
	public void closeChrome() {
		if(driver!=null) {
			System.out.println("*******************");
			System.out.println("Closing chrome browser");
			driver.quit();
		}
	}

}
