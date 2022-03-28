package testCases;

import java.util.Map;


import org.testng.annotations.Test;

import BasePage.TestBasePage;
import pages.login.LoginPage;


public class TestCase_TC02 extends TestBasePage{
	
Map<String,String> tcData;
LoginPage login;	


	
	
	

  @Test(priority = 1)
public void TC001ValidLogin() {
 
	tcData= utill.getTestCaseDataMap();
	      
		   login=new LoginPage(utill);
		   
		   login.validateUserName();
		
		   login.validateUserPass();
		 
		   login.validateUserLogIn();
		 
		   login.verifyHomePage(tcData.get("hmLogo"));
	    
	}
	
	@Test(priority = 2, enabled = true)
	public void TC002InvalidLoginWithPass() {
		tcData= utill.getTestCaseDataMap();
		
		login=new LoginPage(utill);
		
		login.validateUserName();
		
		login.validateUserPass();
		
		login.validateUserLogIn();
		
		login.verifyTextOnWrongCred(tcData.get("exp_text"));
		
		
	}
	
	
	@Test(priority = 3)
	public void TC003InvalidLoginWith_InactiveId(){
		tcData= utill.getTestCaseDataMap();
		
		login=new LoginPage(utill);
		 
		login.validateUserName();
		
		login.validateUserPass();
		
		login.validateUserLogIn();
		
		login.verifyTextOnWrongCred(tcData.get("exp_text"));
		
	}
	
    @Test(priority = 4)
	public void TC004InvalidLoginWith_IncompleteUser(){
		tcData= utill.getTestCaseDataMap();
		login=new LoginPage(utill);
		 
		login.validateUserName();
		
		login.validateUserPass();
		
		login.validateUserLogIn();
		
		login.verifyTextOnWrongCred(tcData.get("exp_text"));
		
	}
	
	@Test(priority = 5)
	public void TC005InvalidLoginWith_InvalidUser(){
		tcData= utill.getTestCaseDataMap();
		login=new LoginPage(utill);
		 
		login.validateUserName();
		
		login.validateUserPass();
		
		login.validateUserLogIn();
		
		login.verifyTextOnWrongCred(tcData.get("exp_text"));
		
	}
	
   @Test(priority = 6)
	public void TC006VerifyPre_detailPgByEmail(){
		tcData= utill.getTestCaseDataMap();
		login=new LoginPage(utill);

		 
	    login.validUserLogIn();
		
	    login.selectEmailAdd();
	    
		login.verifyTextOnPriCtc_Sel(tcData.get("exp_text1"));
		
		login.putEmail();
		
		login.clickOnUpdate();
		
		login.verifyTextOnPriCtc_upd(tcData.get("exp_text2"));
		
		
		
	}
	
 @Test(priority = 7)
	public void TC007VerifyPre_detailPgByEmail(){
		tcData= utill.getTestCaseDataMap();
		login=new LoginPage(utill);
	
		 
	    login.validUserLogIn();
		
	    login.selectEmailAdd();
	    
		login.verifyTextOnPriCtc_Sel(tcData.get("exp_text1"));
		
		login.putEmail();
		
		login.putMobile();
		
		login.clickOnUpdate();
		
		login.verifyTextOnPriCtc_upd(tcData.get("exp_text2"));
			
		
	}
	
	@Test(priority = 8)
	public void TC008VerifyPre_detailPgByMobile(){
		tcData= utill.getTestCaseDataMap();
		login=new LoginPage(utill);

		 
	    login.validUserLogIn();
			
	    login.selectMoblileNo();
	    
	    login.verifyTextOnPriCtc_Sel(tcData.get("exp_text1"));
	    
	    login.putEmail();
	    
	    login.putMobile();
	    
	    login.clickOnUpdate();
	    
	    login.verifyTextOfPre_ctcPage(tcData.get("exp_text2"));
	
	}
	
	
	@Test(priority = 9)
	public void TC009VerifyPre_detailPgByMobile(){
		tcData= utill.getTestCaseDataMap();
		login=new LoginPage(utill);

		 
	    login.validUserLogIn();
			
	    login.selectMoblileNo();
	    
	    login.verifyTextOnPriCtc_Sel(tcData.get("exp_text1"));
	    
	    login.putEmail();
	    
	    login.putMobile();
	    
 //	    login.clickOnUpdate();
	    
//      login.verifyHomePage(tcData.get("exp_text2"));
	
	}
	
	

	@Test(priority = 10)
	public void TC010VerifyFogtPass_LinkByText(){
		tcData= utill.getTestCaseDataMap();
		login=new LoginPage(utill);
	
		 
	    login.clickOn_forgetPass_Link();
	    
	    login.verifyTextOfForgetPass();
			
	    login.validateUserName();
	    
        login.clickOnContinue_bt();
	 
	    login.clickOnText();
	    
	    login.clickOnContinue_bt();
	    
	    utill.HoldOn(25000);
	    
	    login.verifyEnabledContinue_bt();
	    
	    login.clickOnContinue_bt();
	    
	    login.verifyCreateNewPassPage();
	 
	}

	
	@Test(priority = 11)
	public void TC011VerifyFogtPass_LinkByEmail(){
		tcData= utill.getTestCaseDataMap();
		login=new LoginPage(utill);
		
		 
	    login.clickOn_forgetPass_Link();
	    
	    login.verifyTextOfForgetPass();
			
	    login.validateUserName();
	    
	    login.clickOnContinue_bt();
	    
	 
	    login.clickOnEmail();
	    
	    login.clickOnContinue_bt();
	    
	    utill.HoldOn(25000);
	    
	    login.verifyEnabledContinue_bt();
	    
	    login.clickOnContinue_bt();
	    
	    login.verifyCreateNewPassPage();
	 
	}
	
@Test(priority=12)
public void TC012VerifyLearnMore_lk() {
	tcData= utill.getTestCaseDataMap();
	login=new LoginPage(utill);
	
	login.clickOnLearnMore_Link();
	
	login.verifyNavigatedPage_lk(tcData.get("learn_lk"));
}
	
	
}
