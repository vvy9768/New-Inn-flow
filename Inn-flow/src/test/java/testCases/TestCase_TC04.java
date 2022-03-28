package testCases;

import org.testng.annotations.Test;

import BasePage.TestBasePage;
import pages.UserManagment.Users;
import pages.administration.Admin;


public class TestCase_TC04 extends TestBasePage {
	Admin admin;
	Users user;
@Test(enabled = false)
	public void validateEnabilityOfUserPage() {

		admin = new Admin(utill);
		user=new Users(utill);
		admin.validUserLogIn();
		admin.NavigateToUsersPage();
		user.AddNewUsers();
		user.validateEnabilityOfElement();
	
}

@Test(enabled = false)
   public void  validateAddNewUserDetails() {
	admin = new Admin(utill);
	user=new Users(utill);
	admin.validUserLogIn();
	admin.NavigateToUsersPage();
	user.AddNewUsers();
	user.addNewUserDetails();
	user.clk_on_AddUserDetails();
	user.ValidateUserName();
}

@Test
public void crtNewUserWithoutPayroll() {
	admin = new Admin(utill);
	user=new Users(utill);
	admin.validUserLogIn();
	admin.NavigateToUsersPage();
	user.AddNewUsers();
	user.addNewUserDetails();
	user.clk_on_AddUserDetails();
    user.calanderMaintains();
	
	
}
}
