package pages;

import org.openqa.selenium.By;

import webUtills.WebUtills;

public class Report {

	WebUtills utill;
	public Report(WebUtills utill) {
		this.utill=utill;
		// TODO Auto-generated constructor stub
	}
	
	By clkOn_date=By.cssSelector("select#ctl00_ContentPlaceHolder1_ddlEndingDate");
	By runReport=By.cssSelector("button#ctl00_ContentPlaceHolder1_btnSer");
	By downPdf=By.cssSelector("input#btnPdf");
	By downExcel=By.cssSelector("input#btnExcel");
	
	
	
	public void focusOnPage(String exp_url) {
//	   utill.explicitlyWait(utill.getdriver(), 50);
//		utill.waitUntillUrlToBePresent(exp_url);
		utill.HoldOn(50000);
		utill.getWindowHandleByUrl(exp_url);
		
	}
	
	public void putDateOReport(String date) {
		utill.selectByValue(clkOn_date, date);
	}
	
	
	 public void chaked_ChakeBoxes(String companyName) {
		 if(companyName=="All Hotals") {
			 By cpmnyName=By.xpath("//span[text()='"+companyName+"']//ancestor::div//input[@id='ctl00_ContentPlaceHolder1_ckhHotel']");			
			 utill.click( cpmnyName);
		 }else {			 
		By cpmnyName=By.xpath("//td[text()='"+companyName+"']//ancestor::tr//input");
		utill.click( cpmnyName);
		 }
	 }
	
	public void loadTheReport() {
		
		utill.click(runReport);
	}
	
	
	public void downloadTheReportInPDF() {
		
		utill.click(downPdf);
	}

	public void downloadTheReportInExcel() {

		utill.click(downExcel);
	}


	
	
	
	
	
	
	
}
