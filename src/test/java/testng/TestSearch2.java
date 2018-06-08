package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import core.Format;

public class TestSearch2 extends TestBase {
	String url = "https://seagate--test--c.cs61.visual.force.com/apex/Sales_RFQ_Summary_VF?sfdc.tabName=01r3A000001HIj0";
	String className = this.getClass().getSimpleName();
	Table table;
	int totalPage;
	int currentPage;
	
	@BeforeClass
	public void openRFQ() throws InterruptedException {
		System.out.println("open RFQ");
		driver.findElement(By.cssSelector(".wt-RFQ > a")).click();
		Thread.sleep(3000);
	}	
	
	@BeforeMethod
	public void commonSet() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(3000);
	}	
	
	@Test
	public void validateSearchByRFQID() throws InterruptedException  {
		String pRfqId = Format.getParam(className, "pRfqId");
		System.out.println("Type RFQID:" + pRfqId);
		driver.findElement(By.name("j_id0:search:j_id24")).sendKeys(pRfqId);	
		driver.findElement(By.className("rfq-apply")).click();
		Thread.sleep(3000);
		
		totalPage = Integer.parseInt(driver.findElement(By.cssSelector(".rfq-pagination")).getText().trim().split(" ")[3]);
		System.out.println("Total Page:" + totalPage);
		currentPage = Integer.parseInt(driver.findElement(By.id("j_id0:tbl:pageNumber")).getAttribute("value"));
		System.out.println("Current Page:" + currentPage);
		if(currentPage != 1){
			driver.findElement(By.id("first")).click();
		}
		Thread.sleep(3000);

		for(int page = 1; page <= totalPage; page = page + 1){ 
			System.out.println("Current Page is:" + page);
			table = new Table(driver.findElement(By.cssSelector(".rfq-search-table > .rfq-content-table > table")));
			int rows = table.getRowCount();
			System.out.println("Rows:" + rows);
			
			for(int rowNum = 0; rowNum < rows; rowNum = rowNum + 1){  
				int cellNum = table.getCellCount(rowNum);
				System.out.println("Cells:" + cellNum);
				
				WebElement cell = table.getCell(rowNum, 0);
				String cellData = table.getCellData(cell);
				System.out.println("RFQ ID[" + rowNum + ",0]:" + cellData);	
				
				Boolean testStatus = cellData.startsWith(pRfqId);
				Assert.assertTrue(testStatus);
			}
			if(page < totalPage){
				driver.findElement(By.id("next")).click();
			}
			
			Thread.sleep(3000);
		}
	}
	
	@Test
	public void validateSearchByStatus() throws InterruptedException  {
		String pStatus = Format.getParam(className, "pStatus");
		System.out.println("Select Status:" + pStatus);
		WebElement dropdown = driver.findElement(By.name("j_id0:search:j_id26"));
		Select select = new Select(dropdown);
		select.selectByValue(pStatus);
		driver.findElement(By.className("rfq-apply")).click();
		Thread.sleep(3000);
		
		totalPage = Integer.parseInt(driver.findElement(By.cssSelector(".rfq-pagination")).getText().trim().split(" ")[3]);
		System.out.println("Total Page:" + totalPage);
		currentPage = Integer.parseInt(driver.findElement(By.id("j_id0:tbl:pageNumber")).getAttribute("value"));
		System.out.println("Current Page:" + currentPage);
		if(currentPage != 1){
			driver.findElement(By.id("first")).click();
		}
		Thread.sleep(3000);

		for(int page = 1; page <= totalPage; page = page + 1){ 
			System.out.println("Current Page is:" + page);
			table = new Table(driver.findElement(By.cssSelector(".rfq-search-table > .rfq-content-table > table")));
			int rows = table.getRowCount();
			System.out.println("Rows:" + rows);
			
			for(int rowNum = 0; rowNum < rows; rowNum = rowNum + 1){  
				int cellNum = table.getCellCount(rowNum);
				System.out.println("Cells:" + cellNum);
				
				WebElement cell = table.getCell(rowNum, 3);
				String cellData = table.getCellData(cell);
				System.out.println("RFQ ID[" + rowNum + ",3]:" + cellData);	
				
				Assert.assertEquals(cellData,pStatus);
			}
			if(page < totalPage){
				driver.findElement(By.id("next")).click();
			}
			Thread.sleep(3000);
		}
	}
	@Test
	public void validateSearchByUserName() throws InterruptedException  {
		String pUserName = Format.getParam(className, "pUserName");
		System.out.println("Type UserName:" + pUserName);
		driver.findElement(By.name("j_id0:search:j_id36")).sendKeys(pUserName);	
		driver.findElement(By.className("rfq-apply")).click();
		Thread.sleep(3000);
		
		totalPage = Integer.parseInt(driver.findElement(By.cssSelector(".rfq-pagination")).getText().trim().split(" ")[3]);
		System.out.println("Total Page:" + totalPage);
		currentPage = Integer.parseInt(driver.findElement(By.id("j_id0:tbl:pageNumber")).getAttribute("value"));
		System.out.println("Current Page:" + currentPage);
		if(currentPage != 1){
			driver.findElement(By.id("first")).click();
		}
		Thread.sleep(3000);

		for(int page = 1; page <= totalPage; page = page + 1){ 
			System.out.println("Current Page is:" + page);
			table = new Table(driver.findElement(By.cssSelector(".rfq-search-table > .rfq-content-table > table")));
			int rows = table.getRowCount();
			System.out.println("Rows:" + rows);
			
			for(int rowNum = 0; rowNum < rows; rowNum = rowNum + 1){  
				int cellNum = table.getCellCount(rowNum);
				System.out.println("Cells:" + cellNum);
				
				WebElement cell = table.getCell(rowNum, 2);
				String cellData = table.getCellData(cell);
				System.out.println("RFQ ID[" + rowNum + ",2]:" + cellData);	
				
				Boolean testStatus = cellData.contains(pUserName);
				Assert.assertTrue(testStatus);
			}
			if(page < totalPage){
				driver.findElement(By.id("next")).click();
			}
			
			Thread.sleep(3000);
		}
	}
}
