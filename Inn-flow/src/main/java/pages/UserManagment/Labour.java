package pages.UserManagment;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import basePage.BasePage;
import webUtills.WebUtills;

public class Labour extends BasePage {

	WebUtills utill;

	public Labour(WebUtills utill) {
		super(utill);
		this.utill = utill;
	}
	
	
	By selHrPerWk=By.cssSelector("input#txtSalariedhours");
    By ptoDate_in=By.xpath("//input[@name='ptoStartDate']");
	By ptoName_in=By.cssSelector("button#ddlptoname");
	By ptoStatus_dd=By.cssSelector("button#ddlStatus");
	By addPto_bt=By.cssSelector("button#ddlAddPTOType");
	By slctEmp_type = By.xpath("//span[text()='Select Employee Type']");
	By avgHrWork = By.xpath("//input[@id='averageHoursWorked']");
	By hourlyWages = By.xpath("//div//a[text()='Hourly Wage']");
	By addPayroll_bt = By.xpath("//button[text()='Add Hotel Wide Settings']");
	By slc_EHID = By.xpath("//div/span[text()='Select HID']");
	By slc_tab = By.xpath("//div[@class='modal-body']//a[@class='dropdown-item']");
    By put_DBO=By.xpath("//input[@placeholder='MM/DD/YYYY']");
	By userPosition_dd=By.xpath("//div/span[text()='Select Position']");
	By regular_rate=By.xpath("//input[@id='txtRegularRate']");
	By slc_name_dd=By.xpath("//*[@aria-labelledby='ddlptoname']//li");
	By slctedName_txt=By.xpath("//div/span[@class='selected-item']");
	By addReview_bt=By.xpath("//button[text()='Add Review']");
	By enabilitiOfAddReview_list=By.xpath("//div[@class='modal-body']//div[@class='form-group']");
	//By slc_Ehid=By.xpath("//button[text()='Add Review']");
	By slc_EHID_dd=By.xpath("//*[@id='businessAccountSelector']//span");
	By slc_freq_dd=By.xpath("//*[@id='ddlFrequency']//span");
	By slc_type_dd=By.xpath("//*[@id='ddlReviewType']//span");
	By slc_all_bt=By.xpath("(//*[@id='selectAll'])[2]");
	By oneTime_freq=By.xpath("//*[text()='One Time']");
	By NoOfDays_list=By.xpath("//div[@class='dropdown-menu show']//div");
	By noOfDays=By.xpath("//*[text()='30 Day Review']");
	By close_bt=By.xpath("//*[@class='close']");
	By date_bt=By.xpath("(//*[@placeholder='MM/DD/YY'])[2]");
	By slcPosition_list=By.xpath("//div[@class='dropdown-menu show']//div[@class='list-area']//div");
	By addPayroll_EHID_bt=By.xpath("//button[@class='btn btn-primary btn btn-primary']");
	By docs_pg=By.xpath("//a[@id='left-tabs-example-tab-documents']");
	By hotalWiseSetting=By.xpath("//span[@class='chevronCollpsed']");
	By timeHr=By.xpath("//input[@placeholder='HH:MM']");
	By clkOnAdd= By.xpath("//div[contains(text(),'Set Salaried Hours/Week')]//following::div/button[text()='Add']");
	
	
	
	
	public void AddDetailsOfPayroll(String amount) {
		clickOnEHID_dd();
		slc_defaultItem();
		clickOnselectPostion();
		utill.selectRandomDataFromDD(slcPosition_list);
		try {
		putRegularRate(amount);
		}catch(Exception e) {
			utill.waitUntillVisibleOfElement(5, regular_rate);
			putRegularRate(amount);
		}
		utill.click_Act(addPayroll_EHID_bt, " EHID Added  ");
	}
	public void clickOnEHID_dd() {
		utill.click(slc_EHID, "add payroll");
	}
	
	public void clickOnaddPayroll_bt() {
		utill.waitUntillVisibleOfElement(10, addPayroll_bt);
		utill.click_Act(addPayroll_bt, "add payroll");
	}

	public void slc_defaultItem() {
		String text = utill.getText(slc_tab);
		utill.click(slc_tab, text);
	}

	public void click_onSelectEmp() {
		utill.click_Act(slctEmp_type, "Select Emp column");
	}
 
	public void Slc_EmpTypeExcFromPayRoll(String ElementName) {
		By excludeFromPayroll = By.xpath("//div//a[text()='"+ElementName+"']");
		utill.click_Act(excludeFromPayroll, " ExcludeFromPayroll");
	}

	public void putAvgHrWorked() {
		utill.sendKeys(avgHrWork, "40");
	}

	public void slc_EmpHourlyWages() {
		utill.click_Act(hourlyWages, " hourlyWages ");
	}

	public void put_DBO(String MM_DD_YYYY) {
		utill.sendKeys(put_DBO, MM_DD_YYYY);
	}
	
	public void clickOnselectPostion() {
		utill.click_Act(userPosition_dd, "Select UserPosition ");
	}
	public void  putRegularRate(String regularRate) {
		utill.sendKeys(regular_rate, regularRate);
	}
	public void clickOnAddPTO_Bt() {
		utill.click(addPto_bt, "Add PTO botton");
	}
	public void clickOnAddReview_Bt() {
		utill.click(addReview_bt, "Add Review botton");
	}
	
	

	public void fillDataInPto() {
		utill.click(ptoName_in, "PTO Name DD");
		utill.click(slc_name_dd, "DefaultItme Name");
		String text=utill.getText(slctedName_txt);
		utill.setText(text);
		
	}
	public void validatePTOCreated() {
		String text=utill.getText();
		By eleName=By.xpath("//*[contains(text(),'"+text+"')]");		
		utill.verifyText(eleName, text);
	}
	public void enabilityOfAddReview() {
		List<WebElement> list=utill.getElements(enabilitiOfAddReview_list);
		for(WebElement we:list) {
			String text=we.getText();
			utill.verifyElement_IsEnable(we, text);	
		}
		
		//utill.click_Act(close_bt, "closed Review Window");
	}
	
	public void fillAddReviewDetails() {
		utill.waitUntillVisibleOfElement(5,date_bt);
		utill.click_Act(slc_EHID_dd, "Add Review page select EHID");
		utill.click_Act(slc_all_bt,"select all button ");
		utill.click_Act(slc_freq_dd, " frequency DD");
		utill.click_Act(oneTime_freq, " one Time ");
		utill.click_Act(slc_type_dd, " No of Days in Review ");
		utill.selectRandomDataFromDD(NoOfDays_list);
		
	}
	
	public Document navigatedOnDocPage() {
		utill.waitUntillVisibleOfElement(5, hotalWiseSetting);
	  utill.click_Act(docs_pg, "Document button");
	  return new Document(utill);
	}
	public void SalHrPerWeek() {
		utill.HoldOn(3);
		utill.click(selHrPerWk, " Selery per week ");
		utill.sendKeys(timeHr, "12:00");
		utill.click(clkOnAdd , " Add Hours ");
	}
	

}
