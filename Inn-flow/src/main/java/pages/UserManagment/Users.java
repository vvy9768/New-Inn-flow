package pages.UserManagment;

import org.openqa.selenium.By;

import webUtills.WebUtills;

public class Users {
	WebUtills utill;
	public Users(WebUtills utill) {
		this.utill=utill;
	}

By firstName_In=By.cssSelector("input#txtFirstName");
By lastName_In=By.cssSelector("input#txtLastName");
By email_In =By.cssSelector("input#txtEmail");
By mobile_In= By.cssSelector("input#txtPhoneNo");
By addUser_bt1=By.xpath("//button[@type='submit']");
By addUser_bt=By.xpath("//button[text()='Add User']");	
By userName=By.cssSelector("input#txtUsername");
By Auto_uName=By.xpath("//div[@class='user-details align-items-center']/div");
By cal_Bt=By.xpath("//div[@class='calender-display']");
By yr_bt=By.xpath("//button[@type='button']/span");
By month_bt=By.xpath("");
By day_bt= By.xpath("");



public void AddNewUsers() {
	utill.click_Act(addUser_bt, " Add Users ");
}

	
	public void validateEnabilityOfElement() {
		utill.verifyElement_IsEnable(firstName_In, "FirstName Inputbox");
		utill.verifyElement_IsEnable(lastName_In, "LastName Inputbox");
		utill.verifyElement_IsEnable(email_In, "Email Inputbox");
		utill.verifyElement_IsEnable(mobile_In, "Mobile No. Inputbox ");
	}
	
	
	
    public void addNewUserDetails() {
    	utill.explicitlyWait(utill.getdriver(), 10);
    	utill.waitUntillVisibleOfElement(3,firstName_In);
    	utill.actSendKeys(firstName_In,utill.getRandomString());
    	utill.actSendKeys(lastName_In, utill.getRandomString());
    	utill.HoldOn(5);
    	utill.setText(utill.getAtributeValue(userName, "value"));
    	utill.actSendKeys(email_In, utill.getRandomString()+"@gmail.com");
    	utill.actSendKeys(mobile_In, utill.getRandomMobileNumber());	
	}
    
    
    
   public void clk_on_AddUserDetails() {
	   utill.click_Act(addUser_bt1, " add User botton ");
	  
   }
	public void ValidateUserName() {
		utill.verifyTextByContains(Auto_uName,utill.getText());
	}
	
	public void calanderMaintains() {
		utill.jsScrollDown(400);
		utill.click_Act(cal_Bt, " Calander ");
		utill.click_Act(yr_bt, " year button ");
		utill.click(yr_bt, "year column ");
		
	}
	
	
	
}
