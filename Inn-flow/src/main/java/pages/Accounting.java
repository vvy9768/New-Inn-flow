package pages;

import org.openqa.selenium.By;

import webUtills.WebUtills;

public class Accounting {
	
	
	WebUtills utill;	
public Accounting(WebUtills utill) {
	// TODO Auto-generated constructor stub
	this.utill=utill;
}

By clk_Report=By.cssSelector("a#Reports");

By clkOnBallenceSheet_=By.xpath("//td[text()='BALANCE SHEET']");

By text_we=By.cssSelector("pan#ctl00_ContentPlaceHolder1_LblBorad");






public void windowHandle(String title) {
	utill.pageLoadTimeOut(30);
	utill.getWindowHandleByTitle( title);
}



public void clickOnReport() {
	utill.click( clk_Report);
}



public Report NavigateOnReport(String nameOfDepartment) {
	
	
	By nmOfDertment=By.xpath("//td[text()='"+nameOfDepartment+"']");
	utill.click(nmOfDertment);
	return new Report(utill);
}


}