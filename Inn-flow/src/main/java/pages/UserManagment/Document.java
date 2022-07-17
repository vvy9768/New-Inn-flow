package pages.UserManagment;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import basePage.BasePage;
import webUtills.WebUtills;

public class Document extends BasePage  {
	 public Document(WebUtills utill) {
		// TODO Auto-generated constructor stub
		    super(utill);
			this.utill = utill;
	}

	 
	 By addDocs_pg=By.xpath("//div[@id='btnModal']");
	 
	 By add_bt=By.xpath("//button[@id='ddladdDocument']");
	 
	 By type_bt=By.xpath("//button[@id='ddlDocType']");
	 
	 By type_dd=By.xpath("//div[@aria-labelledby='ddladdDocument']//li");
	 
	 By decs_in=By.cssSelector("textarea#txtDescription");
	 
	 By date_in=By.xpath("(//input[@placeholder='MM/DD/YY'])[2]");
	 
	 By brow_lk=By.xpath("//span[text()='browse files']");
	 
	 By addtype = By.xpath("//li[text()='Add Type']");
	 
	 By putNewName=By.xpath("//input[@id='txtTypeName']");
	 
	 By addType_bt =By.xpath("//button[@type='submit']");
	 
	 public void clickOnAddDocs() {
		 utill.click(add_bt, "add document bt");
	 }
	 
	public void addType() {
		utill.click(type_bt, "Type of document");
	    utill.click_Act(addtype,"addType");
	    String txt=utill.getRandomStringOnLength(4);
	    utill.actSendKeys(putNewName, txt);
	    utill.click_Act(addType_bt, "Add new type of docs");
	}
	public void  addDocumentType() {
		tcData = utill.getTestCaseDataMap();
		utill.click(type_bt, "Type of document");
		utill.selectRandomDataFromDD(type_dd);
		utill.click(date_in, "date input box");
		calanderMaintains(tcData.get("day"), tcData.get("month"), tcData.get("year"));
	//	utill.click_Act(brow_lk, "brows file link");
		utill.sendKeys(brow_lk, "‪C:\\Users\\softe\\Downloads\\UM- Document Tab.xlsx");
		utill.sendKeysByKeyBoard(brow_lk, Keys.ENTER);
		
	}
	 
	private void calanderMaintains(String string, String string2, String string3) {
		// TODO Auto-generated method stub
		
	}

	public void  enabilityOfDocs() {
		utill.verifyElement_IsEnable(decs_in, "Decreptions input box");
		utill.verifyElement_IsEnable(type_bt, "Decreptions input box");
		utill.verifyElement_IsEnable(date_in, "Decreptions input box");
		
	}
	 
	 
	 
	 
	 
}
