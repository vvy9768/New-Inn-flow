package pages.administration;

import org.openqa.selenium.By;

import basePage.BasePage;
import webUtills.WebUtills;

public class Admin extends BasePage {
	WebUtills utill;

	public Admin(WebUtills utill) {
		super(utill);
	   this.utill=utill;	
  
}
	By admin=By.xpath("//img[@alt='Administration']");
	By users=By.xpath("//div[text()='Users']");
	
	
	public void NavigateToUsersPage() {
		
		utill.mouseOver(admin," Administration ");
		utill.click(users, " Users Managment ");
	}
	
	
}