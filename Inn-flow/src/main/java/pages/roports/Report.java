package pages.roports;

import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;

import webUtills.WebUtills;

public class Report {

	WebUtills utill;
	public Report(WebUtills utill) {
		this.utill=utill;
	}
	
	
	By clkOn_date=By.cssSelector("select#ctl00_ContentPlaceHolder1_ddlEndingDate");
	By runReport=By.cssSelector("button#ctl00_ContentPlaceHolder1_btnSer");
	By downPdf=By.cssSelector("input#btnPdf");
	By downExcel=By.cssSelector("input#btnExcel");
	
	
	
	public void focusOnPage(String exp_url) {
		utill.HoldOn(50000);
		utill.getWindowHandleByUrl(exp_url);
		
	}
	
	public void putDateOReport(String date) {
		utill.selectByValue(clkOn_date, date);
	}
	
	
	 public void chaked_ChakeBoxes(String companyName) {
		 if(companyName.equalsIgnoreCase("Hotel Accounts")) {
			 By cpmnyName=By.xpath("//span[text()='"+companyName+"']//ancestor::div//input[@id='ctl00_ContentPlaceHolder1_ckhHotel']");			
			 utill.click( cpmnyName,companyName);
		 }
		 else if (companyName.equalsIgnoreCase("Non-Hotel Accounts")) {
			 By cpmnyName=By.cssSelector("input#ctl00_ContentPlaceHolder1_chkAllNH");
				utill.click( cpmnyName,companyName);	
		 }
		 else{			 
		     By cpmnyName=By.xpath("//td[text()='"+companyName+"']//ancestor::tr//input");
		utill.click( cpmnyName,companyName);
		 }
	 }
	
	 
	public void loadTheReport() {
		
		utill.click(runReport,"load Report");
	}
	
	
	public void downloadTheReportInPDF() {
		
		utill.click(downPdf,"pdf button");
		 utill.getExtentLogger().log(Status.PASS, " Report is created  Successfully ");

	}

	public void downloadTheReportInExcel() {

		utill.click(downExcel,"excel button ");
		 utill.getExtentLogger().log(Status.PASS, " Report is created  Successfully ");

	}


	
	
	
	
	
	
	
}
