package testCases;



import java.util.Map;

import org.testng.annotations.Test;
import BasePage.TestBasePage;
import excelUtill.ExcelUtill;
import pages.accounting.Accounting;
import pages.homepage.HomePage;
import pages.login.LoginPage;
import pages.roports.Report;




public class TestCase_TC01 extends TestBasePage {
Map<String,String> tcData;
LoginPage   login;	
HomePage hmPage;
Accounting accPage;
@Test(priority = 1)
public void  TC001ValidateLoginPage() {
	   login=new LoginPage(utill);
	   login.validateUserName();
	
	   login.validateUserPass();
	 
	   login.validateUserLogIn();
    
}

@Test(priority = 2)
public void TC0002GenrateReport() throws InterruptedException {	
	     tcData= utill.getTestCaseDataMap();
	   login=new LoginPage(utill);
	   hmPage=  login.validUserLogIn();
	   hmPage.wait_HomePage();
	   
	  Accounting accPg=hmPage.NavigateOnAccount();
	 
	 Thread.sleep(2000);
	 accPg.windowHandle(tcData.get("NvtToDash"));
	 accPg.clickOnReport();
	
	 String  nameOfDepartment[] =utill.getKey("ReportName");
	 Report report=accPg.NavigateOnReport(nameOfDepartment[0]);
	 accPg.windowHandle(tcData.get("NvtToReport"));
	  String [] date=utill.getKey("Date");
	  report.putDateOReport(date[0]);
	 
     String [] HIDs=utill.getKey("EHID");
	 for (int i = 0; i <= HIDs.length-1; i++) {
	       HIDs=utill.getKey("EHID");
           report.chaked_ChakeBoxes( HIDs[i]);
	}
     report.loadTheReport();
     report.focusOnPage(tcData.get("NvtOnReport"));
     report.downloadTheReportInExcel();
   
}
@Test(priority = 3)
public void TC0003ValidateWebTableData() {
	//String path="src/main/resources/WorkBooks/WriteData.xlsx";
	  tcData= utill.getTestCaseDataMap();
	  login=new LoginPage(utill);
	  hmPage=  login.validUserLogIn();
	  accPage = hmPage.mouseOvertoAccounting();
	  accPage.mouseOvertoAccountAndclick();
	   int size=accPage.NumOfRowInWebTable();
	   for (int i = 1; i <=size ; i++) {
		for (int j = 1; j <7 ; j++) {
		String exp_text=ExcelUtill.getExcelObj().getOneDataFromExcel(utill.getPropVal("writeDataEx"), "AccountsData", i, j);
		String act_text=accPage.getWebData(i,j);
		  utill.verifyText(act_text, exp_text);
	  
	    
		}
	}
	    	  	
}


}
