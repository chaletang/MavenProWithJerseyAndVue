package testng;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Table {
	private WebDriver driver;
	private WebElement table;
	private WebElement tbody;   
	private List<WebElement> rows;    
	private List<WebElement> cells; 
	
	public Table(WebElement table) {  
        this.table = table;
    }
	
	public void getTbody(){
		this.tbody = this.table.findElement(By.tagName("tbody"));
	}
	
	public void getRows(){
		getTbody();
		this.rows = this.tbody.findElements(By.tagName("tr"));
	}
	
	public int getRowCount(){
		getRows();
		return this.rows.size();
	}
	
	public void getCells(int rowNum){
		getRows();
		WebElement specificRow = this.rows.get(rowNum); 
		this.cells = specificRow.findElements(By.tagName("td"));
	}
	
	public int getCellCount(int rowNum){
		getCells(rowNum);
		return this.cells.size();
	}
	
	public WebElement getCell(int rowNum, int colNum){
		getRows();
		WebElement specificRow = rows.get(rowNum); 
		this.cells = specificRow.findElements(By.tagName("td")); 
		return this.cells.get(colNum); 
	}
	
	public String getCellData(WebElement cell){
		return cell.getText();
	}
}
