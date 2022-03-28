package BasePage;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.log4testng.Logger;
import com.aventstack.extentreports.Status;
import Listeners.SendJavaMailReport;
import excelUtill.ExcelUtill;
import webUtills.WebUtills;

public class TestBasePage {
	
protected WebUtills utill=WebUtills.getObj();
protected static final Logger logger = Logger.getLogger(TestBasePage.class.getClass());  
protected Map<String ,String > mapdata;
private String className;
private String  methodName;

@BeforeSuite
public void beforeSuit() {
	
	utill.initHtmlReport();

}
 
 





@BeforeMethod ()
public void beforeMethod(Method name) {
	//==========================MethodName, className=============================//
	     methodName=name.getName();
	     String [] clsName=super.getClass().toString().split(" ");
	     className=clsName[1].toString().replaceAll("testCases.", "");
//==============================SetMethodName==================================//	               
	    utill.setExtentLogger(methodName);
		
	   	utill.launchBrowser(utill.getPropVal("browser"));
	  	utill.maximize();
	   	//======================Reports Url Config.=============================//
		
	  	if(methodName.equalsIgnoreCase("TC0002GenrateReport"))
		utill.get(utill.getPropVal("url"));
		else
			utill.get(utill.getPropVal("QA_url"));
	    String crtUrl= utill.getCurrentUrl();
	    if(!crtUrl.equalsIgnoreCase(utill.getPropVal("QA_Login")))
	    	utill.get(utill.getPropVal("QA_Login"));
	    	
	   
	  	//=====================================================================//
	  	
		utill.imlecitlyWait(30);

		utill.setTestCaseData(ExcelUtill.getExcelObj().getDataFromExcelFromTestCase(utill.getPropVal("testCaseEx"),className, methodName, "TestCaseID"));
	     		
		//utill.setTestCaseData( ExcelUtill.getExcelObj().getDataFromExcel(utill.getPropVal("readDataEx"), "LogInPage", methodName, "TestCaseID"));   
	   //utill.setTestCaseData( ExcelUtill.getExcelObj().getDataFromExcel(utill.getPropVal("readDataEx"), "EHID", "TC001", "Report"));
		
 }







	@AfterMethod
	public void afterMethod(ITestResult result) {
		logger.info("Test Suite Execution method has been Start");
		  
		if (result.getStatus() == ITestResult.FAILURE) {
			
		//	ExcelUtill.getExcelObj().writeResultInTestCase(utill.getPropVal("testCaseEx"),className, methodName, "Ressult Status", "FAIL");
			utill.getExtentLogger().log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); 
																								
			utill.getExtentLogger().log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); 

			String screenshotPath = utill.takeSnapshot(utill.getdriver(),result.getName()); 
			try {
			 utill.getExtentLogger().addScreenCaptureFromPath(screenshotPath);
			} catch (IOException e) {
			
				e.printStackTrace();
			}

		} else if (result.getStatus() == ITestResult.SKIP) {
			
			utill.getExtentLogger().log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
			
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			utill.getExtentLogger().log(Status.PASS, "Test Case PASSED IS " + result.getName());
		
		}
		
	
		logger.info("Test Suite Execution method has been completed");
		utill.quitBrowser();
	}

	
	
	

@AfterSuite
public void afterSuit() {
	utill.flushExtentsReport();
	//new SendJavaMailReport(utill.getReportName()).main();

	 
}

}
