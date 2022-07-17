package pages.homepage;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.Status;
import excelUtill.ExcelUtill;
import pages.accounting.Accounting;
import sql.FatchDataFromDataBase;
import webUtills.WebUtills;

public class HomePage {
	
	WebUtills utill;	
public HomePage(WebUtills utill) {

	this.utill=utill;
}

By hmPage_text=By.xpath("//div[text()='Inn-Flow']");

By acct_link=By.cssSelector("a#defaultSet-Accounting");

By sales_link=By.cssSelector("a#defaultSet-Sales");

By labour_link=By.cssSelector("a#defaultSet-Labor");

By proc_link=By.cssSelector("a#defaultSet-Procurement");

By set_link=By.cssSelector("a#defaultSet-Settings");

By act_dd=By.xpath("//div[text()='Accounting']");

By acct_bt=By.xpath("//div[text()='Accounts']");

By profile_Role_txt=By.cssSelector("div.department");

By profile_txt=By.cssSelector("div.userName");

By profile_clk=By.cssSelector("div.position");

By scroll_list=By.xpath("//div[@class='scroll-area']/div/div/a");

By labor_txt=By.xpath("//div[text()='Labor']");







public void wait_HomePage() {
	utill.explicitlyWait(utill.getdriver(), 10);
	utill.waitUntillVisibleOfElement(10,hmPage_text); 
   
}


public void validateUserRoleText(String expText) {

	utill.click(profile_clk, "User Profile");
	utill.verifyText(profile_Role_txt, expText);
	
}




static Workbook wbook ;
static Sheet sheetObj;	
static InputStream fi;
static OutputStream fo;
 Row rowObj;
 Cell cellObj;
 
 
 public void verifyTextOnMenuBar() 
 {
	 String menu=null;
	 String role=utill.getText(profile_txt);
	    utill.setText(role);
	 FatchDataFromDataBase.fatchData();
	 for (int i = 1; i <10; i++) {
    menu =ExcelUtill.getExcelObj().getOneDataFromExcel("SQLSheet/dataBase.xlsx", "Sheet3",i , 1);
    By menu_txt=By.xpath("//*[contains(text(),'"+menu+"')]");
    if(menu.equalsIgnoreCase("Profile"))
    	continue;
    		
	  utill.verifyText(menu_txt, menu);
	 } 

 } 
	
 
 
 public void verifyTheAcc_roles() {
		String act_txt;
	 String menu=null;
	 String role=utill.getText(profile_txt);
	 utill.setText(role);
	 
	 FatchDataFromDataBase.fatchData();
	 
	 menu =ExcelUtill.getExcelObj().getOneDataFromExcel(utill.getPropVal("dataBaseEx"), utill.getPropVal("Accounting_st"),1 , 1);
	 int lastRowNum=ExcelUtill.getLastRowNum();
	 
	 for (int i = 1; i <lastRowNum; i++) {
	
    menu =ExcelUtill.getExcelObj().getOneDataFromExcel(utill.getPropVal("dataBaseEx"), utill.getPropVal("Accounting_st"),i , 1);
   
    List<WebElement> scroll_sz=utill.getElements(scroll_list);
    for (int j = 0; j < scroll_sz.size(); j++) {
    	List<WebElement> scroll_lst=utill.getElements(scroll_list);
    	   act_txt=scroll_lst.get(j).getText(); 
    	    
    	   if(menu.equalsIgnoreCase(act_txt))
    		utill.verifyText(act_txt, menu);
    	   
	}

	
  }
	 
}
 
 public void verifyTheLabor_roles() {
		String act_txt;
	 String menu=null;
	 String role=utill.getText(profile_txt);
	 utill.setText(role);
	 
	 FatchDataFromDataBase.fatchData();
	 menu =ExcelUtill.getExcelObj().getOneDataFromExcel(utill.getPropVal("dataBaseEx"), utill.getPropVal("Labor_st"),1 , 1);
	 int lastRowNum=ExcelUtill.getLastRowNum();
	 for (int i = 1; i <lastRowNum; i++) {	
		 
 menu =ExcelUtill.getExcelObj().getOneDataFromExcel(utill.getPropVal("dataBaseEx"), utill.getPropVal("Labor_st"),i , 1);
 List<WebElement> scroll_sz=utill.getElements(scroll_list);
 for (int j = 0; j < scroll_sz.size(); j++) {
 	List<WebElement> scroll_lst=utill.getElements(scroll_list);
 	   act_txt=scroll_lst.get(j).getText(); 
 	    
 	   if(menu.equalsIgnoreCase(act_txt))
 		utill.verifyText(act_txt, menu);
 	   
	}
 }
	 
}

 public void clkOnLabor() {
	 
	 utill.click(labor_txt, "Labor");
 }
 
 


















public Accounting NavigateOnAccount() {
	
	 utill.click( acct_link,"Account link");
	 utill.getExtentLogger().log(Status.PASS, String.format(" Navigated on Account "));
	return new Accounting(utill);
	
}
public Accounting mouseOvertoAccounting() {
	utill.mouseOver(act_dd," Accounting ");
	utill.click_Act(act_dd,"Accounting link");
	return new Accounting(utill);
	
}
public Accounting clkOnAccounting() {
	
	utill.click_Act(act_dd,"Accounting link");
	return new Accounting(utill);
	
}



 
 








}
