package testCases;

import java.util.Map;

import org.testng.annotations.Test;

import BasePage.TestBasePage;
import pages.HomePage;
import pages.LoginPage;

public class TestCase_TC03 extends TestBasePage{
    
	
	Map<String,String> tcData;
	LoginPage loginPg;	
	HomePage homePg;
	
	
	@Test
	public void validateUserRoletext() {
		tcData= utill.getTestCaseDataMap();
		loginPg=new LoginPage(utill);
	
		homePg=loginPg.validUserLogIn();
		
		homePg.validateUserRoleText(tcData.get("roleText"));
		
		
	}
	
	
	@Test
	public void validateMenuBar() {
		tcData= utill.getTestCaseDataMap();
		loginPg=new LoginPage(utill);
	
		homePg=loginPg.validUserLogIn();
		
	    homePg.verifyTextOnMenuBar();
		
		
	}
	
	@Test
	public void validateAccountingMenu() {
		
		loginPg=new LoginPage(utill);
	
		homePg=loginPg.validUserLogIn();
		
		homePg.clkOnAccounting();
		
		homePg.verifyTheAcc_roles();
		
		
	}
	@Test
	public void validateLabourMngMenu() {
		loginPg=new LoginPage(utill);
		
		homePg=loginPg.validUserLogIn();
		
		homePg.clkOnLabor();
		
		homePg.verifyTheLabor_roles();
	}
	
	
	
	
	
	
	
}
