package pages.accounting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import pages.roports.Report;
import webUtills.WebUtills;

public class Accounting {
	
	
	WebUtills utill;	
public Accounting(WebUtills utill) {
	// TODO Auto-generated constructor stub
	this.utill=utill;
}

By report_bt=By.cssSelector("a#Reports");

By acct_bt=By.xpath("//div[text()='Accounts']");

By webTb_lk_tr=By.xpath("//table[@class='table table-hover table-bordered table-condensed']/tbody/tr");

By webTb_lk_td=By.xpath("//table[@class='table table-hover table-bordered table-condensed']/tbody/tr/td");




public void windowHandle(String title) {
	utill.pageLoadTimeOut(30);
	utill.getWindowHandleByTitle( title);
}



public void clickOnReport() {
	utill.click( report_bt,"Report button");
}



public Report NavigateOnReport(String nameOfDepartment) {
	
	By nmOfDertment=By.xpath("//td[text()='"+nameOfDepartment+"']");
	utill.click(nmOfDertment,nameOfDepartment);
	return new Report(utill);
}

public void mouseOvertoAccountAndclick() {
	utill.mouseOver(acct_bt," Accounting ");
	utill.click_Act(acct_bt,"Account dropdown");
	
}
public int NumOfRowInWebTable() {
	int size=utill.getdriver().findElements(webTb_lk_tr).size();
	   
	return size;
}



WebElement weData;
public String  getWebData(int rowNum,int CellNum) {
	 	
	 weData=utill.getdriver().findElement(By.xpath("//table[@class='table table-hover table-bordered table-condensed']//tbody/tr["+rowNum+"]/td["+CellNum+"]"));
     
    return weData.getText();
	 
}

}
