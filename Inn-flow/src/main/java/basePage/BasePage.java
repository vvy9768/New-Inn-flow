package basePage;

import java.util.Map;

import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;

import pages.homepage.HomePage;
import webUtills.WebUtills;

public class BasePage {

	
	protected	WebUtills utill;
	protected	String elemtName;
	protected	String text;
    protected Map<String, String> tcData;
	public BasePage(WebUtills utill) {
		this.utill = utill;

	}


	protected By we_uName = By.cssSelector("input#formBasicEmail");

	protected By we_uPass = By.cssSelector("input#formBasicPassword");

	protected	By we_uLogIn_Bt = By.cssSelector("button[type='submit']");
   
	protected	By we_HmLogo=By.xpath("//div[text()='Inn-Flow']");

	protected	By continue_Bt=By.xpath("//button[contains(text(),'Continue')]");

	protected   By learn_Lk=By.xpath("//a[contains(text(),'Learn more')]");
	
	
	
	
	
	
	
	public HomePage validUserLogIn() {
		tcData = utill.getTestCaseDataMap();
		
		utill.sendKeys(we_uName, tcData.get("uName"));
		utill.sendKeys(we_uPass, tcData.get("uPass"));
		utill.click(we_uLogIn_Bt,"login button ");
		utill.getExtentLogger().log(Status.PASS, " Inn-flow is LogIn Successfully  ");
		return new HomePage(utill);
	}
	
	
	
	
	public void verifyHomePage(String expText) {
		utill.HoldOn(1000);
		utill.verifyText(we_HmLogo, expText );
		utill.getExtentLogger().log(Status.PASS, " Inn-flow is LogIn Successfully and Navigated On HomePage ");
	      
	}

	
	
	
	
}
