package pages.login;


import org.openqa.selenium.By;
import com.aventstack.extentreports.Status;
import basePage.BasePage;
import webUtills.WebUtills;

public class LoginPage extends BasePage {

	WebUtills utill;
	String elemtName;
	String text;

	public LoginPage(WebUtills utill) {
		super(utill);
		this.utill = utill;

	}

   
	By we_HmLogo=By.xpath("//div[text()='Inn-Flow']");

	By we_invald_txt=By.xpath("(//span[@class='invalid-feedback'])[3]");
	
	By we_primCtc_dd= By.cssSelector("select[title='Select Primary Contact']");
	
	By we_primCtc_txt=By.cssSelector("p[class='text-danger text-center my-2']");
	
	By we_priCtc_rq_txt= By.cssSelector("em[class='text-danger']");
	
	By we_priEmail= By.cssSelector("input[title='Enter email address']");
	
	By we_priMobile=By.cssSelector("input[title='Enter mobile number']");
	
	By we_updt_bt=By.cssSelector("button[title='Update']");
	
	By we_pre_txt=By.cssSelector("div[class='modal-title h4']");
	
	By fogtPass_link=By.xpath("//a[contains(text(),'Forgot your password?')]");
	
	By fogtPass_txt = By.xpath("//div[contains(text(),'Forgot Your Password')]");
	
	By crtNewPass_txt=By.xpath("//div[contains(text(),'Create New Password')]");
	
	By sms_bt=By.xpath("//input[@id='SMS']");
	
	By mail_bt=By.xpath("//input[@id='Email']");
	
	
	
	
	public void validateUserName() {
		elemtName =utill.getPropVal("type_uName");
		 
		 tcData = utill.getTestCaseDataMap();
		 utill.verifyElement_IsEnable(we_uName, elemtName);
		 utill.sendKeys(we_uName, tcData.get("uName"));
		 
	}
	
	
	
	public void validateUserPass() {
		elemtName=utill.getPropVal("type_uPass");
		utill.verifyElement_IsEnable(we_uPass, elemtName);
		utill.sendKeys(we_uPass, tcData.get("uPass"));
	}
	

	public void validateUserLogIn() {
		elemtName=utill.getPropVal("type_uLogIn");
		utill.verifyElement_IsEnable(we_uLogIn_Bt, elemtName);
		utill.click(we_uLogIn_Bt, "login button ");
		utill.HoldOn(2000);
		utill.getExtentLogger().log(Status.PASS, " Inn-flow is LogIn button Click Successfully ");
	       
		  
	}
	
	
	public void verifyTextOnWrongCred(String expText) {
		utill.HoldOn(1000);
		utill.verifyText(we_invald_txt, expText);
		
		
	}
	
	public void selectEmailAdd() {
		utill.selectByValue(we_primCtc_dd, "email");
	}
	
	public void selectMoblileNo() {
		utill.selectByValue(we_primCtc_dd, "mobile");
	}
	
	
	public void verifyTextOnPriCtc_Sel(String expText) {
		utill.HoldOn(1000);
		utill.verifyText(we_priCtc_rq_txt, expText);
		
	}
	public void verifyTextOnPriCtc_upd(String expText) {
		utill.HoldOn(2000);
		utill.verifyText(we_primCtc_txt, expText);
		
	}
	
	public void putEmail() {
		
		utill.sendKeys(we_priEmail,tcData.get("Email") );
	}
	
   public void putMobile() {
		
		utill.sendKeys(we_priMobile,tcData.get("Mobile") );
	}
	
	public void clickOnUpdate() {
		utill.click_Act(we_updt_bt,"Update button ");
	}
	
	public void  verifyTextOfPre_ctcPage(String exp_Text) {
		utill.verifyText(we_pre_txt, exp_Text);
	}

	
	public void clickOn_forgetPass_Link() {
		tcData = utill.getTestCaseDataMap();
		utill.click(fogtPass_link,"ForgetPassword Link");
	}
	
	public void verifyTextOfForgetPass() {
		utill.verifyText(fogtPass_txt, tcData.get("fgt_txt"));
	}
	
	public void verifyEnabledContinue_bt()
	{
		
		utill.verifyText(continue_Bt,tcData.get("ctn_bt"));
	}
	
	public void clickOnContinue_bt()
	{	
		
		utill.click(continue_Bt, "Contunue button");
		
	}
	
	public void verifyCreateNewPassPage() {
		
		utill.verifyText(crtNewPass_txt, tcData.get("crtNewPass"));
	}
	
	public void clickOnText() {
		utill.click_Act(sms_bt,"Text chakebox ");
	}
	
   public void clickOnEmail() {
		utill.click_Act(mail_bt, "Email chakeBox ");
	}
	
   public void clickOnLearnMore_Link() {
	   utill.click(learn_Lk, "Learn more link ");
   }
   
   public void verifyNavigatedPage_lk(String exp_text) {
	   utill.getWindowHandleByUrl(exp_text);
	   utill.verifyUrlByElement(exp_text);
   }
}
