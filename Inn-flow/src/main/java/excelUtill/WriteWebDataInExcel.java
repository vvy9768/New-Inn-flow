package excelUtill;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import webUtills.WebUtills;

public class WriteWebDataInExcel {
	static String  path="src/main/resources/WorkBooks/WriteData.xlsx";
	public static void main(String[] args) throws IOException {
		
	
		getRollsData();
	   // webdata();
	
	
	
	}
	static WebUtills gm;
	public  static void  webdata() throws IOException {
		writeData("Sheet1", 0, 1, "Account Number");
		writeData("Sheet1", 0, 2, "Bank");
		writeData("Sheet1", 0, 3, "COA");
		writeData("Sheet1", 0, 4, "NickName");
		writeData("Sheet1", 0, 5, "EHID");
		writeData("Sheet1", 0, 6, "Account type");
		writeData("Sheet1", 0, 7, "Inn-Flow Balance");
		
	gm=WebUtills.getObj();
	gm.launchBrowser("chrome");
	gm.getdriver().get("https://inn-flow-v2-testv2.azurewebsites.net");
	gm.getdriver().manage().window().maximize();
	gm.getdriver().findElement(By.cssSelector("input#formBasicEmail")).sendKeys("admin.202");
	gm.getdriver().findElement(By.cssSelector("input#formBasicPassword")).sendKeys("test.123");
	gm.getdriver().findElement(By.cssSelector("button[class='lg-btn action btn btn-primary']")).click();
   gm.getdriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	Actions act =new Actions(gm.getdriver());
	act.moveToElement(gm.getdriver().findElement(By.xpath("//div[text()='Accounting']"))).click().build().perform();;	
	act.moveToElement(gm.getdriver().findElement(By.xpath("//div[text()='Accounts']"))).click().build().perform();;
  
	int size=gm.getdriver().findElements(By.xpath("//table[@class='table table-hover table-bordered table-condensed']/tbody/tr")).size();
    for (int j = 1; j <=size; j++) {
    for (int i = 1; i <=7; i++) { 	
	  WebElement we= gm.getdriver().findElement(By.xpath("//table[@class='table table-hover table-bordered table-condensed']//tbody/tr["+j+"]/td["+i+"]"));
    ExcelUtill.getExcelObj().writeData(path,"Sheet1", j, i,we.getText() );
      System.out.print( we.getText()+"  ");
 
    }System.out.println();
    
    }
   
   
    
}
	static Workbook wbook;
	static InputStream fi;
	static OutputStream fo;
	static Sheet sheetObj;
	static Row rowObj;
	static Cell cellObj;
public static void writeData(String sheetName,int rowNum,int cellNum,String data) throws IOException {
	//File file= new File(path);
//	if(!file.exists())
//		wbook=new XSSFWorkbook();
//	      fo= new FileOutputStream(path);
//	       wbook.write(fo);
//	       
	       
	fi=new FileInputStream(path);
	wbook= new XSSFWorkbook(fi);
	
	if(wbook.getSheetIndex(sheetName)==-1)
	wbook.createSheet(sheetName);
	
	sheetObj=wbook.getSheet(sheetName);
	
	if(sheetObj.getRow(rowNum)==null) 
		sheetObj.createRow(rowNum);
		rowObj=sheetObj.getRow(rowNum);
		
		cellObj=rowObj.createCell(cellNum);
		cellObj.setCellValue(data);
		
		fo=new FileOutputStream(path);
		wbook.write(fo);
		wbook.close();
	  fi.close();
	  fo.close();
}

public static  void getRollsData() {	

gm=WebUtills.getObj();
gm.initHtmlReport();
gm.setExtentLogger("TestCaseName");
gm.launchBrowser("chrome");
gm.getdriver().manage().window().maximize();
gm.get("https://inn-flow-v2-testv2.azurewebsites.net/");

gm.imlecitlyWait(30);
gm.getdriver().findElement(By.cssSelector("input#formBasicEmail")).sendKeys("jyoti.rajpal6");
gm.getdriver().findElement(By.cssSelector("input#formBasicPassword")).sendKeys("test.123");
gm.getdriver().findElement(By.cssSelector("button[class='lg-btn action btn btn-primary']")).click();
gm.getdriver().findElement(By.xpath("//div[contains(text(),'Administration')]")).click();
gm.getdriver().findElement(By.xpath("//div[contains(text(),'Roles & Permissions')]")).click();
gm.getdriver().findElement(By.xpath("//div[text()='Corporate Controller']")).click();


}
}
