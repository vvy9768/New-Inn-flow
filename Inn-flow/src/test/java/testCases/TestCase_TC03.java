package testCases;

import java.util.Map;

import org.testng.annotations.Test;

import BasePage.TestBasePage;
import pages.homepage.HomePage;
import pages.login.LoginPage;

public class TestCase_TC03 extends TestBasePage{
    
	
	Map<String,String> tcData;
	LoginPage loginPg;	
	HomePage homePg;
	
	
	@Test(priority = 1,groups = "Rolls&permission")
	public void validateUserRoletext() {
		tcData= utill.getTestCaseDataMap();
		loginPg=new LoginPage(utill);
	
		homePg=loginPg.validUserLogIn();
		
		homePg.validateUserRoleText(tcData.get("roleText"));
		
		
	}
	
	
	@Test (priority = 2,groups = "Rolls&permission")	
	public void validateMenuBar() {
		tcData= utill.getTestCaseDataMap();
		loginPg=new LoginPage(utill);
	
		homePg=loginPg.validUserLogIn();
		
	    homePg.verifyTextOnMenuBar();
		
		
	}
	
	@Test (priority = 3,groups = "Rolls&permission")
	public void validateAccountingMenu() {
		
		loginPg=new LoginPage(utill);
	
		homePg=loginPg.validUserLogIn();
		
		homePg.clkOnAccounting();
		
		homePg.verifyTheAcc_roles();
		
		
	}
	@Test(priority = 4, groups = "Rolls&permission")
	public void validateLabourMngMenu() {
		loginPg=new LoginPage(utill);
		
		homePg=loginPg.validUserLogIn();
		
		homePg.clkOnLabor();
		
		homePg.verifyTheLabor_roles();
	}
	
	
	
	
	
	
	
}
